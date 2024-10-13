package com.ernie.HW6CustomStorages;

public class TestAL {
    public static void main(String[] args) {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.put("Element 1");
        myArrayList.put("Element 2");
        myArrayList.put("Element 3");
        myArrayList.put("Element 4");
        myArrayList.put("Element 5");
        myArrayList.put("Element 6");
        myArrayList.put("Element 7");
        myArrayList.put("Element 8");
        myArrayList.put("Element 9");
        myArrayList.put("Element 10");
        myArrayList.put("Element 11");

        myArrayList.printAll();
        System.out.println(myArrayList.size() + " " + myArrayList.capacity() + "\n");
        System.out.println(myArrayList.getByIndex(4) + "\n");

        myArrayList.deleteByIndex(4);
        myArrayList.printAll();
        System.out.println(myArrayList.size() + " " + myArrayList.capacity() + "\n");

        myArrayList.print();

    }
}
