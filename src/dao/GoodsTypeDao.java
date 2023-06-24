package dao;

import model.GoodsType;
import utiles.JDBCUtils;
import utiles.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GoodsTypeDao {
	/**
	 * 商品类别添加
	 * @param conn
	 * @return
	 */
	public int addType(Connection conn,GoodsType type) throws Exception{
		String sql = "insert into t_goodsType values(null,?,?)";
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setString(1, type.getType());
		pre.setString(2,type.getDesc());
		return pre.executeUpdate();
	}
	
	/**
	 * 商品类别的结果集
	 * @param conn
	 * @return
	 */
	public ResultSet typeList(Connection conn,GoodsType type) throws Exception{
		StringBuffer sql = new StringBuffer("select * from t_goodstype");
		if(StringUtils.isNotEmpty(type.getType())&&type.getId()!=-1) {
			sql.append(" where goodsTypeName like '%"+type.getType()+"%'");
		}
		PreparedStatement pre = conn.prepareStatement(sql.toString());
		return pre.executeQuery();
	}
	
	/**
	 * 商品类别删除
	 * @param conn
	 * @return
	 */
	public int deleteType(Connection conn,Integer id) throws Exception{
		
		String sql = "delete from t_goodstype where id=?";
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setInt(1, id);
		int i = pre.executeUpdate();
		JDBCUtils.release(conn,pre);
		return i;
	}
	
	/**
	 * 商品类别更新
	 * @param conn
	 * @return
	 */
	public int updateType(Connection conn,GoodsType type) throws Exception{
		String sql = "update t_goodstype set goodsTypeName=?,goodsTypeDesc=? where id=?";
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setString(1,type.getType() );
		pre.setString(2, type.getDesc());
		pre.setInt(3, type.getId());
		int i =  pre.executeUpdate();
		JDBCUtils.release(conn,pre);
		return i;
	}
	
}
