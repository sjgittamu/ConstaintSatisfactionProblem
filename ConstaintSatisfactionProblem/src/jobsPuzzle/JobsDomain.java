package jobsPuzzle;

public class JobsDomain {

	Enum<Jobs> value;
	
	public JobsDomain(Enum<Jobs> value) {
		super();
		this.value = value;
	}

	
	public Enum<Jobs> getDomain() {
		return value;
	}
	
	@Override
	public String toString() {
		return  value.name();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobsDomain other = (JobsDomain) obj;
		if (value == null) {
			if (other.value!= null)
				return false;
		}else if (other.value == null) {
			if (value!= null)
				return false;
		}else if (value.name() == null) {
			if (other.value.name() != null)
				return false;
		} else if (!this.value.name().equals(other.value.name()))
			return false;
		return true;
	}
	public boolean isConsistent(Assignment assgn, PeopleVariable var) {
		JobsConstaintSatisfactionProb_bk csp = new JobsConstaintSatisfactionProb_bk();
		return csp.areConstraintsSatisfied(assgn, this, var);
	}	
	
}
