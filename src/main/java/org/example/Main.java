package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PanfArrList<String> panfArrList = new PanfArrList<>();
        System.out.println("size at the start = " + panfArrList.size());
        System.out.println("isempty? " + panfArrList.isEmpty());
        for (int i = 0; i < 10; i++) {
            panfArrList.add("One " + i);
        }
        panfArrList.add(10, "fdfdf");
        System.out.println("size = " + panfArrList.size());
        System.out.println(panfArrList);
        System.out.println(panfArrList.contains("One 2"));
        System.out.println(panfArrList.get(2));
        panfArrList.add(2, "New 666");
        System.out.println(panfArrList);
        System.out.println("size = " + panfArrList.size());
        System.out.println("removed " + panfArrList.remove(10));
        System.out.println("removed " + panfArrList.remove(8));
        System.out.println(panfArrList);
        System.out.println("size = " + panfArrList.size());
//        panfArrList.clear();
//        System.out.println(panfArrList);
//        System.out.println("size = " + panfArrList.size());
//        panfArrList.add("New data");
//        System.out.println(panfArrList);
        System.out.println("size = " + panfArrList.size());
//        System.out.println(list.indexOf("dkdr"));
        System.out.println(panfArrList.printList());
        System.out.println(panfArrList.get(-1));
        panfArrList.trimToSize();
        System.out.println(panfArrList.printList());
    }
}