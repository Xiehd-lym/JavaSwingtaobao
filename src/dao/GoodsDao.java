package dao;

import model.BuyGoods;
import model.Goods;
import utiles.JDBCUtils;
import utiles.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GoodsDao {
	/**
	 * ��Ʒ���
	 * @param conn
	 * @return
	 */
	public int addType(Connection conn,Goods goods) throws Exception{
		String sql = "insert into t_goods values(null,?,?,?,?,?)";
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setString(1, goods.getGoodsName());
		pre.setDouble(2, goods.getPrice());
		pre.setInt(3, goods.getGoodsTypeId());
		pre.setString(4,goods.getGoodsDesc());
		pre.setInt(5, goods.getCount());
		return pre.executeUpdate();
	}
	/**
	 * ��Ʒ�Ľ����
	 * @param conn
	 * @return
	 */
	public ResultSet goodsList(Connection conn,Goods goods) throws Exception{
		StringBuffer sql = new StringBuffer("select * from t_goods g,t_goodstype gt where g.goodsTypeId=gt.id");
		if(StringUtils.isNotEmpty(goods.getGoodsName())) {
			sql.append(" and g.goodsName like '%"+goods.getGoodsName()+"%'");
		}
		if(StringUtils.isNotEmpty(goods.getId())) {
			sql.append(" and g.id like '%"+goods.getId()+"%'");
		}
		
		if(StringUtils.isTrue(goods.getGoodsTypeId())) {
			sql.append(" and gt.id like '%"+goods.getGoodsTypeId()+"%'");
		}
		
		PreparedStatement pre = conn.prepareStatement(sql.toString());
		return pre.executeQuery();
	}
	/**
	 * ��Ʒ����
	 * @param conn
	 * @return
	 */
	public int updateGoods(Connection conn,Goods goods) throws Exception{
		String sql = "update t_goods set price=?,goodsDesc=?,count=? where goodsName=?";
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setDouble(1,goods.getPrice());
		pre.setString(2, goods.getGoodsDesc());
		pre.setInt(3, goods.getCount());
		pre.setString(4, goods.getGoodsName());
		int i =  pre.executeUpdate();
		JDBCUtils.release(conn,pre);
		return i;
	}
	/**
	 * ��Ʒɾ��
	 * @param conn
	 * @return
	 */
	public int deleteGoods(Connection conn,String goodsName) throws Exception{
		
		String sql = "delete from t_goods where goodsName=?";
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setString(1, goodsName);
		int i = pre.executeUpdate();
		JDBCUtils.release(conn,pre);
		return i;
	}
	/**
	 * ���ҵ�����Ʒ(Ŀ�ģ������)
	 * @param conn
	 * @return
	 */
	public ResultSet oneGoods(Connection conn,Integer goodsId) throws Exception{
		StringBuffer sql = new StringBuffer("select * from t_goods where id=?");		
		PreparedStatement pre = conn.prepareStatement(sql.toString());
		pre.setInt(1, goodsId);
		return pre.executeQuery();
	}
	/**
	 * �û��޸���Ʒ����Ʒ��������
	 * @param conn
	 * @return
	 */
	public int updateOneGoods(Connection conn,BuyGoods buygoods,boolean flag,Integer i) throws Exception{
		String sql = "update t_goods set count=count+? where id=?";
		if(flag) {
			sql = sql.replace('+', '-');
		}
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setInt(1,i);
		pre.setInt(2, buygoods.getGoodsId());
		int j =  pre.executeUpdate();
		
		return j;
	}
}
