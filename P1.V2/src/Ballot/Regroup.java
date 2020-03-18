package Ballot;


/**
 * 
 * @author Ydiel Flores 
 */
/**
 * This class is meant to save a rank received in some ballot with a ballot ID.
 * 
 */
public class Regroup {
	
	private int ballotID;
	private int rank;
	
	public Regroup(int ballotID, int rank) {
		this.ballotID = ballotID;
		this.rank = rank;
	}
	public int getBallotID() {
		return ballotID;
	}
	public void setBallotID(int ballotID) {
		this.ballotID = ballotID;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
}
