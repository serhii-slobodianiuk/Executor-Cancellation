import java.util.concurrent.Callable;

public class CountOfFibonacci implements Callable<Long> {

    private long current;
    private long previous;

    @Override
    public Long call() throws Exception {
        long sum = 1;
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            current = previous + sum;
            previous = current - previous;
            sum = current;
            count++;
            if (count == 10) {
                break;
            }
            System.out.println("Fibonacci: " + current);
        }
        return current;
    }
}
