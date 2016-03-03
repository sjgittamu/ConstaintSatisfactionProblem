package houses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BacktrackHouses {
	static int counter=0;
	boolean heur = false;
	public HousesAssgn backtrackingSearch( HousesAssgn assgn, boolean heuristic){
		//Assignment assgn = new Assignment();	//should it be initial assgn??
		heur = heuristic;
		backtrack(assgn);
		System.out.println();
		System.out.println("Number of iterations taken: "+counter);
		System.out.println();
		System.out.println("Final Assignment: ");
		for (Map.Entry<Integer, ArrayList<String>> entry : assgn.getAssgn().entrySet()) {
			System.out.println("House "+entry.getKey()+": "+entry.getValue());
		}
		
		return null;
	}

	private boolean backtrack(HousesAssgn assgn) {
		counter++;
		if(assgn.isComplete()){
			return true;
		}
		Integer var = 0;
		if(heur)
			var = selectUnassignedVariableMRV(assgn);		//what variable should be assigned next	.. use MRV here
		else
			var = selectUnassignedVariable(assgn);		
		List<ArrayList<String>> thingsAlreadyTaken = initialize();	//[[all taken colors], [all taken drinks], [..], ]
		List<ArrayList<String>> values = orderDomainValues(var,assgn, thingsAlreadyTaken);	//what order should values be tried
		int i=0;
		for (ArrayList<String> oneAttrList : values) {
			for (String value : oneAttrList) {
				if(value == null){
					continue;
				}
				if(assgn.isConsistent(value,var, i, thingsAlreadyTaken)){
					assgn.add(var, value, i);	
					if(backtrack(assgn))	
						return true;
					assgn.remove(var, value, i);
				}
			}
			i++;
		}
		return false;
	}

	private List<ArrayList<String>> orderDomainValues(Integer var, HousesAssgn assgn, List<ArrayList<String>> thingsAlreadyTaken) {
		Map<Integer, ArrayList<String>> assignment = assgn.getAssgn();
		boolean[] indexesToBeChecked = {false,false,false,false,false};
		for(int i=0;i<5;i++){
			if(assignment.get(var).get(i) == null){
				indexesToBeChecked[i] = true;
			}
		}
		List<ArrayList<String>> possibleFinalThings = initialize();

		int j;
		List<ArrayList<String>> possibleThings = getAlreadyTakenFields(thingsAlreadyTaken, assignment);
		for (int i=0; i<5; i++) {		//
			j=0;
			if(!indexesToBeChecked[i]){
				continue;
			}
			ArrayList<String> values = possibleThings.get(i);	//list of one attribute say color only
			for (String string : values) {
				if(!thingsAlreadyTaken.get(i).contains(string)){
					ArrayList<String> oneAttrList =possibleFinalThings.get(i);
					if(oneAttrList.size() == 0){
						oneAttrList = initializeStrings();
					}
					oneAttrList.set(j, string);
					possibleFinalThings.set(i,oneAttrList);
					j++;
				}	
			}

		}

		return possibleFinalThings;
	}

	private List<ArrayList<String>> getAlreadyTakenFields(List<ArrayList<String>> thingsAlreadyTaken,
			Map<Integer, ArrayList<String>> assignment) {
		List<ArrayList<String>> possibleThings = HousesConstraintSatisfactionProblem.values;
		int j=0;
		for (ArrayList<String> values : assignment.values()) {	//one values = [color, drink, food, nation, pet]
			if(values.size() >0){	//save unnessary looping
				for (int i=0; i< 5; i++) {				
					if(values.size()>i && values.get(i) != null){		//ith attribute, it can be any of the color, drink,food...
						ArrayList<String> particularAttributeList = thingsAlreadyTaken.get(i);		//it can be say.. all colors if i=0
						particularAttributeList.set(j, values.get(i));			//dunno if i need to set it again
					}
				}
			}
			j++;
		}
		return possibleThings;
	}


	private ArrayList<String> initializeStrings() {
		ArrayList<String> attrList = new ArrayList<String>(5);
		attrList.add(null);
		attrList.add(null);
		attrList.add(null);
		attrList.add(null);
		attrList.add(null);
		return attrList;
	}

	private List<ArrayList<String>> initialize() {
		List<ArrayList<String>> thingsAlreadyTaken = new ArrayList<ArrayList<String>>(5);
		ArrayList<String> a1 = initializeStrings();
		ArrayList<String> a2 = initializeStrings();
		ArrayList<String> a3 = initializeStrings();
		ArrayList<String> a4 = initializeStrings();
		ArrayList<String> a5 = initializeStrings();
		thingsAlreadyTaken.add(a1);
		thingsAlreadyTaken.add(a2);
		thingsAlreadyTaken.add(a3);
		thingsAlreadyTaken.add(a4);
		thingsAlreadyTaken.add(a5);
		return thingsAlreadyTaken;
	}

	private Integer selectUnassignedVariable(HousesAssgn assgn) {
		Map<Integer, ArrayList<String>> assignment = assgn.getAssgn();
		for (Map.Entry<Integer, ArrayList<String>> entry : assignment.entrySet()) {
			ArrayList<String> val = entry.getValue();
			if( val.size()== 0)
				return entry.getKey();
			else{
				for (String str : val) {
					if(str == null){
						return entry.getKey();
					}
				}
			}
		}
		return null;
	}

	private Integer selectUnassignedVariableMRV(HousesAssgn assgn) {
		Map<Integer, ArrayList<String>> assignment = assgn.getAssgn();
		int maxSize=Integer.MIN_VALUE;
		int varWithMaxAllotment = -1;
		for (Map.Entry<Integer, ArrayList<String>> entry : assignment.entrySet()) {
			int count =0;
			int var = entry.getKey();
			for (String str : entry.getValue()) {
				if(str == null){
					count++;
				}
			}
			if(count>maxSize){
				maxSize = count;
				varWithMaxAllotment = var;
			}
		}
		return varWithMaxAllotment;


		/*Random rn = new Random();
		int next = rn.nextInt(5)+1;
		for(String str :assignment.get(next)){
			if(str == null)
				return next;
			else
				next = rn.nextInt(5)+1;
		}
		return next;*/			 
		/*
		for (Map.Entry<Integer, ArrayList<String>> entry : assignment.entrySet()) {


			ArrayList<String> val = entry.getValue();
			if( val.size()== 0)
				return entry.getKey();
			else{
				for (String str : val) {
					if(str == null){
						return entry.getKey();
					}
				}
			}
		}
		return null;
		 */	
	}
}
