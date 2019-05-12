/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Item implements Comparable {
    private int weight;
    private int value;
    
    public Item(int weight,int value){
        this.value = value;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Object o) {
        Item i = (Item) o;
        double pw1 = this.value*1.0/this.weight;
        double pw2 = i.value*1.0/i.weight;
        if(pw1 > pw2)
        {
            return -1;
        }
        else if(pw1 < pw2)
        {
            return 1;
        }
        else
        {
            if(this.weight > i.weight)
            {
                return 1;
            }
            else if(this.weight < i.weight)
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
    }
    
    
}
