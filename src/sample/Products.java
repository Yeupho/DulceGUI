package sample;


public class Products {
	private int UID;
	private String name;
	private String Type;
	private double cost;
	private int OtherIngr;
	private int Size;
	private int Ice;
	private int Sugar;
	private int Temp;
	private int LoginID;
	private String login;
	private String password;
	public Products(int UID, String name, String Type, double cost ){
		this.UID = UID;
		this.name = name;
		this.Type = Type;
		this.cost = cost;
		
	}
	
	//OrderID
	public int getID(){
		return UID;
	}
	public void setID(int UID){
		this.UID=UID; 
	}
	//Smoothie Name
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	//Smoothie Type
	public String getType(){
		return Type;
	}
	public void setType(String Type){
		this.Type = Type;
	}
	
	//Smoothie Cost
	public double getCost(){
		return cost;
	}
	public void setCost(double Cost){
		this.cost = cost;
	}

}
