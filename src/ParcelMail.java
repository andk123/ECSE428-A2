import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParcelMail {
	
	public static void main(String[] args) {
		
		final String regex_valid_postal_code = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";
		final Pattern regex_pattern = Pattern.compile(regex_valid_postal_code);
		double rate;
		
		// error checking: no arguments
		if (args == null){
				System.out.print("Usage: parcelmail from to len width height weight type");
				return;
		}
		
		// error checking: incorrect number of arguments
		if (args.length != 7){
			if (args.length == 0){
				System.out.print("Usage: parcelmail from to len width height weight type");
			}
			else if (args.length < 7){
				System.out.print("Usage: parcelmail from to len width height weight type");
			}
			else if (args.length > 7){ 
				System.out.print("Usage: parcelmail from to len width height weight type");
			}
			return;
		}
		// error checking: incorrect value of arguments
		else{
			if (Integer.parseInt(args[2]) < 10) {
				System.out.print("Usage: parcelmail from to len width height weight type \nlen >= 10");
			}
			else if (Integer.parseInt(args[2]) > 200) {
				System.out.print("Usage: parcelmail from to len width height weight type \nlen <= 200");
			}
			else if (Integer.parseInt(args[3]) < 10) {
				System.out.print("Usage: parcelmail from to len width height weight type \nwidth >= 10");
			}
			else if (Integer.parseInt(args[3]) > 200) {
				System.out.print("Usage: parcelmail from to len width height weight type \nwidth <= 200");
			}
			else if (Integer.parseInt(args[4]) < 0.1) {
				System.out.print("Usage: parcelmail from to len width height weight type \nheight >= 0.1");
			}
			else if (Integer.parseInt(args[4]) > 200) {
				System.out.print("Usage: parcelmail from to len width height weight type \nheight <= 200");
			}
			else if (Integer.parseInt(args[5]) > 30) {
				System.out.print("Usage: parcelmail from to len width height weight type \nweight <= 30");
			}
			else if (Integer.parseInt(args[3])*2 + Integer.parseInt(args[4])*2 > 300) {
				System.out.print("Usage: parcelmail from to len width height weight type \ngirth <= 300");
			}
			else if (!args[6].equals( "regular") && !args[6].equals( "xpresspost") && !args[6].equals( "priority")) {
				System.out.print("Usage: parcelmail from to len width height weight type \ntype = {regular, xpresspost,priority}");
			}
			else if (!regex_pattern.matcher(args[0]).matches()) {
				System.out.print("Usage: parcelmail from to len width height weight type \nfrom: use valid postal code");
			}
			else if (!regex_pattern.matcher(args[1]).matches()) {
				System.out.print("Usage: parcelmail from to len width height weight type \nto: use valid postal code");
			}
			else {
			
			// functionality to calculate rates
			rate = PostalRateCalculator.getRegularRate(args[1].substring(0,3), Integer.parseInt(args[5]), args[6]);
			System.out.print(rate);
			
			}
			
		}
		
	}
	
	
}
