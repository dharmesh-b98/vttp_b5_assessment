package vttp.batch5.sdf.task02;

import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		String boardPath = " ";
		try{
			boardPath = args[0];
		}catch(ArrayIndexOutOfBoundsException e){
			System.err.println("Please add board path as an arguement");
			throw e;
		}

		//System.out.printf("hello, world\n");
		System.out.println("Processing: " + boardPath + "\n");
		char[][] board = BoardReader.loadBoard(boardPath);
		System.out.println("Board: ");
		BoardReader.printBoard(board);
		System.out.println("----------------------------");

		List<List<Integer>> emptyPosList = BoardReader.getAllEmptyPos(board);
		List<List<Integer>> utilityList = BoardReader.getUtility(board,emptyPosList);
		BoardReader.printUtilityList(utilityList);
		

		
	}
}
