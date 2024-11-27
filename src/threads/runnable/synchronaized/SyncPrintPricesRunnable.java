package threads.runnable.synchronaized;

public class SyncPrintPricesRunnable implements Runnable {
    private PrintSynchronaizer printSynchronaizer;
public SyncPrintPricesRunnable(PrintSynchronaizer printSynchronaizer){
    this.printSynchronaizer = printSynchronaizer;
}
    @Override
    public void run() {
        try {
            while (printSynchronaizer.canPrintPrice()) {
                printSynchronaizer.printPrice();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
