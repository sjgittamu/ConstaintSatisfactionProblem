package jobsPuzzle;

public enum Jobs {
	CHEF("CHEF"), GUARD("GUARD"), NURSE("NURSE"), CLERK("CLERK"), POLICE_OFFICER("POLICE OFFICER"), TEACHER("TEACHER"), ACTOR("ACTOR"), BOXER("BOXER"); 
	
	private String value;
	private Jobs(String value){
		this.value = value;
	}
	public String getValue(){
		return value;
	}
}
