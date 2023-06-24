package view;

import dao.UserDao;
import model.User;
import utiles.JDBCUtils;
import utiles.StringUtils;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FirstLogin extends JFrame {

	private JPanel contentPane;
	
	private JTextField textField;	//�û���
	private JPasswordField passwordField;//����
	private JPasswordField passwordField_1;//ȷ������



	/**
	 * Create the frame.
	 */
	public FirstLogin() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 852);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("�û�����");
		lblNewLabel.setFont(new Font("΢���ź�", Font.BOLD, 15));
		
		JLabel lblNewLabel_1 = new JLabel("����");
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.BOLD, 15));
		
		textField = new JTextField();
		
		textField.setFont(new Font("΢���ź�", Font.BOLD, 15));
		textField.setColumns(10);
		
		passwordField_1 = new JPasswordField();
		
		JLabel lblNewLabel_1_1 = new JLabel("ȷ������");
		lblNewLabel_1_1.setFont(new Font("΢���ź�", Font.BOLD, 15));
		
		passwordField = new JPasswordField();
		
		JButton btnNewButton_1 = new JButton("ע��");
		
		btnNewButton_1.setFont(new Font("΢���ź�", Font.BOLD, 15));
		
		JLabel lblNewLabel_2 = new JLabel("ע��");
		lblNewLabel_2.setForeground(new Color(255, 255, 0));
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 30));
		
		JButton btnNewButton_1_1 = new JButton("����");
		
		btnNewButton_1_1.setFont(new Font("΢���ź�", Font.BOLD, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(114, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_1_1)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(161)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addGap(54)
							.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(125, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(225)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(231, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(90)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(73)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(67)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(66)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(74)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(237))
		);
		contentPane.setLayout(gl_contentPane);

		this.setTitle("ע�����");
		ImageIcon bg=new ImageIcon(FirstLogin.class.getResource("/image/firstlogin.jpg"));
		  this.setSize(bg.getIconWidth(),bg.getIconHeight());
		  JLabel label=new JLabel(bg); 
		  label.setSize(bg.getIconWidth(),bg.getIconHeight());
		  this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
		  JPanel pan=(JPanel)this.getContentPane();
		  pan.setOpaque(false); 
		  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		  this.setLocationRelativeTo(null);
		  
		  /**
		   * �������
		   */
		  textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					check(e);
				}
			});
		  passwordField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					check(e);
				}
			});
		  passwordField_1.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					check(e);
				}
			});
		  btnNewButton_1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(checkText()) {
						addUser();
					}
				}
			});
		  //���ذ�ť���¼�
		  btnNewButton_1_1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					new Login().setVisible(true);
				}
			});
	}
	
	/**
	 * ����ע���¼�
	 * @param e
	 */
	private void check(KeyEvent e) {
		if(e.getKeyCode()==10&&checkText()) {
			addUser();
		}
	}
	
	/**
	 * ����û��ķ���
	 */
	private void addUser() {
		String userName = textField.getText();
		String password = passwordField.getText();
		String password1 = passwordField_1.getText();
		try {
			//�����û������࣬���û�д�����ݿ�
			User user = new User(userName,password1);
			//�÷��������Ƿ��Ѵ��ڸ��û��������ڲ��������
			if(new UserDao().addUser(JDBCUtils.getConnection(),user)) {
				JOptionPane.showMessageDialog(null, "ע��ɹ�");
				dispose();
				new Login().setVisible(true);
				return;
			}else {
				JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ��û��Ѵ���");
				return;
			}
		} catch (Exception e) {
			e = new Exception("����û�ʧ��");
			e.printStackTrace();
		} 
	}
	
	/**
	 * ���������Ƿ�Ϊ��
	 * @return
	 */
	private boolean checkText() {
		String userName = textField.getText();
		String password = passwordField.getText();
		String password1 = passwordField_1.getText();
		if(StringUtils.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
			return false;
		}else if(StringUtils.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
			return false;
		}else if(StringUtils.isEmpty(password1)) {
			JOptionPane.showMessageDialog(null, "��ȷ������");
			return false;
		}else if(!password.equals(password1)) {
			JOptionPane.showMessageDialog(null, "��ȷ������������ͬ");
			return false;
		}
		return true;
	}
}
