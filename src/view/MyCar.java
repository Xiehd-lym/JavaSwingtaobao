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
	
		setTitle("���ﳵ");
		setClosable(true);
		setIconifiable(true);
		setBounds(540, 100, 745, 690);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton = new JButton("����");
		
		btnNewButton.setFont(new Font("΢���ź�", Font.BOLD, 15));
		
		JButton btnNewButton_1 = new JButton("ɾ��");
		
		btnNewButton_1.setFont(new Font("΢���ź�", Font.BOLD, 15));
		
		JButton btnNewButton_2 = new JButton("�޸�");
		
		btnNewButton_2.setFont(new Font("΢���ź�", Font.BOLD, 15));
		
		JButton btnNewButton_2_1 = new JButton("���");
		
		btnNewButton_2_1.setFont(new Font("΢���ź�", Font.BOLD, 15));
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
				"��Ʒid", "��Ʒ����", "�۸�", "����", "��Ʒ����"
			}
		));
		table.setToolTipText("");
		table.setFont(new Font("����ϸ��", Font.ITALIC, 15));
		table.setBorder(UIManager.getBorder("CheckBox.border"));
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		
		/**
		 * �������
		 */
		//���ڹر�
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				UserFrm.flagMyCar = true;
			}
		});
		//����
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buyGoods();
			}
		});
		//ɾ��
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteGoodsOne();
			}
		});
		//�޸�
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateCount();
			}
		});
		//���
		btnNewButton_2_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteAll();
			}

		});
	}
	/**
	 * ��ʼ�����
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
	 * ������Ʒ�����������ʷ��¼
	 */
	private void buyGoods() {

		
		try {
			BuyGoods buyGoods = goodsRow();
			if(buyGoods==null) {
				return;
			}
			Double goodsmoney = buyGoods.getPrice();
			Double money = UserFrm.money;
			int res = JOptionPane.showConfirmDialog(null, "��֧��" + goodsmoney + "��ȷ��Ҫ֧��ô��","����",JOptionPane.YES_NO_OPTION);
			if(res==0) {
				String password = JOptionPane.showInputDialog("����������");
				if(money<goodsmoney) {
					JOptionPane.showMessageDialog(null, "���㣬���ֵ");
					return;
				}
				if(LoginConfig.getUserList().get(1).equals(password)) {
					buyGoodsDao.deleteGoods(JDBCUtils.getConnection(), buyGoods);
					//userDao.updateUserMoney(JDBCUtils.getConnection(), goodsmoney, id, false);
					UserFrm.money = money - goodsmoney;
					BuyDialog buyDialog = new BuyDialog();
					buyDialog.lblNewLabel_2.setText("��" +  goodsmoney);
					buyDialog.setVisible(true);
					fillTable();
					return;
				}else {
					BuyDialog buyDialog = new BuyDialog();
					buyDialog.lblNewLabel.setIcon(new ImageIcon(BuyDialog.class.getResource("/image/c.jpg")));
					buyDialog.lblNewLabel_1.setText("֧��ʧ��");
					buyDialog.lblNewLabel_2.setText("��" +  goodsmoney);
					buyDialog.setVisible(true);
					return;
				}
			}
		} catch (Exception e) {
			e = new Exception("���빺�ﳵ����");
			e.printStackTrace();
		}
	}
	/**
	 * �ж��Ƿ�ѡ����Ʒ�Ĺ���
	 */
	private BuyGoods goodsRow() {
		int row = table.getSelectedRow();
		if(row==-1) {
			JOptionPane.showMessageDialog(null, "��ѡ����Ʒ");
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
	 * ��հ�ť
	 */
	private void deleteAll() {
		int j = carIsEmpty();
		if(j==-1) {
			JOptionPane.showMessageDialog(null, "���ﳵΪ��");
			return;
		}
		Integer useid = Integer.parseInt(LoginConfig.getUserList().get(2));
		Double money = UserFrm.money;
		try {
			Double goodsmoney = buyGoodsDao.moneyGoodsAll(JDBCUtils.getConnection(), useid);
			int res = JOptionPane.showConfirmDialog(null, "ȷ����չ��ﳵ����֧��" + goodsmoney + "Ԫ","����",JOptionPane.YES_NO_OPTION);
			if(res==0) {
				String password = JOptionPane.showInputDialog("����������");
				if(money<goodsmoney) {
					JOptionPane.showMessageDialog(null, "���㣬���ֵ");
					return;
				}
				if(LoginConfig.getUserList().get(1).equals(password)) {
					buyGoodsDao.historyShoping(JDBCUtils.getConnection(), useid);//д������
					int i = buyGoodsDao.deleteGoodsAll(JDBCUtils.getConnection(), useid);
					if(i>0) {
						//Thread.sleep(1000);
						//userDao.updateUserMoney(JDBCUtils.getConnection(), goodsmoney, useid, false);
						UserFrm.money = money - goodsmoney;
						buyGoodsDao.historyShoping(JDBCUtils.getConnection(), useid);
						BuyDialog buyDialog = new BuyDialog();
						buyDialog.lblNewLabel_2.setText("��" + goodsmoney);
						buyDialog.setVisible(true);
						fillTable();
						return;
					}else {
						JOptionPane.showMessageDialog(null, "���ﳵΪ��");
						return;
					}
				}
				else {
					//Thread.sleep(1000);
					BuyDialog buyDialog = new BuyDialog();
					buyDialog.lblNewLabel.setIcon(new ImageIcon(BuyDialog.class.getResource("/image/c.jpg")));
					buyDialog.lblNewLabel_1.setText("֧��ʧ��");
					buyDialog.lblNewLabel_2.setText("��" + goodsmoney);
					buyDialog.setVisible(true);
					table.removeAll();
					return;
				}
			}else {
				int i = buyGoodsDao.deleteGoodsAll(JDBCUtils.getConnection(), null,useid);
				if(i>0) {
					JOptionPane.showMessageDialog(null, "��չ��ﳵ�ɹ�");
					fillTable();
				}else {
					JOptionPane.showMessageDialog(null, "���ﳵΪ��");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * ɾ����ť
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
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				fillTable();
				return;
			}else {
				JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * �޸İ�ť
	 */
	private void updateCount() {
		BuyGoods buyGoods = goodsRow();
		if(buyGoods==null) {
			return;
		}
		String count = JOptionPane.showInputDialog("������Ҫ���������");
			if(StringUtils.isEmpty(count)) {
				JOptionPane.showMessageDialog(null, "����Ϊ��");
			}else if(StringUtils.isNumber(count)==false){
				JOptionPane.showMessageDialog(null, "�������");
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
						JOptionPane.showMessageDialog(null, "��治��");
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
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
					return;
						
				} catch (Exception e) {
					e.printStackTrace();
				}
			}		
	}
	/**
	 * ��鹺�ﳵ�Ƿ�Ϊ�գ�Ϊ��հ�ť����
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
