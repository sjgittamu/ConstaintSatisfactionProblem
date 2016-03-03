package houses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HousesConstraintSatisfactionProblem {

	Set<Integer> variables ;
	public static List<ArrayList<String>> values = new ArrayList<ArrayList<String>>();

	static{
		ArrayList<String> colors = new ArrayList<String>();
		colors.add(Color.YELLOW.getValue());
		colors.add(Color.BLUE.getValue());
		colors.add(Color.RED.getValue());
		colors.add(Color.IVORY.getValue());
		colors.add(Color.GREEN.getValue());
		values.add(colors);


		ArrayList<String> drinks = new ArrayList<String>();
		drinks.add(Drink.WATER.getValue());
		drinks.add(Drink.COFFEE.getValue());
		drinks.add(Drink.MILK.getValue());
		drinks.add(Drink.TEA.getValue());
		drinks.add(Drink.ORANGE_JUICE.getValue());
		values.add(drinks);

		ArrayList<String> foods = new ArrayList<String>();
		foods.add(Food.KITKAT.getValue());
		foods.add(Food.HERSHEY.getValue());
		foods.add(Food.MILKY_WAY.getValue());
		foods.add(Food.SMARTIES.getValue());
		foods.add(Food.SNICKERS.getValue());
		values.add(foods);

		ArrayList<String> nationalities = new ArrayList<String>();
		nationalities.add(Nationality.NORWEGIAN.getValue());
		nationalities.add(Nationality.UKRAINIAN.getValue());
		nationalities.add(Nationality.ENGLISHMAN.getValue());
		nationalities.add(Nationality.SPANISH.getValue());
		nationalities.add(Nationality.JAPANESE.getValue());
		values.add(nationalities);

		ArrayList<String> pets = new ArrayList<String>();
		pets.add(Pet.DOG.getValue());
		pets.add(Pet.FOX.getValue());
		pets.add(Pet.SNAIL.getValue());
		pets.add(Pet.HORSE.getValue());
		pets.add(Pet.ZEBRA.getValue());
		values.add(pets);
	}

	public static boolean areConstraintsSatisfied(HousesAssgn assgn, String value, Integer variable, int index, List<ArrayList<String>> thingsAlreadyTaken  ){
		ArrayList<String> currValues = assgn.getAssgn().get(variable);
		//0. color 1.drink 2. food 3. nationality 4. pet

		/*if(value.equals(Color.RED.getValue()) || value.equals(Nationality.ENGLISHMAN.getValue()))
			System.out.println("yes");*/
		//constraints: 7 done only once, 
		//do separately for index

		if(index == 0){
			//put all color constraints
			//constraint 1
			if((value.equals(Color.RED.getValue()))) {
				if(!(variable == 3 || variable == 5)){
					return false;
				}
				if(currValues.get(3) == null){
					if(thingsAlreadyTaken.get(3).contains(Nationality.ENGLISHMAN.getValue())) 
						return false;
				}else{
					if(!currValues.get(3).equals(Nationality.ENGLISHMAN.getValue()))
						return false;

				}
			}
			//constraint 6
			if((value.equals(Color.YELLOW.getValue()))) {
				if(currValues.get(2) == null){
					if(thingsAlreadyTaken.get(2).contains(Food.KITKAT.getValue())) 
						return false;
				}else{
					if(!currValues.get(2).equals(Food.KITKAT.getValue()))
						return false;

				}
			}
			//constraint 4
			if(value.equals(Color.GREEN.getValue())){
				if(!(variable == 4 || variable == 5)){
					return false;
				}
				boolean takenAlready = thingsAlreadyTaken.get(0).contains(Color.IVORY.getValue());
				if(variable > 1 && variable < 5){
					if(!(assgn.getAssgn().get(variable-1).get(0) == null || assgn.getAssgn().get(variable-1).get(0).equals(Color.IVORY.getValue()))
							&& (assgn.getAssgn().get(variable+1).get(0) == null || assgn.getAssgn().get(variable+1).get(0).equals(Color.IVORY.getValue()))){
						if(takenAlready){
							return false;
						}
					}
					
				}else if( variable ==1){
					//as blue is 2
					return false;
				}else if (variable == 5){
					if(!(assgn.getAssgn().get(variable-1).get(0) == null || assgn.getAssgn().get(variable-1).get(0).equals(Color.IVORY.getValue()))){
						if(takenAlready){
							return false;
						}
					}
				}
			}
/*			if((value.equals(Color.GREEN.getValue())) && 
					!((variable > 1 && ((assgn.getAssgn().get(variable-1).get(0) == null)) || ( variable > 1 && assgn.getAssgn().get(variable-1).get(0).equals(Color.IVORY.getValue()))) ||
							(variable < 5 && ((assgn.getAssgn().get(variable+1).get(0) == null)) || ( variable < 5 && assgn.getAssgn().get(variable+1).get(0).equals(Color.IVORY.getValue()))))){	
				return false;
			}

*/

			//constraint 4
			if(value.equals(Color.IVORY.getValue())){
				if(!(variable == 3 || variable == 4)){
					return false;
				}
				boolean takenAlready = thingsAlreadyTaken.get(0).contains(Color.GREEN.getValue());
				if(variable > 1 && variable < 5){
					if(!(assgn.getAssgn().get(variable-1).get(0) == null || assgn.getAssgn().get(variable-1).get(0).equals(Color.GREEN.getValue()))
							&& (assgn.getAssgn().get(variable+1).get(0) == null || assgn.getAssgn().get(variable+1).get(0).equals(Color.GREEN.getValue()))){
						if(takenAlready){
							return false;
						}
					}
					
				}else if( variable ==1){
					//blue is 2
					return false;
				}else if (variable == 5){
					if(!(assgn.getAssgn().get(variable-1).get(0) == null || assgn.getAssgn().get(variable-1).get(0).equals(Color.GREEN.getValue()))){
						if(takenAlready){
							return false;
						}
					}
				}
			}
			
			//constraint 7
			if((value.equals(Color.BLUE.getValue())) && (variable != 2)){	
				return false;
			}
			//constraint 13
			if((value.equals(Color.GREEN.getValue()))) {
				if(currValues.get(1) == null){
					if(thingsAlreadyTaken.get(1).contains(Drink.COFFEE.getValue())) 
						return false;
				}else{
					if(!currValues.get(1).equals(Drink.COFFEE.getValue()))
						return false;

				}
			}
		}else if(index ==1){
			//drink
			//constraint 9
			if((value.equals(Drink.ORANGE_JUICE.getValue()))) {
				if(currValues.get(2) == null){
					if(thingsAlreadyTaken.get(2).contains(Food.SNICKERS.getValue())) 
						return false;
				}else{
					if(!currValues.get(2).equals(Food.SNICKERS.getValue()))
						return false;

				}
			}
			//constraint 10
			if((value.equals(Drink.TEA.getValue()))) {
				if(currValues.get(3) == null){
					if(thingsAlreadyTaken.get(3).contains(Nationality.UKRAINIAN.getValue())) 
						return false;
				}else{
					if(!currValues.get(3).equals(Nationality.UKRAINIAN.getValue()))
						return false;

				}
			}
			//constraint 13
			if((value.equals(Drink.COFFEE.getValue()))) {
				if(currValues.get(0) == null){
					if(thingsAlreadyTaken.get(0).contains(Color.GREEN.getValue())) 
						return false;
				}else{
					if(!currValues.get(0).equals(Color.GREEN.getValue()))
						return false;

				}
			}
			//constraint 14
			if((value.equals(Drink.MILK.getValue())) && !(variable == 3)){	
				return false;
			}
		}else if(index ==2){
			//food
			//constraint 6
			if((value.equals(Food.KITKAT.getValue()))) {
				if(currValues.get(0) == null){
					if(thingsAlreadyTaken.get(0).contains(Color.YELLOW.getValue())) 
						return false;
				}else{
					if(!currValues.get(0).equals(Color.YELLOW.getValue()))
						return false;

				}
			}

			//constraint 5
			if(value.equals(Food.HERSHEY.getValue())){
				boolean takenAlready = thingsAlreadyTaken.get(4).contains(Pet.FOX.getValue());
				if(variable > 1 && variable < 5){
					if(!(assgn.getAssgn().get(variable-1).get(4) == null || assgn.getAssgn().get(variable-1).get(4).equals(Pet.FOX.getValue()))
							&& (assgn.getAssgn().get(variable+1).get(4) == null || assgn.getAssgn().get(variable+1).get(4).equals(Pet.FOX.getValue()))){
						if(takenAlready){
							return false;
						}
					}
					
				}else if( variable ==1){
					if(!(assgn.getAssgn().get(variable+1).get(4) == null || assgn.getAssgn().get(variable+1).get(4).equals(Pet.FOX.getValue()))){
						if(takenAlready){
							return false;
						}
					}
				}else if (variable == 5){
					if(!(assgn.getAssgn().get(variable-1).get(4) == null || assgn.getAssgn().get(variable-1).get(4).equals(Pet.FOX.getValue()))){
						if(takenAlready){
							return false;
						}
					}
				}
			}
			
			/*if((value.equals(Food.HERSHEY.getValue())) && 
					( !((variable < 5 && (assgn.getAssgn().get(variable+1).get(4) == null) || ( variable < 5 && assgn.getAssgn().get(variable+1).get(4).equals(Pet.FOX.getValue()))  ))
							|| (!((variable >1 && (assgn.getAssgn().get(variable-1).get(4) == null) || ( variable >1 && assgn.getAssgn().get(variable-1).get(4).equals(Pet.FOX.getValue()))  ))))
					){	
				return false;
			}*/
			
			//constraint 8
			if((value.equals(Food.SMARTIES.getValue()))) {
				if(currValues.get(4) == null){
					if(thingsAlreadyTaken.get(4).contains(Pet.SNAIL.getValue())) 
						return false;
				}else{
					if(!currValues.get(4).equals(Pet.SNAIL.getValue()))
						return false;

				}
			}

			//constraint 9
			if((value.equals(Food.SNICKERS.getValue()))) {
				if(currValues.get(1) == null){
					if(thingsAlreadyTaken.get(1).contains(Drink.ORANGE_JUICE.getValue())) 
						return false;
				}else{
					if(!currValues.get(1).equals(Drink.ORANGE_JUICE.getValue()))
						return false;

				}
			}
			//constraint 11
			if((value.equals(Food.MILKY_WAY.getValue()))) {
				if(currValues.get(3) == null){
					if(thingsAlreadyTaken.get(3).contains(Nationality.JAPANESE.getValue())) 
						return false;
				}else{
					if(!currValues.get(3).equals(Nationality.JAPANESE.getValue()))
						return false;

				}
			}
			//constraint 12
			if(value.equals(Food.KITKAT.getValue())){
				boolean takenAlready = thingsAlreadyTaken.get(4).contains(Pet.HORSE.getValue());
				if(variable > 1 && variable < 5){
					if(!(assgn.getAssgn().get(variable-1).get(4) == null || assgn.getAssgn().get(variable-1).get(4).equals(Pet.HORSE.getValue()))
							&& (assgn.getAssgn().get(variable+1).get(4) == null || assgn.getAssgn().get(variable+1).get(4).equals(Pet.HORSE.getValue()))){
						if(takenAlready){
							return false;
						}
					}
					
				}else if( variable ==1){
					if(!(assgn.getAssgn().get(variable+1).get(4) == null || assgn.getAssgn().get(variable+1).get(4).equals(Pet.HORSE.getValue()))){
						if(takenAlready){
							return false;
						}
					}
				}else if (variable == 5){
					if(!(assgn.getAssgn().get(variable-1).get(4) == null || assgn.getAssgn().get(variable-1).get(4).equals(Pet.HORSE.getValue()))){
						if(takenAlready){
							return false;
						}
					}
				}
			}
			
			/*if((value.equals(Food.KITKAT.getValue())) && 
					( !((variable < 5 && (assgn.getAssgn().get(variable+1).get(4) == null) || ( variable < 5 && assgn.getAssgn().get(variable+1).get(4).equals(Pet.HORSE.getValue())) ))
							|| (!((variable >1 && (assgn.getAssgn().get(variable-1).get(4) == null) || ( variable >1 && assgn.getAssgn().get(variable-1).get(4).equals(Pet.HORSE.getValue())) ))))
					){	
				return false;
			}*/
		}else if(index ==3){
			//nationality
			//constraint 1
			if((value.equals(Nationality.ENGLISHMAN.getValue()))) {
				if(currValues.get(0) == null){
					if(thingsAlreadyTaken.get(0).contains(Color.RED.getValue())) 
						return false;
				}else{
					if(!currValues.get(0).equals(Color.RED.getValue()))
						return false;

				}
			}
			//constraint 3
			if((value.equals(Nationality.NORWEGIAN.getValue())) && (variable != 1)){	//only one is satisfied
				return false;
			}
			//constraint 2
			if((value.equals(Nationality.SPANISH.getValue()))) {
				if(currValues.get(4) == null){
					if(thingsAlreadyTaken.get(4).contains(Pet.DOG.getValue())) 
						return false;
				}else{
					if(!currValues.get(4).equals(Pet.DOG.getValue()))
						return false;

				}
			}
			//constraint 10
			if((value.equals(Nationality.UKRAINIAN.getValue()))) {
				if(currValues.get(1) == null){
					if(thingsAlreadyTaken.get(1).contains(Drink.TEA.getValue())) 
						return false;
				}else{
					if(!currValues.get(1).equals(Drink.TEA.getValue()))
						return false;

				}
			}
			//constraint 11
			if((value.equals(Nationality.JAPANESE.getValue()))) {
				if(currValues.get(2) == null){
					if(thingsAlreadyTaken.get(2).contains(Food.MILKY_WAY.getValue())) 
						return false;
				}else{
					if(!currValues.get(2).equals(Food.MILKY_WAY.getValue()))
						return false;
				}
			}

		}else if(index ==4){
			//pet
			//constraint 2
			if((value.equals(Pet.DOG.getValue()))) {
				if(currValues.get(3) == null){
					if(thingsAlreadyTaken.get(3).contains(Nationality.SPANISH.getValue())) 
						return false;
				}else{
					if(!currValues.get(3).equals(Nationality.SPANISH.getValue()))
						return false;
				}
			}
			//constraint 5
			if(value.equals(Pet.FOX.getValue())){
				boolean takenAlready = thingsAlreadyTaken.get(2).contains(Food.HERSHEY.getValue());
				if(variable > 1 && variable < 5){
					if(!(assgn.getAssgn().get(variable-1).get(2) == null || assgn.getAssgn().get(variable-1).get(2).equals(Food.HERSHEY.getValue()))
							&& (assgn.getAssgn().get(variable+1).get(2) == null || assgn.getAssgn().get(variable+1).get(2).equals(Food.HERSHEY.getValue()))){
						if(takenAlready){
							return false;
						}
					}
					
				}else if( variable ==1){
					if(!(assgn.getAssgn().get(variable+1).get(2) == null || assgn.getAssgn().get(variable+1).get(2).equals(Food.HERSHEY.getValue()))){
						if(takenAlready){
							return false;
						}
					}
				}else if (variable == 5){
					if(!(assgn.getAssgn().get(variable-1).get(2) == null || assgn.getAssgn().get(variable-1).get(2).equals(Food.HERSHEY.getValue()))){
						if(takenAlready){
							return false;
						}
					}
				}
			}
			/*if((value.equals(Pet.FOX.getValue())) && 
					( !( (variable < 5 && (assgn.getAssgn().get(variable+1).get(2) == null) || ( variable < 5 && assgn.getAssgn().get(variable+1).get(2).equals(Food.HERSHEY.getValue())) ))
							|| (!((variable >1 && (assgn.getAssgn().get(variable-1).get(2) == null) || ( variable >1 && assgn.getAssgn().get(variable-1).get(2).equals(Food.HERSHEY.getValue()))  ))))
					){	
				return false;
			}*/
			//constraint 8
			if((value.equals(Pet.SNAIL.getValue())) && !((currValues.get(2) == null) || (currValues.get(2).equals(Food.SMARTIES.getValue())) )){	
				return false;
			}
			if((value.equals(Pet.SNAIL.getValue()))) {
				if(currValues.get(2) == null){
					if(thingsAlreadyTaken.get(2).contains(Food.SMARTIES.getValue())) 
						return false;
				}else{
					if(!currValues.get(2).equals(Food.SMARTIES.getValue()))
						return false;
				}
			}
			//constraint 12
			if(value.equals(Pet.HORSE.getValue())){
				if(variable != 2){
					return false;
				}
				boolean takenAlready = thingsAlreadyTaken.get(2).contains(Food.KITKAT.getValue());
				if(variable > 1 && variable < 5){
					if(!(assgn.getAssgn().get(variable-1).get(2) == null || assgn.getAssgn().get(variable-1).get(2).equals(Food.KITKAT.getValue()))
							&& (assgn.getAssgn().get(variable+1).get(2) == null || assgn.getAssgn().get(variable+1).get(2).equals(Food.KITKAT.getValue()))){
						if(takenAlready){
							return false;
						}
					}
					
				}else if( variable ==1){
					if(!(assgn.getAssgn().get(variable+1).get(2) == null || assgn.getAssgn().get(variable+1).get(2).equals(Food.KITKAT.getValue()))){
						if(takenAlready){
							return false;
						}
					}
				}else if (variable == 5){
					if(!(assgn.getAssgn().get(variable-1).get(2) == null || assgn.getAssgn().get(variable-1).get(2).equals(Food.KITKAT.getValue()))){
						if(takenAlready){
							return false;
						}
					}
				}
				
			}
			/*if((value.equals(Pet.HORSE.getValue())) && 
					( !( (variable < 5 && (assgn.getAssgn().get(variable+1).get(2) == null) || ( variable < 5 && assgn.getAssgn().get(variable+1).get(2).equals(Food.KITKAT.getValue())) ))
							|| (!( (variable >1 && (assgn.getAssgn().get(variable-1).get(2) == null) || ( variable >1 && assgn.getAssgn().get(variable-1).get(2).equals(Food.KITKAT.getValue()))))))
					){	
				return false;
			}*/
		}
		if(variable == 1 && index == 3){
			if(!value.equals(Nationality.NORWEGIAN.getValue()))
				return false;
		}
		if(variable == 2 && index == 0){
			if(!value.equals(Color.BLUE.getValue()))
				return false;
		}
		if(variable == 3 && index == 1){
			if(!value.equals(Drink.MILK.getValue()))
				return false;
		}
		if(variable == 1 && index == 1){
			if(!value.equals(Drink.WATER.getValue()))
				return false;
		}
		if(variable == 1 && index == 2){
			if(!value.equals(Food.KITKAT.getValue()))
				return false;
		}
		if(variable == 2 && index == 4){
			if(!value.equals(Pet.HORSE.getValue()))
				return false;
		}
		for (Map.Entry<Integer,ArrayList<String>> entry : assgn.getAssgn().entrySet()) {
			if(entry.getValue().contains(value))
				return false;
		}

		return true;
	}
}
