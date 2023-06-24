package view;

import utiles.JDBCUtils;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class UserShopHistory extends JInternalFrame {
	
	private JTable table;
	
	private JTextField textField;//总收入
	private JTextField textField_1;//交易次数
	private Connection conn = JDBCUtils.getConnection();
	
	private static UserShopHistory usershophistory = new UserShopHistory();
	
	public static UserShopHistory getShopHistory() {
		return usershophistory;
	}
	/**
	 * Create the frame.
	 */
	private UserShopHistory() {
		setTitle("销售记录");
		setClosable(true);
		setBounds(440, 100, 1100, 682);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("总收入：");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setFont(new Font("微软雅黑", Font.BOLD, 15));
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("万元");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JLabel lblNewLabel_2 = new JLabel("交易次数：");
		lblNewLabel_2.setForeground(new Color(0, 204, 0));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("次");
		lblNewLabel_1_1.setForeground(new Color(51, 204, 0));
		lblNewLabel_1_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(296)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(88)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(279, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(65, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 979, GroupLayout.PREFERRED_SIZE)
					.addGap(40))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(65)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 463, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addContainerGap(57, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setBackground(new Color(204, 255, 204));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"用户id", "用户名", "商品名称", "商品价格", "商品数量", "购买时间"
			}
		));
		table.setToolTipText("");
		table.setFont(new Font("微软雅黑", Font.BOLD, 15));
		table.setBorder(UIManager.getBorder("CheckBox.border"));
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);

		
		

		this.allMoney();
		this.allBuyCount();
		/**
		 * 点击事件
		 */
		//窗口关闭
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				AdminFrm.flagUserShopHistory = true;
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
		String sql = "select u.id,u.username,g.goodsName,g.price*s.count as sum,s.count,s.btime from t_goods g,t_usershophistory s,t_user u where g.id = s.goodsid and s.useid = u.id";
		try {
			pre = conn.prepareStatement(sql.toString());
			 rs = pre.executeQuery();
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getDouble(4));
				v.add(rs.getInt(5));
				v.add(rs.getString(6));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 总收入
	 */
	private void allMoney() {
		PreparedStatement pre = null;
		ResultSet rs = null;
		Double money = null;
		String sql = "select sum(g.price*s.count) as sum from t_goods g,t_usershophistory s where g.id = s.goodsid";
		try {
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			 if(rs.next()) {
				  money = rs.getDouble(1); 
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		BigDecimal bigDecimal =	BigDecimal.valueOf(money);
		BigDecimal number = BigDecimal.valueOf(1000);
		
		 textField.setText(bigDecimal.divide(number,4,BigDecimal.ROUND_UP).toString());
		
	}
	/**
	 * 交易次数
	 */
	private void allBuyCount() {
		PreparedStatement pre = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select COUNT(*) from t_usershophistory";
		try {
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			 if(rs.next()) {
				 count = rs.getInt(1); 
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 textField_1.setText(String.valueOf(count));
	}
}
