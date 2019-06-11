import java.util.concurrent.Callable;

public class Timer implements Callable {

    @Override
    public Long call() throws Exception {
        long n = -1;
        while (!Thread.currentThread().isInterrupted()) {
            n++;
            System.out.println("Timer: " + n);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return n;
    }
}
