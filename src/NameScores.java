import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Hashtable;


public class NameScores {

	public static void main(String[] args) {
		
		String[] names = readFromFile();
		
		int totalSum = findNameScores(names);
		
		System.out.println("Total name score is : " + totalSum);
		
	}

	private static int findNameScores(String[] names) {
		
		int sum = 0;
		
		Hashtable<String, Integer> letterValues = AlphabetFunctions.createLetterValueTable();
		
		for(int i = 0; i < names.length; i++) 
		{
			String name = names[i];
			int nameSum = 0;
			for(int c = 0; c < name.length(); c++) 
			{
				String letter = null;
				if(c == name.length()-1) {
					letter = name.substring(c);
				} else { 
					letter = name.substring(c, c+1);
				}
				nameSum += letterValues.get(letter);
			}
			sum += nameSum*(i+1);
		}
		
		return sum;
	}

	private static String[] readFromFile() {
		
		String[] names = null;
		try {
			FileReader fileReader = new FileReader("names.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String readLine = null;
			
			while((readLine = bufferedReader.readLine()) != null) {
				names = readLine.split("\",\"");
			}
			
			//Get the double quotes out of the first and last names in the list
			names[0] = names[0].substring(1);
			names[names.length-1] = names[names.length-1].substring(0, names[names.length-1].length()-1);
			
			Arrays.sort(names);
			
			bufferedReader.close();
			fileReader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return names;
	}
}
