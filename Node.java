/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astarsearch;

/**
 *
 * @author Austen
 */
public class Node implements Comparable<Node>{

    private int row, col, f, g, h, type;
    private Node parent;

    public Node(int r, int c, int t){
            row = r;
            col = c;
            type = t;
            parent = null;
            //type 0 is traverseable, 1 is not
    }

    //mutator methods to set values
    public void setF(){
            f = g + h;
    }
    public void setG(int value){
            g = value;
    }
    public void setH(int value){
            h = value;
    }
    public void setParent(Node n){
            parent = n;
    }

    //accessor methods to get values
    public int getF(){
            return f;
    }
    public int getG(){
            return g;
    }
    public int getH(){
            return h;
    }
    public Node getParent(){
            return parent;
    }
    public int getRow(){
            return row;
    }
    public int getCol(){
            return col;
    }
    
    public boolean canTraverse(){
        return type == 0;
    }
    
    @Override
    public boolean equals(Object in){
            //typecast to Node
            Node n = (Node) in;

            return row == n.getRow() && col == n.getCol();
    }
    
    @Override
    public int compareTo(Node n){
        if(this.f < n.getF()){
            return -1;
        }
        return 1;
    }
    
    @Override
    public String toString(){
            return "Node: " + row + "_" + col;
    }
    
}

