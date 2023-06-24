package utiles;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * 
 */
public class JDBCUtils {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    private static ResourceBundle bundle;
   
    
    static{
        bundle = ResourceBundle.getBundle("db");
        driver = bundle.getString("jdbc.driverClass");
        url = bundle.getString("jdbc.jdbcUrl");
        username = bundle.getString("jdbc.username");
        password = bundle.getString("jdbc.password");
    }
 
    /**
     * 
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

   
    public static void release(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
  

    public static void release(Connection conn, PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void release(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
