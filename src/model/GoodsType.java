package model;

public class GoodsType {
	
	private Integer id;
	private String type;
	private String desc;
	
	public GoodsType() {
		super();
	}

	public GoodsType(String type) {
		super();
		this.type = type;
	}
	public GoodsType(String type, String desc) {
		super();
		this.type = type;
		this.desc = desc;
	}
	
	public GoodsType(Integer id, String type, String desc) {
		super();
		this.id = id;
		this.type = type;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		
		return type;
	}
	

	
}
