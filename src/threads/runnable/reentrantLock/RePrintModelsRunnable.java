package threads.runnable.reentrantLock;

import interfaces.Vehicle;

import java.util.concurrent.locks.ReentrantLock;

public class RePrintModelsRunnable implements Runnable{
    private ReentrantLock reentrantLock;
    private Vehicle vehicle;
    public RePrintModelsRunnable(ReentrantLock reentrantLock, Vehicle vehicle){
        this.reentrantLock = reentrantLock;
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        reentrantLock.lock();
        System.out.println("Назания");
        for (String s:vehicle.getNames()){
            System.out.println(s);
        }
        reentrantLock.unlock();
    }
}
