
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Knapsack {
    private Node root;
    private int[] bitString;
    private int maxWeight;
    private Item[] item;
    private BufferedWriter writer = null;
    private String data1,data2;
    private PriorityQueue<Node> result1;
    private PriorityQueue<Node> result2;
    private PriorityQueue<Data> result3;
    private PriorityQueue<Node> pq;
    private PriorityQueue<Data> pq1;
    
    public Knapsack(int mw,Item[] item){
        this.maxWeight = mw;
        this.item = item;
        this.data1 = new String();
        this.data2 = new String();
        this.root = new Node(null);
        this.bitString = new int[item.length];
        Comparator<Node> compare = new NodeComparator();
        Comparator<Node> compare1 = new ResultComparator();
        Comparator<Data> compare2 = new DataComparator();
        this.pq = new PriorityQueue<Node>(compare);
        this.pq1 = new PriorityQueue<Data>(compare2);
        this.result1 = new PriorityQueue<Node>(compare1);
        this.result2 = new PriorityQueue<Node>(compare1);
        this.result3 = new PriorityQueue<Data>(compare2);
        Arrays.sort(this.item);
    }
    
    public int getItemSize(){
        return this.item.length;
    }
    
    public void calcUpperLowerBound(Node n){
        double upper = 0.0;
        double lower = 0.0;
        double weight = 0.0;
        int i = 0;
        Node parent = n.parent;
            if(n.state == 1 && parent.parent == null)
            {
                weight = n.weightSum;
                upper = item[n.idx].getValue();
                lower = item[n.idx].getValue();
                i = n.idx+1;
            }
            else if(n.state == 0 && parent.parent == null)
            {
                weight = n.weightSum;
                i = n.idx+1;
            }
            else if(n.state == 1)
            {
                weight = n.weightSum;
                upper = this.totalRate(n)+item[n.idx].getValue();
                lower = this.totalRate(n)+item[n.idx].getValue();
                i = n.idx+1;
            }
            else if(n.state == 0)
            {
                weight = n.weightSum;
                upper = this.totalRate(n);
                lower = this.totalRate(n);
                i = n.idx+1;
            }
            while(weight < maxWeight && i < item.length)
                {
                    if(weight + item[i].getWeight() <= maxWeight)
                    {
                        weight += item[i].getWeight();
                        upper += item[i].getValue();
                        lower += item[i].getValue();
                        n.tempKombinasi += "["+i+"]";
                        n.tempWeightSum += item[i].getWeight();
                    }
                    else
                    {
                        upper += ((maxWeight-weight)*1.0/item[i].getWeight())*item[i].getValue();
                        weight = maxWeight;
                    }
                    i++;
                }
            n.upperBound = upper;
            n.lowerBound = lower;
    }
    
    public void processLeftRight(Node right,Node left){
        if(left != null && right != null)
        {
            this.calcUpperLowerBound(left);
            this.calcUpperLowerBound(right);
            pq.add(left);
            pq.add(right);
        }
        else if(right != null)
        {
            this.calcUpperLowerBound(right);
            pq.add(right);
        }
        else if(left != null)
        {
            this.calcUpperLowerBound(left);
            pq.add(left);
        }
    }
    
    public void searchBnB(int k){
        Node left = new Node(root,0,0);
        Node right = new Node(root,1,item[0].getWeight());
        root.addChild(right, left, maxWeight);
        processLeftRight(root.right,root.left);
        while(!pq.isEmpty() && this.result1.size() < k)
        {
            Node temp = pq.poll();
            if(this.result1.size() == k-1)
            {
                if(temp.upperBound == temp.lowerBound)
                {
                    temp.kombinasi = temp.tempKombinasi;
                    temp.weightSum = temp.tempWeightSum;
                    this.result1.add(temp);
                }
                else
                {
                    left = new Node(temp,0,temp.weightSum);
                    right = new Node(temp,1,temp.weightSum+item[temp.idx+1].getWeight());
                    temp.addChild(right, left, maxWeight);
                    processLeftRight(temp.right,temp.left);
                }
            }
            else if(temp.idx == item.length-1)
            {
                this.result1.add(temp);
            }
            else
            {
                left = new Node(temp,0,temp.weightSum);
                right = new Node(temp,1,temp.weightSum+item[temp.idx+1].getWeight());
                temp.addChild(right, left, maxWeight);
                processLeftRight(temp.right,temp.left);
            }
        }
        this.result2 = result1;
    }
    
    public PriorityQueue<Node> getResultBnB(){
        this.pq.clear();
        return this.result2;
    }
    
    public PriorityQueue<Data> getResultES(){
        this.pq1.clear();
        return this.result3;
    }
    
    public double totalRate(Node temp){
        double result = 0;
        Node parent = temp.parent;
        while(parent.idx != null)
        {
            if(parent.state.equals(1))
            {
                result += item[parent.idx].getValue();
            }
            parent = parent.parent;
        }
        return result;
    }
    
    public String getItem(){
        String s = new String();
        for(int i = 0;i < item.length;i++)
        {
            s += "["+item[i].getValue()+","+item[i].getWeight()+"]";
        }
        return s;
    }
    
    public Item[] getItem2(){
        return this.item;
    }
    
    public void findBnB2(int k){
        this.searchBnB(k);
    }
    
    public void findBnB(int k){
        this.searchBnB(k);
        while(!result1.isEmpty())
        {
            Node temp = result1.poll();
            System.out.println(temp.kombinasi+" "+temp.weightSum+" "+temp.lowerBound);
        }
    }
    
    public void process(int k){
        int hasil = 0;
        int berat = 0;
        Data data = new Data();
        for(int i = 0;i < this.bitString.length;i++)
        {
            if(bitString[i] == 1)
            {
                hasil += item[i].getValue();
                berat += item[i].getWeight();
                data.addIdx(i);
            }
        }
        data.addValue(hasil);
        data.addWeight(berat);
        if(berat <= maxWeight)
        {
            if(this.pq1.size() < k)
            {
                this.pq1.add(data);
                this.result3.add(data);
            }
            else
            {
                Data temp = this.pq1.peek();
                if(temp.value < hasil)
                {
                    this.pq1.poll();
                    this.result3.poll();
                    this.pq1.add(data);
                    this.result3.add(data);
                }
            }
        }
    }
    
    public void bitSearch(int counter,int k,int weight){
            if(counter == this.bitString.length)
            {
                process(k);
            }
            else
            {
                this.bitString[counter] = 0;
                bitSearch(counter+1,k,0);
                this.bitString[counter] = 1;
                bitSearch(counter+1,k,weight+item[counter].getWeight());
            }
    }
    
    public void searchES(int k){
        this.bitSearch(0,k,0);
    }
    
    public void print(){
        while(!this.pq1.isEmpty())
        {
            Data temp = this.pq1.poll();
            System.out.println(temp.indexBarang+" "+temp.weight+" "+temp.value);
        }
        this.pq1.clear();
    }
    
    public void finsdEs(int k){
        searchES(k);
        print();
    }
    
    public void run(int k){
        System.out.println("Exhaustive Search");
        long startTime = System.nanoTime();
        this.finsdEs(k);
        long endTime   = System.nanoTime();
        long EStime = (endTime-startTime);
        System.out.println(EStime);
        System.out.println("Branch and Bound");
        startTime = System.nanoTime();
        this.findBnB(k);
        endTime   = System.nanoTime();
        long BNBtime = (endTime-startTime);
        System.out.println(BNBtime);
        data1 += EStime+"\n";
        data2 += BNBtime+"\n";
        this.pq.clear();
    }
    
    public void write2(int i) throws IOException{
        this.writer = new BufferedWriter(new FileWriter("./kasus"+i+".txt"));
        try {
            writer.write(data1+data2);
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        }
        data1 = new String();
        data2 = new String();
    }
}
    
