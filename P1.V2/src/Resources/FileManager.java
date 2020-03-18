package Resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Ballot.Ballot;
import Ballot.Candidate;
import Ballot.CastedVotes;
import DataStructures.LinkedList.LinkedList;

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
		System.out.println("Wait until implemented");
		return null;
	}
	
	private void candidateBuilder(File candidateDocument) throws FileNotFoundException{
		candidateList = new LinkedList<Candidate>();
		Scanner sc = new Scanner(candidateDocument);
		String editor;
		
//		while(sc.hasNextLine()) {
//			
//		}
		
		sc.close();
	}
	
	private void printBallotIDs(LinkedList<Ballot> list) {
		
		System.out.println("BallotIDs");
		for(Ballot b : list) {
			System.out.println(b.getBallotID());
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
 