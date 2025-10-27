package Labs.Week2;

import java.util.*;

public class ArrayList<E>{
    private static final int DEFAULT_CAPACITY = 4;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public boolean add(E element) {
        if (this.size == this.elements.length) {
            this.elements = grow();
        }
        this.elements[this.size++] = element;
        return true;
    }

    public E get(int index) {
        checkIndex(index);
        return getElement(index);
    }

    public E set(int index, E element) {
        checkIndex(index);
        E oldElement = getElement(index);
        this.elements[index] = element;
        return oldElement;
    }

    public E remove(int index) {
        checkIndex(index);
        E element = getElement(index);

        int numMoved = this.size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(this.elements, index + 1, this.elements, index, numMoved);
        }
        this.elements[--this.size] = null;

        ensureCapacity();
        return element;
    }

    public int size() {
        return this.size;
    }

    @SuppressWarnings("unchecked")
    private E getElement(int index) {
        return (E) this.elements[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
    }

    private Object[] grow() {
        int newCap = Math.max(this.elements.length * 2, DEFAULT_CAPACITY);
        return Arrays.copyOf(this.elements, newCap);
    }

    private Object[] shrink() {
        int newCap = Math.max(this.elements.length / 2, DEFAULT_CAPACITY);
        return Arrays.copyOf(this.elements, newCap);
    }
    
    private void ensureCapacity() {
        if (this.size <= this.elements.length / 4 && this.elements.length > DEFAULT_CAPACITY) {
            this.elements = shrink();
        }
    }
}
