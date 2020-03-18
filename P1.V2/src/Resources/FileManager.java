package Resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Ballot.Ballot;
import Ballot.Candidate;
import Ballot.CastedVotes;
import Ballot.Regroup;
import DataStructures.LinkedList.LinkedList;
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
	
	private int totalBallots = 0;
	private int totalBlankBallots = 0;
	private int totalInvalidBallots = 0;
	
	public FileManager() {
		
		ballots = new File("res/ballots/ballots.csv");
		ballots2 = new File("res/ballots/ballots2.1.csv");
		candidates = new File("res/candidates/candidates.csv");
		
	}
	
	public void start(File ballotDocument, File candidateDocument) throws FileNotFoundException {
		ballotBuilder(ballotDocument);
		candidateBuilder(candidateDocument);
		
		printBallotIDs(getBallotList());
		System.out.println(totalBallots);
		System.out.println(totalBlankBallots);
	}
	

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
	private int findBallotID(String s) {
		Integer toInt = new Integer(s.substring(0, s.indexOf(",")));
		return toInt.intValue();
	}
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
			toInt = new Integer (editorRank.substring(2,3));
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
			candidate = new Candidate(editorName.substring(0, editorName.indexOf(",")), ID, new LinkedList<Regroup>());
			candidateList.add(candidate);
		}
	
		sc.close();
	}

	private void printBallotIDs(LinkedList<Ballot> list) {
		
		for(Ballot b : list) {
			System.out.println(b.getBallotID());
			printCastedVotes(b.getCastedVotes());
		}
	}
	
	private void printCastedVotes(LinkedList<CastedVotes> list) {
		for(CastedVotes cv : list) {
			System.out.println("ID : " + cv.getCandidateID() + " Rank : " + cv.getRank());
		}
	}
	
	
	
	
	public int getTotalBallots() {
		return totalBallots;
	}
	public int getBlankBallots() {
		return totalBlankBallots;
	}
	public int getInvalidBallots(){
		return totalInvalidBallots;
	}
	public LinkedList<Candidate> getCandidateList(){
		return candidateList;
	}
	public LinkedList<Ballot> getBallotList(){
		return ballotList;
	}
}
 