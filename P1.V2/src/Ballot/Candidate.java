package Ballot;

import DataStructures.LinkedList.LinkedList;

/** 
 * Candidate class is responsible for saving the candidate name, candidate ID, group the votes received and store the amount of ones at the time of elimination.
 * @author Ydiel Zaid Flores Torres
 *
 */
public class Candidate {
	
	private String candidateName;
	private int candidateID;
	private LinkedList<Regroup> votesReceived;
	private int amountOfOnesAtElimination;
	
	public Candidate(String candidateName, int candidateID, LinkedList<Regroup> votesReceived, int amountOfOnesAtElimination) {
		this.candidateName = candidateName;
		this.candidateID = candidateID;
		this.votesReceived = votesReceived;
		this.amountOfOnesAtElimination = amountOfOnesAtElimination;
	}
	
	
	/**
	 * This method finds a combination of ballot ID and rank given a ballot Id
	 * @param ballotID
	 * @return
	 */
	public Regroup findRegroupByBallotID(int ballotID) {
		for(Regroup r : getVotesReceived()) {
			if(r.getBallotID() == ballotID) {
				return r;
			}
		}
		return null;
	}
	/**
	 * This method returns the amount of ones after elimination.
	 * @return
	 */
	public int getAmountOfOnesAtElimination() {
		return amountOfOnesAtElimination;
	}
	/**
	 * This method lets you set the amount of ones at eliminations.
	 * @param amountOfOnesAtElimination
	 */
	public void setAmountOfOnesAtElimination(int amountOfOnesAtElimination) {
		this.amountOfOnesAtElimination = amountOfOnesAtElimination;
	}
	 /**
	  * This method returns the amount of a rank this candidate received.
	  * @param rank
	  * @return
	  */
	public int getAmountOf(int rank) {
		int count = 0;
		for(Regroup r : getVotesReceived()) {
			if(r.getRank() == rank) {
				count++;
			}
		}
		return count;
	}
	/**
	 * Returns the candidate Name.
	 * @return
	 */
	public String getCandidateName() {
		return candidateName;
	}
	/**
	 * Lets you set the candidate name.
	 * @param candidateName
	 */
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	/**
	 * Returns the candidate ID.
	 * @return
	 */
	public int getCandidateID() {
		return candidateID;
	}
	/**
	 * Lets you set the candidate ID.
	 * @param candidateID
	 */
	public void setCandidateID(int candidateID) {
		this.candidateID = candidateID;
	}
	
	/**
	 * Returns the list containing the combination of ballot IDs and ranks.
	 * @return
	 */
	public LinkedList<Regroup> getVotesReceived() {
		return votesReceived;
	}
	/**
	 * Lets you set the list of the combination of ballot IDs and ranks.
	 * @param votesReceived
	 */
	public void setVotesReceived(LinkedList<Regroup> votesReceived) {
		this.votesReceived = votesReceived;
	}
}
