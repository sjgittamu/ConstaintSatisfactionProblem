package houses;

public enum Color {
	RED("RED"), GREEN("GREEN"), IVORY("IVORY"), YELLOW("YELLOW"),BLUE("BLUE");
	
	String color;
	private Color(String color){
		this.color = color;
	}
	public String getValue(){
		return color;
	}
}
