package Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;

import Ballot.Ballot;
import Ballot.Candidate;
import Ballot.Regroup;
import DataStructures.LinkedList.LinkedList;
import Resources.FileManager;

public class CountingProcess {
	FileManager fm = new FileManager();
	private LinkedList<Candidate> eliminatedList = new LinkedList<Candidate>();
	private LinkedList<Candidate> tieList = new LinkedList<Candidate>();
	private Candidate winner;
	public CountingProcess() {}

	/**
	 * This method starts the counting process.
	 * @param candidateList
	 * @param ballotList
	 * @param totalBallots
	 * @param blankBallots
	 * @param invalidBallots
	 * @throws IOException
	 */
	public void startCountingProcess(LinkedList<Candidate> candidateList, LinkedList<Ballot> ballotList, int totalBallots, int blankBallots, int invalidBallots) throws IOException {
		
		Candidate toEliminate;
		while(candidateList.size() > 1) {
			if(checkForFifty(candidateList,ballotList)) {
				writeByFifty(eliminatedList, winner, blankBallots, totalBallots, invalidBallots);
				break;
			}

			candidateList = sortSmallestFirst(candidateList,1);


			if(checkForTies(candidateList,1)) {
				toEliminate =  determineTieLoser(tieList, candidateList);
				eliminatedList.add(toEliminate);
				candidateList.remove(toEliminate);
				updateEliminate(candidateList,toEliminate,eliminatedList);
				tieList.clear();
			}else if(candidateList.get(0).getAmountOf(1) > candidateList.get(1).getAmountOf(1)) {
				toEliminate = candidateList.get(1);
				eliminatedList.add(toEliminate);
				candidateList.remove(toEliminate);
				updateEliminate(candidateList,toEliminate, eliminatedList);
			}else {
				toEliminate = candidateList.get(0);
				eliminatedList.add(toEliminate);
				candidateList.remove(toEliminate);
				updateEliminate(candidateList, toEliminate, eliminatedList);
			}
		}
		write(eliminatedList, candidateList, blankBallots, totalBallots, invalidBallots);
		
	}
	/**
	 * The method that writes a new txt file.
	 * @param eliminatedList
	 * @param candidateList
	 * @param blankBallots
	 * @param totalBallots
	 * @param invalidBallots
	 * @throws IOException
	 */
	public void write(LinkedList<Candidate> eliminatedList,LinkedList<Candidate> candidateList, int blankBallots, int totalBallots, int invalidBallots) throws IOException {
		
		PrintWriter pw = new PrintWriter("result.txt","UTF-8");

		pw.write("Number of Ballots: " + totalBallots);
		pw.write("\n");
		pw.write("Number of Blank Ballots: " + blankBallots);
		pw.write("\n");
		pw.write("Number of Invalid Ballots :" + invalidBallots);
		pw.write("\n");

		int roundC = 1;
		for(Candidate ec : eliminatedList) {

			pw.write("In round " + roundC+ ", " + ec.getCandidateName()+ " was eliminated with " + ec.getAmountOfOnesAtElimination() + " ones.");
			pw.write("\n");
			roundC++;	
		}
			
		pw.write("Winner is " + candidateList.get(0).getCandidateName() + " with " + candidateList.get(0).getAmountOf(1) + " that many amount of ones.");
		pw.close();
	}
	
	/**
	 * This method writes a new txt file when there is a winner with an advantage of more than fifty percent of ones.
	 * @param eliminatedList
	 * @param winner
	 * @param blankBallots
	 * @param totalBallots
	 * @param invalidBallots
	 * @throws IOException
	 */
	public void writeByFifty(LinkedList<Candidate> eliminatedList,Candidate winner, int blankBallots, int totalBallots, int invalidBallots) throws IOException {
		
		PrintWriter pw = new PrintWriter("result.txt","UTF-8");

		pw.write("Number of Ballots: " + totalBallots);
		pw.write("\n");
		pw.write("Number of Blank Ballots: " + blankBallots);
		pw.write("\n");
		pw.write("Number of Invalid Ballots :" + invalidBallots);
		pw.write("\n");

		int roundC = 1;
		for(Candidate ec : eliminatedList) {

			pw.write("In round " + roundC+ ", " + ec.getCandidateName()+ " was eliminated with " + ec.getAmountOfOnesAtElimination() + " ones.");
			pw.write("\n");
			roundC++;	
		}
			
		pw.write("Winner is " + winner.getCandidateName() + " with " + winner.getAmountOf(1) + " that many amount of ones.");
		pw.close();
	}

	/**
	 *This method is searches and eliminates a candidate from an ballot.
	 *
	 * @param candidateList
	 * @param toEliminate
	 * @param eliminatedList
	 */
	private void updateEliminate(LinkedList<Candidate> candidateList, Candidate toEliminate, LinkedList<Candidate> eliminatedList) {
		int ballotIDofToEliminateWithOne = 0;
		toEliminate.setAmountOfOnesAtElimination(toEliminate.getAmountOf(1));
		for(Regroup r : toEliminate.getVotesReceived()) {
			if(r.getRank() == 1) {
				ballotIDofToEliminateWithOne = r.getBallotID();
				updateEliminateEnhanced(candidateList, ballotIDofToEliminateWithOne, eliminatedList);
			}
			break;
		}
	}
	
	/**
	 * This method eliminates a candidate but it also tries to update the ballot in base of who was previously eliminated.
	 * @param candidateList
	 * @param ballotIDofToEliminateWithOne
	 * @param eliminatedList
	 */
	private void updateEliminateEnhanced(LinkedList<Candidate> candidateList, int ballotIDofToEliminateWithOne, LinkedList<Candidate> eliminatedList) {

		for(Candidate e : eliminatedList) {

			for(Regroup re : e.getVotesReceived()) {

				for(Candidate c : candidateList) {

					for(Regroup r : c.getVotesReceived()) {
						if(re.equals(r)) {
							if(re.getRank() < r.getRank()) {
								r.setRank(r.getRank() - 1);
								break;
							}
						}
					}

				}
				re.setRank(0);
			}

		}

	}
	/**
	 * This method check for ties.
	 * @param candidateList
	 * @param rank
	 * @return
	 */
	private boolean checkForTies(LinkedList<Candidate> candidateList, int rank) {
		//Check for ties.
		for(Candidate c1 : candidateList) {
			for (Candidate c2 : candidateList) {
				if(!c1.equals(c2) && c1.getAmountOf(rank) == c2.getAmountOf(rank)) {
					if(!tieList.contains(c1)) {
						tieList.add(c1);
					}
				}
			}
		}
		//This leaves the candidates with the smallest ones.
		for(Candidate c1 : tieList) {
			for(Candidate c2: tieList) {
				if(c2.getAmountOf(rank) > c1.getAmountOf(rank)) {
					tieList.remove(c2);
				}
			}
		}
		if(tieList.size() == 0) {
			return false;
		}
		return candidateList.get(0).getAmountOf(1) <= tieList.get(0).getAmountOf(1);
	}
	/**
	 * This method is meant to break a tie between two members of the candidate list. 
	 * @param candidateList
	 * @return Candidate - This candidate object represents the tie breaker loser.
	 */
	private Candidate determineTieLoser(LinkedList<Candidate> tieList, LinkedList<Candidate> candidateList) {
		int rankChecker = 2;
		int toRemove = 0;

		while(rankChecker < candidateList.size()) {
			for(Candidate c1 : tieList) {
				for(Candidate c2 : tieList) {
					if(c1.getAmountOf(rankChecker) > c2.getAmountOf(rankChecker) && !c1.equals(c2)) {
						toRemove++;
					}
					if(toRemove == tieList.size() - 1) {
						tieList.remove(c1);
					}
				}
			}
			if(tieList.size() == 1) {
				break;
			}
			rankChecker++;
		}
		if(tieList.size() > 1) {
			return determineBiggerID(tieList);
		}
		return tieList.get(0);
	}
	/**
	 * This method returns the biggest ID from the list.
	 * @param tieList
	 * @return Candidate- This Candidate represent the one with the biggest ID in the list.
	 */
	private Candidate determineBiggerID(LinkedList<Candidate> tieList) {
		Candidate bigger = tieList.get(0);
		for(Candidate c : tieList) {
			if(c.getCandidateID() > bigger.getCandidateID()) {
				bigger = c;
			}
		}
		return bigger;
	}

	private boolean checkForFifty(LinkedList<Candidate> candidateList, LinkedList<Ballot> ballotList) {
		for(Candidate c : candidateList) {
			if(c.getAmountOf(1) >= ballotList.size()/2) {
				winner = c;
				return true;
			}
		}
		return false;
	}


	/**
	 * This method sorts the candidateList.
	 * 
	 * @param candidateList
	 * @return candidateList - Returns the same candidateList but sorted from 
	 * who has the most ones at any given time of the voting process.
	 */
	private LinkedList<Candidate> sortSmallestFirst(LinkedList<Candidate> candidateList,int rank) {
		Candidate temp;
		for(int i = 0; i<candidateList.size(); i++) {
			for(int j = 1; j < candidateList.size(); j++) {
				if(candidateList.get(j-1).getAmountOf(rank) > candidateList.get(j).getAmountOf(rank)) {
					temp = candidateList.get(j-1);
					candidateList.set(j-1, candidateList.get(j));
					candidateList.set(j, temp);
				}
			}
		}
		return candidateList;
	}
}
