/**
 * @author Arnav Singh - 40258921
 * @author Sahar Hassanabadi - 40242124
 */

public class MyLinkedList<E> {

    // Inner class Node: represents each element in the list
    private class Node<E> {
        E element;
        Node<E> prev, next;

        // Parameterized constructor
        Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }
    }

    private Node<E> header, trailer; // dummy nodes for easy insert/remove
    private int size;

    // No-argument constructor
    public MyLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.next = trailer;
        size = 0;
    }

    // Returns the number of elements in the list
    public int size() {
        return size;
    }

    // Clears the list
    public void clear() {
        header.next = trailer;
        trailer.prev = header;
        size = 0;
    }

    // Returns a string representation of the list
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

    // Adds an element at the end of the list
    public boolean add(E e) {
        addBetween(e, trailer.prev, trailer);
        return true;
    }

    // Adds an element at a specific index
    public void add(int index, E element) {
        checkIndexForAdd(index);
        Node<E> nodeAtIndex = getNode(index);
        addBetween(element, nodeAtIndex.prev, nodeAtIndex);
    }

    // Removes and returns the element at the given index
    public E remove(int index) {
        checkIndex(index);
        Node<E> node = getNode(index);
        return removeNode(node);
    }

    // Removes the first occurrence of the given object
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

    // HELPER METHODS

    // Helper method to add a new node between two nodes
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newNode = new Node<>(e, predecessor, successor);
        predecessor.next = newNode;
        successor.prev = newNode;
        size++;
    }

    // Helper method to remove a node and return its element
    private E removeNode(Node<E> node) {
        Node<E> pred = node.prev;
        Node<E> succ = node.next;
        pred.next = succ;
        succ.prev = pred;
        size--;
        return node.element;
    }

    // Returns the node at a given index
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

    // Checks if an index is valid for accessing the elements
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    // Checks if an index is valid for adding elements
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
}