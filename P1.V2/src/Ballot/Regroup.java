package Ballot;


/**
 * The Regroup class is meant to save the ranks that a candidate received in
 *  a ballot and pair it with the ballot ID where the rank was casted.
 * 
 * @author Ydiel Zaid Flores Torres
 */
public class Regroup {
	
	private int ballotID;
	private int rank;
	
	public Regroup(int ballotID, int rank) {
		this.ballotID = ballotID;
		this.rank = rank;
	}
	
	/**
	 * Access the ballot ID.
	 * 
	 * @return int - This int represents the ballotID.
	 */
	public int getBallotID() {
		return ballotID;
	}
	/**
	 * This method let's you set the ballotID.
	 * @param ballotID
	 */
	public void setBallotID(int ballotID) {
		this.ballotID = ballotID;
	}
	/**
	 * Access the rank given to the candidate in this ballot.
	 * @return int - represents the rank
	 */
	public int getRank() {
		return rank;
	}
	/**
	 * This method let's you set the rank in this ballot. 
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
}
