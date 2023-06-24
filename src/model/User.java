package model;

public class User {
	
	private Integer id;
	private String nameName;
	private String password;
	private Double money;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNameName() {
		return nameName;
	}
	public void setNameName(String nameName) {
		this.nameName = nameName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public User(String nameName, String password) {
		super();
		this.nameName = nameName;
		this.password = password;
	}
	public User(Integer id, Double money) {
		super();
		this.id = id;
		this.money = money;
	}
	
	

	
	
	
}
