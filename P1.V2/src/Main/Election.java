package Main;

import java.io.FileNotFoundException;
import java.io.IOException;

import Resources.FileManager;

public class Election {
	public static FileManager fileManager = new FileManager();
	public static CountingProcess cp = new CountingProcess();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		fileManager.start(fileManager.ballots, fileManager.candidates);
		cp.startCountingProcess(fileManager.getCandidateList(), fileManager.getBallotList(), fileManager.getTotalBallots(),fileManager.getBlankBallots(),fileManager.getInvalidBallots());
	}

}
