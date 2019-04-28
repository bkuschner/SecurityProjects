import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Random;

public class main {
    
    
    /*
     * a few test cases to test correctness. covers basics and some edge cases
     * @return true if passed all tests, false if there were error(s)
     */
    public static boolean correctnessTest() {
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
        if(errorIndicies.size() == 0) {
            System.out.println("no errors. Good job!");
            return true;
        }
        else {
            System.out.println("errors at indicies:");
            System.out.println(errorIndicies.toString());
            return false;
        }
    }

    /*
     * stress tests isValid function. prints time to check test cases
     * @param count number of test cases to run
     */
    public static void stressTest(int count) {
        int minLength = 5;
        int maxLength = 20;
        int nextLength;
        char nextChar;
        char[] nextString;
        Random myRandom = new Random();
        String[] testCases = new String[count];
        boolean[] testResults = new boolean[count];
        for (int i = 0; i < testCases.length; ++i) {
            nextLength = (myRandom.nextInt(maxLength - minLength + 1) + minLength);
            nextString = new char[nextLength];
            for (int j = 0; j < nextString.length; ++j) {
                //arbitrary ascii range containing mostly valid characters
                nextChar = (char) (myRandom.nextInt(100) + 27);
                nextString[j] = nextChar;
            }
            testCases[i] = new String(nextString);
        }
        
        long startTime = System.nanoTime();
        for (int i = 0; i < testCases.length; ++i) {
            testResults[i] = utils.isValid(testCases[i]);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("time to check: " + duration + " nanoseconds");
    }
    
    public static void main(String[] args) {
        correctnessTest();
        stressTest(1000);
    }

}