package model;
 
public class Goods {
	
	private Integer id;
	private String goodsName;
	private double price;
	private Integer goodsTypeId;
	private String goodsDesc;
	private Integer count;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getGoodsTypeId() {
		return goodsTypeId;
	}
	public void setGoodsTypeId(Integer goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Goods() {
		super();
	}
	
	public Goods(Integer id, String goodsName, Integer goodsTypeId) {
		super();
		this.id = id;
		this.goodsName = goodsName;
		this.goodsTypeId = goodsTypeId;
	}
	public Goods(String goodsName, double price, String goodsDesc, Integer count) {
		super();
		this.goodsName = goodsName;
		this.price = price;
		this.goodsDesc = goodsDesc;
		this.count = count;
	}
	public Goods(String goodsName, double price,int goodsTypeId, String goodsDesc, Integer count) {
		super();
		this.goodsName = goodsName;
		this.price = price;
		this.goodsTypeId = goodsTypeId;
		this.goodsDesc = goodsDesc;
		this.count = count;
	}
	
	public Goods(String goodsName, Integer goodsTypeId) {
		super();
		this.goodsName = goodsName;
		this.goodsTypeId = goodsTypeId;
	}
	@Override
	public String toString() {
		
		return this.goodsName;
	}
	
	
	
	
}