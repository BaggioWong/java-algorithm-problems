package algorithms;

import java.util.*;

/**
 * Original problem can be found here:
 * https://leetcode.com/problems/integer-to-roman/
 * @author Baggio
 *
 */
public class IntegerToRoman {
	public static String intToRoman(int num) {
		String result = "";
		
		//	enumerate seven basic roman numerals
		Map<Character, Integer> basicNumerals = new HashMap<Character, Integer>();
		basicNumerals.put('I', 1);
		basicNumerals.put('V', 5);
		basicNumerals.put('X', 10);
		basicNumerals.put('L', 50);
		basicNumerals.put('C', 100);
		basicNumerals.put('D', 500);
		basicNumerals.put('M', 1000);
		
		//	enumerate six special numerals
		Map<String, String> specialNumerals = new HashMap<String, String>();
		specialNumerals.put("DCCCC", "CM");
		specialNumerals.put("CCCC", "CD");
		specialNumerals.put("LXXXX", "XC");
		specialNumerals.put("XXXX", "XL");
		specialNumerals.put("VIIII", "IX");
		specialNumerals.put("IIII", "IV");
		
		char[] numerals = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};						//	for reference (index access instead of HashMap key-based access)
		String[] specials = {"DCCCC", "CCCC", "LXXXX", "XXXX", "VIIII", "IIII"}; 	//	for reference
		int[] frequency = new int[7];												//	frequency of numerals (each frequency correponds respective numeral in numerals) 
		int counter = 0;	
		int denominator = 1;
		int current = num;
		
		//	iterate through each element in numerals to calculate frequency of each
		while(current != 0) {
			denominator = basicNumerals.get(numerals[counter]);
			frequency[counter] = current / denominator;
			current %= denominator;
			counter++;
		}
		
		//	concatenate numerals[i] occuring frequency[i] times
		for (int i = 0; i < numerals.length; i++) {
			for (int j = 0; j < frequency[i]; j++) {
				result += numerals[i];
			}
		}
		
		//	replace special occurrences with their respective replacement strings
		for (String special : specials) {
			String regex = special;
			result = result.replaceFirst(regex, specialNumerals.get(special));
		}
		
		return result;
	}
	
	public static void main (String[] args) {
		System.out.println(intToRoman(1800));
	
	}
}
