package jobsPuzzle;

public enum People {
	ROBERTA2("ROBERTA"), THELMA2("THELMA"), STEVE2("STEVE"), PETE2("PETE"),ROBERTA1("ROBERTA"), THELMA1("THELMA"), STEVE1("STEVE"), PETE1("PETE");
	
	private String value;
	private People(String value){
		this.value = value;
	}
	public String getValue(){
		return value;
	}
}
