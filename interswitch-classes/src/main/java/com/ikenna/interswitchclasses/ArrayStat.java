package com.ikenna.interswitchclasses;

public class ArrayStat {
    static void min(int arr[]){
        int min = arr[0];
        for (int i = 0; i < arr.length;  i++)
            if (min>arr[i])
                min = arr[i];
            System.out.println(min);
        }

    public static void main(String[] args) {
        int a[]= {2,33,3,4,0,5};
        min(a);
    }
}
