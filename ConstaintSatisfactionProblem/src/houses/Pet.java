package houses;

public enum Pet {
	DOG("DOG"), FOX("FOX"), SNAIL("SNAIL"), HORSE("HORSE"),ZEBRA("ZEBRA");
	
	String pet;
	private Pet(String pet){
		this.pet = pet;
	}
	public String getValue(){
		return pet;
	}
}
