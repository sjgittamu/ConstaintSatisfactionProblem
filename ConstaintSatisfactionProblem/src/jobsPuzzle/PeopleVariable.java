package jobsPuzzle;

public class PeopleVariable{

	People variable;
	
	public PeopleVariable(People variable) {
		super();
		this.variable = variable;
	}

	public People getVariable() {
		return variable;
	}

	@Override
	public String toString() {
		return  variable.name() ;
	}

}
