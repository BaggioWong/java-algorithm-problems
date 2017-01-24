package algorithms;

/**
 * Original problem can be found here: 
 * https://leetcode.com/problems/integer-to-english-words/
 * @author Baggio
 *
 */
public class NumberToWords {
	/**
	 * Convert a single digit (1 - 9) to English.
	 * @param digit
	 * @return
	 */	
	public static String digitToWord(String digit) {
		int digitInt = Integer.parseInt(digit);
		final String[] singleDigits = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
		
		return singleDigits[digitInt - 1];
	}
	
	/**
	 * Helper method to convert a number (1 - 999) into words.
	 * @param num
	 * @return
	 */
	public static String hundredsToWords(String num) {
		String result = "";
		
		int numInt = Integer.parseInt(num);
		String numString = "";
		if (numInt >= 100 && numInt <= 999)
			numString = String.valueOf(num);
		else if (numInt >= 10 && numInt <= 99)
			numString = "0" + String.valueOf(numInt);
		else if (numInt <= 9)
			numString = "0" + "0" + String.valueOf(numInt);
		
		final String[] singleDigits = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
		final String[] tensSpecialsError = {"TenOne", "TenTwo", "TenThree", "TenFour", "TenFive", "TenSix", "TenSeven", "TenEight", "TenNine"};
		final String[] tensSpecials = {"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
		final String[] tensDigits = {"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
		
		int hundredsInt = numString.charAt(0) - '0';
		int tensInt = numString.charAt(1) - '0';
		int singleInt = numString.charAt(2) - '0';
		
		if (hundredsInt == 0 && tensInt == 0 && singleInt == 0)	// 000
			result = "";
		else if (hundredsInt == 0 && tensInt == 0)				// 001
			result = singleDigits[singleInt - 1];
		else if (hundredsInt == 0 && singleInt == 0) 			// 010
			result = tensDigits[tensInt - 1];
		else if (tensInt == 0 && singleInt == 0) 				// 100
			result = singleDigits[hundredsInt - 1] + "Hundred";
		else if (singleInt == 0)								// 110
			result = singleDigits[hundredsInt - 1] + "Hundred" + tensDigits[tensInt - 1];
		else if (tensInt == 0)									// 101
			result = singleDigits[hundredsInt - 1] + "Hundred" + singleDigits[singleInt - 1];
		else if (hundredsInt == 0) 								// 011
			result = tensDigits[tensInt - 1] + singleDigits[singleInt - 1];
		else 													// 111
			result = singleDigits[hundredsInt - 1] + "Hundred" + tensDigits[tensInt - 1] + singleDigits[singleInt - 1];
		
		for (int i = 0; i < tensSpecialsError.length; i++) {
			result = result.replaceAll(tensSpecialsError[i], tensSpecials[i]);
		}
		
		return result;
	}
	
	/**
	 * Whether to display words such as "hundreds", "billions", based on its three digits (e.g. 200,000 - enter 200 to check
	 * thousands, enter 000 to check hundreds).
	 * @param num
	 * @return
	 */
	public static boolean displayPlaceholderWord(String num) {
		int numInt = Integer.parseInt(num);
		
		if (numInt == 0)
			return false;
		
		return true;
	}
	
	/**
	 * Convert number from range 0 ~ 2*31 - 1
	 * @param num
	 * @return
	 */
	public static String numberToWords(int num) {
		String result = "";
		String numString = String.valueOf(num);
		int length = numString.length();
		
		String billions = "";
		String millions = "";
		String thousands = "";
		String hundreds = "";
		
		String billionsString = "";
		String millionsString = "";
		String thousandsString = "";
		String hundredsString = "";
		
		
		final String[] specials = {"Hundred", "Thousand", "Million", "Billion"};
		
		//	edge case (0)
		if (num == 0)
			return "Zero";
		
		if (length == 10) {
			billions = numString.substring(0, 1);
			millions = numString.substring(1, 4);
			thousands = numString.substring(4, 7);
			hundreds = numString.substring(7, 10);
			
			billionsString = digitToWord(billions) 			+ ((displayPlaceholderWord(billions)) ? specials[3] : "");
			millionsString = hundredsToWords(millions) 		+ ((displayPlaceholderWord(millions)) ? specials[2] : "");
			thousandsString = hundredsToWords(thousands)	+ ((displayPlaceholderWord(thousands)) ? specials[1] : "");
			hundredsString = hundredsToWords(hundreds);
			
			result = billionsString + millionsString + thousandsString + hundredsString; 
		} else if (length <= 9 && length > 6) {
			millions = numString.substring(0, length - 7 + 1);
			thousands = numString.substring(length - 7 + 1, length - 4 + 1);
			hundreds = numString.substring(length - 4 + 1, length - 1 + 1);
			
			millionsString = hundredsToWords(millions) 		+ ((displayPlaceholderWord(millions)) ? specials[2] : "");
			thousandsString = hundredsToWords(thousands)	+ ((displayPlaceholderWord(thousands)) ? specials[1] : "");
			hundredsString = hundredsToWords(hundreds);
			
			result = millionsString + thousandsString + hundredsString;
		} else if (length <= 6 && length > 3) {
			thousands = numString.substring(0, length - 4 + 1);
			hundreds = numString.substring(length - 4 + 1, length - 1 + 1);
			
			thousandsString = hundredsToWords(thousands)	+ ((displayPlaceholderWord(thousands)) ? specials[1] : "");
			hundredsString = hundredsToWords(hundreds);
			
			result = thousandsString + hundredsString;
		} else if (length <= 3) {
			hundreds = numString.substring(0, length - 1 + 1);
			
			hundredsString = hundredsToWords(hundreds);
			
			result = hundredsString;
		}
		
		String[] tokens = result.split("(?<=[a-z])(?=[A-Z])");
		result = "";
		for (int i = 0; i < tokens.length; i++)
			result += tokens[i] + " ";
		
		result = result.trim();
		
		return result;
	}
	public static void main(String[] args) {
		//	tests for converting (0 - 999) to words
		System.out.println("000: " + hundredsToWords("000"));
		System.out.println("001: " + hundredsToWords("001"));
		System.out.println("010: " + hundredsToWords("010"));
		System.out.println("011: " + hundredsToWords("011"));
		
//		System.out.println(hundredsToWords("000"));
//		System.out.println(hundredsToWords("001"));
//		System.out.println(hundredsToWords("010"));
//		System.out.println(hundredsToWords("011"));
//		System.out.println(hundredsToWords("100"));
//		System.out.println(hundredsToWords("101"));
//		System.out.println(hundredsToWords("110"));
//		System.out.println(hundredsToWords("111"));
//		
//		//	testing main convert to words method
		System.out.println(numberToWords(2147483647));
		System.out.println(numberToWords(2047483647));
		System.out.println(numberToWords(2107483647));
		System.out.println(numberToWords(2140483647));
		System.out.println(numberToWords(2007483647));
		System.out.println(numberToWords(2100483647));
		System.out.println(numberToWords(2000483647));
		System.out.println(numberToWords(2000083647));
		System.out.println(numberToWords(2000403647));
		System.out.println(numberToWords(2000480647));
		System.out.println(numberToWords(2000003647));
		System.out.println(numberToWords(2000400647));
		System.out.println(numberToWords(2000000647));
		System.out.println(numberToWords(2000000047));
		System.out.println(numberToWords(2000000607));
		System.out.println(numberToWords(2000000640));
		System.out.println(numberToWords(2000000600));
		System.out.println(numberToWords(2000000007));
		System.out.println(numberToWords(2000000000));
//		
//		System.out.println(numberToWords(0));
//		System.out.println(numberToWords(1));
//		System.out.println(numberToWords(10));
//		System.out.println(numberToWords(11));
//		System.out.println(numberToWords(100));
//		System.out.println(numberToWords(101));
//		System.out.println(numberToWords(110));
//		System.out.println(numberToWords(111));
//		
//		System.out.println(numberToWords(1000));
//		System.out.println(numberToWords(1001)); //
//		System.out.println(numberToWords(1010)); //
//		System.out.println(numberToWords(1011)); //
//		System.out.println(numberToWords(1100));
//		System.out.println(numberToWords(1101));
//		System.out.println(numberToWords(1110));
//		System.out.println(numberToWords(1111));
//		
//		System.out.println(numberToWords(10000));
//		System.out.println(numberToWords(10001)); //
//		System.out.println(numberToWords(10010)); //
//		System.out.println(numberToWords(10011)); //
//		System.out.println(numberToWords(10100));
//		System.out.println(numberToWords(10101));
//		System.out.println(numberToWords(10110));
//		System.out.println(numberToWords(10111));
//		System.out.println(numberToWords(11000));
//		System.out.println(numberToWords(11001)); //
//		System.out.println(numberToWords(11010)); //
//		System.out.println(numberToWords(11011)); //
//		System.out.println(numberToWords(11100));
//		System.out.println(numberToWords(11101));
//		System.out.println(numberToWords(11110));
//		System.out.println(numberToWords(11111));
	}

}
