

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
	public Products(String name, String Type, double cost){
		
		this.name = name;
		this.Type = Type;
		this.cost = cost;
	
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
