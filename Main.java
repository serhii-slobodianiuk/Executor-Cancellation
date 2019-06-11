import java.io.File;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        CountOfFactorial task = new CountOfFactorial();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        final Future<Long> result = executor.submit(task);
        try {
            System.out.println();
            result.get(50, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println();
            System.out.println("Current thread was interrupted/cancelled");
            result.cancel(true);
            System.out.println("Factorial has been cancelled");

        } catch (ExecutionException e) {
            System.out.println();
            System.err.println("Internal factorial exception: " + e.getMessage());

        } catch (TimeoutException e) {
            result.cancel(true);
            System.out.println();
            System.out.println("Factorial has timed out and cancelled");
        }

        CountOfFibonacci task1 = new CountOfFibonacci();
        final Future<Long> result1 = executor.submit(task1);
        try {
            System.out.println();
            result1.get(5, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println();
            System.out.println("Current thread was interrupted/cancelled");
            result1.cancel(true);
            System.out.println("Fibonacci has been cancelled");

        } catch (ExecutionException e) {
            System.out.println();
            System.err.println("Internal fibonacci exception: " + e.getMessage());

        } catch (TimeoutException e) {
            result1.cancel(true);
            System.out.println();
            System.out.println("Fibonacci has timed out and cancelled");
        }


        Timer task2 = new Timer();
        final Future<Long> result2 = executor.submit(task2);
        try {
            System.out.println();
            result2.get(6, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println();
            System.out.println("Current thread was interrupted/cancelled");
            result2.cancel(true);
            System.out.println("Timer has been cancelled");

        } catch (ExecutionException e) {
            System.out.println();
            System.err.println("Internal Timer exception: " + e.getMessage());

        } catch (TimeoutException e) {
            result2.cancel(true);
            System.out.println();
            System.out.println("Timer has timed out and cancelled");
        }

        File inputFile = new File(/* "pathname" */);
        File outFile = new File(/* "pathname" */);

        executor.submit(new CopyFilesBuiltInBufferedTask(inputFile, outFile, new ConsoleLogger()));
        executor.shutdown();

    }
}

class ConsoleLogger implements CopyFilesBuiltInBufferedTask.Observer {

    @Override
    public void onProgressUpdated(long receivedBytes, long totalBytes) {
        System.out.println(String.format("Progress: %d/%d", receivedBytes, totalBytes));
    }

    @Override
    public void onCopyingCompleted(File outputFile) {
        System.out.println();
        System.out.println(String.format("Copying has completed to %s", outputFile.getAbsolutePath()));
    }
}
