/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Data {
    String indexBarang;
    int size,value,weight;
    
    Data(){
        this.indexBarang = new String();
        this.size = 0;
        this.value = 0;
        this.weight = 0;
    }
    
    public void addIdx(int idx){
        this.indexBarang += "["+idx+"]";
        this.size++;
    }
    
    public void addValue(int value){
        this.value = value;
    }
    
    public void addWeight(int weight){
        this.weight = weight;
    }
}
