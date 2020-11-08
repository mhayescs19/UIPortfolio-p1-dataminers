package view_control;

import java.util.Scanner;

public class WeightConverter {
    public static void main(String[] args) {

        Scanner sc=new Scanner (System.in);
        System.out.println("Select a weight conversion:\t");
        System.out.println("1. Pounds (lbs) to Kilograms (kg)");
        System.out.println("2. Kilograms (kg) to Pounds (lbs)");
        int n=sc.nextInt();
        System.out.println("Enter the numerical weight to be converted:");
        double d=sc.nextDouble();
        String st;
        double result;
        if(n==1)
        {result=d*0.4536;
            st="kg";}
        else
        {  result=d*2.2046;
            st="lbs";}
        System.out.println("Converted weight: "+result+" "+st);

    }
}