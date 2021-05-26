/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astarsearch;

import static java.lang.Math.abs;
import java.util.Hashtable;
import java.util.PriorityQueue;

/**
 *
 * @author Austen
 */
public class AStarAlgorithm {
    private Node[][] board;
    private int endRow, endCol;
    int BOARD_SIZE;
    
    PriorityQueue<Node> openList = new PriorityQueue<Node>();
    Hashtable<Node, Boolean> closedList = new Hashtable<Node, Boolean>();

    public AStarAlgorithm(Node[][] board, int endRow, int endCol, int BOARD_SIZE) {
        this.board = board;
        this.endRow = endRow;
        this.endCol = endCol;
        this.BOARD_SIZE = BOARD_SIZE;
    }
    
    public String runSearch(int startRow, int startCol){
        calcH(startRow, startCol);
        board[startRow][startCol].setG(0);
        board[startRow][startCol].setF();
        
        openList.add(board[startRow][startCol]);
        
        if(searchNode(openList.peek())){
            return returnPath(board[endRow][endCol]);
        }
        
        String path = "No solution found";
        return path;
    }
    
    public boolean searchNode(Node currentNode){
        //pop node from list
        openList.poll();
        
        //is current node goal?
        if(currentNode.getRow() == endRow && currentNode.getCol() == endCol){
            return true;
        }
        
        //make sure the board is updated (shouldnt be needed, but just in case)
        board[currentNode.getRow()][currentNode.getCol()] = currentNode;
        
        //search adjacent tiles
        for(int rowMod = -1; rowMod < 2; rowMod++){
            for(int colMod = -1; colMod < 2; colMod++){
                int newRow = rowMod + currentNode.getRow();
                int newCol = colMod + currentNode.getCol();
                
                //make sure tile is within the boundries
                if(newRow < 0 || newRow >= BOARD_SIZE ||  newCol < 0 || newCol >= BOARD_SIZE){
                }
                //ignore the current tile
                else if(newRow == currentNode.getRow() && newCol == currentNode.getCol()){
                }
                //if the tile has not been explored
                else if(!(closedList.containsKey(board[newRow][newCol]))){
                    //if the node is not in the open list and traversable, add it to the open list
                    if(!(openList.contains(board[newRow][newCol])) && board[newRow][newCol].canTraverse()){
                        calcH(newRow, newCol);
                        
                        //calc g cost
                        int parentCost = currentNode.getG();
                        
                        if(colMod == 0 || rowMod == 0) //non diagonals
                            board[newRow][newCol].setG(parentCost + 10);
                        else //diagonals
                            board[newRow][newCol].setG(parentCost + 14);
                        
                        //set F value, store parent and add to open list
                        board[newRow][newCol].setF();
                        board[newRow][newCol].setParent(currentNode);
                        openList.add(board[newRow][newCol]);
                    }
                    //if the node is already in the open list, check if it needs to update cost
                    else if(board[newRow][newCol].canTraverse()){
                        //get current node cost
                        int currentCost = currentNode.getG();
                        
                        if(colMod == 0 || rowMod == 0) //non diagonals
                            currentCost += 10;
                        else //diagonals
                            currentCost += 14;
                        
                        if(currentCost < board[newRow][newCol].getG()){
                            board[newRow][newCol].setParent(currentNode);
                            board[newRow][newCol].setG(currentCost);
                            board[newRow][newCol].setF();
                        }
                    }
                }
            }
        }
        
        //add current node to closed list
        closedList.put(currentNode, true);
        
        //continue searching
        if(!(openList.isEmpty())){
            return searchNode(openList.peek());
        }
        
        //return false if no remaining available nodes
        return false;
    }
        
    public void calcH(int i, int j){
        int H = abs(i - endRow) * 10 + abs(j - endCol) * 10;
        board[i][j].setH(H);
    }
    
    public String returnPath(Node n){
        if(n.getParent() == null){
            return n.toString();
        }
        return returnPath(n.getParent()) + " -> " + n.toString();
    }
}