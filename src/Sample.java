
import stdlib.StdOut;
public class Sample {

    // uses a random queue to sample k integers from the interval [lo, hi]
    // and writes the samples to standard output
    public static void main(String[] args) {
        // accept int lo, int hi,int k and mode as commandline arguments
        int lo = Integer.parseInt(args[0]);
        int hi = Integer.parseInt(args[1]);
        int k = Integer.parseInt(args[2]);
        String mode = args[3];

        if (!mode.equals("+") && !mode.equals("-")) {
            throw new IllegalArgumentException("Illegal mode");
        }

        // create a new Random queue
        ResizingArrayRandomQueue<Integer> randomQueue =
                new ResizingArrayRandomQueue<>();

        // enqueue integers from the interval [lo, hi]
        for (int i = lo; i <= hi; i++) {
            randomQueue.enqueue(i);
        }


        for (int i = 0; i < k; i++) {
            if (mode.equals("+")) {
                // sample and write to standard output
                StdOut.println(randomQueue.sample());

            } else {
                // otherwise dequeue and write to standard output
                StdOut.println(randomQueue.dequeue());
            }
        }
    }
}