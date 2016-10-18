/*
 *  Name  : Shaylyn Wetts
 *  Class : CS 356 Object Oriented Design and Programming
 *  
 *  Date  : 10/18/2016
 *  
 *  Assignment 1
 *  	Question class.  Contains methods for changing the question type,
 *  	changing the question name, adding a new label for answer choices,
 *  	removing an old label, listing the current labels, returning the
 *  	current question type, returning the current question name, and
 *  	returning the current label list.
 */

package cs356_Assignment_1;

// Include libraries
import java.util.ArrayList;

public class Question {
	
	// Global variables
	private String questionType;
	private String questionName;
	private ArrayList<String> labels = new ArrayList<String>();

	// Constructor for Question object
	public Question(String type, String name){
		questionType = type;
		questionName = name;
	}
	
	// Changes the question type
	public void changeQuestionType(String type) {
		questionType = type;
	}
	
	// Changes the question name
	public void changeQuestionName(String name) {
		questionName = name;
	}
	
	// Adds a new answer choice
	public void addLabel(String newLabel) {
		labels.add(newLabel);
		System.out.println("Label '" + newLabel + "' added to question list.");
	}
	
	// Removes a current answer choice
	public void removeLabel(int labelIndex) {
		labels.remove((labelIndex));
		System.out.println("Label removed.");
	}
	
	// Lists all current answer choices
	public void listLabels() {
		Object[] labelArray = labels.toArray();
		for (int i = 0; i < labelArray.length; i++) {
			String currentLabel = labelArray[i].toString();
			System.out.println((i + 1) + ". " + currentLabel);
		}
		
	}
	
	// Returns the question type
	public String getQuestionType(){
		return questionType;
	}
	
	// Returns the question name
	public String getQuestionName() {
		return questionName;
	}
	
	// Returns the current answer choices
	public ArrayList<String> getLabels() {
		return labels;
	}
	
}
