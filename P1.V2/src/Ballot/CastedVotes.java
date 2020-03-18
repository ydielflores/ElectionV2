package Ballot;

public class CastedVotes {
	
	private int candidateID;
	private int rank;
	
	public CastedVotes(int candidateID, int rank) {
		this.candidateID = candidateID;
		this.rank = rank;
	}

	public int getCandidateID() {
		return candidateID;
	}

	public void setCandidateID(int candidateID) {
		this.candidateID = candidateID;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
}
