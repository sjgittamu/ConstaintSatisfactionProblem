package houses;

public enum Drink {
	TEA("TEA"), COFFEE("COFFEE"), MILK("MILK"), ORANGE_JUICE("ORANGE JUICE"),WATER("WATER");
	
	String drink;
	private Drink(String drink){
		this.drink = drink;
	}
	public String getValue(){
		return drink;
	}
}
