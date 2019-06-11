import java.io.File;

public class ConsoleLogger implements CopyFilesBuiltInBufferedTask.Observer {

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
