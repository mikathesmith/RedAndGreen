import java.util.*;
import java.io.*; 

/*
 * Mika Smith
 * Etude 10: Red and Green
 * 
 * Given n, an integer k is a near factor of n if there is some 
 * d where 2 <= d <= n such that n/d = k. Eg, near factors of 
 * 13 are 1, 2, 3, 4 and 6. 
 * 
 * n's are divided up into two groups: 
 * 	Green : n = 1
 *  Red   : n > 1 && more of its near factors are green (=1) than red
 
 	This program takes in input with scenarios seperated by blank lines
 	
 	One scenario is a pair of positive integers a and b. 
 	
 	The programs outputs for each scenario, a string of length b, consisting
 	of the characters R and G representing the type of the integers a, a+1,
 	through a+b-1
 *
 */

public class NearFactor{
	
	public static HashMap<Integer, Integer> freqMap;
	public static HashMap<Integer, Character> typeMap = new HashMap<Integer, Character>(); //only defined once
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		typeMap.put(1, 'G');
		
		int a;
		int b; 
		while(sc.hasNextLine()){
			String s = sc.nextLine();
			if(!s.contains("#") && !s.equals("")){ //Ignore lines with # comments and blank lines
        		Scanner sc2 = new Scanner(s);
        		
        		a = sc2.nextInt(); 
    			b = sc2.nextInt(); 
    			
    			System.out.println(computeRedGreen(a, b));
        	}
		}
	}
	
	
	public static String computeRedGreen(int a, int b){
	//	int i=a; 
		String result =""; 
		int i=0; 
		//System.out.println("Looking at " + b + " integers between " + a + " and " + (a+b));
		while(i <= (a+b-1)){ //count + length -1
			calculateNearFactors(i, result); //just filling table, not adding to output
			if(i >= a){
				result += calculateNearFactors(i, result);
			}
			
			i++;
		}
		return result; 
	}
	
	//Both n and the near factors have a type - n inherits its type from the
	//type of its near factors.
	public static char calculateNearFactors(int n, String res){
	//	System.out.println("\nFor n "+ n);
		
		//Find all near factors first
		ArrayList<Integer> nearFactors = new ArrayList<Integer>();
		for(int d=2; d <= n; d++){
			int k = n/d; //should return how many times its divided in with no remainder
		//	System.out.println("K is " + k);
			if(!nearFactors.contains(k)){ //ensures each near factor is only added once. 
			//	System.out.println("Added to nearfactor list");
				nearFactors.add(k);
			}
		}
		
		//For each of the added near factors, find their type 
		int redCount= 0;
		int greenCount = 0; 
		char c; 
		for(Integer i : nearFactors){ //for each unique nearfactor of n
		//	System.out.println("Looking at " + i + " as a nearfactor of " + n);
			if(typeMap.containsKey(i)){ //If we have already found its type previously (which we would have)
				c = typeMap.get(i);   //get it 
				if(c == 'R'){  
					redCount++; 
				}else{
					greenCount++; 
				}
			}else{
				System.out.println("NOT FOUND");
			}
		}
		//System.out.println("Found " + redCount + " reds and " + greenCount + " greens.");
		if(redCount >= greenCount){
			typeMap.put(n, 'G');
			return 'G';
		}else{
			typeMap.put(n, 'R');
			return 'R';
		}
	}
}