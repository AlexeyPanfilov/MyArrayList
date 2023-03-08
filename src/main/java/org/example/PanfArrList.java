package org.example;

import java.util.*;

/**Реализация динамического массива, версия Панфилова А., для хранения и манипуляции данными
 * Данная реализация не потокобезопасна, так что в случае использования её в многопоточной среде,
 * если планируется доступ к ней одновременно нескольких потоков для модификации, разработчику необходимо
 * дополнительно синхронизировать используемые методы.*/

public class PanfArrList<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int currentCapacity = DEFAULT_CAPACITY;

    private Object[] listOfobjects;

    private int size = 0;

    public PanfArrList() {
        this.listOfobjects = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public boolean add(T t) {
        if (size >= currentCapacity) {
            this.grow();
        }
        listOfobjects[size] = t;
        size++;
        return true;
    }

    private Object[] grow() {
        return listOfobjects = Arrays.copyOf(listOfobjects, currentCapacity = this.size + 10);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        for (Object obj : listOfobjects) {
            if (o.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(Object o) {
        if (indexOf(o) >= 0) {
            remove(indexOf(o));
            return true;
        } else {
            return false;
        }
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            listOfobjects[i] = null;
        }
        size = 0;
    }

    public T get(int index) {
        return (T) listOfobjects[index];
    }

    public T set(int index, T element) {
        if (index < size) {
            listOfobjects[index] = element;
        }
        return null;
    }

    public void add(int index, T element) {
        for (int i = this.size; i > index; i--) {
            listOfobjects[i] = listOfobjects[i - 1];
        }
        listOfobjects[index] = element;
        size++;
    }

    public T remove(int index) {
        T valueToRemove = this.get(index);
        listOfobjects[index] = null;
        for (int i = index; i < this.size - 1; i++) {
            listOfobjects[i] = listOfobjects[i + 1];
        }
        size--;
        listOfobjects[size] = null;
        return valueToRemove;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < this.size - 1; i++) {
            if (listOfobjects[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public void trimToSize() {
        if (listOfobjects.length > this.size && this.size > DEFAULT_CAPACITY) {
            listOfobjects = Arrays.copyOf(listOfobjects, this.size);
        } else if (this.size < DEFAULT_CAPACITY) {
            listOfobjects = Arrays.copyOf(listOfobjects, DEFAULT_CAPACITY);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object o : listOfobjects) {
            if (o != null) {
                sb.append(o).append(", ");
            }
        }
        String s = sb.toString().replaceAll(", $", "");
        sb = new StringBuilder(s).append("]");
        return sb.toString();
    }

    public String printList() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object o : listOfobjects) {
            sb.append(o).append(", ");
        }
        String s = sb.toString().replaceAll(", $", "");
        sb = new StringBuilder(s).append("]");
        return sb.toString();
    }
}