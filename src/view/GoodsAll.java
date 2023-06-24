package view;

import dao.GoodsDao;
import dao.GoodsTypeDao;
import model.Goods;
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

public class GoodsAll extends JInternalFrame {
	
	
	
	private JTable table;
	private JTextField textField;//商品名称
	private JTextField textField_1;//商品编号
	private JTextField textField_2;//表单的商品名称
	private JTextField textField_3;//表单的单价
	private JTextField textField_4;//表单的数量
	private JTextArea textArea;//表单的商品描述
	private JComboBox comboBox;//表单的商品描述

	private  GoodsTypeDao goodsTypeDao = new GoodsTypeDao();
	private GoodsDao goodsDao = new GoodsDao();
	private static GoodsAll goodsAll = new GoodsAll();
	
	public static GoodsAll getGoodsAll(){
        
        return goodsAll;
    }
	/**
	 * Create the frame.
	 */
	private GoodsAll() {
		setTitle("商品维护");
		
		setIconifiable(true);
		setClosable(true);
		setBounds(500, 15, 935, 919);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		
		JButton btnNewButton_1 = new JButton("修改");
		
		btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JButton btnNewButton_2 = new JButton("删除");
		
		btnNewButton_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "表单操作", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(152)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 635, GroupLayout.PREFERRED_SIZE)
							.addGap(3)))
					.addGap(127))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(318)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(88)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(334, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
					.addGap(55)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addGap(118))
		);
		
		JLabel lblNewLabel_3 = new JLabel("商品名称：");
		lblNewLabel_3.setForeground(Color.ORANGE);
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("单价：");
		lblNewLabel_1_1.setForeground(Color.GREEN);
		lblNewLabel_1_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("微软雅黑", Font.BOLD, 15));
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("数量：");
		lblNewLabel_1_1_1.setForeground(Color.PINK);
		lblNewLabel_1_1_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("微软雅黑", Font.BOLD, 15));
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("描述：");
		lblNewLabel_1_1_2.setForeground(new Color(135, 206, 235));
		lblNewLabel_1_1_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		 textArea = new JTextArea();
		 textArea.setFont(new Font("Monospaced", Font.BOLD, 15));
		 textArea.setBackground(new Color(204, 255, 204));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(37)
							.addComponent(lblNewLabel_1_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
						.addComponent(textArea))
					.addContainerGap(93, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(58, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("商品名称：");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.BOLD, 15));
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("商品编号：");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("类别：");
		lblNewLabel_2.setForeground(new Color(51, 153, 255));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JButton btnNewButton_1_1 = new JButton("查询");
		
		btnNewButton_1_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		 comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 204, 204));
		comboBox.setFont(new Font("微软雅黑", Font.BOLD, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(256)
							.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
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
		table.setToolTipText("");
		table.setBorder(UIManager.getBorder("CheckBox.border"));
		table.setFont(new Font("华文细黑", Font.ITALIC, 15));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"商品编号", "商品名称", "单价", "数量", "商品描述", "类别"
			}
		));
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		this.fillTable(new Goods());
		
		/**
		 * 点击方法
		 */
		//窗口关闭
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				AdminFrm.flagGoodsAll = true;
			}
		});
		//查询按钮
		btnNewButton_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectType();
			}
		});
		//删除按钮
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				typeDelete();
			}
		});
		//修改按钮
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				typeUpdate();
			}
		});
	}
	
	/**
	 * 初始化表格
	 */
	public void fillTable(Goods goods) {
		//默认的表格
		DefaultTableModel dtm = (DefaultTableModel) this.table.getModel();
		//默认选中第1行
		dtm.setRowCount(0);
		try {
			//拿到全部商品的结果集
			ResultSet rs = goodsDao.goodsList(JDBCUtils.getConnection(), goods);
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt(1));
				v.add(rs.getString(2));
				v.add(rs.getDouble(3));
				v.add(rs.getInt(6));
				v.add(rs.getString(5));
				v.add(rs.getString(8));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e = new Exception("初始化表格错误");
			e.printStackTrace();
		}
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
			e = new Exception("初始化下拉表错误");
			e.printStackTrace();
		}
	}
		
		/**
		 * 表格点击事件处理
		 * 
		 */
		private void tableMousePressed() {
			
			int row = table.getSelectedRow();
			textField_2.setText(table.getValueAt(row, 1).toString());
			textField_3.setText(table.getValueAt(row, 2).toString());
			textField_4.setText(table.getValueAt(row, 3).toString());
			textArea.setText((String)table.getValueAt(row, 4));
			
		}
	/**
	 * 查询商品
	 */
	private void selectType() {
		
		  String goodsName = this.textField.getText(); 
		  String id = this.textField_1.getText();
		  GoodsType goodsType = (GoodsType)comboBox.getSelectedItem();
		  Goods goods = null;
		  if(StringUtils.isNotEmpty(id)&&!StringUtils.isNumber(id)) {
			  JOptionPane.showMessageDialog(null, "编号输入错误");
			  return;
		  }
		  //根据不同封装数据进行不同的查询 (将封装的数据传到fillTable方法中)
		  if(StringUtils.isEmpty(id)) {
			  goods = new Goods(null, goodsName,goodsType.getId());
		  }else {
			  goods = new Goods(Integer.valueOf(id), goodsName,goodsType.getId());
		  }
		  this.textField.setText(""); 
		  this.textField_1.setText("");
		  this.fillTable(goods);
		
		
	}
	/**
	 * 修改商品
	 */
	private void typeUpdate() {
		String goodsName = textField_2.getText();
		String price = textField_3.getText();
		String count = textField_4.getText();
		String goodsDesc = textArea.getText();
		if(StringUtils.isEmpty(goodsName)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的记录");
			return;
		}
		if(StringUtils.isEmpty(price)) {
			JOptionPane.showMessageDialog(null, "单价不能为空");
			return;
		}
		if(StringUtils.isEmpty(count)) {
			JOptionPane.showMessageDialog(null, "数量不能为空");
			return;
		}
		try {
			//通过正则表达式进行格式检查，正确再修改数据
			if(StringUtils.isNumber(count)&&StringUtils.isNumber(price)) {
				//将商品进行封装
				Goods goods = new Goods(goodsName, Double.valueOf(price), goodsDesc, Integer.valueOf(count));
				//修改商品，返回修改的记录行数
				int i = goodsDao.updateGoods(JDBCUtils.getConnection(), goods);
				if(i==1) {
					JOptionPane.showMessageDialog(null, "修改成功");
					this.resetValue();
					this.fillTable(new Goods());//修改完把表格刷新一下
					return;
				}else {
					JOptionPane.showMessageDialog(null, "修改失败");
					return;
				}
			}else {
				JOptionPane.showMessageDialog(null, "输入格式错误");
			}
			
		} catch (Exception e) {
			e = new Exception("修改异常");
			e.printStackTrace();
		}
	}
	/**
	 * 删除商品类型
	 */
	private void typeDelete() {
		
		String goodsName = textField_2.getText();
		if(StringUtils.isEmpty(goodsName)) {
			JOptionPane.showMessageDialog(null, "请选择要删除的记录");
			return;
		}
		
		try {
			//删除商品进行警告，点击确定才会执行删除方法，点击确定返回1
			int res = JOptionPane.showConfirmDialog(null, "确认要删除该商品么？","警告",JOptionPane.YES_NO_OPTION);
			if(res==0) {
				int i = goodsDao.deleteGoods(JDBCUtils.getConnection(), goodsName);
				if(i>=1) {
					JOptionPane.showMessageDialog(null, "删除成功");
					this.resetValue();
					this.fillTable(new Goods());//刷新表格
					return;
				}else {
					JOptionPane.showMessageDialog(null, "删除失败");
					return;
				}
			}else {
				this.resetValue();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	/**
	 * 重置
	 */
	private void resetValue() {
		this.textField_2.setText("");
		this.textField_3.setText("");
		this.textField_4.setText("");
		this.textArea.setText("");;
	
	}
}
