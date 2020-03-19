package Ballot;

import DataStructures.LinkedList.LinkedList;

public class Ballot {

	private int ballotID;
	private LinkedList<CastedVotes> castedVotes;
	
	/**
	 * The ballot class is responsible for saving the ballot ID and the votes that were casted in that ballot.
	 * 
	 * @param ballotID
	 * @param castedVotes
	 */
	public Ballot(int ballotID, LinkedList<CastedVotes> castedVotes) {
		this.ballotID = ballotID;
		this.castedVotes = castedVotes;
	}
	
	/**
	 * This method looks for a candidate rank given the candidate ID.
	 * @param candidateId
	 * @return int - Returns the candidate rank or -1 if the candidate was not found.
	 */
	 public int getRankByCandidate(int candidateId) {
		 // rank for that candidate
		 for(CastedVotes cv : getCastedVotes()) {
			 if(cv.getCandidateID() == candidateId) {
				 return cv.getRank();
			 }
		 }
		 return -1;
	 }
	 
	 /**
	  * This method looks for a candidate ID given a rank.
	  * @param rank
	  * @return int - Returns the candidate ID or -1 if the rank was not found.
	  */
	 public int getCandidateByRank(int rank) {
		 // candidate with that rank
		 for(CastedVotes cv : getCastedVotes()) {
			 if(cv.getRank() == rank) {
				 return cv.getCandidateID();
			 }
		 }
		 return -1;
	 }
	
	
	
	
	/**
	 * @return - Returns the ballotID (otherwise known as ballotNum).
	 */
	public int getBallotID() {
		return ballotID;
	}
	/**
	 * @return - Returns the votes casted in this ballot. 
	 */
	public LinkedList<CastedVotes> getCastedVotes() {
		return castedVotes;
	}
}
