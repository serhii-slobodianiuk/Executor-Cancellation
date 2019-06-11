import java.util.concurrent.Callable;

public class CountOfFactorial implements Callable<Long> {

    @Override
    public Long call() {
        long factorial = 1;
        long count = 1;

        while (!Thread.currentThread().isInterrupted()) {
            factorial *= count;
            count++;
            System.out.println("Factorial: " + factorial);
            if(count == 11){
                break;
            }
        }
        return factorial;
    }
}
