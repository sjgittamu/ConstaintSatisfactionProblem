package jobsPuzzle;

import java.util.Map;

public class Assignment {

	Map<PeopleVariable, JobsDomain> assgn;
	
	public Map<PeopleVariable, JobsDomain> getAssgn() {
		return assgn;
	}

	public void setAssgn(Map<PeopleVariable, JobsDomain> assgn) {
		this.assgn = assgn;
	}

	public Assignment(Map<PeopleVariable, JobsDomain> assgn) {
		super();
		this.assgn = assgn;
	}

	public boolean isComplete() {
		for (JobsDomain value: assgn.values()) {
			if(value==null ){	//null check aint sufficient
				return false;
			}else if(value.value == null){
				return false;
			}
		}
		return true;
	}

	public void add(PeopleVariable var, JobsDomain value) {
		assgn.put(var, value);
	}
	
	public void remove(PeopleVariable var, JobsDomain value) {
		assgn.put(var, new JobsDomain(null));
		
	}

}
