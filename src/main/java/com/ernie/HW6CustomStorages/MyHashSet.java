package com.ernie.HW6CustomStorages;

import java.util.HashMap;
import java.util.Set;

public class MyHashSet {
    private final int BASE_CAPACITY = 8;
    private HashMap<String, Boolean> baseHashMap;

    public MyHashSet() {
        baseHashMap = new HashMap<>(BASE_CAPACITY);
    }

    public void put(String newElement) {
        baseHashMap.put(newElement, true);
    }

    public boolean contains(String element) {
        return baseHashMap.containsKey(element);
    }

    public Set<String> iterate() {
        return baseHashMap.keySet();
    }

    public void delete(String element) {
        baseHashMap.remove(element);
    }

    public void print() {
        System.out.println("Printing my Hash Set:");
        for (String s : iterate()) {
            System.out.println(s);
        }
    }

    public int size() {
        return baseHashMap.size();
    }

}