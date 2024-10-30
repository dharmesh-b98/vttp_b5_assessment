package vttp.batch5.sdf.task02;

import java.io.*;
import java.util.*;

public class BoardReader {
    public static char[][] loadBoard(String dataPath) throws IOException{
        char[][] board = new char[][]{{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '}};
        FileReader fr = new FileReader(dataPath);
        BufferedReader br = new BufferedReader(fr);

        String line;
        int i = 0;
        while ((line = br.readLine()) != null){
            char[] lineArray = line.toCharArray();
            board[i] = lineArray;
            i++;
            
        }
        return board;        
    }



    public static void printBoard(char[][] board){
        for (int i = 0; i<3; i++){
            System.out.println(String.valueOf(board[i][0]) +' '+String.valueOf(board[i][1]) +' '+String.valueOf(board[i][2]));
        }
    }



    public static boolean isSpotEmpty(char[][] board, Integer rowSpot,Integer columnSpot){
        if (board[rowSpot][columnSpot] == '.'){
            return true;
        }
        return false;
    }



    public static List<List<Integer>> getAllEmptyPos(char[][] board){
        List<List<Integer>> emptyPosList = new ArrayList<>();
        for (int row = 0 ; row < 3; row ++){
            for (int column = 0; column < 3; column ++){
                if (isSpotEmpty(board, row, column)){
                    List<Integer> position = new ArrayList<>();
                    position.add(row);
                    position.add(column);
                    emptyPosList.add(position);
                }
            }
        }
        return emptyPosList;
    }



    public static boolean didPlayerWin(char[][] board, char player){
        //horizontal
        for (int row = 0; row < 3 ;row++ ){
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player){
                return true;
            }
        }
        //vertical
        for (int column = 0; column < 3 ;column++ ){
            if (board[0][column] == player && board[1][column] == player && board[2][column] == player){
                return true;
            }
        }
        //diagonal
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[2][0] == player && board[1][1] == player && board[0][2] == player) {
            return true;
        }
        
        return false;
    }



    public static boolean isBoardFull(char[][] board){
        for (int row = 0 ; row < 3; row ++){
            for (int column = 0; column < 3; column ++){
                if (board[row][column] == '.'){
                    return false;
                }
            }
        }
        return true;
    }



    public static List<List<Integer>> getUtility(char[][] board, List<List<Integer>> emptyPosList){
        List<List<Integer>> utilityList = new ArrayList<>();
        for (List<Integer> emptyPos : emptyPosList){
            Integer posX = emptyPos.get(0);
            Integer posY = emptyPos.get(1);
            Integer utility = Integer.MIN_VALUE;
            char[][] newBoard = new char[3][3]; //deepcopy of board
            for (int row = 0 ; row < 3; row ++){
                for (int column = 0; column < 3; column ++){
                    newBoard[row][column] = board[row][column];
                }
            }

            newBoard[posX][posY] = 'X';
            
            System.out.println("__________________");
            printBoard(newBoard); //////////////////////print
            System.out.println("__________________");

            if (didPlayerWin(newBoard, 'X')){
                utility = 1;
            }
            else if (isBoardFull(newBoard)){
                utility = 0;
            }
            else{//board is not full
                List<List<Integer>> nextEmptyPosList = getAllEmptyPos(newBoard);
                nextEmptyPosList.forEach(x -> x.forEach(y->System.out.print(y)));
                System.out.println(" "); ////////////print
                for (List<Integer> nextEmptyPos : nextEmptyPosList){
                    Integer nextPosX = nextEmptyPos.get(0);
                    Integer nextPosY = nextEmptyPos.get(1);
                    char[][] nextNewBoard = new char[3][3]; //deepcopy of newBoard
                    for (int row = 0 ; row < 3; row ++){
                        for (int column = 0; column < 3; column ++){
                            nextNewBoard[row][column] = newBoard[row][column];
                        }
                    }

                    nextNewBoard[nextPosX][nextPosY] = 'O';
                    System.out.println("<><><>"); //////////////print
                    printBoard(nextNewBoard);
                    System.out.println("<><><>");
                    if (didPlayerWin(nextNewBoard, 'O')){
                        utility = -1;
                        break;
                    }
                    else { // if (isBoardFull(nextNewBoard))
                        utility = 0;
                        
                    }
                }
            }
            List<Integer> utilityEntry = new ArrayList<>();
            utilityEntry.add(posX);
            utilityEntry.add(posY);
            utilityEntry.add(utility);
            utilityList.add(utilityEntry);
        }
        return utilityList;
    }


    public static void printUtilityList(List<List<Integer>> utilityList){
        for (List<Integer> utilityEntry: utilityList){
            System.out.println("y=%d, x=%d, utility=%d".formatted(utilityEntry.get(0),utilityEntry.get(1),utilityEntry.get(2)));
        }
    }


}
