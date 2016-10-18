/*
 *  Name  : Shaylyn Wetts
 *  Class : CS 356 Object Oriented Design and Programming
 *  
 *  Date  : 10/18/2016
 *  
 *  Assignment 1
 *  	Student class.  Contains methods for generating submissions
 *  	for Single type questions, generating submissions for Multiple
 *  	type questions, returning a student's ID, returning a student's
 *  	submission for Single type questions, and returning a student's
 *  	submission for Multiple type questions.
 */

package cs356_Assignment_1;


// Include libraries
import java.util.Arrays;
import java.util.Random;

public class Student {

	// Global variables
	private String studentID;
	private int submission;
	private Integer[] multSubmission;
	private boolean generatedSinSub = false;
	private boolean generatedMultSub = false;

	// Constructor for Student object
	public Student(String id) {
		studentID = id;
	}

	// Generates a random number for a Single type question.
	// Does not allow the number to be more than the number of
	// choices or less than 1
	private int generateSingleSubmission(int numLabels) {
		if (generatedSinSub == true) {
			return submission;
		}
		Random subGenerator = new Random();
		int submission = subGenerator.nextInt(numLabels);
		while (submission == 0) {
			submission = subGenerator.nextInt(numLabels);
		}
		generatedSinSub = true;
		return submission;
	}

	// Generates random numbers for a Multiple type question.
	// Does not allow the numbers to be more than the number of
	// choices or less than 1
	private Integer[] generateMultipleSubmission(int numLabels) {
		if (generatedMultSub == true) {
			return multSubmission;
		}
		Random subGenerator = new Random();
		int numSubmissions = subGenerator.nextInt(numLabels);
		while (numSubmissions == 0) {
			numSubmissions = subGenerator.nextInt(numLabels);
		}
		Integer[] submission = new Integer[numSubmissions];
		for (int i = 0; i < submission.length; i++) {
			int temp = subGenerator.nextInt(numLabels);
			while (temp == 0) {
				temp = subGenerator.nextInt(numLabels);
			}
			while (Arrays.asList(submission).contains(temp)) {
				temp = subGenerator.nextInt(numLabels);
				while (temp == 0) {
					temp = subGenerator.nextInt(numLabels);
				}
			}
			submission[i] = temp;
		}
		multSubmission = submission;
		generatedMultSub = true;
		return multSubmission;
	}

	// Returns the student's ID
	public String getID() {
		return studentID;
	}

	// Returns the student's Single submission
	public int getSingleSubmission(int submissionMax) {
		submission = generateSingleSubmission(submissionMax);
		return submission;
	}

	// Return's the student's Multiple submission
	public Integer[] getMultipleSubmission(int numLabels) {
		multSubmission = generateMultipleSubmission(numLabels);
		return multSubmission;
	}

}
