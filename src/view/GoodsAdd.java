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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class GoodsAdd extends JInternalFrame {
	
	private JTextField textField; //商品名称
	private JTextField textField_1;//商品价格
	private JTextField textField_2;//商品数量
	private JTextArea textArea;	//商品描述
	private JComboBox comboBox;
	//以下是工具类，为了处理数据
	private GoodsDao goodsDao = new GoodsDao();
	private  GoodsTypeDao goodsTypeDao = new GoodsTypeDao();
	//下面这行代码是为了点击时只能生成一个窗口，不会重复new出新的窗口(饥饿模式，类加载的时候就完成了实例化)
	private static GoodsAdd goodsAdd = new GoodsAdd();
	
	public static GoodsAdd getGoodsAdd() {
		return goodsAdd;
	}
	/**
	 * Create the frame.
	 */
	private GoodsAdd() {
		setTitle("商品添加");
		setClosable(true);
		getContentPane().setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JLabel lblNewLabel = new JLabel("商品名称：");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.BOLD, 15));
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("价格：");
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		textField_1.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 204, 204));
		comboBox.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JLabel lblNewLabel_2 = new JLabel("数量：");
		lblNewLabel_2.setForeground(new Color(51, 153, 255));
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
		
		JButton btnNewButton = new JButton("添加");
		
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(79)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_2)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(97)
							.addComponent(lblNewLabel_3)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(35)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(74, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(291, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(264))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(94)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(86)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
					.addGap(70)
					.addComponent(btnNewButton)
					.addContainerGap(145, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

		setBounds(575, 100, 660, 717);
		
		/**
		 *点击方法 
		 */
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addGoodsType();
			}
		});
	}
	/**
	 * 初始化商品类别下拉表
	 */
	public void fillJComboBox() {
		//默认的初始化下拉表，当每次生成界面时就删除原来的下拉表的内容，重新再生成
		DefaultComboBoxModel combox = (DefaultComboBoxModel) this.comboBox.getModel();
		combox.removeAllElements();
		try {
			//调用商品类别的工具类，拿到所有的商品类别结果集
			ResultSet rs = goodsTypeDao.typeList(JDBCUtils.getConnection(), new GoodsType());
			while(rs.next()) {
				GoodsType goodsType = new GoodsType();//将类别进行封装，为了选中时拿到数据进行添加
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
	 * 商品添加
	 */
	private void addGoodsType() {
		String goodsName = textField.getText();
		String price = textField_1.getText();
		GoodsType goodsType = (GoodsType)comboBox.getSelectedItem();//拿到选中的下拉表，包含了id和类别名
		int id = goodsType.getId();
		String count = textField_2.getText();
		String goodsDesc = textArea.getText(); 
		if(StringUtils.isEmpty(goodsName)) {
			JOptionPane.showMessageDialog(null, "商品名称为空");
			return;
		}else if(StringUtils.isEmpty(price)) {
			JOptionPane.showMessageDialog(null, "价格为空");
			return;
		}else if(StringUtils.isEmpty(count)) {
			JOptionPane.showMessageDialog(null, "数量为空");
			return;
		}else {
			
			try {
				if(StringUtils.isNumber(count)) {
					Goods goods = new Goods(goodsName, Double.valueOf(price), id,goodsDesc, Integer.valueOf(count));
					int i = goodsDao.addType(JDBCUtils.getConnection(), goods);
					if(i==1) {
						JOptionPane.showMessageDialog(null, "添加成功");
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textArea.setText("");
						GoodsAll.getGoodsAll().fillTable(new Goods());
						return;
					}
					else {
						JOptionPane.showMessageDialog(null, "添加失败");
						return;
					}	
				}else {
					JOptionPane.showMessageDialog(null, "数量输入格式错误");
					return;
				}
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
	}
}
