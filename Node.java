/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Node {
    int weightSum,tempWeightSum;
    double lowerBound,upperBound;
    Integer idx,state;
    Node parent,left,right;
    String kombinasi,tempKombinasi;
    
    Node(Node parent,Integer state,int weightSum){
        this.state = state;
        this.parent = parent;
        if(parent.idx == null)
        {
            this.idx = 0;
        }
        else
        {
            this.idx = parent.idx+1;
        }
        this.left = null;
        this.right = null;
        this.weightSum = weightSum;
        this.tempWeightSum = weightSum;
        this.lowerBound = 0.0;
        this.upperBound = 0.0;
        if(this.state.equals(1))
        {
            this.kombinasi = parent.kombinasi+"["+this.idx+"]";
            this.tempKombinasi = parent.kombinasi+"["+this.idx+"]";
        }
        else
        {
            this.kombinasi = parent.kombinasi;
            this.tempKombinasi = parent.kombinasi;
        }
    }
    
    Node(Integer state){
        this.state = state;
        this.parent = null;
        this.idx = null;
        this.left = null;
        this.right = null;
        this.weightSum = 0;
        this.lowerBound = 0.0;
        this.upperBound = 0.0;
        this.kombinasi = "";
    }

    public void addChild(Node right,Node left,int capacity){
        if(right.weightSum <= capacity)
        {
            this.right = right;
        }
        else
        {
            this.right = null;
        }
        if(left.weightSum <= capacity)
        {
            this.left= left;
        }
        else
        {
            this.left = null;
        }
    }
}
