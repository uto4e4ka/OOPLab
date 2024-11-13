package threads;

import interfaces.Vehicle;

public class PrintNamesThread extends Thread{
    Vehicle vehicle;
    public PrintNamesThread(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Названия моделей:");
        for (String s:vehicle.getNames()){
            System.out.println(s);
        }
    }
}
