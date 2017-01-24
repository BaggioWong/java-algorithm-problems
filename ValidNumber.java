package algorithms;

public class ValidNumber {
	/**
	 * Original question can be found here. 
	 * https://leetcode.com/problems/valid-number/
	 * @param s
	 * @return
	 */
    public boolean isNumber(String s) {
		String[] invalidNumberRegex = {
			//	hex letters appearing alone, or g-z appearing
			"\\s*[a-f]{1}\\s*|\\s*[g-z]+\\s*",
			//  dot appearing alone
			"\\s*\\.\\s*"
		};
		
		String[] validNumberRegex = {
			//	pure number
			"\\s*[\\+\\-]{0,1}[0-9]+\\s*",													
			//	fraction
			"\\s*[\\+\\-]{0,1}[0-9]+\\s*[\\/]\\s*[0-9]+\\s*",								
			//	decimal
			"\\s*[\\+\\-]{0,1}[0-9]+[.][0-9]+\\s*|\\s*[\\+\\-]*[0-9]+[.]\\s*|\\s*[\\+\\-]*[.][0-9]+\\s*",	
			//	complex numbers
			"\\s*[^\\-]*[0-9]+[\\u2148]\\s*",	
			//	integral exponents
			"\\s*[\\+\\-]{0,1}[0-9]+[e][\\+\\-]{0,1}[0-9]+\\s*",
			//  decimal exponents
			"\\s*([\\+\\-]{0,1}[0-9]+[.][0-9]+|[\\+\\-]*[0-9]+[.]|[\\+\\-]*[.][0-9]+)[e][\\+\\-]{0,1}[0-9]+\\s*",
			//	hex number
			"\\s*0[X][0-9A-F]+\\s*|\\s*0[x][0-9a-f]+\\s*",
			//	octal number
			"\\s*0[o][0-7]+\\s*",													
			//	special constants (Euler constant and PI)
			"\\s*[\\u2107\\03C0]\\s*"
		};
		
		//	loop to check against definitions of an invalid number
		for (int i = 0; i < invalidNumberRegex.length; i++) {
			//	if match, return false
			if (s.matches(invalidNumberRegex[i]))
				return false;
		}
		
		//	loop to check against definitions of a valid number
		for (int i = 0; i < validNumberRegex.length; i++) {
			//	if match, return true
			if (s.matches(validNumberRegex[i]))
				return true;
		}
		//	return false by default
		return false;
    }
	
//	public static void main(String[] args) {
//		String test = "π";
//		
//		boolean result = test.matches("[\\u2107\\u03C0]");
		
//		System.out.println(isNumber("e"));
//	}

}
