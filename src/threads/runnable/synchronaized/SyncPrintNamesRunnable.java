package threads.runnable.synchronaized;

public class SyncPrintNamesRunnable implements Runnable{
    PrintSynchronaizer printSynchronaizer;
    public SyncPrintNamesRunnable(PrintSynchronaizer printSynchronaizer){
        this.printSynchronaizer = printSynchronaizer;
    }
    @Override
    public void run() {
        try {
            while (printSynchronaizer.canPrintModel()) {
                printSynchronaizer.printModel();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
