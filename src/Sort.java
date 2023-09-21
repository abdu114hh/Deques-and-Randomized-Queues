import dsa.LinkedStack;
import stdlib.StdOut;
import stdlib.StdIn;
public class Sort {
    // Entry point.
    public static void main(String[] args) {
        // new linked deque d
        LinkedDeque<String> d = new LinkedDeque<String>();
        // new linked stack s
        LinkedStack<String> s = new LinkedStack<String>();

        // as long as there is standard input
        while (!StdIn.isEmpty()) {

            // read x from standard input
            String x = StdIn.readString();

            // if q is empty, add x to the deque
            if (d.isEmpty()) {
                d.addFirst(x);

            // if x is less than the first item in d
            // add x to the front of the deque
            } else if (less(x, d.peekFirst())) {
                d.addFirst(x);

            // if x is greater than the last item in d
            // add x to the end of the deque
            } else if (less(d.peekLast(), x)) {
                d.addLast(x);


            } else {
                // otherwise remove all the words that are
                // less than x from the front of the deque and
                // store them in s
                while (less(d.peekFirst(), x)) {
                    s.push(d.removeFirst());
                }

                // add w to the front of the deque
                d.addFirst(x);

                // until s is empty, add words from s to the front  of d

                while (!s.isEmpty()) {
                    d.addFirst(s.pop());
                }
            }
        }

        // write the words in d to standard output
        for (String c : d) {
            StdOut.println(c);
        }
    }

        // Returns true if v is less than w according to their
        // lexicographic order, and false otherwise.
    private static boolean less(String v, String w) {
        return v.compareTo(w) < 0;
    }
}
