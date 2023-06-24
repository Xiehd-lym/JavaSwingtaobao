package view;

import dao.UserDao;
import model.User;
import model.UserId;
import utiles.JDBCUtils;
import utiles.LoginConfig;
import utiles.StringUtils;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtT;
	private JPasswordField passwordField;

	private UserDao userDao = new UserDao();
	
	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 482);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("ÃÜÂë");
		lblNewLabel_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
		
		JButton btnNewButton = new JButton("µÇÂ¼");
		btnNewButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
		
		
		txtT = new JTextField();
		txtT.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
		txtT.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("×¢²á");
		
		btnNewButton_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
		
		JButton btnNewButton_2 = new JButton("ÖØÖÃ");
		
		btnNewButton_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
		
		passwordField = new JPasswordField();
		
		
		JLabel lblNewLabel = new JLabel("ÓÃ»§Ãû£º");
		lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(108)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(8)
									.addComponent(lblNewLabel_1))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
								.addComponent(txtT, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(141, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(150, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtT, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_1))
					.addContainerGap(108, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		 ImageIcon bg=new ImageIcon(Login.class.getResource("/image/login.jpg"));
		  this.setSize(bg.getIconWidth(),bg.getIconHeight());
		  JLabel label=new JLabel(bg); 
		  label.setSize(bg.getIconWidth(),bg.getIconHeight());
		  JPanel pan=(JPanel)this.getContentPane();
		  pan.setOpaque(false); 
		  this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
		 
		  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  this.setLocationRelativeTo(null);
		  
		  /**
		   * µã»÷·½·¨
		   */
		  btnNewButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					check();
				}
			});
		  btnNewButton_1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					dispose();
					new FirstLogin().setVisible(true);
				}
			});
		  btnNewButton_2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					txtT.setText("");
					passwordField.setText("");
				}
			});
		  passwordField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==10) {
						check();
					}
				}
			});
		
	}
	/**
	 * µÇÂ¼¼ì²é
	 */
	private void check() {
		String userName = txtT.getText();
		String password = passwordField.getText();
		Connection conn = JDBCUtils.getConnection();
		if(StringUtils.isEmpty(userName, password)) {
			JOptionPane.showMessageDialog(null, "ÓÃ»§Ãû»òÃÜÂë²»ÄÜÎª¿Õ");
			return;
		}
		User user = new User(userName,password);
		UserId userid = null;
		try {
			userid = userDao.login(conn,user);//·µ»ØÈ¨ÏÞ
			if(userid!=null) {
				if(userid.getUserid()==1) {
					LoginConfig.writeUser(userName,userid.getId().toString(),password,userid.getMoney().toString());
					JOptionPane.showMessageDialog(null, "»¶Ó­Äã¹ÜÀíÔ±");
					dispose();
					AdminFrm adminfrm = new AdminFrm();
					adminfrm.setVisible(true);
					return;
				}else if(userid.getUserid()==0){
					LoginConfig.writeUser(userName,userid.getId().toString(),password,userid.getMoney().toString());
					JOptionPane.showMessageDialog(null, "µÇÂ¼³É¹¦");
					dispose();
					UserFrm userfrm = new UserFrm();
					userfrm.setVisible(true);
					return;
				}
			}else {
				JOptionPane.showMessageDialog(null, "µÇÂ¼Ê§°Ü");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
