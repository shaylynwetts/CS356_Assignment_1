/*
 *  Name  : Shaylyn Wetts
 *  Class : CS 356 Object Oriented Design and Programming
 *  
 *  Date  : 10/18/2016
 *  
 *  Assignment 1
 *  	Interface for IVoteService class.
 */

package cs356_Assignment_1;

public interface IVote {

	public void setSubmissionSingle(String id, int sub);
	public void setSubmissionMultiple(String id, Integer[] sub);
	public int[] getResults(String questionType);
	
}
