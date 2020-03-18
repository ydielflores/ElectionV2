package Ballot;

import DataStructures.LinkedList.LinkedList;

/**
 * BallotValidation class validates the ballots in a document. 
 * 
 * @author Ydiel Zaid Flores Torres
 *
 */
public class BallotValidation {

	private int invalidBallots = 0;
	private LinkedList<Ballot> ballotsToDiscard = new LinkedList<Ballot>();


	public BallotValidation() {	}
	
	/**
	 * This method validates every ballot in the ballotList. 
	 * @param ballotList
	 * @param candidateList
	 * @return ballotList - Returns the same ballotList as parameter but without the invalid ballots.
	 */

	public LinkedList<Ballot> validate(LinkedList<Ballot> ballotList, LinkedList<Candidate> candidateList){
		validateDublicate(ballotList);
		validateAmountOfVotes(ballotList,candidateList);
		validateSkippedRank(ballotList);

		return remove(ballotList);
	}

	/**
	 * This method checks if there are duplicate candidates and
	 *  duplicate ranks in a ballot;
	 * @param ballotList
	 */
	private void validateDublicate(LinkedList<Ballot> ballotList){

		for(Ballot b : ballotList) {
			for(int i = 0; i < b.getCastedVotes().size(); i++) {
				for(int j = 0; j < b.getCastedVotes().size(); j++) {
					if(i!=j) {
						if(b.getCastedVotes().get(i).getCandidateID() == b.getCastedVotes().get(j).getCandidateID()) {
							canAdd(b);
						}else if(b.getCastedVotes().get(i).getRank() == b.getCastedVotes().get(j).getRank()) {
							canAdd(b);
						}
					}
				}
			}

		}

	}
	/**
	 * This method checks if the amount of votes casted in the ballot is not bigger
	 * than the amount of Candidates available, bigger than the amount of casted Votes and if the votes are smaller than 1.
	 * 
	 * @param ballotList
	 * @param candidateList
	 */
	private void validateAmountOfVotes(LinkedList<Ballot> ballotList, LinkedList<Candidate> candidateList){
		for(Ballot b : ballotList) {
			if(b.getCastedVotes().size() > candidateList.size()) {
				canAdd(b);
				continue;
			}
			for(int i = 0; i < b.getCastedVotes().size(); i++) {
				if(b.getCastedVotes().get(i).getRank() > b.getCastedVotes().size()) {
					canAdd(b);
					break;
				}else if(b.getCastedVotes().get(i).getRank() < 1) {
					canAdd(b);
				}
			}
		}
	}
	
	/**
	 * This method checks if the ballots have skipped ranks. 
	 * @param ballotList
	 */
	private void validateSkippedRank(LinkedList<Ballot> ballotList) {
		int rankOrderCheck = 0;
		for(Ballot b : ballotList) {
			for(int i = 0; i < b.getCastedVotes().size(); i++) {
				rankOrderCheck++;
				if(b.getCastedVotes().get(i).getRank() != rankOrderCheck) {
					canAdd(b);
					break;
				}
			}
			rankOrderCheck = 0;
		}
	}

	/**
	 * This method removes the invalid ballots from the ballotList.
	 * 
	 * @param ballotList
	 * @return ballotList - Return the ballot list without the invalid ballots.
	 */
	private LinkedList<Ballot> remove(LinkedList<Ballot> ballotList){

		for(Ballot b : ballotsToDiscard) {
			ballotList.remove(b);
			invalidBallots++;
		}
		ballotsToDiscard.clear();
		return ballotList;
	}
	/**
	 * This method determines if the ballot that was found to be invalid
	 * is not already in the list to be discarded.
	 * @param b - Receives a Ballot.
	 */
	private void canAdd(Ballot b) {
		if(!ballotsToDiscard.contains(b)) {
			ballotsToDiscard.add(b);
		}
	}

	/**
	 * This method let's you access the amount of invalid ballots.
	 * @return int - Represents the amount of invalid ballots.
	 */
	public int getInvalidBallots() {
		return invalidBallots;
	}

}
