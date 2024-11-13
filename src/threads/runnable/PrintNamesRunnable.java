package threads.runnable;

import interfaces.Vehicle;

public class PrintNamesRunnable implements Runnable{
    private Vehicle vehicle;
    public PrintNamesRunnable(Vehicle vehicle){
        this.vehicle = vehicle;
    }
    @Override
    public void run() {
        System.out.println("Названия "+vehicle.getBrand()+":");
        for (String s:vehicle.getNames()){
            System.out.println(s);
        }
    }
}
