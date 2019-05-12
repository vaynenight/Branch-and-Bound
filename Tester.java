
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
class Solution{
     HashMap<Integer,Integer> hm;
    public int solution(int[] A) {
        int result = 1;
        if(A.length == 1)
        {
            if(A[0] > 0)
            {
                result = A[0];
            }
        }
        else
        {
            hm = new HashMap<Integer,Integer>();
            Arrays.sort(A);
            hm.put(A[0],1);
            for(int i = 1;i < A.length;i++)
            {
                if(hm.containsKey(A[i]))
                {
                    int temp = hm.get(A[1]).intValue();
                    hm.replace(A[i],temp+1);
                }
                else
                {
                    hm.put(A[i],1);
                }
            
                if(!(A[i]-A[i-1] == 0 || A[i]-A[i-1] == 1))
                {
                    if(A[i] > 0)
                    {
                        if(A[i-1] <= 0)
                        {
                            result = 1;
                        }
                        else
                        {
                            result = A[i-1]+1;
                        }
                        break;
                    }   
                }
            }
            if(count(A) == A.length)
            {
                if(A[A.length-1]<0)
                {
                    result = 1;
                }
                else
                {
                    result = A[A.length-1]+1;
                }
            }
        }
        return result;
    }
    
    public int count(int[] A){
        int count = 0;
        int idx = 0;
        count += hm.get(A[0]);
        for(int i = 1;i < A.length;i++)
        {
            if(idx != i)
            {
                idx = i;
                count += hm.get(A[i]);
            }
        }
        return count;
    }
}

public class Tester {

    
    
    public static void main(String[] args) {
        int[] a = {1,3,6,4,1,2};
        int b = -2;
        int c = -1;
        Solution s = new Solution();
        System.out.println(s.solution(a));
        System.out.println(b-c);
    }
    
}
