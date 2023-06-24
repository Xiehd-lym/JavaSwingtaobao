package view;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyDialog extends JDialog {
	
	public JLabel lblNewLabel; //图片(对和错)
	public JLabel lblNewLabel_1;//提示支付成功或失败
	public JLabel lblNewLabel_2;//显示金额
	
	/**
	 * Create the dialog.
	 */
	public BuyDialog() {
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 446, 363);
		
		 lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(BuyDialog.class.getResource("/image/g.jpg")));
		
		 lblNewLabel_1 = new JLabel("\u652F\u4ED8\u6210\u529F");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		 lblNewLabel_2 = new JLabel("         ");
		 lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(155)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 95, Short.MAX_VALUE)
					.addGap(180))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(165)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_2, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(189, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(146)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(165, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addGap(18)
					.addComponent(lblNewLabel_2)
					.addGap(32)
					.addComponent(btnNewButton)
					.addContainerGap(35, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		JPanel pan=(JPanel)this.getContentPane();
		  pan.setOpaque(true); 
		/**
		 * 点击事件
		 */
		//关闭对话框
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
}
