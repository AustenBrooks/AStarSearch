/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astarsearch;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Austen
 */
public class AStarSearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //set the board size
        final int BOARD_SIZE = 15;
        
        //construct the 15X15 board
        Node[][] board = new Node[BOARD_SIZE][BOARD_SIZE];
        Random r = new Random();
        for(int i=0; i<BOARD_SIZE; i++){
            for(int j=0; j<BOARD_SIZE; j++){
                int t = r.nextInt(10);
                if(t == 0){
                    t = 1;
                }
                else
                    t = 0;
                board[i][j] = new Node(i,j,t);
            }
        }
        
        //display the board, with X to mark unpassable tiles
        String boardStr = "";
        for(int i=0; i<BOARD_SIZE; i++){
            for(int j=0; j<BOARD_SIZE; j++){
                if(board[i][j].canTraverse())
                    boardStr += "[ ]";
                else
                    boardStr += "[X]";
            }
            boardStr += "\n";
        }
        System.out.println(boardStr);
        
        
        //run the algorithm loop
        Scanner scnr = new Scanner(System.in);
        while(true){
            //get the start position
            System.out.println("Enter the starting row: ");
            int startRow = scnr.nextInt();
            System.out.println("Enter the starting column: ");
            int startCol = scnr.nextInt();

            //get the goal position
            System.out.println("Enter the ending row: ");
            int endRow = scnr.nextInt();
            System.out.println("Enter the ending column: ");
            int endCol = scnr.nextInt();
            scnr.nextLine();
            
            //call the algorithm
            AStarAlgorithm search = new AStarAlgorithm(board, endRow, endCol, BOARD_SIZE);
            
            //print results
            String solution = search.runSearch(startRow, startCol);
            System.out.println(solution);
            
            System.out.println("Would you like to see the solution displayed? Type 0 for no or 1 for yes)");
            int choice = scnr.nextInt();
            
            if(choice == 1 && solution != "No solution found"){
                Scanner path = new Scanner(solution);
                while(path.hasNext()){
                    String next = path.next();
                    
                    if(next.contains("_")){
                        String coords = next.replace('_', ' ');
                        Scanner position = new Scanner(coords);
                        
                        int agentRow = position.nextInt();
                        int agentCol = position.nextInt();
                        
                        String agentBoard = "";
                        for(int i=0; i<BOARD_SIZE; i++){
                            for(int j=0; j<BOARD_SIZE; j++){
                                if(i == agentRow && j == agentCol){
                                    agentBoard += "[A]";
                                }
                                else if(board[i][j].canTraverse())
                                    agentBoard += "[ ]";
                                else
                                    agentBoard += "[X]";
                            }
                            agentBoard += "\n";
                        }
                        System.out.println(agentBoard);
                        
                        try {
                            sleep(3000);
                        }
                        catch (InterruptedException ex) { 
                        }
                    }
                    
                }
            }
        }
    }
}
