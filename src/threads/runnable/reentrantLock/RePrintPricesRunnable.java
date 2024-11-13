package threads.runnable.reentrantLock;

import interfaces.Vehicle;

import java.util.concurrent.locks.ReentrantLock;

public class RePrintPricesRunnable implements Runnable{
    Vehicle vehicle;
    ReentrantLock reentrantLock;

    public RePrintPricesRunnable( ReentrantLock reentrantLock,Vehicle vehicle) {
        this.vehicle = vehicle;
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {
        reentrantLock.lock();
        System.out.println("Цены:");
        for (Double d:vehicle.getPrices()){
            System.out.println(d);
        }
        reentrantLock.unlock();

    }
}
