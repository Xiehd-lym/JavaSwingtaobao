package view;

import dao.BuyGoodsDao;
import dao.GoodsDao;
import dao.GoodsTypeDao;
import model.BuyGoods;
import model.Goods;
import model.GoodsType;
import utiles.JDBCUtils;
import utiles.LoginConfig;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class Shoping extends JInternalFrame {
	
	private static Shoping shoping = new Shoping();
	private GoodsDao goodsDao = new GoodsDao();
	private JTable table;
	private Connection conn = JDBCUtils.getConnection();
	private JTextField textField;
	private JComboBox comboBox;

	
	private  GoodsTypeDao goodsTypeDao = new GoodsTypeDao();
	private BuyGoodsDao buyGoodsDao = new BuyGoodsDao();
	
	public static Shoping getShoping(){
        
        return shoping;
    }
	/**
	 * Create the frame.
	 */
	private Shoping() {
		setTitle("购买商品");
		
		setIconifiable(true);
		setClosable(true);
		setBounds(550, 100, 766, 708);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		
		JButton btnNewButton_1 = new JButton("加入购物车");
		
		
		btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(79)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE))
					.addContainerGap(66, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(324, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addGap(319))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_1)
					.addGap(333))
		);
		
		JLabel lblNewLabel = new JLabel("商品名称：");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.BOLD, 15));
		textField.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("微软雅黑", Font.BOLD, 15));
		comboBox.setForeground(new Color(0, 204, 204));
		
		JLabel lblNewLabel_2 = new JLabel("类别：");
		lblNewLabel_2.setForeground(new Color(51, 153, 255));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JButton btnNewButton_1_1 = new JButton("查询");
		
		btnNewButton_1_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(79, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(47)
							.addComponent(lblNewLabel_2)))
					.addGap(18)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addGap(112))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(lblNewLabel)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
					.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		table = new JTable();
		table.setBackground(new Color(204, 255, 255));
		
		
		table.setToolTipText("");
		table.setBorder(UIManager.getBorder("CheckBox.border"));
		table.setFont(new Font("华文细黑", Font.ITALIC, 15));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"商品id", "商品名称", "类别", "单价", "数量", "商品名称"
			}
		));
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		
		 this.fillTable(new Goods()); 
		 this.fillJComboBox2(new Goods());
		 
		 /**
		  * 点击方法
		  */
		 //窗口关闭方法
		 addInternalFrameListener(new InternalFrameAdapter() {
				@Override
				public void internalFrameClosed(InternalFrameEvent e) {
					UserFrm.flagShoping = true;
				}
			});
		 //购买按钮
		 btnNewButton_1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					buygoods();
				}
			});
		 //查询按钮
		 btnNewButton_1_1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectType();
				}
			});
		 //表格点击
		 table.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					goodsRow();
				}
			});
	}
	
	/**
	 * 初始化表格
	 * @param goods
	 */
	public void fillTable(Goods goods) {
		 DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		try {
			ResultSet rs = goodsDao.goodsList(conn, goods);
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt(1));
				v.add(rs.getString(2));
				v.add(rs.getString(8));
				v.add(rs.getDouble(3));
				v.add(rs.getInt(6));
				v.add(rs.getString(5));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e = new Exception("初始化表格错误");
			e.printStackTrace();
		}
	}
	/**
	 * 初始化下拉表
	 * @param goods
	 */
		private void fillJComboBox2(Goods goods) {
	
		GoodsType Li = new GoodsType(); 
		Li.setType("请选择"); 
		Li.setId(-1);
		comboBox.addItem(Li); 
		try {
			ResultSet rs = goodsTypeDao.typeList(conn, new GoodsType());
			while(rs.next()) {
				GoodsType goodsType = new GoodsType();
				goodsType.setType(rs.getString("goodsTypeName"));
				goodsType.setId(rs.getInt("id"));
				comboBox.addItem(goodsType);
			}
		} catch (Exception e) {
			e = new Exception("初始化下拉表错误");
			e.printStackTrace();
		}
	}
		
	/**
	 * 查询商品
	 */
	private void selectType() {
		  String goodsName = this.textField.getText(); 
		  GoodsType goodsType = (GoodsType)comboBox.getSelectedItem();
		  Goods goods = new Goods(goodsName,goodsType.getId());	
		  this.fillTable(goods);
	}
	/**
	 *添加至购物车
	 */
	private void buygoods() {
		
		try {
			BuyGoods buyGoods = goodsRow();
			int i = buyGoodsDao.checkGoodsCount(JDBCUtils.getConnection(), buyGoods);
			if(i>0) {
				int j = buyGoodsDao.updateGoodsCount(JDBCUtils.getConnection(), buyGoods);
				if(j>0) {
					JOptionPane.showMessageDialog(null, "添加购物车成功");
					this.fillTable(new Goods());
					buyGoodsDao.insertGoods(JDBCUtils.getConnection(), buyGoods);
					return;
				}else {
					JOptionPane.showMessageDialog(null, "添加购物车失败");
					return;
				}
			}else {
				JOptionPane.showMessageDialog(null, "库存不足");
				return;
			}
		} catch (Exception e) {
			e = new Exception("添加至购物车错误");
			e.printStackTrace();
		}
	}
	/**
	 * 鼠标点击表格获取商品信息
	 * @return 
	 */
	private BuyGoods goodsRow() {
		int row = table.getSelectedRow();
		String useId = LoginConfig.getUserList().get(2);
		String goodsId = String.valueOf(table.getValueAt(row, 0));
		String goodsName = String.valueOf(table.getValueAt(row, 1));
		String goodsType = String.valueOf(table.getValueAt(row, 2));
		String price = String.valueOf(table.getValueAt(row, 3));
		String count =String.valueOf(table.getValueAt(row, 4));
		String goodsDesc = String.valueOf(table.getValueAt(row, 5));
		BuyGoods buygoods = new BuyGoods(Integer.parseInt(useId),Integer.parseInt(goodsId),goodsName, goodsType, Double.parseDouble(price), Integer.parseInt(count), goodsDesc);
		return buygoods;
	}
}
