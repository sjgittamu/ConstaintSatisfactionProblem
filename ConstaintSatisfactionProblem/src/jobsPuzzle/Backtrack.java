package jobsPuzzle;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Backtrack {
	static int counter=0;
	boolean heur = false;
	public Assignment backtrackingSearch( Assignment assgn, boolean heuristic){
		//Assignment assgn = new Assignment();	//should it be initial assgn??
		heur = heuristic;
		backtrack(assgn);
		System.out.println();
		System.out.println("Number of iterations: "+counter);
		System.out.println();
		System.out.println("Final Assignment: ");
		for (Map.Entry<PeopleVariable, JobsDomain> entry : assgn.getAssgn().entrySet()) {
			System.out.println(entry.getKey().getVariable().getValue()+": "+entry.getValue());
		}

		return null;
	}

	private boolean backtrack(Assignment assgn) {
		counter++;
		if(assgn.isComplete()){
			return true;
		}
		PeopleVariable var = null;
		if(heur)
			var = selectUnassignedVariableMRV(assgn);		//what variable should be assigned next	.. use MRV here
		else
			var = selectUnassignedVariable(assgn);		
		Set<JobsDomain> values = orderDomainValues(var,assgn);	//what order should values be tried
		for (JobsDomain value : values) {
			//for(Domain value : orderDomainValues(var,assgn, csp)){
			if(value.isConsistent(assgn,var)){
				assgn.add(var, value);	
				//System.out.println("Assigned "+var.toString()+" job: "+value.toString());
				if(backtrack(assgn))	//when did they even change??.. => value added in prev line
					return true;
				assgn.remove(var, value);
			}

		}
		return false;
	}

	private Set<JobsDomain> orderDomainValues(PeopleVariable var, Assignment assgn) {
		Map<PeopleVariable, JobsDomain> assignment = assgn.getAssgn();
		Set<JobsDomain> jobsAlreadyTaken = new HashSet<>();
		Set<JobsDomain> possibleJobs = JobsConstaintSatisfactionProb_bk.values;
		Set<JobsDomain> possibleFinalJobs = new HashSet<JobsDomain>(); 
		for (JobsDomain value : assignment.values()) {
			if(value != null)
				jobsAlreadyTaken.add(value);
		}
		for (JobsDomain job : possibleJobs) {
			if(!jobsAlreadyTaken.contains(job)){
				possibleFinalJobs.add(job);
			}
		}
		return possibleFinalJobs;
	}

	private PeopleVariable selectUnassignedVariable(Assignment assgn) {
		Map<PeopleVariable, JobsDomain> assignment = assgn.getAssgn();
		for (Map.Entry<PeopleVariable,JobsDomain> entry : assignment.entrySet()) {
			if(entry.getValue() == null || entry.getValue().value == null)
				return entry.getKey();
		}
		return null;
	}

	private PeopleVariable selectUnassignedVariableMRV(Assignment assgn) {
		Map<PeopleVariable, JobsDomain> assignment = assgn.getAssgn();
		int maxLeft = Integer.MIN_VALUE;
		PeopleVariable toChoose = null;
		for (Map.Entry<PeopleVariable,JobsDomain> entry : assignment.entrySet()) {
			if(entry.getValue() == null || entry.getValue().value == null){
				int jobsThisPersonCantDo = JobsConstaintSatisfactionProb.jobsNotPossible.get(entry.getKey().getVariable().getValue()).size();
				if(jobsThisPersonCantDo > maxLeft){
					maxLeft = jobsThisPersonCantDo;
					toChoose = entry.getKey();
				}
			}

		}
		return toChoose;
	}
}
