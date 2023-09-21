import java.util.Iterator;
import java.util.NoSuchElementException;
import stdlib.StdOut;
import stdlib.StdRandom;

// A data type to represent a double-ended queue (aka deque), implemented using a doubly-linked
// list as the underlying data structure.
public class LinkedDeque<Item> implements Iterable<Item> {

    // Reference to the front of the deque
    private Node first;

    // Reference to the back of the deque
    private Node last;

    // number of items in the deque
    private int n;

    // Constructs an empty deque.
    public LinkedDeque() {
        first = null;
        last = null;
        n = 0;
    }

    // Returns true if this deque is empty, and false otherwise.
    public boolean isEmpty() {
        return n == 0;
    }

    // Returns the number of items in this deque.
    public int size() {
        return n;
    }

    // Adds item to the front of this deque.
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("item is null");
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;

        // if this is the first item being added
        // first and last should point to the same node
        if (size() == 1) {
            last = first;

        // if there are more than one item in the deque
        // the old first node should point to the new first node
        } else if (size() > 1) {
            oldFirst.prev = first;
        }
    }



    // Adds item to the back of this deque.
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("item is null");
        }

        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.prev = oldlast;
        n++;

        // if this is the first item being added
        // first and last should point to the same node
        if (size() == 1) {
            first = last;

        // if there are multiple items in the deque,
        // the old last node should point to the new last node.
        } else if (size() > 1) {
            oldlast.next = last;
        }
    }

    // Returns the item at the front of this deque.
    public Item peekFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        return first.item;
    }

    // Removes and returns the item at the front of this deque.
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            first = null;
            last = null;
        } else if (!isEmpty()) {
            first.prev = null;
        }

        return item;
    }

    // Returns the item at the back of this deque.
    public Item peekLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        return last.item;
    }

    // Removes and returns the item at the back of this deque.
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Item item = last.item;
        last = last.prev;
        n--;

        if (isEmpty()) {
            first = null;
            last = null;
        }
        if (!isEmpty()) {
            last.next = null;
        }
        return item;
    }

    // Returns an iterator to iterate over the items in this deque from front to back.
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // Returns a string representation of this deque.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : this) {
            sb.append(item);
            sb.append(", ");
        }
        return n > 0 ? "[" + sb.substring(0, sb.length() - 2) + "]" : "[]";
    }

    // A deque iterator.
    private class DequeIterator implements Iterator<Item> {

        // Reference to the current node in the Iterator
        private Node current;

        // Constructs an iterator.
        public DequeIterator() {
            current = first;
        }

        // Returns true if there are more items to iterate, and false otherwise.
        public boolean hasNext() {
            return current != null;

        }

        // Returns the next item.
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Iterator is empty");
            } else {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

    // A data type to represent a doubly-linked list. Each node in the list stores a generic item
    // and references to the next and previous nodes in the list.
    private class Node {
        private Item item;  // the item
        private Node next;  // the next node
        private Node prev;  // the previous node
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its several powers, having " +
                "been originally breathed into a few forms or into one; and that, whilst this " +
                "planet has gone cycling on according to the fixed law of gravity, from so simple" +
                " a beginning endless forms most beautiful and most wonderful have been, and are " +
                "being, evolved. ~ Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        StdOut.println("Filling the deque...");
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }
        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }
        StdOut.printf("The deque (%d characters): ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        StdOut.println("Emptying the deque...");
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            } else {
                deque.removeLast();
            }
        }
        StdOut.println("deque.isEmpty()? " + deque.isEmpty());
    }
}
