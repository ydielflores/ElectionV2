package Resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Ballot.Ballot;
import Ballot.BallotValidation;
import Ballot.Candidate;
import Ballot.CastedVotes;
import Ballot.Regroup;
import DataStructures.LinkedList.LinkedList;
import Main.CountingProcess;
/**
 * 
 * @author Ydiel Zaid Flores Torres
 *
 * This class is responsible for loading, reading and decomposing the files.
 */
public class FileManager {
	
	public File ballots;
	public File ballots2;
	public File candidates;
	
	private LinkedList<Ballot> ballotList;
	private LinkedList<Candidate> candidateList;
	
	private BallotValidation ballotValidation = new BallotValidation();
	
	
	public int totalBallots = 0;
	public int totalBlankBallots = 0;
	public int totalInvalidBallots = 0;
	
	public FileManager() {
		
		ballots = new File("res/ballots/ballots.csv");
		ballots2 = new File("res/ballots/ballots2.1.csv");
		candidates = new File("res/candidates/candidates.csv");
		
	}
	/**
	 * This method starts the building the lists.
	 * @param ballotDocument
	 * @param candidateDocument
	 * @throws IOException
	 */
	public void start(File ballotDocument, File candidateDocument) throws IOException {
		ballotBuilder(ballotDocument);
		candidateBuilder(candidateDocument);
		
		ballotList = ballotValidation.validate(ballotList, candidateList);
		totalInvalidBallots = ballotValidation.getInvalidBallots();
		
		fillCanidateVotesList(ballotList, candidateList);
		
	}
	
	/**
	 * This method fills the CastedVotes of every candidate.
	 * @param ballotList
	 * @param candidateList
	 */
	private void fillCanidateVotesList(LinkedList<Ballot> ballotList, LinkedList<Candidate> candidateList) {
		
		Regroup regroup;
		
		for(Candidate c : candidateList) {
			for(Ballot b : ballotList) {
				for(CastedVotes cv : b.getCastedVotes()) {
					if(c.getCandidateID() == cv.getCandidateID()) {
						regroup = new Regroup(b.getBallotID(), cv.getRank());
						c.getVotesReceived().add(regroup);
					}
				}
			}
		}
		
	}
	
	/**
	 * This method builds the ballot list.
	 * @param ballotDocument
	 * @throws FileNotFoundException
	 */
	private void ballotBuilder(File ballotDocument) throws FileNotFoundException{
		ballotList = new LinkedList<Ballot>();
		Scanner sc = new Scanner(ballotDocument);
		String editor;
		Ballot ballot;
		while(sc.hasNextLine()) {
			totalBallots++;
			editor = sc.nextLine();
			if(editor.indexOf(",") > 0) {
				ballot = new Ballot(findBallotID(editor),buildCastedVotesList(editor.substring(editor.indexOf(",") + 1)));
				ballotList.add(ballot);
			}else {
				totalBlankBallots++;
			}
		}
		
		sc.close();
	}
	/**
	 * This method finds the ballot ID.
	 * @param s
	 * @return
	 */
	private int findBallotID(String s) {
		Integer toInt = new Integer(s.substring(0, s.indexOf(",")));
		return toInt.intValue();
	}
	/**
	 * This method builds the casted votes for every ballot.
	 * @param s
	 * @return
	 */
	private LinkedList<CastedVotes> buildCastedVotesList(String s){
		LinkedList<CastedVotes> castedVotesList = new LinkedList<CastedVotes>();
		CastedVotes castedVotes;
		
		int candidateID;
		int rank;
		Integer toInt;
		
		String editorID;
		String editorRank;
		while(s.length()>0) {
			editorID = s;
			editorRank = s;
			toInt = new Integer(editorID.substring(0, 1));
			candidateID = toInt.intValue();
			toInt = new Integer (editorRank.substring(editorRank.indexOf(":") + 1,editorRank.indexOf(",")));
			rank = toInt.intValue();
			castedVotes = new CastedVotes(candidateID, rank);
			castedVotesList.add(castedVotes);
			s = s.substring(s.indexOf(",") + 1);
			if(s.indexOf(",") < 0) {
				editorID = s;
				editorRank = s;
				toInt = new Integer(editorID.substring(0, 1));
				candidateID = toInt.intValue();
				toInt = new Integer (editorRank.substring(2,3));
				rank = toInt.intValue();
				castedVotes = new CastedVotes(candidateID, rank);
				castedVotesList.add(castedVotes);
				break;
			}
		}
		
		return castedVotesList;
	}
	
	/**
	 * This method starts building the candidate list.
	 * @param candidateDocument
	 * @throws FileNotFoundException
	 */
	private void candidateBuilder(File candidateDocument) throws FileNotFoundException{
		candidateList = new LinkedList<Candidate>();
		
		Scanner sc = new Scanner(candidateDocument);
		
		String editorName;
		String editorID;
		
		Candidate candidate;
		
		Integer toInt;
		int ID;
		
		while(sc.hasNextLine()) {
			editorName = sc.nextLine();
			editorID = editorName;
			toInt = new Integer(editorID.substring(editorID.indexOf(",") +1));
			ID = toInt.intValue();
			candidate = new Candidate(editorName.substring(0, editorName.indexOf(",")), ID, new LinkedList<Regroup>(),0);
			candidateList.add(candidate);
		}
	
		sc.close();
	}
	
	
	
	/**
	 * This method returns the total ballots in a file.
	 * @return int
	 */
	public int getTotalBallots() {
		return totalBallots;
	}
	/**
	 * This method returns the amount of blank ballots.
	 * @return int
	 */
	public int getBlankBallots() {
		return totalBlankBallots;
	}
	/**
	 * This method returns the amount of invalid ballots.
	 * @return int
	 */
	public int getInvalidBallots(){
		return totalInvalidBallots;
	}
	/**
	 * This method returns the candidate list.
	 * @return
	 */
	public LinkedList<Candidate> getCandidateList(){
		return candidateList;
	}
	/**
	 * This method returns the ballot list.
	 * @return
	 */
	public LinkedList<Ballot> getBallotList(){
		return ballotList;
	}
}
 