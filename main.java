import javafx.util.Pair;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		ArrayList<Pair<String,Boolean>> testCases = new ArrayList<Pair<String,Boolean>>();
		
		testCases.add(new Pair<String,Boolean>("hello123", new Boolean(false)));
		testCases.add(new Pair<String,Boolean>("Utter37", new Boolean(false)));
		testCases.add(new Pair<String,Boolean>("cliMB0003", new Boolean(false)));
		testCases.add(new Pair<String,Boolean>("CHALK12!@12", new Boolean(false)));
		testCases.add(new Pair<String,Boolean>("!CRime#226", new Boolean(true)));
		testCases.add(new Pair<String,Boolean>("!1Aaaaaa", new Boolean(true)));
		testCases.add(new Pair<String,Boolean>("!!!!!Aa1", new Boolean(true)));
		testCases.add(new Pair<String,Boolean>("aa55555A@", new Boolean(true)));
		testCases.add(new Pair<String,Boolean>("password", new Boolean(false)));
		testCases.add(new Pair<String,Boolean>("test", new Boolean(false)));
		testCases.add(new Pair<String,Boolean>("1!Aa", new Boolean(false)));
		testCases.add(new Pair<String,Boolean>("11@@AAa", new Boolean(false)));
		testCases.add(new Pair<String,Boolean>("snarl49001", new Boolean(false)));
		
		ArrayList<Boolean> testResults = new ArrayList<Boolean>();
		for(int i = 0; i < testCases.size(); ++i) {
			testResults.add(utils.isValid(testCases.get(i).getKey()));
		}
		ArrayList<Integer> errorIndicies = new ArrayList<Integer>();
		for(int i = 0; i < testResults.size(); ++i) {
			if(testResults.get(i) != testCases.get(i).getValue().booleanValue())
				errorIndicies.add(new Integer(i));
		}
		if(errorIndicies.size() == 0)
			System.out.println("no errors. Good job!");
		else {
			System.out.println("errors at indicies:");
			System.out.println(errorIndicies.toString());
		}
		

	}

}
