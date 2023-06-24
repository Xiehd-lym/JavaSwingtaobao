package view;

import dao.UserDao;
import model.User;
import utiles.JDBCUtils;
import utiles.LoginConfig;
import utiles.StringUtils;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class UpdatePassword extends JInternalFrame {
	private JTextField textField;
	private static UpdatePassword updatePassword;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	
	public static UpdatePassword getUpdatePassword() {
		if(updatePassword==null){
			return new UpdatePassword();
		}
		return updatePassword;
	}
	/**
	 * Create the frame.
	 */
	private UpdatePassword() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				UserFrm.flagUpdatePassword = true;
			}
		});
		setTitle("修改密码");
		setClosable(true);
		getContentPane().setBackground(Color.WHITE);
		setBounds(600, 200, 604, 487);
		
		JLabel lblNewLabel = new JLabel("用户名：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("微软雅黑", Font.BOLD, 15));
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("原密码：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JLabel lblNewLabel_1_1 = new JLabel("新密码：");
		lblNewLabel_1_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JLabel lblNewLabel_1_2 = new JLabel("确认密码：");
		lblNewLabel_1_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JButton btnNewButton = new JButton("修改");
		
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JButton btnNewButton_1 = new JButton("重置");
		
		btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		passwordField = new JPasswordField();
		
		passwordField_1 = new JPasswordField();
		
		passwordField_2 = new JPasswordField();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(150)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(12)
									.addComponent(lblNewLabel)
									.addGap(18)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(15)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(passwordField))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_2))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(passwordField_2, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
										.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(216)
							.addComponent(btnNewButton)
							.addGap(38)
							.addComponent(btnNewButton_1)))
					.addContainerGap(191, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(54)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField_2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(70)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(61, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		this.fillName();
		
		/**
		 * 点击方法
		 */
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {
					check();
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				check();
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
	}
	private void fillName() {

		ArrayList useList = LoginConfig.getUserList();
		String userName = useList.get(0).toString();
		System.out.println(userName);
		textField.setText(userName);
	}
	/**
	 * 修改密码
	 */
	private void check() {
		
		String userName = textField.getText();
		String password = passwordField.getText();
		String password1 = passwordField_1.getText();
		String password2 = passwordField_2.getText();
		User user = new User(userName,password2);
		if(StringUtils.isEmpty(password, password1)) {
			JOptionPane.showMessageDialog(null, "新旧密码不能为空");
			return;
		}
		if(password.equals(password1)) {
			JOptionPane.showMessageDialog(null, "新旧密码不能相同");
			return;
		}
		if(!password1.equals(password2)) {
			JOptionPane.showMessageDialog(null, "请确认两次密码相同");
			return;
		}
		try {
			if(!password.equals(LoginConfig.getUserList().get(1))) {
				JOptionPane.showMessageDialog(null, "原密码错误");
				return;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		try {
			new UserDao().updateUser(JDBCUtils.getConnection(),user);
			JOptionPane.showMessageDialog(null, "修改成功");
			LoginConfig.writeUser(LoginConfig.getUserList().get(0), LoginConfig.getUserList().get(2), password2,LoginConfig.getUserList().get(3));
			reset();
			UpdatePassword updatePassword = UpdatePassword.getUpdatePassword();
			updatePassword.dispose();
			return;
		}  catch (Exception e1) {
			e1 = new Exception("修改密码错误");
			e1.printStackTrace();
		} 
	
	}
	/**
	 * 重置
	 */
	private void reset() {
		passwordField.setText("");
		passwordField_1.setText("");
		passwordField_2.setText("");
	}
}
