import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PostalRateCalculator {
	
	public static void main (String args[]){
		System.out.println(getRegularRate("K1P0A9", 15, "xpresspost"));
	}
	public static double getRegularRate(String destination, double weight, String type ){
		
		//Capitalize the first letter of type to match name of files
		type = type.substring(0, 1).toUpperCase() + type.substring(1);
		
		//Round weight to ceiling of nearest 0.5
		if (weight <= 0.75) weight = 0.75;
		else {
			weight = roundToHalf(weight);
		}
		
		String rateCode = "";
		String destinationFirstLetters = destination.substring(0,3).toUpperCase();
		String csvFileCodes = "Rate Codes.csv";
		String csvFilePrice = type+".csv";
        String line = "";
        String cvsSplitBy = ",";
        double price = 0;
		
        //Get the rate code
        try (BufferedReader br = new BufferedReader(new FileReader(csvFileCodes))) {

            while ((line = br.readLine()) != null) {
            	String words[] = line.split(cvsSplitBy);
            	
            	if ( destinationFirstLetters.equals(words[0])){
            		rateCode = words[1];
            		break;
            	}

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        //With the rate code and weight, find the price
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePrice))) {
        		
        	//Get corresponding row of the code
        	line = br.readLine();
        	String words[] = line.split(cvsSplitBy);
        	int columnCode = 0;
        	
        	for(int i = 0; i < words.length; i++){
        		if (words[i].equals(rateCode)){
        			columnCode = i;
        		}
        	}
        	
            while ((line = br.readLine()) != null) {
            	words = line.split(cvsSplitBy);
            	
            	if ( weight == Double.parseDouble(words[0])){
            		price = Double.parseDouble(words[columnCode]);
            		return price;
            	}

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
		return 0;
	
	}
	
	public static double roundToHalf(double d) {
	    return Math.ceil(d * 2) / 2.0;
	}
	
}
