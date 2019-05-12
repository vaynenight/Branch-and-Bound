
import java.io.IOException;
import java.util.Random;
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
public class Main2 {
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Random r = new Random();
        int c = sc.nextInt();
        Item[] barang = new Item[n];
        for(int i = 0;i < n;i++)
        {
            int x = sc.nextInt();
            int y = sc.nextInt();
            barang[i] = new Item(x,y);
        }
        int j;
        if(n*10 <= Math.pow(2,n))
        {
            j = n*10;
        }
        else
        {
            j = (int)Math.pow(2,n);
        }
        Knapsack knap = new Knapsack(c,barang);
        int k = 1;
        while(k <= j && k != 0)
        {
                System.out.println(knap.getItem());
                if(k == j)
                {
                    k = (int)Math.pow(2,n);
                }
                knap.run(k);
                k++;
        }
        knap.write2(1);
    }
}
