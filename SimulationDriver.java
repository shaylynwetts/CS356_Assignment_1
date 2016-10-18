/*
 *  Name  : Shaylyn Wetts
 *  Class : CS 356 Object Oriented Design and Programming
 *  
 *  Date  : 10/18/2016
 *  
 *  Assignment 1
 *  	Create a SimulationDriver to automatically simulate iVote process.  Creates a question
 *  	of a particular type, adds options for answers, generates students, gets responses,
 *  	and calculates results for both Single type questions and Multiple type questions.
 *  	Demonstrates the capabilities of classes Student, Question, and IVoteService.
 */

package cs356_Assignment_1;


// Include libraries
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SimulationDriver {
	
	// Global variables
	public static final int MAX_STUDENTS = 50;
	public static final int MIN_STUDENTS = 10;
	private static final int ID_LENGTH = 10;

	public static void main(String[] args) {
		
		// Simulates entering a question type and question name
		System.out.println("Welcome to iVote Service.  Enter a question type (Single/Multiple) and name.");
		System.out.println("Question Type: Multiple");
		System.out.println("Question Name: What are your favorite food?");
		System.out.println();
		
		// Creates the question object, setting type and name
		Question testQuestion = new Question("Multiple", "What are your favorite food?");
		
		// Adds choices of answers to the question object
		System.out.println("Add labels for choices.");
		testQuestion.addLabel("Pizza");
		testQuestion.addLabel("Pasta");
		testQuestion.addLabel("Potato");
		testQuestion.addLabel("Burito");
		System.out.println();
		
		// Lists the current question and answer choices
		System.out.println("Current question and choices:");
		System.out.println(testQuestion.getQuestionName());
		testQuestion.listLabels();
		System.out.println();
		
		// Generates a random number of students with random String IDs
		// between the amount of 10 and 60
		System.out.println("Generating test students...");
		Random numGenerator = new Random();
		int numStudents = numGenerator.nextInt(MAX_STUDENTS);
		if (numStudents < 10) {
			numStudents = MIN_STUDENTS;
		}
		String[] studentIDArray = new String[numStudents];
		for (int i = 0; i < numStudents; i++){
			String id = randomIDGenerator();
			while (Arrays.asList(studentIDArray).contains(id)) {
				id = randomIDGenerator();
			}
			studentIDArray[i] = id;
		}
		Object[] students = new Object[numStudents];
		for (int i = 0; i < numStudents; i++) {
			students[i] = new Student(studentIDArray[i]);
		}
		System.out.println(numStudents + " students generated.");
		System.out.println();
		
		// Generates answers for each student for a Multiple type question.  This
		// allows a single student to answer for multiple choices
		System.out.println("Generating student answers and sending to iVote service...");
		IVoteService iVote = new IVoteService(testQuestion.getLabels());
		for (int i = 0; i < numStudents; i++) {
			Student currentStudent = (Student) students[i];
			if (testQuestion.getQuestionType().equals("Single")) {
				iVote.setSubmissionSingle(currentStudent.getID(), 
						currentStudent.getSingleSubmission(testQuestion.getLabels().size() + 1));
			} else if (testQuestion.getQuestionType().equals("Multiple")) {
				iVote.setSubmissionMultiple(currentStudent.getID(), 
						currentStudent.getMultipleSubmission(testQuestion.getLabels().size() + 1));
			} else {
				System.out.println("Incorrect question type.");
				System.exit(1);
			}
		}
		System.out.println("Student answers generated.");
		System.out.println();
		
		// Calculates the distribution of answers among the choices by the students
		System.out.println("Calculating results");
		int[] results = iVote.getResults(testQuestion.getQuestionType());
		System.out.println("Results for the question '" + testQuestion.getQuestionName() + "' from " + numStudents + " students:");
		ArrayList<String> labels = testQuestion.getLabels();
		Object[] labelArray = labels.toArray();
		for (int i = 0; i < results.length; i++) {
			System.out.println(labelArray[i] + " = " + results[i]);
		}
		System.out.println();
		
		// Simulates changing the name and type of the question
		System.out.println("Changing question type and name.");
		System.out.println("Question Type: Single");
		System.out.println("Question Name: Do you own a backpack?");
		System.out.println();
		
		// Changes the name and type of question
		testQuestion.changeQuestionType("Single");
		testQuestion.changeQuestionName("Do you own a backpack?");
		
		// Removes old answer choices from question object and adds
		// new ones
		System.out.println("Removing old labels...");
		for (int i = 0; i < labelArray.length; i++) {
			testQuestion.removeLabel(0);
		}
		System.out.println("Old labels removed.  Adding new labels.");
		testQuestion.addLabel("Yes");
		testQuestion.addLabel("No");
		System.out.println();
		
		// Resets the previous iVote settings from the last survey
		System.out.println("Resetting iVote...");
		iVote.clearAll();
		System.out.println("iVote reset.");
		System.out.println();
		
		// Lists the current question and answer choices
		System.out.println("Current question and choices:");
		System.out.println(testQuestion.getQuestionName());
		testQuestion.listLabels();
		System.out.println();
		
		// Students submit their answers for a Single choice system, where
		// they only choose one option
		System.out.println("Students resubmitting responses...");
		for (int i = 0; i < numStudents; i++) {
			Student currentStudent = (Student) students[i];
			if (testQuestion.getQuestionType().equals("Single")) {
				iVote.setSubmissionSingle(currentStudent.getID(), 
						currentStudent.getSingleSubmission(testQuestion.getLabels().size() + 1));
			} else if (testQuestion.getQuestionType().equals("Multiple")) {
				iVote.setSubmissionMultiple(currentStudent.getID(), 
						currentStudent.getMultipleSubmission(testQuestion.getLabels().size() + 1));
			} else {
				System.out.println("Incorrect question type.");
				System.exit(1);
			}
		}
		System.out.println("Student answers generated.");
		System.out.println();
		
		// Calculates the distribution of answers among the choices by the students
		System.out.println("Calculating results...");
		results = iVote.getResults(testQuestion.getQuestionType());
		System.out.println("Results for the question '" + testQuestion.getQuestionName() + "' from " + numStudents + " students:");
		labels = testQuestion.getLabels();
		labelArray = labels.toArray();
		for (int i = 0; i < results.length; i++) {
			System.out.println(labelArray[i] + " = " + results[i]);
		}
		System.out.println();
		
		System.out.println("Thank you for using iVote.");
	}
	
	// Generates a random string ID for each student using the
	// English alphabet
	public static String randomIDGenerator() {
		Random stringGenerator = new Random();
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] randomID = new char[ID_LENGTH];
		for (int i = 0; i < ID_LENGTH; i++) {
			randomID[i] = alphabet.charAt(stringGenerator.nextInt(alphabet.length()));
		}
		String randomIDString = new String(randomID);
		
		return randomIDString;
	}

}
