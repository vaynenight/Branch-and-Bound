
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class ResultComparator implements Comparator<Node>{
    
    @Override
    public int compare(Node o1, Node o2) {
        if(o1.lowerBound > o2.lowerBound)
        {
            return 1;
        }
        else if(o1.lowerBound < o2.lowerBound)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}
