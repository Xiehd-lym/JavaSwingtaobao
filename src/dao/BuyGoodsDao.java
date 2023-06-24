package dao;

import model.BuyGoods;
import utiles.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;



public class BuyGoodsDao {
	
	private GoodsDao goodsDao = new GoodsDao();
	/**
	 * ��Ʒ�������
	 * @param conn
	 * @return ��Ʒ����count
	 */
	public int checkGoodsCount(Connection conn,BuyGoods buygoods) throws Exception{
		String sql = "select * from t_goods where id=?";
		 Connection con = JDBCUtils.getConnection();
		ResultSet rs = null;
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setInt(1, buygoods.getGoodsId());
		 rs = pre.executeQuery();
		 int count = -1;
		 while(rs.next()) {
			  count = rs.getInt("count");
		 }
		JDBCUtils.release(con,pre);
		return count;
	}
	/**
	 * ��Ʒ��������(������Ʒʱ���Զ�-1)
	 * @param conn
	 * @return ��Ʒ����count
	 */
	public int updateGoodsCount(Connection conn,BuyGoods buygoods) throws Exception{
		String sql = "update t_goods set count=? where id=?";
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setInt(1, buygoods.getCount()-1);
		pre.setInt(2, buygoods.getGoodsId());
		int i = pre.executeUpdate();
		JDBCUtils.release(conn,pre);
		return i;
	}
	/**
	 * ��Ʒ��������ﳵ
	 * @param conn
	 * @return ��Ʒ����count
	 */
	public int insertGoods(Connection conn,BuyGoods buygoods) throws Exception{
		PreparedStatement pre = null;
		int i = -1;
		if(checkGoods(conn, buygoods)) {
			String sql = "update t_shoping set count=count+1 where goodsid=? and useid = ?";
			pre = conn.prepareStatement(sql);
			pre.setInt(1, buygoods.getGoodsId());
			pre.setInt(2, buygoods.getUseId());
			 i = pre.executeUpdate();
			JDBCUtils.release(conn,pre);
			return i;
		}else {
			String sql = "insert into t_shoping (useid,goodsid,count) values(?,?,1)";
			pre = conn.prepareStatement(sql);
			pre.setInt(1,buygoods.getUseId());
			pre.setInt(2, buygoods.getGoodsId());
			 i = pre.executeUpdate();
			JDBCUtils.release(conn,pre);
			return i;
		}
		
	}
	/**
	 * ��鹺�ﳵ�Ƿ����и���Ʒ
	 * @param conn
	 * @return ��Ʒ����count
	 */
	public boolean checkGoods(Connection conn,BuyGoods buygoods) throws Exception{
		String sql = "select * from t_shoping where goodsid=? and useid=?";
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setInt(1, buygoods.getGoodsId());
		pre.setInt(2, buygoods.getUseId());
		ResultSet rs = pre.executeQuery();
		while(rs.next()) {
			return true;
		}
		return false;
	}
	/**
	 * ������Ʒ(ɾ����Ʒ)
	 * @param conn
	 * @return ��Ʒ����count
	 */
	public int deleteGoods(Connection conn,BuyGoods buygoods) throws Exception{
		PreparedStatement pre = null;
		PreparedStatement pre1 = null;
		PreparedStatement pre2 = null;
			String sql = "delete from t_shoping  where goodsid=? and useid = ?";
			String sql1 = "insert into t_shophistory (useid,goodsid,btime,count) values(?,?,?,?)";
			String sql2 = "insert into t_usershophistory (useid,goodsid,btime,count) values(?,?,?,?)";
			pre = conn.prepareStatement(sql);
			pre1 = conn.prepareStatement(sql1);
			pre2 = conn.prepareStatement(sql2);
			
			pre.setInt(1, buygoods.getGoodsId());
			pre.setInt(2, buygoods.getUseId());
			
			pre1.setInt(1, buygoods.getUseId());
			pre1.setInt(2, buygoods.getGoodsId());
			pre2.setInt(1, buygoods.getUseId());
			pre2.setInt(2, buygoods.getGoodsId());
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String time = dateFormat.format(date);
			pre1.setString(3, time);
			pre2.setString(3, time);
			pre1.setInt(4, buygoods.getCount());
			pre2.setInt(4, buygoods.getCount());
			pre1.executeUpdate();
			pre2.executeUpdate();
			
			int i = pre.executeUpdate();
			JDBCUtils.release(conn,pre1);
			return i;
	}
	/**
	 * ��չ��ﳵ
	 * @param conn
	 * @return ��Ʒ����count
	 */
	public int deleteGoodsAll(Connection conn,Integer useid) throws Exception{
		PreparedStatement pre = null;
			String sql = "delete from t_shoping  where  useid = ?";
			pre = conn.prepareStatement(sql);
			pre.setInt(1, useid);
			int i = pre.executeUpdate();
			JDBCUtils.release(conn,pre);
			return i;
	}
	/**
	 * �޸���Ʒ����
	 * @param conn
	 * @return ��Ʒ����count
	 */
	public int updateOneGoodsCount(Connection conn,BuyGoods buygoods, Integer useid,boolean flag,Integer i) throws Exception{
		String sql = "update t_shoping set count=? where useid=? and goodsid=?";
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setInt(1, buygoods.getCount());
		pre.setInt(2,useid);
		pre.setInt(3, buygoods.getGoodsId());
		int j = pre.executeUpdate();
		goodsDao.updateOneGoods(conn, buygoods,flag, i);
		JDBCUtils.release(conn,pre);
		return j;
	}
	/**
	 * ɾ����Ʒ
	 * @param conn
	 * @return ��Ʒ����count
	 */
	public int deleteGoodsButton(Connection conn,BuyGoods buygoods) throws Exception{
		PreparedStatement pre = null;
			String sql = "delete from t_shoping  where goodsid=? and useid = ?";
			goodsDao.updateOneGoods(conn, buygoods,false, buygoods.getCount());
			pre = conn.prepareStatement(sql);
			pre.setInt(1, buygoods.getGoodsId());
			pre.setInt(2, buygoods.getUseId());
			int i = pre.executeUpdate();
			JDBCUtils.release(conn,pre);
			return i;
	}
	/**
	 * ��չ��ﳵ������ʱд�뵽��ʷ��¼��
	 * @param conn
	 * @return ��Ʒ����count
	 */
	public Double historyShoping(Connection conn,Integer useid) throws Exception{
		
		PreparedStatement pre1 = null;
		PreparedStatement pre2 = null;
		ResultSet goodsall = goodsAll(conn,useid);
		Double i = null;
			String sql1 = "insert into t_shophistory (useid,goodsid,btime,count) values(?,?,?,?)";
			String sql2 = "insert into t_usershophistory (useid,goodsid,btime,count) values(?,?,?,?)";
			pre1 = conn.prepareStatement(sql1);
			pre2 = conn.prepareStatement(sql2);
			while(goodsall.next()) {
				pre1.setInt(1, useid);
				pre1.setInt(2, goodsall.getInt("id"));
				Date date = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				String time = dateFormat.format(date);
				pre1.setString(3, time);
				pre1.setInt(4, goodsall.getInt("count"));
				
				pre2.setInt(1, useid);
				pre2.setInt(2, goodsall.getInt("id"));
				pre2.setString(3, time);
				pre2.setInt(4, goodsall.getInt("count"));
				
				pre1.addBatch();
				pre2.addBatch();
			}
			pre1.executeBatch();
			pre2.executeBatch();
			return i;
	}
	/**
	 * ������Ǯ��
	 * @param conn
	 * @return ��Ʒ����count
	 */
	public Double moneyGoodsAll(Connection conn,Integer useid) throws Exception{
		PreparedStatement pre = null;
		ResultSet rs = null;
		Double i = null;
		String sql = "select sum(g.price*s.count) as sum from t_goods g,t_shoping s where g.id = s.goodsid and s.useid=?";
			pre = conn.prepareStatement(sql);
			pre.setInt(1, useid);
		 rs = pre.executeQuery();
		 if(rs.next()) {
			  i = rs.getDouble(1); 
		 }
			JDBCUtils.release(conn,pre,rs);
			return i;
	}
	/**
	 * ����ȫ����Ʒ�Ľ����
	 * @param conn
	 * @return ��Ʒ����count
	 */
	public ResultSet goodsAll(Connection conn,Integer useid) throws Exception{
		PreparedStatement pre = null;
		ResultSet rs = null;
		Double i = null;
			String sql = "select g.id,g.goodsName,g.price*s.count as sum,s.count,g.goodsName from t_goods g,t_shoping s where g.id = s.goodsid and s.useid=?";
			pre = conn.prepareStatement(sql);
			pre.setInt(1, useid);
			 rs = pre.executeQuery();
			return rs;
	}
	/**
	 * ��չ��ﳵ(������)
	 * @param conn
	 * @return ��Ʒ����count
	 */
	public int deleteGoodsAll(Connection conn,BuyGoods buygoods,Integer useid) throws Exception{
		int i = -1;
		ResultSet goodsall = goodsAll(conn,useid);
		String sql = "update t_goods set count=count+? where id=?";
		PreparedStatement pre = conn.prepareStatement(sql);
		while(goodsall.next()) {
			pre.setInt(1,goodsall.getInt("count"));
			pre.setInt(2, goodsall.getInt("id"));
			pre.addBatch();
		}
		pre.executeBatch();
		i = deleteGoodsAll(JDBCUtils.getConnection(),useid);
		return i;
	}
	
}
