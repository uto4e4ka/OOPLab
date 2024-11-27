import Exeptions.UnknownClassExeption;
import Exeptions.DuplicateModelNameException;
import Exeptions.NoSuchModelNameException;
import interfaces.Vehicle;
import reflection.ReflectTools;
import threads.PrintNamesThread;
import threads.PrintPricesThread;
import threads.runnable.PrintNamesRunnable;
import threads.runnable.fileInput.FileReadModelsRunnable;
import threads.runnable.synchronaized.SyncPrintNamesRunnable;
import threads.runnable.synchronaized.SyncPrintPricesRunnable;
import threads.runnable.synchronaized.PrintSynchronaizer;
import threads.runnable.reentrantLock.RePrintModelsRunnable;
import threads.runnable.reentrantLock.RePrintPricesRunnable;
import tools.Transport;
import vehicles.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    static final String PATH = "C:\\Users\\Uto4ka\\IdeaProjects\\OOPLab\\";
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, IOException, UnknownClassExeption, ClassNotFoundException, CloneNotSupportedException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, InterruptedException {
        Automobile automobile = new Automobile("BMW");
      //  first(automobile);
       // second(automobile);
       // third(automobile);
      //  exec(automobile);
        files();

    }
    private static void first(Vehicle vehicle) throws DuplicateModelNameException {

       vehicle.addItem("M1",34333);
       vehicle.addItem("M3",22323);
       vehicle.addItem("X6",114141);
       vehicle.addItem("X7",1231313);
        PrintPricesThread prices = new PrintPricesThread(vehicle);
        PrintNamesThread names = new PrintNamesThread(vehicle);
        prices.setPriority(Thread.MAX_PRIORITY);
        names.setPriority(Thread.MIN_PRIORITY);
        prices.start();
        names.start();
        try {
            prices.join();
            names.join();
        }catch (Exception e){

        }
    }
    private static void second(Vehicle vehicle){
                System.out.println("Вывод через синхронизатор");
        PrintSynchronaizer printSynchronaizer = new PrintSynchronaizer(vehicle);
        Thread printPrice = new Thread(new SyncPrintNamesRunnable(printSynchronaizer));
        Thread printNames = new Thread(new SyncPrintPricesRunnable(printSynchronaizer));
        printPrice.start();
        printNames.start();
        try {
            printPrice.join();
            printNames.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private static void third(Vehicle vehicle){
        System.out.println("Вывод через Reentrant");
        ReentrantLock reentrantLock = new ReentrantLock();
       Thread rPrintN = new Thread(new RePrintModelsRunnable(reentrantLock,vehicle));
       Thread rPrintP = new Thread(new RePrintPricesRunnable(reentrantLock,vehicle));
       rPrintN.start();
       rPrintP.start();
       try {
           rPrintN.join();
           rPrintP.join();
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
    }
    private static void exec(Vehicle vehicle){
               System.out.println("С помощью ExecutorService");
        Scuter scuter = new Scuter("Scuter1",10);
       Motorcycle motorcycle = new Motorcycle("Moto",12);
       Moped moped = new Moped("Mop1",5);
        ExecutorService executors = Executors.newFixedThreadPool(2);
        executors.submit(new PrintNamesRunnable(vehicle));
        executors.submit(new PrintNamesRunnable(scuter));
        executors.submit(new PrintNamesRunnable(motorcycle));
        executors.submit(new PrintNamesRunnable(moped));
    }
    private static void files() throws InterruptedException {
        String[] filePaths = {"ts1","ts2","ts3","ts4","ts5"};
        BlockingQueue<Vehicle> blockingQueue = new ArrayBlockingQueue<>(2);
        for(String s:filePaths){
            new Thread(new FileReadModelsRunnable(PATH+s+".txt",blockingQueue)).start();
        }
        for(int i =0;i<filePaths.length;i++){
            System.out.println(blockingQueue.take().getBrand());
        }
    }
   static void getModel(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String name = args[0];
        String brand = "Lada";
        int size = 10;
        String methodName = args[1];
        String nameModel = args[2];
        double price = Double.parseDouble(args[3]);
        Vehicle veh = (Vehicle)ReflectTools.setPrice("vehicles."+name,brand,size,methodName,nameModel,price);
        System.out.println(veh.getBrand());
        Transport.printModels(veh);
    }

}