package jobsPuzzle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JobsConstaintSatisfactionProb {
	Set<PeopleVariable> variables ;
	public static Set<JobsDomain> values = new HashSet<JobsDomain>();
	//key is string and not peroplevarible as roberta1 and roberta2 shud be same
	static Map<String, Set<JobsDomain>> jobsNotPossible = new HashMap<String, Set<JobsDomain>>();	//it keeps changing and is updated each time new info is there

	static{
		values.add(new JobsDomain(Jobs.ACTOR));
		values.add(new JobsDomain(Jobs.BOXER));
		values.add(new JobsDomain(Jobs.CHEF));
		values.add(new JobsDomain(Jobs.TEACHER));
		values.add(new JobsDomain(Jobs.CLERK));
		values.add(new JobsDomain(Jobs.POLICE_OFFICER));
		values.add(new JobsDomain(Jobs.GUARD));
		values.add(new JobsDomain(Jobs.NURSE));
		

		Set<JobsDomain> jobsRobertaCantDo = new HashSet<>();
		jobsRobertaCantDo.add(new JobsDomain(Jobs.BOXER));
		jobsRobertaCantDo.add(new JobsDomain(Jobs.CHEF));
		jobsRobertaCantDo.add(new JobsDomain(Jobs.POLICE_OFFICER));
		jobsRobertaCantDo.add(new JobsDomain(Jobs.NURSE));
		jobsRobertaCantDo.add(new JobsDomain(Jobs.CLERK));
		jobsRobertaCantDo.add(new JobsDomain(Jobs.ACTOR));
		jobsNotPossible.put(People.ROBERTA2.getValue(), jobsRobertaCantDo);	//roberta1 n 2 r same

		Set<JobsDomain> jobsThelmaCantDo = new HashSet<>();
		jobsThelmaCantDo.add(new JobsDomain(Jobs.NURSE));
		jobsThelmaCantDo.add(new JobsDomain(Jobs.CLERK));
		jobsThelmaCantDo.add(new JobsDomain(Jobs.ACTOR));
		jobsNotPossible.put(People.THELMA1.getValue(), jobsThelmaCantDo);	//thelma1 n 2 r same

		Set<JobsDomain> jobsSteveCantDo = new HashSet<>();
		jobsSteveCantDo.add(new JobsDomain(Jobs.CHEF));
		jobsNotPossible.put(People.STEVE1.getValue(), jobsSteveCantDo);	//thelma1 n 2 r same

		Set<JobsDomain> jobsPeteCantDo = new HashSet<>();
		jobsPeteCantDo.add(new JobsDomain(Jobs.CHEF));
		jobsPeteCantDo.add(new JobsDomain(Jobs.NURSE));
		jobsPeteCantDo.add(new JobsDomain(Jobs.TEACHER));
		jobsPeteCantDo.add(new JobsDomain(Jobs.POLICE_OFFICER));
		jobsNotPossible.put(People.PETE1.getValue(), jobsPeteCantDo);	//thelma1 n 2 r same
	}

	public boolean areConstraintsSatisfied(Assignment assgn, JobsDomain value, PeopleVariable var){
		//PeopleVariable var = null;//= new PeopleVariable();	//get both of these values from assgn
		JobsDomain domain = value;	//TODO
		Enum<People> person =var.getVariable();
		Enum<Jobs> job =domain.getDomain();

		//male or female?
		boolean isFemale = isFemale(person);

		if(isFemale){	//if female..cant be these
			if(job.equals(Jobs.NURSE))		//nurse is male
				return false;
			if(job.equals(Jobs.CLERK))		//clerk is male
				return false;
			if(job.equals(Jobs.ACTOR))		//actor is male
				return false;

		}else{			//if male..cant be these
			if(job.equals(Jobs.CHEF))	//as chef is female
				return false;
		}
		//generic
		if((person.equals(People.ROBERTA2) || person.equals(People.ROBERTA1)) && (job.equals(Jobs.BOXER) || job.equals(Jobs.CHEF) || job.equals(Jobs.POLICE_OFFICER)))		//Roberta is not a boxer
			return false;
		if((person.equals(People.PETE2) || person.equals(People.PETE1)) && (job.equals(Jobs.NURSE) || job.equals(Jobs.TEACHER) || job.equals(Jobs.POLICE_OFFICER)))		//Pete is not a boxer
			return false;
		
		
		if(assgn.getAssgn().containsValue(value))
			return false;
			
		return true;
	}

	private boolean isFemale(Enum<People> person) {
		if(person.equals(People.THELMA2) || person.equals(People.ROBERTA2) || person.equals(People.THELMA1) || person.equals(People.ROBERTA1) )
			return true;
		return false;
	}

	

}
