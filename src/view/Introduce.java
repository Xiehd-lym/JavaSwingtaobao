package view;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;

public class Introduce extends JInternalFrame {
	private static Introduce introduce= new Introduce();
	public static Introduce getIntroduce(){
        
        return introduce;
    }
	/**
	 * Create the frame.
	 */
	
	private Introduce() {
		setTitle("介绍");
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				AdminFrm.flagIntroduce = true;
			}
		});
		
		setClosable(true);
		setEnabled(false);
		getContentPane().setBackground(new Color(204, 255, 153));
		getContentPane().setForeground(new Color(255, 255, 255));
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.BOLD, 15));
		textArea.setBackground(new Color(152, 251, 152));
		textArea.setForeground(Color.BLACK);
		//可以在这里修改介绍
		textArea.setText("Hello 这是一个超市管理与购物系统");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(218)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 547, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(247, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(166)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(203, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		setBounds(410, 65, 1028, 771);
	}
}
