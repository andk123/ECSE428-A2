
public class ParcelMail {
	
	public static void main(String[] args) {
		
		if (args == null){
				System.out.print("Enter arguments");
				return;
		}
		
		if (args.length != 7){
			if (args.length == 0){
				System.out.print("Enter arguments");
			}
			else if (args.length < 7){
				System.out.print("Missing arguments");
			}
			else { 
				System.out.print("Too many arguments");
			}
			return;
		}
	}
}
