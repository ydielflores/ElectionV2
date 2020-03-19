package Ballot;
/**
 * This method is responsible to pair candidate IDs with the rank they received in a ballot.
 * @author Ydiel Zaid Flores Torres
 *
 */
public class CastedVotes {
	
	private int candidateID;
	private int rank;
	
	public CastedVotes(int candidateID, int rank) {
		this.candidateID = candidateID;
		this.rank = rank;
	}
	/**
	 * Returns Candidate ID
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
	 * Returns the rank received in this ballot.
	 * @return
	 */
	public int getRank() {
		return rank;
	}
	/**
	 * Lets you set the rank.
	 * @param rank
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
}
