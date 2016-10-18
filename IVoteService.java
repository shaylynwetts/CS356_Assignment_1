/*
 *  Name  : Shaylyn Wetts
 *  Class : CS 356 Object Oriented Design and Programming
 *  
 *  Date  : 10/18/2016
 *  
 *  Assignment 1
 *  	IVoteService class.  Contains methods for setting submissions,
 *  	tallying submissions, returning results, and clearing previous
 *  	submissions.
 */

package cs356_Assignment_1;

// Include libraries
import java.util.ArrayList;
import java.util.HashMap;

public class IVoteService implements IVote{
	
	// Global variables
	private HashMap<String, Integer> singleSubmissions = new HashMap<String, Integer>();
	private HashMap<String, Integer[]> multipleSubmissions = new HashMap<String, Integer[]>();
	private ArrayList<String> studentIDs = new ArrayList<String>();
	private ArrayList<String> labels = new ArrayList<String>();
	
	// Constructor for IVoteService object
	public IVoteService(ArrayList<String> labelArray) {
		labels = labelArray;
	}
	
	// Tallies the submission answers based on Single or Multiple type questions
	private int[] tallySubmissions(String questionType) {
		int[] subTally = new int[labels.size()];
		if (questionType.equals("Single")) {
			for (int i = 0; i < studentIDs.size(); i++) {
				String currentStudent = studentIDs.get(i);
				int subValue = singleSubmissions.get(currentStudent);
				subTally[subValue - 1] = subTally[subValue - 1] + 1;
			}
			return subTally;
		} else if (questionType.equals("Multiple")) {
			for (int i = 0; i < studentIDs.size(); i++) {
				String currentStudent = studentIDs.get(i);
				Integer[] subValues = multipleSubmissions.get(currentStudent);
				for (int j = 0; j < subValues.length; j++) {
					int subValue = subValues[j];
					subTally[subValue - 1] = subTally[subValue - 1] + 1;
				}
			}
			return subTally;
		} else {
			System.out.println("Incorrect question type entered.");
			System.exit(1);
			return null;
		}
	}
	
	// Sets submission for a particular student's ID for Single type questions.
	// Will replace a student's previous answer with the last submitted answer
	// from that unique ID
	public void setSubmissionSingle(String id, int sub) {
		if (singleSubmissions.containsKey(id)) {
			singleSubmissions.replace(id, sub);
		} else {
			singleSubmissions.put(id, sub);
			studentIDs.add(id);
		}
	}
	
	// Sets submission for a particular student's ID for Multiple type questions.
	// Will replace a student's previous answer with the last submitted answer
	// from that unique ID
	public void setSubmissionMultiple(String id, Integer[] sub) {
		if (multipleSubmissions.containsKey(id)) {
			multipleSubmissions.replace(id, sub);
		} else {
			multipleSubmissions.put(id, sub);
			studentIDs.add(id);
		}
	}
	
	// Returns the result of the tallySubmissions method
	public int[] getResults(String questionType) {
		return tallySubmissions(questionType);
	}
	
	// Clears previously set submissions and student IDs
	public void clearAll() {
		singleSubmissions = new HashMap<String, Integer>();
		multipleSubmissions = new HashMap<String, Integer[]>();
		studentIDs = new ArrayList<String>();
	}
	
}
