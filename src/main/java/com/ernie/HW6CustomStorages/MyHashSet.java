package com.ernie.HW6CustomStorages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet {
    private final int BASE_CAPACITY = 10;
    private final double RESIZE_FACTOR = 1.5;
    private int currentCapacity;
    private int size;
    private String[] baseArray;

    public MyHashSet() {
        currentCapacity = BASE_CAPACITY;
        baseArray = new String[currentCapacity];
        size = 0;
    }

    public void put(String newElement) {
        autoResize();
        if (!contains(newElement)) {
            baseArray[size] = newElement;
            size++;
        }
    }

    public boolean contains(String element) {
        for (int i = 0; i < size; i++) {
            if (baseArray[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public Iterator<String> iterate() {
        return new Iterator<String>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                while (i < size && baseArray[i] != null) {
                    i++;
                }
                return i < size;
            }

            @Override
            public String next() {
                return baseArray[i++];
            }
        };
    }

    public void delete(String element) {
        if (contains(element)) {
            for (int i = 0; i < size; i++) {
                if (baseArray[i].equals(element)) {
                    baseArray[i] = null;
                    for (int j = i; j < currentCapacity - 1; j++) {
                        baseArray[j] = baseArray[j + 1];
                    }
                    size--;
                }
            }
        }
    }

    public void print() {
        System.out.println("Printing my Hash Set:");
        for (int i = 0; i < size; i++) {
            System.out.println(baseArray[i]);
        }
    }

    public int size() {
        return size;
    }

    private void autoResize() {
        if (size == currentCapacity) {
            currentCapacity = (int) Math.round(currentCapacity * RESIZE_FACTOR);
            String[] newArray = new String[currentCapacity];
            for (int i = 0; i < baseArray.length; i++) {
                newArray[i] = baseArray[i];
            }
            baseArray = newArray;
        }
    }

}