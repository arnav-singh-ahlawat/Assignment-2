/**
 * @author Arnav Singh - 40258921
 * @author Sahar Hassanabadi -
 */

public class MyArrayList<E> {
    private E[] data;
    private int size;
    private int capacity;

    private static final int INITIAL_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        capacity = INITIAL_CAPACITY;
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
        resizeIfNeeded();
    }

    public boolean add(E e) {
        ensureCapacity(size + 1);
        data[size++] = e;
        return true;
    }

    public void add(int index, E element) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = element;
        size++;
    }

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

    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if ((o == null && data[i] == null) || (o != null && o.equals(data[i]))) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private void ensureCapacity(int required) {
        if (required > capacity) {
            resize(capacity * 2);
        }
    }

    private void resizeIfNeeded() {
        if (size < capacity / 4 && capacity > INITIAL_CAPACITY) {
            resize(Math.max(capacity / 2, INITIAL_CAPACITY));
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
        capacity = newCapacity;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
}