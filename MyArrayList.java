/**
 * @author Arnav Singh - 40258921
 * @author Sahar Hassanabadi - 40242124
 */

public class MyArrayList<E> {
    // Array to store list elements
    private E[] data;
    // Number of elements in the list
    private int size;
    // Current capacity of the internal array
    private int capacity;

    private static final int INITIAL_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        // Initializing the array with an initial capacity
        capacity = INITIAL_CAPACITY;
        data = (E[]) new Object[capacity];
        size = 0;
    }

    // Returns the current size of the list
    public int size() {
        return size;
    }

    // Clears the list
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
        resizeIfNeeded();
    }

    // Adds element at the end of the list
    public boolean add(E e) {
        ensureCapacity(size + 1);
        data[size++] = e;
        return true;
    }

    // Adds element at a specified index
    public void add(int index, E element) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = element;
        size++;
    }

    // Removes element at a given index
    public E remove(int index) {
        checkIndex(index);
        E removed = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--size] = null;
        resizeIfNeeded();
        return removed;
    }

    // Removes the first occurrence of a given object
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if ((o == null && data[i] == null) || (o != null && o.equals(data[i]))) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    // Returns the list as a string
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // Makes sure the array can hold the required number of elements
    private void ensureCapacity(int required) {
        if (required > capacity) {
            resize(capacity * 2);
        }
    }

    // Shrinks the array if less than 25% is used
    private void resizeIfNeeded() {
        if (size < capacity / 4 && capacity > INITIAL_CAPACITY) {
            resize(Math.max(capacity / 2, INITIAL_CAPACITY));
        }
    }

    // Resizes the array to a new capacity
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
        capacity = newCapacity;
    }

    // Throws exception if index is out of bounds (for get/remove)
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    // Throws exception if index is out of bounds (for add)
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
}