package com.ikenna.interswitchclasses;

import java.util.Scanner;

public class LeapYear {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int y;

        System.out.print("Enter year: ");
        y = scanner.nextInt();
        if (!((y%400==0) || ((y%4==0) && (y%100 !=0)))){
            System.err.println(y + " is not a leap year");
        }
        else
            System.out.println(y + " is a leap year");
    }
}
