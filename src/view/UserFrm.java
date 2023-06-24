package view;

import dao.UserDao;
import model.Goods;
import utiles.JDBCUtils;
import utiles.LoginConfig;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class UserFrm extends JFrame {

    private JPanel contentPane;
    private JMenu mnNewMenu;
    private JDesktopPane desk;

    private UserDao userDao = new UserDao();
    //Ϊ��ʵ��һ�ε��ֻ�ܴ�һ�����ڣ��򿪱�Ϊfalse���رձ�Ϊtrue
    public static boolean flagMyCar = true;
    public static boolean flagShoping = true;
    public static boolean flagShopHistory = true;
    public static boolean flagUpdatePassword = true;
    public static boolean flagChongMoney = true;

    public static Double money = Double.parseDouble(LoginConfig.getUserList().get(3));

    /**
     * Create the frame.
     */
    public UserFrm() {
        setTitle("�û�����");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 893, 813);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        mnNewMenu = new JMenu();
        mnNewMenu.setIcon(new ImageIcon(UserFrm.class.getResource("/image/user2.jpg")));
        mnNewMenu.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 15));
        mnNewMenu.setBackground(new Color(0, 204, 255));
        menuBar.add(mnNewMenu);

        JMenuItem shop = new JMenuItem("�ҵĹ��ﳵ");
        shop.setIcon(new ImageIcon(UserFrm.class.getResource("/image/car2.jpg")));

        shop.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        mnNewMenu.add(shop);

        JMenuItem paswordUpd = new JMenuItem("�޸�����");
        paswordUpd.setIcon(new ImageIcon(UserFrm.class.getResource("/image/password.jpg")));

        paswordUpd.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        mnNewMenu.add(paswordUpd);

        JMenuItem mntmNewMenuItem = new JMenuItem("���￨��ֵ");
        mntmNewMenuItem.setIcon(new ImageIcon(UserFrm.class.getResource("/image/money3.jpg")));

        mntmNewMenuItem.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        mnNewMenu.add(mntmNewMenuItem);

        JMenuItem logout = new JMenuItem("�˳���¼");
        logout.setIcon(new ImageIcon(UserFrm.class.getResource("/image/logout.jpg")));

        logout.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        mnNewMenu.add(logout);

        JMenu mnNewMenu_1 = new JMenu("��Ʒ");
        mnNewMenu_1.setIcon(new ImageIcon(UserFrm.class.getResource("/image/shop.jpg")));
        mnNewMenu_1.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 15));
        mnNewMenu_1.setBackground(new Color(240, 240, 240));

        menuBar.add(mnNewMenu_1);

        JMenuItem shopBuy = new JMenuItem("������Ʒ");
        shopBuy.setIcon(new ImageIcon(UserFrm.class.getResource("/image/shoping.jpg")));

        shopBuy.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        mnNewMenu_1.add(shopBuy);

        JMenuItem shopHistory = new JMenuItem("������ʷ");
        shopHistory.setIcon(new ImageIcon(UserFrm.class.getResource("/image/history.jpg")));


        shopHistory.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        mnNewMenu_1.add(shopHistory);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        desk = new JDesktopPane();
        desk.setBackground(new Color(0, 204, 255));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(desk, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(desk, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
        );
        contentPane.setLayout(gl_contentPane);
        this.setLocationRelativeTo(null);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);


        this.fillName();

        /**
         * �������
         */
        //���ڹر�
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Integer id = Integer.parseInt(LoginConfig.getUserList().get(2));
                Double money;
                String smoney = ChongMoney.getChongMoney().textField.getText();
                if ("".equals(smoney)) {
                    money = Double.parseDouble(LoginConfig.getUserList().get(3));
                } else {
                    money = Double.parseDouble(smoney);
                }
                try {
                    userDao.updateUserMoney(JDBCUtils.getConnection(), money, id, false);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                LoginConfig.reset();
            }
        });
        //�ҵĹ��ﳵ����
        shop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyCar myCar = MyCar.getMyCar();
                if (flagMyCar) {
                    myCar.setVisible(true);
                    myCar.fillTable();
                    desk.add(myCar);
                    flagMyCar = false;
                }

            }
        });
        //�������
        shopBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shoping shoping = Shoping.getShoping();
                if (flagShoping) {
                    shoping.setVisible(true);
                    shoping.fillTable(new Goods());
                    desk.add(shoping);
                    flagShoping = false;
                }

            }
        });
        //������ʷ����
        shopHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShopHistory shopHistory = ShopHistory.getShopHistory();
                if (flagShopHistory) {
                    shopHistory.setVisible(true);
                    shopHistory.fillTable();
                    shopHistory.allMoney();
                    desk.add(shopHistory);
                    flagShopHistory = false;
                }

            }
        });
        //�޸��������
        paswordUpd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdatePassword updatePassword = UpdatePassword.getUpdatePassword();
                if (flagUpdatePassword) {
                    updatePassword.setVisible(true);
                    desk.add(updatePassword);
                    flagUpdatePassword = false;
                }

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
    }

    private void fillName() {
        ArrayList useList = LoginConfig.getUserList();
        String userName = useList.get(0).toString();
        mnNewMenu.setText(userName);
    }
}
