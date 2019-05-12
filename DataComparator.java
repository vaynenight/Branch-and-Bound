
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
public class DataComparator implements Comparator<Data>{
    @Override
    public int compare(Data o1, Data o2) {
        if(o1.value < o2.value)
        {
            return -1;
        }
        else if(o1.value > o2.value)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
