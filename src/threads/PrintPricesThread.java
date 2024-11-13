package threads;

import interfaces.Vehicle;

public class PrintPricesThread extends Thread{
    Vehicle vehicle;
    public PrintPricesThread(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Цены моделей:");
        for (Double d:vehicle.getPrices()){
            System.out.println(d);
        }
    }
}
