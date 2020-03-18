package Ballot;

import DataStructures.LinkedList.LinkedList;

public class Candidate {
	
	private String candidateName;
	private int candidateID;
	private LinkedList<Regroup> votesReceived;
	
	public Candidate(String candidateName, int candidateID, LinkedList<Regroup> votesReceived) {
		this.candidateName = candidateName;
		this.candidateID = candidateID;
		this.votesReceived = votesReceived;
	}
	
	public int getAmountOf(int rank) {
		int count = 0;
		for(Regroup r : getVotesReceived()) {
			if(r.getRank() == rank) {
				count++;
			}
		}
		return count;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public int getCandidateID() {
		return candidateID;
	}

	public void setCandidateID(int candidateID) {
		this.candidateID = candidateID;
	}

	public LinkedList<Regroup> getVotesReceived() {
		return votesReceived;
	}

	public void setVotesReceived(LinkedList<Regroup> votesReceived) {
		this.votesReceived = votesReceived;
	}
}
