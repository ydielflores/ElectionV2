package Main;

import java.io.FileNotFoundException;

import Resources.FileManager;

public class Election {
	public static FileManager fileManager = new FileManager();
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		fileManager.start(fileManager.ballots2, fileManager.candidates);
	}

}
