package com.scaler.productservice;

import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        List<Integer> list1 = List.of(1,2,3,4,5);
        List<String> list2 = List.of("Hello","Hey","You","Piyush");
        int a[] = {1,2,3,4,5};
        System.out.println(list1.getClass().getName());
        System.out.println(list2.getClass().getName());
        System.out.println(a.getClass().getName());
    }
}
