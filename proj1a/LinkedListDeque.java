public class LinkedListDeque<T> {
    private class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node e, T i, Node h) {
            prev = e;
            item = i;
            next = h;
        }
    }

    private Node sentinel;
    private int size;

    /**
     * Creates an empty list.
     */
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public LinkedListDeque(T x) {
        size = 1;
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
    }

    public void addFirst(T item) {
        size++;
        Node oldFrontNode = sentinel.next;
        Node newNode = new Node(sentinel, item, oldFrontNode);
        sentinel.next = newNode;
        oldFrontNode.prev = newNode;
    }

    public void addLast(T item) {
        size++;
        Node oldLastNode = sentinel.prev;
        Node newLastNode = new Node(oldLastNode, item, sentinel);
        sentinel.prev = newLastNode;
        oldLastNode.next = newLastNode;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.item + " ");
            current = current.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        size--;
        T originFirst = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return originFirst;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        size--;
        T originLast = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return originLast;
    }

    public T get(int index) {
        Node current = sentinel.next;
        if (index == 0) {
            return current.item;
        }
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    public T getRecursive(int index) {
        return getRecursiveHelp(sentinel.next, index);
    }

    private T getRecursiveHelp(Node current, int index) {
        if (index == 0) {
            return current.item;
        } else {
            current = current.next;
            return getRecursiveHelp(current, index - 1);
        }
    }
}
