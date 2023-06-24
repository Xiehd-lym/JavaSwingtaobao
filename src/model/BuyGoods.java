package model;

public class BuyGoods {
	
	private Integer useId;
	private Integer goodsId;
	private String goodsName;
	private String goodsType;
	private Double price;
	private Integer count;
	private String goodsDesc;
	
	
	public Integer getUseId() {
		return useId;
	}
	public void setUseId(Integer useId) {
		this.useId = useId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	
	public BuyGoods() {
		super();
	}
	public BuyGoods(Integer useId,Integer goodsId, String goodsName, String goodsType, Double price, Integer count, String goodsDesc) {
		super();
		this.useId = useId;
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsType = goodsType;
		this.price = price;
		this.count = count;
		this.goodsDesc = goodsDesc;
	}
	
	public BuyGoods(Integer useId,Integer goodsId,String goodsName, Double price,Integer count , String goodsDesc){
		super();
		this.useId = useId;
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.price = price;
		this.count = count;
		this.goodsDesc = goodsDesc;
	}
	
}
