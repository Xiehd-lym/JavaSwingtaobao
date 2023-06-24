package view;

import dao.BuyGoodsDao;
import dao.GoodsDao;
import dao.UserDao;
import model.BuyGoods;
import model.Goods;
import utiles.JDBCUtils;
import utiles.LoginConfig;
import utiles.StringUtils;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class MyCar extends JInternalFrame {
	private static MyCar myCar = new MyCar();
	private static JTable table;
	private BuyGoodsDao buyGoodsDao = new BuyGoodsDao();
	private GoodsDao goodsDao = new GoodsDao();
	private UserDao userDao = new UserDao();
	private Integer id = Integer.parseInt(LoginConfig.getUserList().get(2));
	public static MyCar getMyCar() {
		return myCar;
	}
	/**
	 * Create the frame.
	 */
	private MyCar() {
	
		setTitle("购物车");
		setClosable(true);
		setIconifiable(true);
		setBounds(540, 100, 745, 690);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton = new JButton("购买");
		
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JButton btnNewButton_1 = new JButton("删除");
		
		btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JButton btnNewButton_2 = new JButton("修改");
		
		btnNewButton_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JButton btnNewButton_2_1 = new JButton("清空");
		
		btnNewButton_2_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(87)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 563, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(170)
							.addComponent(btnNewButton)
							.addGap(45)
							.addComponent(btnNewButton_1)
							.addGap(42)
							.addComponent(btnNewButton_2)
							.addGap(45)
							.addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(100, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(70)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setBackground(new Color(204, 255, 255));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"商品id", "商品名称", "价格", "数量", "商品描述"
			}
		));
		table.setToolTipText("");
		table.setFont(new Font("华文细黑", Font.ITALIC, 15));
		table.setBorder(UIManager.getBorder("CheckBox.border"));
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		
		/**
		 * 点击方法
		 */
		//窗口关闭
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				UserFrm.flagMyCar = true;
			}
		});
		//购买
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buyGoods();
			}
		});
		//删除
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteGoodsOne();
			}
		});
		//修改
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateCount();
			}
		});
		//清空
		btnNewButton_2_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteAll();
			}

		});
	}
	/**
	 * 初始化表格
	 */
	public void fillTable() {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement pre =null;
		ResultSet rs = null;
		String sql = "select g.id,g.goodsName,g.price*s.count as sum,s.count,g.goodsName from t_goods g,t_shoping s where g.id = s.goodsid and s.useid=?";
		
		try {
			pre = conn.prepareStatement(sql.toString());
			pre.setInt(1, Integer.parseInt(LoginConfig.getUserList().get(2)));
			 rs = pre.executeQuery();
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt(1));
				v.add(rs.getString(2));
				v.add(rs.getDouble(3));
				v.add(rs.getInt(4));
				v.add(rs.getString(5));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 购买商品，并添加至历史记录
	 */
	private void buyGoods() {

		
		try {
			BuyGoods buyGoods = goodsRow();
			if(buyGoods==null) {
				return;
			}
			Double goodsmoney = buyGoods.getPrice();
			Double money = UserFrm.money;
			int res = JOptionPane.showConfirmDialog(null, "需支付" + goodsmoney + "，确认要支付么？","警告",JOptionPane.YES_NO_OPTION);
			if(res==0) {
				String password = JOptionPane.showInputDialog("请输入密码");
				if(money<goodsmoney) {
					JOptionPane.showMessageDialog(null, "余额不足，请充值");
					return;
				}
				if(LoginConfig.getUserList().get(1).equals(password)) {
					buyGoodsDao.deleteGoods(JDBCUtils.getConnection(), buyGoods);
					//userDao.updateUserMoney(JDBCUtils.getConnection(), goodsmoney, id, false);
					UserFrm.money = money - goodsmoney;
					BuyDialog buyDialog = new BuyDialog();
					buyDialog.lblNewLabel_2.setText("￥" +  goodsmoney);
					buyDialog.setVisible(true);
					fillTable();
					return;
				}else {
					BuyDialog buyDialog = new BuyDialog();
					buyDialog.lblNewLabel.setIcon(new ImageIcon(BuyDialog.class.getResource("/image/c.jpg")));
					buyDialog.lblNewLabel_1.setText("支付失败");
					buyDialog.lblNewLabel_2.setText("￥" +  goodsmoney);
					buyDialog.setVisible(true);
					return;
				}
			}
		} catch (Exception e) {
			e = new Exception("加入购物车错误");
			e.printStackTrace();
		}
	}
	/**
	 * 判断是否选中商品的功能
	 */
	private BuyGoods goodsRow() {
		int row = table.getSelectedRow();
		if(row==-1) {
			JOptionPane.showMessageDialog(null, "请选择商品");
			return null;
		}
		String useId = LoginConfig.getUserList().get(2);
		String goodsId = String.valueOf(table.getValueAt(row, 0));
		String goodsName = String.valueOf(table.getValueAt(row, 1));
		String price = String.valueOf(table.getValueAt(row, 2));
		String count =String.valueOf(table.getValueAt(row, 3));
		String goodsDesc = String.valueOf(table.getValueAt(row, 4));
		BuyGoods buygoods = new BuyGoods(Integer.parseInt(useId),Integer.parseInt(goodsId),goodsName, Double.parseDouble(price), Integer.parseInt(count), goodsDesc);
		return buygoods;
		
	}
	/**
	 * 清空按钮
	 */
	private void deleteAll() {
		int j = carIsEmpty();
		if(j==-1) {
			JOptionPane.showMessageDialog(null, "购物车为空");
			return;
		}
		Integer useid = Integer.parseInt(LoginConfig.getUserList().get(2));
		Double money = UserFrm.money;
		try {
			Double goodsmoney = buyGoodsDao.moneyGoodsAll(JDBCUtils.getConnection(), useid);
			int res = JOptionPane.showConfirmDialog(null, "确认清空购物车？需支付" + goodsmoney + "元","提醒",JOptionPane.YES_NO_OPTION);
			if(res==0) {
				String password = JOptionPane.showInputDialog("请输入密码");
				if(money<goodsmoney) {
					JOptionPane.showMessageDialog(null, "余额不足，请充值");
					return;
				}
				if(LoginConfig.getUserList().get(1).equals(password)) {
					buyGoodsDao.historyShoping(JDBCUtils.getConnection(), useid);//写入数据
					int i = buyGoodsDao.deleteGoodsAll(JDBCUtils.getConnection(), useid);
					if(i>0) {
						//Thread.sleep(1000);
						//userDao.updateUserMoney(JDBCUtils.getConnection(), goodsmoney, useid, false);
						UserFrm.money = money - goodsmoney;
						buyGoodsDao.historyShoping(JDBCUtils.getConnection(), useid);
						BuyDialog buyDialog = new BuyDialog();
						buyDialog.lblNewLabel_2.setText("￥" + goodsmoney);
						buyDialog.setVisible(true);
						fillTable();
						return;
					}else {
						JOptionPane.showMessageDialog(null, "购物车为空");
						return;
					}
				}
				else {
					//Thread.sleep(1000);
					BuyDialog buyDialog = new BuyDialog();
					buyDialog.lblNewLabel.setIcon(new ImageIcon(BuyDialog.class.getResource("/image/c.jpg")));
					buyDialog.lblNewLabel_1.setText("支付失败");
					buyDialog.lblNewLabel_2.setText("￥" + goodsmoney);
					buyDialog.setVisible(true);
					table.removeAll();
					return;
				}
			}else {
				int i = buyGoodsDao.deleteGoodsAll(JDBCUtils.getConnection(), null,useid);
				if(i>0) {
					JOptionPane.showMessageDialog(null, "清空购物车成功");
					fillTable();
				}else {
					JOptionPane.showMessageDialog(null, "购物车为空");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 删除按钮
	 */
	private void deleteGoodsOne() {
		
		BuyGoods buyGoods = goodsRow();
		int row = table.getSelectedRow();
		
		if(buyGoods==null) {
			return;
		}
		try {
			int i = buyGoodsDao.deleteGoodsButton(JDBCUtils.getConnection(), buyGoods);
			if(i>0) {
				JOptionPane.showMessageDialog(null, "删除成功");
				fillTable();
				return;
			}else {
				JOptionPane.showMessageDialog(null, "删除失败");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 修改按钮
	 */
	private void updateCount() {
		BuyGoods buyGoods = goodsRow();
		if(buyGoods==null) {
			return;
		}
		String count = JOptionPane.showInputDialog("请输入要购买的数量");
			if(StringUtils.isEmpty(count)) {
				JOptionPane.showMessageDialog(null, "输入为空");
			}else if(StringUtils.isNumber(count)==false){
				JOptionPane.showMessageDialog(null, "输入错误");
			}else {
				Integer inputcount = Integer.parseInt(count);
				BuyGoods buygoods = goodsRow();
				Integer goodsid = buygoods.getGoodsId(); 
				Integer usecount = buygoods.getCount();
				try {
					ResultSet rs = goodsDao.oneGoods(JDBCUtils.getConnection(), goodsid);
					Integer kc = null;
					boolean flag = false;
					if(rs.next()) {
						 kc = rs.getInt(6);
					}
					if(inputcount>kc) {
						JOptionPane.showMessageDialog(null, "库存不足");
						return;
					}else if(inputcount>usecount) {
						flag = true;
					}
					int i = Math.abs(inputcount-usecount);
					buygoods.setCount(inputcount);
					buyGoodsDao.updateOneGoodsCount(JDBCUtils.getConnection(), buygoods,Integer.parseInt(LoginConfig.getUserList().get(2)),flag,i);
					Shoping shoping =Shoping.getShoping();
					shoping.fillTable(new Goods());
					fillTable();
					JOptionPane.showMessageDialog(null, "修改成功");
					return;
						
				} catch (Exception e) {
					e.printStackTrace();
				}
			}		
	}
	/**
	 * 检查购物车是否为空，为清空按钮调用
	 * @return
	 */
	private int carIsEmpty() {
		Integer useid = Integer.parseInt(LoginConfig.getUserList().get(2));
		ResultSet rs = null;
		try {
			 rs = buyGoodsDao.goodsAll(JDBCUtils.getConnection(), useid);
			 if(rs.next()) {
				 return 1;
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
