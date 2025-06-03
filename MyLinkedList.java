/**
 * @author Arnav Singh - 40258921
 * @author Sahar Hassanabadi -
 */

public class MyLinkedList<E> {
    private class Node<E> {
        E element;
        Node<E> prev, next;

        Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }
    }

    private Node<E> header, trailer;
    private int size;

    public MyLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.next = trailer;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        header.next = trailer;
        trailer.prev = header;
        size = 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = header.next;
        while (current != trailer) {
            sb.append(current.element);
            if (current.next != trailer) sb.append(", ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean add(E e) {
        addBetween(e, trailer.prev, trailer);
        return true;
    }

    public void add(int index, E element) {
        checkIndexForAdd(index);
        Node<E> nodeAtIndex = getNode(index);
        addBetween(element, nodeAtIndex.prev, nodeAtIndex);
    }

    public E remove(int index) {
        checkIndex(index);
        Node<E> node = getNode(index);
        return removeNode(node);
    }

    public boolean remove(Object o) {
        Node<E> current = header.next;
        while (current != trailer) {
            if ((o == null && current.element == null) || (o != null && o.equals(current.element))) {
                removeNode(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }


    //Helper Methods
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newNode = new Node<>(e, predecessor, successor);
        predecessor.next = newNode;
        successor.prev = newNode;
        size++;
    }

    private E removeNode(Node<E> node) {
        Node<E> pred = node.prev;
        Node<E> succ = node.next;
        pred.next = succ;
        succ.prev = pred;
        size--;
        return node.element;
    }

    private Node<E> getNode(int index) {
        Node<E> current;
        if (index < size / 2) {
            current = header.next;
            for (int i = 0; i < index; i++) current = current.next;
        } else {
            current = trailer;
            for (int i = size; i > index; i--) current = current.prev;
        }
        return current;
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