package mx.tec.lab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

	public int add(String numbers) {
		Pattern p = Pattern.compile("//(.)\n");
		Matcher m = p.matcher(numbers);
		String separator = ",";
		String prefix = "";
		if(m.find()) {
			prefix = m.group(0);
			separator = m.group(1);
		}
		
		String args[] = numbers.substring(prefix.length()).split(separator + "|\n");
		
		// Empty string case
		if(args.length == 1 && args[0].isBlank()) {
			return 0;
		}
		
		// One number case
		if(args.length == 1) {
			return Integer.parseInt(args[0]);
		}
		
		// Normal case
		int result = 0;
		for(String arg : args) {
			result += Integer.parseInt(arg);
		}
		
		return result;
	}

} 