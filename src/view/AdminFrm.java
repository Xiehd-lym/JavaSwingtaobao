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
    //Ϊ��ʵ��һ�ε��ֻ�ܴ�һ�����ڣ��򿪱�Ϊfalse���رձ�Ϊtrue
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
        setTitle("����Ա����");
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


        JMenuItem paswordUpd = new JMenuItem("�޸�����");
        paswordUpd.setIcon(new ImageIcon(UserFrm.class.getResource("/image/password.jpg")));
        paswordUpd.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        mnNewMenu.add(paswordUpd);


        JMenuItem logout = new JMenuItem("�˳���¼");
        logout.setIcon(new ImageIcon(UserFrm.class.getResource("/image/logout.jpg")));
        logout.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        mnNewMenu.add(logout);

        JMenu mnNewMenu_1 = new JMenu("��Ʒά��");
        //mnNewMenu_1.setBackground(Color.ORANGE);
        mnNewMenu_1.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 15));
        menuBar.add(mnNewMenu_1);

        JMenu menu = new JMenu("��Ʒ������");
        menu.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        mnNewMenu_1.add(menu);

        JMenuItem typeAll = new JMenuItem("��Ʒ���ά��");

        typeAll.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        menu.add(typeAll);

        JMenuItem typeAdd = new JMenuItem("��Ʒ������");

        typeAdd.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        menu.add(typeAdd);

        JMenu menu_1 = new JMenu("��Ʒ����");
        menu_1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        mnNewMenu_1.add(menu_1);

        JMenuItem shopAll = new JMenuItem("��Ʒά��");

        shopAll.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        menu_1.add(shopAll);

        JMenuItem shopAdd = new JMenuItem("��Ʒ���");

        shopAdd.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        menu_1.add(shopAdd);

        JMenu mnNewMenu_3 = new JMenu("�������");
        mnNewMenu_3.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 15));
        menuBar.add(mnNewMenu_3);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("�û��б�");

        mntmNewMenuItem_1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        mnNewMenu_3.add(mntmNewMenuItem_1);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("���ۼ�¼");

        mntmNewMenuItem_2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        mnNewMenu_3.add(mntmNewMenuItem_2);

        JMenu mnNewMenu_2 = new JMenu("��������");
        mnNewMenu_2.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 15));
        menuBar.add(mnNewMenu_2);
        JMenuItem mntmNewMenuItem = new JMenuItem("����");
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
         * ������ɽ���
         */
        //���ܽ���
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
        //��Ʒ���������
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
        //��Ʒ��ӽ���
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
        //��Ʒ�������
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
        //��Ʒ��ӽ���
        shopAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GoodsAdd goodsAdd = GoodsAdd.getGoodsAdd();
                goodsAdd.setVisible(true);
                goodsAdd.fillJComboBox();
                desk.add(goodsAdd);
            }
        });
        //�û��б����
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
        //���ۼ�¼����
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

        //�޸��������
        paswordUpd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdatePassword updatePassword = UpdatePassword.getUpdatePassword();
                updatePassword.setVisible(true);
                desk.add(updatePassword);
            }
        });
        //��ֵ����
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
        //�˳���¼
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "�˳��ɹ�");
                dispose();
                new Login().setVisible(true);
            }
        });
//		logout.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent mouseEvent) {
//				JOptionPane.showMessageDialog(null, "�˳��ɹ�");
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
