package houses;

public enum Food {
	HERSHEY("HERSHEY"), KITKAT("KITKAT"), SMARTIES("SMARTIES"), SNICKERS("SNICKERS"),MILKY_WAY("MILKY WAY");
	
	String food;
	private Food(String food){
		this.food = food;
	}
	public String getValue(){
		return food;
	}
}
