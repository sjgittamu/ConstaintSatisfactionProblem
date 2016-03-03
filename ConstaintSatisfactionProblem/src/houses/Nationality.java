package houses;

public enum Nationality {
	ENGLISHMAN("ENGLISHMAN"), SPANISH("SPANISH"), UKRAINIAN("UKRAINIAN"), JAPANESE("JAPANESE"),NORWEGIAN("NORWEGIAN");
	
	String nationality;
	private Nationality(String nationality){
		this.nationality = nationality;
	}
	public String getValue(){
		return nationality;
	}
}
