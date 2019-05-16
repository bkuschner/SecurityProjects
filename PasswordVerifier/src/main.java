import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Properties;
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
     * this method takes logging as a parameter to avoid having to retrieve
     * information from the config file
     * @param count number of test cases to run
     * @param loggingEnabled writes results to log if true
     */
    public static void stressTest(int count, boolean loggingEnabled) {
        final int minLength = 5;
        final int maxLength = 20;
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
        
        //nanoTime() instead of currentTimeMillis() is used for higher precision
        //since relationship to actual system time is not needed
        long startTime = System.nanoTime();
        for (int i = 0; i < testCases.length; ++i) {
            testResults[i] = utils.isValid(testCases[i]);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("time to check: " + duration + " nanoseconds");
        
        if(loggingEnabled) {
	        PrintWriter myWriter = null;
	        try {
	        	myWriter = new PrintWriter("log.txt", "UTF-8");
	        	for (int i = 0; i < testCases.length; ++i) {
	        		myWriter.println(testCases[i] + ": " + testResults[i]);
	        	}
	        } catch (FileNotFoundException e) {
	        	System.out.println("could not find log.txt");
	        	e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	        	e.printStackTrace();
	        } finally {
	        	myWriter.close();
	        }
        }
    }
    
    public static void main(String[] args) {
    	final int stressIterations = 1000;
    	Properties myProperties = new Properties();
    	FileInputStream input = null;
    	boolean loggingEnabled = false;
    	try {
    		input = new FileInputStream("/Users/benjaminkuschner/Documents/SecurityProjects/PasswordVerifier/src/config.properties");
    		myProperties.load(input);
    		if(myProperties.getProperty("loggingEnabled").equals("true"))
				loggingEnabled = true;
    		else
    			loggingEnabled = false;
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch(IOException e ) {
    				e.printStackTrace();
    			}
    		}
    	}
    	
        boolean correctness = correctnessTest();
        if (correctness)
        	System.out.println("correct");
        else
        	System.out.println("incorrect");
        
        stressTest(stressIterations, loggingEnabled);
    }

}
