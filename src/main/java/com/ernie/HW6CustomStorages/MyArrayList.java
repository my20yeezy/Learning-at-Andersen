package com.ernie.HW6CustomStorages;

public class MyArrayList {
    private final int INITIAL_CAPACITY = 10;
    private final double RESIZE_FACTOR = 1.5;
    private int currentCapacity;
    private int size;
    private String[] baseArray;

    public MyArrayList() {
        currentCapacity = INITIAL_CAPACITY;
        baseArray = new String[currentCapacity];
        size = 0;
    }

    public void put(String newElement) {
        if (newElement == null) {
            throw new IllegalArgumentException("Null elements not allowed");
        }
        autoResize();
        baseArray[size] = newElement;
        size++;
    }

    public String getByIndex(int index) {
        checkIndex(index);
        return baseArray[index];
    }

    public void deleteByIndex(int index) {
        checkIndex(index);
        baseArray[index] = null;
        for (int i = index; i < currentCapacity - 1; i++) {
            baseArray[i] = baseArray[i + 1];
        }
        size--;
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

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    public void printAll() {
        for (String element : baseArray) {
            System.out.println(element);
        }
    }

    public void print() {
        System.out.println("Printing my Array List:");
        for (int i = 0; i < size; i++) {
            System.out.println(baseArray[i]);
        }
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return currentCapacity;
    }

}