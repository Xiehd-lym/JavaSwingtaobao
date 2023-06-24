package view;

import dao.GoodsTypeDao;
import model.GoodsType;
import utiles.JDBCUtils;
import utiles.StringUtils;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoodsTypeAdd extends JInternalFrame {
	
	
	private JTextField textField;//��Ʒ����
	private JTextArea textArea;//��Ʒ����

	private GoodsTypeDao goodsTypeDao = new GoodsTypeDao();
	
	
	private static GoodsTypeAdd goodsTypeAdd = new GoodsTypeAdd();
	
	public static GoodsTypeAdd getGoodsTypeAdd(){
        return goodsTypeAdd;
    }
	/**
	 * Create the frame.
	 */
	private GoodsTypeAdd() {
		setTitle("��Ʒ������");
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				AdminFrm.flagGoodsTypeAdd = true;
			}
		
		});
		setClosable(true);
		getContentPane().setFont(new Font("΢���ź�", Font.BOLD, 15));
		setBounds(575, 180, 584, 461);
		
		JLabel lblNewLabel = new JLabel("��Ʒ���");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("΢���ź�", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setFont(new Font("΢���ź�", Font.BOLD, 15));
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("���");
		
		btnNewButton.setFont(new Font("΢���ź�", Font.BOLD, 15));
		
		JLabel lblNewLabel_1 = new JLabel("��Ʒ���������");
		lblNewLabel_1.setForeground(new Color(0, 204, 204));
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.BOLD, 15));
		
		textArea = new JTextArea();
		textArea.setBackground(new Color(153, 255, 204));
		textArea.setFont(new Font("Monospaced", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(128, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(207)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(133, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(124, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(49)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
					.addGap(44)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(67))
		);
		getContentPane().setLayout(groupLayout);

		/**
		 * �������
		 */
		//��Ӱ�ť
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addType();
			}
		});
	}
	
	/**
	 * ��Ʒ���
	 */
	private void addType() {
		String goodsType = textField.getText();
		String goodsDesc = textArea.getText();
		if(StringUtils.isEmpty(goodsType)) {
			JOptionPane.showMessageDialog(null, "��Ʒ���Ϊ��");
			return;
		}else {
			
			GoodsType Type = new GoodsType(goodsType, goodsDesc);
			try {
				int i = goodsTypeDao.addType(JDBCUtils.getConnection(), Type);
				if(i==1) {
					JOptionPane.showMessageDialog(null, "��ӳɹ�");
					textField.setText("");
					textArea.setText("");
					GoodsTypeAll goodsTypeAll = GoodsTypeAll.getGoodsTypeAll();
					goodsTypeAll.fillTable(new GoodsType());
					this.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "���ʧ��");
				}
			} catch (Exception e) {
				e = new Exception("��Ʒ��Ӵ���");
				e.printStackTrace();
			}
		}
		
	}

}
