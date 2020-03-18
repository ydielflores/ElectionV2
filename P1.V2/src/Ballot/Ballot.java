package Ballot;

import DataStructures.LinkedList.LinkedList;

public class Ballot {

	private int ballotID;
	private LinkedList<CastedVotes> castedVotes;
	
	public Ballot(int ballotID, LinkedList<CastedVotes> castedVotes) {
		this.ballotID = ballotID;
		this.castedVotes = castedVotes;
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
