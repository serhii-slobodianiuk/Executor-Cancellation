import java.io.*;

public class CopyFilesBuiltInBufferedTask extends AbstractCopyFilesTask {
    private Observer iObserver;

    protected CopyFilesBuiltInBufferedTask(File aSource, File aDest, Observer aObserver) {
        super(aSource, aDest);
        this.iObserver = aObserver;
    }

    @Override
    protected void copyFiles(File source, File dest) throws IOException {
        final InputStream sourceStream = new BufferedInputStream(new FileInputStream(source));
        final OutputStream destStream = new BufferedOutputStream(new FileOutputStream(dest));

        final long totalSize = source.length();
        long readCount = 0;

        while (sourceStream.available() > 0) {
            int readByte = sourceStream.read();
            destStream.write(readByte);
            readCount++;

            iObserver.onProgressUpdated(readCount, totalSize);
        }
        iObserver.onCopyingCompleted(dest);
    }

    /**
     * The observer interface to provide information about current progress on the task
     */
    public static interface Observer {
        /**
         * Notifies an observer about current  progress of copying
         *
         * @param receivedBytes number of received bytes
         * @param totalBytes    total bytes to copy
         */
        void onProgressUpdated(long receivedBytes, long totalBytes);

        /**
         * Notifies an observer about completion of the task
         *
         * @param outputFile The destination file of the copying
         */
        void onCopyingCompleted(File outputFile);

    }
}
