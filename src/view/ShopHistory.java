package view;

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
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ShopHistory extends JInternalFrame {
	private JTable table;
	
	private static ShopHistory shopHistory = new ShopHistory();
	private JTextField textField;
	
	public static ShopHistory getShopHistory() {
		return shopHistory;
	}
	/**
	 * Create the frame.
	 */
	private ShopHistory() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				UserFrm.flagShopHistory = true;
			}
		});
		setClosable(true);
		setBounds(440, 100, 1017, 697);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton = new JButton("清空");
		
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JLabel lblNewLabel = new JLabel("共计消费");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setText((String) null);
		textField.setFont(new Font("微软雅黑", Font.BOLD, 15));
		textField.setEnabled(false);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("元");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(74, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 885, GroupLayout.PREFERRED_SIZE)
							.addGap(42))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
							.addGap(384))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(59)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 463, GroupLayout.PREFERRED_SIZE)
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnNewButton)
					.addContainerGap(45, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setBackground(new Color(204, 255, 255));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"商品id", "商品名称", "商品价格", "商品数量", "购买时间"
			}
		));
		table.setToolTipText("");
		table.setFont(new Font("微软雅黑", Font.BOLD, 15));
		table.setBorder(UIManager.getBorder("CheckBox.border"));
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		this.setTitle("购物历史");
		
		this.fillTable();
		this.allMoney();
		/**
		 * 点击方法
		 */
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteAll();
				allMoney();
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
		String sql = "select g.id,g.goodsName,g.price*s.count as sum,s.count,s.btime from t_goods g,t_shophistory s where g.id = s.goodsid and s.useid=?";
		
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
	 * 共计消费
	 */
	public void allMoney() {
		Integer useid = Integer.parseInt(LoginConfig.getUserList().get(2));
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement pre = null;
		ResultSet rs = null;
		Double money = null;
		String sql = "select sum(g.price*s.count) as sum from t_goods g,t_shophistory s where g.id = s.goodsid and s.useid = ?";
		try {
			pre = conn.prepareStatement(sql);
			pre.setInt(1, useid);
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
		
		 textField.setText(bigDecimal.setScale(1,BigDecimal.ROUND_UP).toString());
		
	}
	private void deleteAll() {
		int res = JOptionPane.showConfirmDialog(null, "确认要清空购物车么？","警告",JOptionPane.YES_NO_OPTION);
		if(res!=0) {
			return;
		}
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement pre =null;
		ResultSet rs = null;
		String sql = "delete from t_shophistory where useid = ?";
		try {
			pre = conn.prepareStatement(sql.toString());
			pre.setInt(1, Integer.parseInt(LoginConfig.getUserList().get(2)));
			int i = pre.executeUpdate();
			if(i>0) {
				JOptionPane.showMessageDialog(null, "清空购物车成功");
				fillTable();
				return;
			}else {
				JOptionPane.showMessageDialog(null, "清空购物车失败");
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
