package view;

import dao.UserDao;
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
import java.math.BigDecimal;

public class ChongMoney extends JInternalFrame {
	public  JTextField textField;
	
	private UserDao userdao = new UserDao();
	private JTextField textField_1;

	private static ChongMoney chongMoney = new ChongMoney();

	public static ChongMoney getChongMoney() {
		return chongMoney;
	}
	/**
	 * Create the dialog.
	 */
	private ChongMoney() {
		setTitle("”‡∂Ó÷ÿ÷√");
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				UserFrm.flagChongMoney = true;
			}
		});
		setClosable(true);
		setBounds(700, 250, 464, 385);
		
		JLabel lblNewLabel = new JLabel("µ±«∞”‡∂Ó£∫");
		lblNewLabel.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 15));
		textField.setEnabled(false);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("‘™");
		lblNewLabel_1.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 15));
		lblNewLabel_1.setForeground(Color.RED);
		
		JLabel lblNewLabel_2 = new JLabel("≥‰÷µ”‡∂Ó£∫");
		lblNewLabel_2.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 15));
		
		JLabel lblNewLabel_1_1 = new JLabel("‘™");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 15));
		
		JButton btnNewButton = new JButton("≥‰÷µ");
		
		btnNewButton.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 15));
		
		textField_1 = new JTextField();
		
		textField_1.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 15));
		textField_1.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(114)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
									.addGap(17)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addGap(18)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(lblNewLabel_1))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(189)
							.addComponent(btnNewButton)))
					.addContainerGap(123, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(64)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(66)
					.addComponent(btnNewButton)
					.addContainerGap(86, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		//this.setMoney();
		//≥‰÷µ∞¥≈•
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String money = textField_1.getText();
				if(StringUtils.isNumber(money)) {
					addMoney();
				}else {
					JOptionPane.showMessageDialog(null, " ‰»Î¥ÌŒÛ");
				}
				
			}
		});
	}
	/**
	 * œ‘ æ”‡∂Ó
	 */
	public  void setMoney() {
		//Integer id = Integer.parseInt(LoginConfig.getUserList().get(2));
		try {
			//Double money = Double.parseDouble(LoginConfig.getUserList().get(3));
			BigDecimal bigDecimal =	BigDecimal.valueOf(UserFrm.money);
			textField.setText(bigDecimal.setScale(1,BigDecimal.ROUND_DOWN).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void addMoney() {
		Double money = Double.parseDouble(textField_1.getText());
		String password = JOptionPane.showInputDialog("«Î ‰»Î√‹¬Î");
		if(LoginConfig.getUserList().get(1).equals(password)) {
			Integer id = Integer.parseInt(LoginConfig.getUserList().get(2));
			try {
				userdao.updateUserMoney(JDBCUtils.getConnection(), money, id,true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			UserFrm.money += money;
			ChongMoney chongMoney = ChongMoney.getChongMoney();
			chongMoney.textField.setText(UserFrm.money.toString());
			BuyDialog buyDialog = new BuyDialog();
			buyDialog.lblNewLabel_1.setText("≥‰÷µ≥…π¶");
			buyDialog.lblNewLabel_2.setText("£§" +  money);
			textField_1.setText("");
			this.dispose();
			buyDialog.setVisible(true);
			return;
		}else {
			BuyDialog buyDialog = new BuyDialog();
			buyDialog.lblNewLabel.setIcon(new ImageIcon(BuyDialog.class.getResource("/image/c.jpg")));
			buyDialog.lblNewLabel_1.setText("≥‰÷µ ß∞‹");
			buyDialog.lblNewLabel_2.setText("£§" +  money);
			buyDialog.setVisible(true);
			return;
		}
	}
}
