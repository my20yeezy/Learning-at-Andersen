package com.ernie.HW6CustomStorages;

public class TestHS {
    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.put("Element 1");
        myHashSet.put("Element 2");
        myHashSet.put("Element 3");
        myHashSet.put("Element 3");
        myHashSet.put("Element 3");

        for (String s: myHashSet.iterate()) {
            System.out.println(s);
        }

        System.out.println(myHashSet.contains("Element 4"));
        System.out.println(myHashSet.contains("Element 2"));

        myHashSet.delete("Element 1");
        for (String s: myHashSet.iterate()) {
            System.out.println(s);
        }

        myHashSet.print();

        System.out.println(myHashSet.size());
    }
}
