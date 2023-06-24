package view;

import dao.UserDao;
import utiles.JDBCUtils;

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
import java.sql.ResultSet;
import java.util.Vector;

public class UserList extends JInternalFrame {
	private JTable table;
	private JTextField textField;

	private static UserList userlist = new UserList();
	private UserDao userDao = new UserDao();
	
	public static UserList getUserList() {
		return userlist;
	}
	
	/**
	 * Create the frame.
	 */
	private UserList() {
		setClosable(true);
		setTitle("ÓÃ»§ÁÐ±í");
		setBounds(500, 150, 771, 587);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("ÓÃ»§Ãû£º");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("²éÑ¯");
		
		btnNewButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
		
		JButton btnNewButton_1 = new JButton("Óà¶îÉýÐò");
		
		btnNewButton_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
		
		JButton btnNewButton_2 = new JButton("Óà¶î½µÐò");
		
		btnNewButton_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(120, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(64)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addGap(225))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE)
							.addGap(107))))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(232)
					.addComponent(btnNewButton_1)
					.addGap(67)
					.addComponent(btnNewButton_2)
					.addContainerGap(230, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(36)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7528\u6237id", "\u7528\u6237\u540D", "\u5BC6\u7801", "\u4F59\u989D"
			}
		));
		table.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
		table.setBackground(new Color(204, 255, 204));
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		
		
		
		/**
		 * µã»÷ÊÂ¼þ
		 */
		//´°¿Ú¹Ø±Õ
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				AdminFrm.flagUserList = true;
			}
		});
		//²éÑ¯°´Å¥
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fillTable(null);
			}
		});
		//Óà¶îÉýÐò°´Å¥
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = userDao.sortUserList(JDBCUtils.getConnection(), false);
					fillTable(rs);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		//½µÐò°´Å¥
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = userDao.sortUserList(JDBCUtils.getConnection(), true);
					fillTable(rs);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	/**
	 * ³õÊ¼»¯±í¸ñ
	 */
	public void fillTable(ResultSet resultSet) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		String name = textField.getText();
		Connection conn = JDBCUtils.getConnection();
		
		ResultSet rs = null;
		try {
			if(resultSet ==null) {
				rs = userDao.userList(JDBCUtils.getConnection(), name);
			}else {
				rs = resultSet;
			}
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				BigDecimal bigDecimal =	BigDecimal.valueOf(rs.getDouble(5));
				v.add(bigDecimal.setScale(2,BigDecimal.ROUND_UP));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
