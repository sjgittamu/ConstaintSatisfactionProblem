package houses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HousesAssgn {

	//house number vs list of 5 attributes in alphabetical order	0. color 1.drink 2. food 3. nationality 4. pet
	Map<Integer, ArrayList<String>> assgn;

	public Map<Integer, ArrayList<String>> getAssgn() {
		return assgn;
	}

	public void setAssgn(Map<Integer, ArrayList<String>> assgn) {
		this.assgn = assgn;
	}

	public HousesAssgn(Map<Integer, ArrayList<String>> assgn) {
		super();
		this.assgn = assgn;
	}

	public boolean isComplete() {
		for (ArrayList<String> value: assgn.values()) {
			for (String string : value) {
				if(string == null){
					return false;
				}
			}
		}
		return true;	
	}

	public void add(Integer var, String value, int index) {
		if(assgn.get(var).get(index) == null)
			assgn.get(var).set(index, value);
	}

	public void remove(Integer var, String value, int index) {
		ArrayList<String> valuesForVar = assgn.get(var);
		valuesForVar.set(index, null);
		assgn.put(var, valuesForVar);

	}

	public boolean isConsistent(String value, Integer variable, int index, List<ArrayList<String>> thingsAlreadyTaken) {
		return HousesConstraintSatisfactionProblem.areConstraintsSatisfied(this, value, variable, index, thingsAlreadyTaken);
	}

	@Override
	public String toString() {
		return "HousesAssgn [assgn=" + assgn + "]";
	}

}
