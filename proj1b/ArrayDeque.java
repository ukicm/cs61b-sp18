public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int init_capacity = 8;
    private static final int r_factor = 2;
    private static final double min_usage_ratio = 0.25;

    /**
     * Creates an empty array deque.
     * The starting size of your array should be 8.
     */
    public ArrayDeque() {
        items = (T[]) new Object[init_capacity];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    /**
     * Creates a deep copy of other.
     */
    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[init_capacity];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];

        int curr = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            newArray[i] = items[curr];
            curr = plusOne(curr);
        }

        items = newArray;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * r_factor);
        }

        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * r_factor);
        }

        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     * Must take constant time.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out the new line.
     */
    public void printDeque() {
        int i = plusOne(nextFirst);

        while (i != nextLast) {
            System.out.print(items[i] + " ");
            i = plusOne(i);
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        int first = plusOne(nextFirst);
        T firstItem = items[first];
        items[first] = null;
        nextFirst = first;
        size--;

        if (items.length >= 16 && size < items.length * min_usage_ratio) {
            resize(items.length / 2);
        }

        return firstItem;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        int last = minusOne(nextLast);
        T lastItem = items[last];
        items[last] = null;
        nextLast = last;
        size--;

        if (items.length >= 16 && size < items.length * min_usage_ratio) {
            resize(items.length / 2);
        }

        return lastItem;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque and must take constant time.
     */
    public T get(int index) {
        if (index > size) {
            return null;
        }

        index = (index + plusOne(nextFirst)) % items.length;
        return items[index];
    }
}