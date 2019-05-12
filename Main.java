
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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = new String();
        
        Random r = new Random();
        int c = sc.nextInt();
        Item[] barang = new Item[n];
        for(int i = 0;i < n;i++)
        {
            int x = sc.nextInt();
            int y = sc.nextInt();
            barang[i] = new Item(x,y);
        }
        Knapsack knap = new Knapsack(c,barang);
        int k = sc.nextInt();
            while(k <= Math.pow(2,knap.getItemSize()) && k != 0)
            {
                System.out.println(knap.getItem());
                knap.run(k);
                k = sc.nextInt();
            }
        
    }
    
}
