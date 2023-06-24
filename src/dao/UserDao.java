package dao;

import model.User;
import model.UserId;
import utiles.JDBCUtils;
import utiles.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
	/**
	 * 登录
	 * @param conn
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public  UserId login(Connection conn,User user) throws Exception{
		String sql = "select * from t_user where userName = ? and password = ?";
		 PreparedStatement pre = null;
		 ResultSet rs = null;
			pre = conn.prepareStatement(sql);
			pre.setString(1, user.getNameName());
			pre.setString(2, user.getPassword());
			rs = pre.executeQuery();
			if(rs.next()) {
				UserId userid = new UserId();
				userid.setId(rs.getInt("id"));
				userid.setUserid(rs.getInt("userid"));
				userid.setMoney(rs.getDouble("money"));
				return userid;
			}
			JDBCUtils.release(conn, pre, rs);
		return null;
	}
	/**
	 * 检查是否已经注册
	 * @param conn
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public  boolean loginCheck(Connection conn,User user) throws Exception{
		String sql = "select * from t_user where userName = ?";
		 PreparedStatement pre = null;
		 ResultSet rs = null;
			pre = conn.prepareStatement(sql);
			pre.setString(1, user.getNameName());
			rs = pre.executeQuery();
			if(rs.next()) {
				return true;
			}
			JDBCUtils.release(conn, pre, rs);
		return false;
	}
	/**
	 * 注册功能
	 * @param conn
	 * @param user
	 * @return
	 * @throws Exception
	 */
	 public boolean addUser(Connection conn,User user)throws Exception{
		 PreparedStatement pre = null;
		 ResultSet rs = null;
		 Connection con = JDBCUtils.getConnection();
	        if(loginCheck(conn,user)==false){
	            String sql = "insert into t_user (userName,password) values(?,?)";
	            	pre = con.prepareStatement(sql);
					pre.setString(1, user.getNameName());
					pre.setString(2, user.getPassword());
					pre.executeUpdate();				
					JDBCUtils.release(con,pre);				
	            return true;	        
	       
	    }
	        return false;
	 }
	 /**
	  * 修改密码
	  * @param conn
	  * @param user
	  * @return
	  * @throws Exception
	  */
	 public void updateUser(Connection conn,User user)throws Exception{
		 PreparedStatement pre = null;
		 ResultSet rs = null;
		 Connection con = JDBCUtils.getConnection();
	 
	            String sql = "update t_user set password=? where userName=?";
	            	pre = con.prepareStatement(sql);
					pre.setString(2, user.getNameName());
					pre.setString(1, user.getPassword());
					pre.executeUpdate();				
					JDBCUtils.release(con,pre);				
	 }
	 /**
		 * 修改密码的检查
		 * @param conn
		 * @param user
		 * @return
		 * @throws Exception
		 */
		public  boolean loginCheckPassword(Connection conn,User user) throws Exception{
			String sql = "select * from t_user where userName = ? and password = ?";
			 PreparedStatement pre = null;
			 ResultSet rs = null;
				pre = conn.prepareStatement(sql);
				pre.setString(1, user.getNameName());
				pre.setString(2, user.getPassword());
				rs = pre.executeQuery();
				if(rs.next()) {
					return true;
				}
				JDBCUtils.release(conn, pre, rs);
			return false;
		}
		
		/**
		 * 充值，消费
		 * @param conn
		 * @return
		 * @throws Exception
		 */
		public  boolean updateUserMoney(Connection conn,Double money,Integer id,boolean flag) throws Exception{
			String sql = "update t_user set money=money + ? where id=?";
			if(flag==false) {
				sql = "update t_user set money=? where id=?";
			}
			 PreparedStatement pre = null;
			 ResultSet rs = null;
				pre = conn.prepareStatement(sql);
				pre.setDouble(1, money);
				pre.setInt(2, id);
				int i = pre.executeUpdate();
				if(i>0) {
					return true;
				}
				JDBCUtils.release(conn, pre, rs);
			return false;
		}
		/**
		 * 返回用户余额
		 * @param conn
		 * @return
		 * @throws Exception
		 */
		public  Double getUserserMoney(Connection conn,Integer id) throws Exception{
			String sql = "select * from t_user where id = ?";
			 PreparedStatement pre = null;
			 ResultSet rs = null;
				pre = conn.prepareStatement(sql);
				pre.setInt(1, id);
				rs = pre.executeQuery();
				if(rs.next()) {
					return rs.getDouble("money");
				}
				JDBCUtils.release(conn, pre, rs);
			return null;
		}
		/**
		 * 用户的结果集
		 * @param conn
		 * @return
		 */
		public ResultSet userList(Connection conn,String name) throws Exception{
			StringBuffer sql = new StringBuffer("select * from t_user u");
			if(StringUtils.isNotEmpty(name)) {
				sql.append(" where u.userName like '%"+name+"%'");
			}
			PreparedStatement pre = conn.prepareStatement(sql.toString());
			return pre.executeQuery();
		}
		/**
		 * 根据余额排序的结果集
		 * @param conn
		 * @return
		 */
		public ResultSet sortUserList(Connection conn,boolean flag) throws Exception{
			StringBuffer sql = new StringBuffer("select * from t_user u order by u.money");
			if(flag) {
				sql.append(" desc");
			}
			PreparedStatement pre = conn.prepareStatement(sql.toString());
			return pre.executeQuery();
		}
}
