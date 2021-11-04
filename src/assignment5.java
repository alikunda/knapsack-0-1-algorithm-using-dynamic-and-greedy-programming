/**
 * Name: Mohammed Ali Kunda
 */


/**
 * this program implement knapsack 0/1 algorithm using dynamic and greedy programming
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author ali kunda
 */
public class assignment5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner key= new Scanner(System.in);
        Random rand = new Random();
        String y_s;

    do{        
        System.out.print("Enter the number of item: ");
        int i = key.nextInt();   //hold the number of items
        i= i+1;
       // System.out.println("");
        System.out.print("Enter the max weight capacity: ");
      //  System.out.println("");
        int maxCap = key.nextInt();     //hold the max weight
        maxCap=maxCap+1;
        int[] weight = new int[i];      //array contains different weights
        int[] value = new int[i];       //array contains different prices for repective weight
        generatevalue(value);

        generateWeight(weight);
        System.out.println("Item\t|\tvalue:\t|\tweight\t|\ti");
        System.out.println("-------------------------------------------------------");
       
        print(value,weight);
        int[][] iw = new int[i][maxCap];
        knapsackDynamic(i, maxCap, weight, value, iw);
        print2D(iw, i, maxCap);
        try {
        fractionalKnapsack( weight, value, maxCap, i);
        }
        catch(Exception e){
            System.out.println("---------------------------------------------------------");
            System.out.println("Error, please restart the program and use different input");
            
        }
        
        
        System.out.println("--------------------the program has been ended------------------------");
        System.out.println("Do you want to run the program again? (Y/N)" );
        y_s= key.next();
        if(y_s.equalsIgnoreCase("No")||y_s.equalsIgnoreCase("N"))
        {
            System.out.println("The program has been termineted.");
        }
    }
    while(y_s.equalsIgnoreCase("Yes")||y_s.equalsIgnoreCase("Y"));
            
    }
    
    public static void generateNum(int[] arr)
    {
        
        Random rand = new Random();
        for(int i = 0; i< arr.length; i++)
        {
         arr[i]= rand.nextInt(50)+1;
         
        }
        
        
        
       
        
    }
    public static void generatevalue(int[] A)
    {
        A[0]=0;
        System.out.println("Enter the values: ");
        Scanner key= new Scanner(System.in);
       for(int index = 1; index < A.length; index++)
       {
          A[index] = key.nextInt();
       }
    }
    public static void generateWeight(int[] A)
    {
        A[0]=0;
        System.out.println("Enter the weights(start with zero): ");
        Scanner key= new Scanner(System.in);
       for(int index = 1; index < A.length; index++)
       {
          A[index] = key.nextInt();
       }
    }
    public static void print(int[] arr1, int[] arr2)
    {
        for(int i = 0; i< arr1.length;i++)
        {
            int counter= i+1;
            System.out.println("\t|\t"+arr1[i]+"\t|\t"+arr2[i]+"\t|\t"+counter);
        }
        
    }
    
     public static void print2D(int[][] arr, int item, int maxCap)
    {
        System.out.println("--------------------------------------------------");
        System.out.println("I:");
          for(int index = 0; index< maxCap;index++)
          {
              System.out.print(index+"\t");
          }
          

          System.out.print("\t");
          System.out.println(" ");
        for(int i = 0; i<item; i++)
        {
            for(int j = 0; j<maxCap; j++)
            {
                System.out.print(arr[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Max knapsack can carry using dynamic algorithm: "+arr[item-1][maxCap-1]);
        System.out.println("----------------------------------------------------------------------");
    }
     public static void knapsackDynamic(int i,int maxCap,int[] weight, int[] val, int[][] v)
    {

        int item_index ;
        int maxCap_index;
        
        for(item_index = 1; item_index<i;item_index++)
        {
            for(maxCap_index = 1; maxCap_index< maxCap;maxCap_index++)
            {
                    if(weight[item_index]>maxCap_index)
                    {
                        v[item_index][maxCap_index]= v[item_index-1][maxCap_index];
                    }
                    else if(weight[item_index]<=maxCap_index) {
                        int counter = item_index - 1;
                        v[item_index][maxCap_index] = Math.max(v[counter][maxCap_index], val[item_index] + v[counter][maxCap_index - weight[item_index]]);
                    }
                }
            }   
    }
    public static void fractionalKnapsack(int[] weight, int[] val, int w, int n)
    {
        ItemValue[] item = new ItemValue[val.length];  //creating object to break it in parts
               
        for(int i = 0; i < val.length; i++)
        {
            item[i]= new ItemValue(val[i],weight[i]);  //array of objects of item values  
        }
        //sorting the arraqy using Arrays class
        Arrays.sort(item,new Comparator<ItemValue>(){
            @Override
            public int compare(ItemValue val1,ItemValue val2)
            {
               return val1.cost>val2.cost?-1:1;
            }
        });

        int load = 0;
        int i = 0;
        int remainder = n;
        double value = 0;
        
         while(load<w && i <= n )
         {   
            if(item[i].weight<(w-load))
            {
                load+=item[i].weight;   //add th weight of item to load
                value+=item[i].value;   //add the value of the item object to var value             
            }
            else{
                remainder = w-load;
                value+=item[i].value*((double)remainder/item[i].weight);
                break;
            }
           i++;
        }
        System.out.printf("Max value %.2f\n",value);
    }   
    static class ItemValue{
         double cost;
         int weight;
         int value;
     
         public ItemValue(int value,int weight)
         {
              this.weight = weight;
              this.value = value;             
              cost = Math.round((double)value/(double)weight);
         }           
    }
}
