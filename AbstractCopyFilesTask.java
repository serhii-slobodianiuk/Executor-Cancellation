import java.io.File;
import java.io.IOException;

public abstract class AbstractCopyFilesTask implements Runnable {
    private File source;
    private File dest;

    protected AbstractCopyFilesTask(File aSource, File aDest) {
        source = aSource;
        dest = aDest;
    }

    @Override
    public void run() {
        try {
            copyFiles(source, dest);
        } catch (IOException e) {
            System.err.println("Failed to copy file with error: " + e.getMessage());
        }
    }

    protected abstract void copyFiles(File source, File dest) throws IOException;
}
