package view;

import dao.GoodsTypeDao;
import model.GoodsType;
import utiles.JDBCUtils;
import utiles.StringUtils;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.Vector;

public class GoodsTypeAll extends JInternalFrame {
	
	
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textArea;
	private JComboBox comboBox;
	
	private GoodsTypeDao goodsTypeDao = new GoodsTypeDao();
	private static GoodsTypeAll goodsTypeAll = new GoodsTypeAll();
	public static GoodsTypeAll getGoodsTypeAll() {
		return goodsTypeAll;
	}
	/**
	 * Create the frame.
	 */
	private GoodsTypeAll() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("全部商品类别");
		
		setClosable(true);
		setBounds(550, 100, 765, 789);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("商品类别名称：");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JButton btnNewButton = new JButton("查询");
		
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "表单操作", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		 comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 204, 204));
		comboBox.setFont(new Font("微软雅黑", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(169)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(226, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(71)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE))
					.addGap(66))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(btnNewButton)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(40, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_1 = new JLabel("编号：");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("商品类别名称：");
		lblNewLabel_2.setForeground(Color.ORANGE);
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("描述：");
		lblNewLabel_3.setForeground(new Color(51, 204, 255));
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		 textArea = new JTextArea();
		 textArea.setBackground(new Color(204, 255, 204));
		 textArea.setFont(new Font("Monospaced", Font.BOLD, 15));
		
		JButton btnNewButton_1 = new JButton("修改");
		btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		
		JButton btnNewButton_2 = new JButton("删除");
		btnNewButton_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_2, 0, 0, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 474, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(166)
							.addComponent(btnNewButton_1)
							.addGap(80)
							.addComponent(btnNewButton_2)))
					.addContainerGap(59, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_1))
					.addGap(22))
		);
		panel.setLayout(gl_panel);
		
		table = new JTable();
		table.setBackground(new Color(204, 255, 204));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableMousePressed();
			}
		});
		table.setFont(new Font("华文细黑", Font.ITALIC, 15));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"商品类别id", "商品类别名称", "描述"
			}
		));
		scrollPane.setViewportView(this.table);
		getContentPane().setLayout(groupLayout);
		this.fillTable(new GoodsType());
		
		/**
		 * 点击方法
		 */
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				AdminFrm.flagGoodsTypeAll = true;
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			private ActionEvent e;
			@Override
			public void actionPerformed(ActionEvent e) {
				this.e = e;
				selectType();
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				typeUpdate();
			}

		});
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				typeDelete();
			}

		});
	}
	
	
	/**
	 * 查询商品类别
	 */
	private void selectType() { 
		  GoodsType goodsType = (GoodsType)comboBox.getSelectedItem();
		  fillTable(goodsType);
	}
	/**
	 * 初始化下拉表
	 */
		public void fillJComboBox2() {
		DefaultComboBoxModel combox = (DefaultComboBoxModel) this.comboBox.getModel();
		combox.removeAllElements();
		GoodsType Li = new GoodsType(); 
		Li.setType("请选择"); 
		Li.setId(-1);
		combox.addElement(Li);
		try {
			ResultSet rs = goodsTypeDao.typeList(JDBCUtils.getConnection(), new GoodsType());
			while(rs.next()) {
				GoodsType goodsType = new GoodsType();
				goodsType.setType(rs.getString("goodsTypeName"));
				goodsType.setId(rs.getInt("id"));
				combox.addElement(goodsType);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 初始化表格
	 */
	public void fillTable(GoodsType type) {
		DefaultTableModel dtm = (DefaultTableModel) this.table.getModel();
		dtm.setRowCount(0);
		try {
			ResultSet rs = goodsTypeDao.typeList(JDBCUtils.getConnection(), type);
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 表格点击事件处理
	 */
	private void tableMousePressed() {
		int row = table.getSelectedRow();
		textField_1.setText(table.getValueAt(row, 0).toString());
		textField_2.setText(table.getValueAt(row, 1).toString());
		textArea.setText((String)table.getValueAt(row, 2));
		
	}
	/**
	 * 修改商品类型
	 */
	private void typeUpdate() {
		String id = textField_1.getText();
		String goodsTypeName = textField_2.getText();
		String goodsTypeDesc = textArea.getText();
		if(StringUtils.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的记录");
			return;
		}
		if(StringUtils.isEmpty(goodsTypeName)) {
			JOptionPane.showMessageDialog(null, "商品类别名称不能为空");
			return;
		}
		GoodsType goodsType = new GoodsType(Integer.valueOf(id),goodsTypeName,goodsTypeDesc);
		try {
			int i = goodsTypeDao.updateType(JDBCUtils.getConnection(), goodsType);
			if(i==1) {
				JOptionPane.showMessageDialog(null, "修改成功");
				this.resetValue();
				this.fillTable(new GoodsType());
			}else {
				JOptionPane.showMessageDialog(null, "修改失败");
			}
		} catch (Exception e) {
			e = new Exception("修改商品类型错误");
			e.printStackTrace();
		}
	}
	/**
	 * 删除商品类型
	 */
	private void typeDelete() {
		String id = textField_1.getText();
		if(StringUtils.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要删除的记录");
			return;
		}
		
		try {
			
			int res = JOptionPane.showConfirmDialog(null, "确认要删除该商品类么？","警告",JOptionPane.YES_NO_OPTION);
			if(res==0) {
				int i = goodsTypeDao.deleteType(JDBCUtils.getConnection(), Integer.valueOf(id));
				if(i==1) {
					JOptionPane.showMessageDialog(null, "删除成功");
					this.resetValue();
					this.fillJComboBox2();
					this.fillTable(new GoodsType());
					return;
				}else {
					JOptionPane.showMessageDialog(null, "删除失败");
					return;
				}
			}else {
				this.resetValue();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "删除失败，该类有商品");
			e.printStackTrace();
		}
		
	}
	/**
	 * 重置
	 */
	private void resetValue() {
		this.textField_1.setText("");
		this.textField_2.setText("");
		this.textArea.setText("");;
	
	}
}
