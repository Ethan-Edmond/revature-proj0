package com.ethanedmond.datastructures;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class DynamicArray<T> {
    private T[] storage;
    private int length;
    private Class<T> childClassObj;

    public DynamicArray(Class<T> clazz) {
        this(clazz, 0);
    }

    public DynamicArray(Class<T> clazz, int length) {
        this.childClassObj = clazz;
        this.length = length;
        this.storage = (T[]) Array.newInstance(clazz, length);
    }

    public int getLength() {
        return this.length;
    }

    private void inBounds(int index) throws IndexOutOfBoundsException {
        if (index < 0 || this.length <= index) {
            throw new IndexOutOfBoundsException();
        }
    }

    public T getAt(int index) {
        inBounds(index);
        return this.storage[index];
    }

    public void setAt(int index, T seated) {
        inBounds(index);
        this.storage[index] = seated;
    }

    public void append(T appended) {
        if (this.storage.length == this.length) {
            T[] newStorage = (T[]) Array.newInstance(this.childClassObj, (this.length * 2) + 1);
            for (int i = 0; i < this.length; i++) {
                newStorage[i] = this.storage[i];
            }
            this.storage = newStorage;
        }
        this.storage[this.length] = appended;
        this.length++;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < this.length; i++) {
            res += this.storage[i].toString();
            if (i != this.length - 1) {
                res += "\n";
            }
        }
        return res;
    }
}
