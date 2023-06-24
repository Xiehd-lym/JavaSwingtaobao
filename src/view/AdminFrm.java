package view;

import utiles.LoginConfig;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminFrm extends JFrame {

    private JMenu mnNewMenu;
    private JPanel contentPane;
    public JDesktopPane desk = new JDesktopPane();
    //为了实现一次点击只能打开一个窗口，打开变为false，关闭变为true
    public static boolean flagGoodsTypeAdd = true;
    public static boolean flagIntroduce = true;
    public static boolean flagGoodsAll = true;
    public static boolean flagGoodsTypeAll = true;
    public static boolean flagUserList = true;
    public static boolean flagUserShopHistory = true;
    public static boolean flagUpdatePassword = true;
    public static boolean flagChongMoney = true;

    /**
     * Create the frame.
     */
    public AdminFrm() {
        setTitle("管理员界面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(893, 813);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        mnNewMenu = new JMenu("");
        menuBar.add(mnNewMenu);

        mnNewMenu = new JMenu();
        mnNewMenu.setIcon(new ImageIcon(UserFrm.class.getResource("/image/user2.jpg")));
        mnNewMenu.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 15));
        //mnNewMenu.setBackground(new Color(0, 204, 255));
        menuBar.add(mnNewMenu);


        JMenuItem paswordUpd = new JMenuItem("修改密码");
        paswordUpd.setIcon(new ImageIcon(UserFrm.class.getResource("/image/password.jpg")));
        paswordUpd.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        mnNewMenu.add(paswordUpd);


        JMenuItem logout = new JMenuItem("退出登录");
        logout.setIcon(new ImageIcon(UserFrm.class.getResource("/image/logout.jpg")));
        logout.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        mnNewMenu.add(logout);

        JMenu mnNewMenu_1 = new JMenu("商品维护");
        //mnNewMenu_1.setBackground(Color.ORANGE);
        mnNewMenu_1.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 15));
        menuBar.add(mnNewMenu_1);

        JMenu menu = new JMenu("商品类别管理");
        menu.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        mnNewMenu_1.add(menu);

        JMenuItem typeAll = new JMenuItem("商品类别维护");

        typeAll.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        menu.add(typeAll);

        JMenuItem typeAdd = new JMenuItem("商品类别添加");

        typeAdd.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        menu.add(typeAdd);

        JMenu menu_1 = new JMenu("商品管理");
        menu_1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        mnNewMenu_1.add(menu_1);

        JMenuItem shopAll = new JMenuItem("商品维护");

        shopAll.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        menu_1.add(shopAll);

        JMenuItem shopAdd = new JMenuItem("商品添加");

        shopAdd.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        menu_1.add(shopAdd);

        JMenu mnNewMenu_3 = new JMenu("出售情况");
        mnNewMenu_3.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 15));
        menuBar.add(mnNewMenu_3);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("用户列表");

        mntmNewMenuItem_1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        mnNewMenu_3.add(mntmNewMenuItem_1);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("出售记录");

        mntmNewMenuItem_2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        mnNewMenu_3.add(mntmNewMenuItem_2);

        JMenu mnNewMenu_2 = new JMenu("关于我们");
        mnNewMenu_2.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 15));
        menuBar.add(mnNewMenu_2);
        JMenuItem mntmNewMenuItem = new JMenuItem("介绍");
        mntmNewMenuItem.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        mnNewMenu_2.add(mntmNewMenuItem);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        desk.setBackground(new Color(72, 209, 204));
        contentPane.add(desk, BorderLayout.CENTER);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);


        /**
         * 点击生成界面
         */
        //介绍界面
        mntmNewMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Introduce introduce = Introduce.getIntroduce();
                if (flagIntroduce) {
                    introduce.setVisible(true);
                    desk.add(introduce);
                    flagIntroduce = false;
                }
            }
        });
        //商品类别管理界面
        typeAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GoodsTypeAll goodsTypeAll = GoodsTypeAll.getGoodsTypeAll();
                if (flagGoodsTypeAll) {
                    goodsTypeAll.setVisible(true);
                    goodsTypeAll.fillJComboBox2();
                    desk.add(goodsTypeAll);
                    flagGoodsTypeAll = false;
                }
            }
        });
        //商品添加界面
        typeAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GoodsTypeAdd goodsTypeAdd = GoodsTypeAdd.getGoodsTypeAdd();
                if (flagGoodsTypeAdd) {
                    goodsTypeAdd.setVisible(true);
                    desk.add(goodsTypeAdd);
                    flagGoodsTypeAdd = false;
                }
            }
        });
        //商品管理界面
        shopAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GoodsAll goodsAll = GoodsAll.getGoodsAll();
                if (flagGoodsAll) {
                    goodsAll.setVisible(true);
                    goodsAll.fillJComboBox2();
                    desk.add(goodsAll);
                    flagGoodsAll = false;
                }

            }
        });
        //商品添加界面
        shopAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GoodsAdd goodsAdd = GoodsAdd.getGoodsAdd();
                goodsAdd.setVisible(true);
                goodsAdd.fillJComboBox();
                desk.add(goodsAdd);
            }
        });
        //用户列表界面
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserList userlist = UserList.getUserList();
                if (flagUserList) {
                    userlist.setVisible(true);
                    userlist.fillTable(null);
                    desk.add(userlist);
                    flagUserList = false;
                }
            }
        });
        //销售记录界面
        mntmNewMenuItem_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserShopHistory usershophistory = UserShopHistory.getShopHistory();
                if (flagUserShopHistory) {
                    usershophistory.setVisible(true);
                    usershophistory.fillTable();
                    desk.add(usershophistory);
                    flagUserShopHistory = false;
                }
            }
        });
        this.fillName();

        //修改密码界面
        paswordUpd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdatePassword updatePassword = UpdatePassword.getUpdatePassword();
                updatePassword.setVisible(true);
                desk.add(updatePassword);
            }
        });
        //充值界面
        mntmNewMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChongMoney chongMoney = ChongMoney.getChongMoney();
                if (flagChongMoney) {
                    chongMoney.setMoney();
                    chongMoney.setVisible(true);
                    desk.add(chongMoney);
                    flagChongMoney = false;
                }

            }
        });
        //退出登录
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "退出成功");
                dispose();
                new Login().setVisible(true);
            }
        });
//		logout.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent mouseEvent) {
//				JOptionPane.showMessageDialog(null, "退出成功");
//				dispose();
//				new Login().setVisible(true);
//			}
//		});
    }

    private void fillName() {
        ArrayList useList = LoginConfig.getUserList();
        String userName = useList.get(0).toString();
        mnNewMenu.setText(userName);
    }
}
