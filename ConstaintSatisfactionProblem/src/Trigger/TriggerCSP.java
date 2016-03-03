package Trigger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import houses.BacktrackHouses;
import houses.HousesAssgn;
import jobsPuzzle.Assignment;
import jobsPuzzle.Backtrack;
import jobsPuzzle.JobsDomain;
import jobsPuzzle.People;
import jobsPuzzle.PeopleVariable;

public class TriggerCSP {
	public static void main(String[] args) {

		int puzzle = Integer.parseInt(args[0]);	//1 for job and 2 for houses		
		boolean  heuristic = Boolean.parseBoolean(args[1]);	//true for MRV false for normal
		
		if(puzzle == 2){
			Map<Integer, ArrayList<String>> assgnHouses = new HashMap<Integer, ArrayList<String>>();
			assgnHouses.put(1, initializeStrings());
			assgnHouses.put(2, initializeStrings());
			assgnHouses.put(3, initializeStrings());
			assgnHouses.put(4, initializeStrings());
			assgnHouses.put(5, initializeStrings());

			HousesAssgn as= new HousesAssgn(assgnHouses);
			BacktrackHouses bt = new BacktrackHouses();
			bt.backtrackingSearch(as, heuristic);
		}
		
		if(puzzle == 1){
			Map<PeopleVariable, JobsDomain> assgn = new HashMap<PeopleVariable, JobsDomain>();
			assgn.put(new PeopleVariable(People.ROBERTA1), null);
			assgn.put(new PeopleVariable(People.ROBERTA2), null);
			
			assgn.put(new PeopleVariable(People.PETE2), null);
			assgn.put(new PeopleVariable(People.THELMA1), null);
			assgn.put(new PeopleVariable(People.THELMA2), null);
			assgn.put(new PeopleVariable(People.PETE1), null);
			
			assgn.put(new PeopleVariable(People.STEVE1), null);
			assgn.put(new PeopleVariable(People.STEVE2), null);
			
			Assignment as= new Assignment(assgn);
			Backtrack bt = new Backtrack();
			bt.backtrackingSearch(as, heuristic);
		}
	}

	static private ArrayList<String> initializeStrings() {
		ArrayList<String> attrList = new ArrayList<String>();
		attrList.add(null);
		attrList.add(null);
		attrList.add(null);
		attrList.add(null);
		attrList.add(null);
		return attrList;
	}
}
