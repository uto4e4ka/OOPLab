import Exeptions.UnknownClassExeption;
import Exeptions.DuplicateModelNameException;
import Exeptions.NoSuchModelNameException;
import interfaces.Vehicle;
import reflection.ReflectTools;
import threads.PrintNamesThread;
import threads.PrintPricesThread;
import threads.runnable.PrintNamesRunnable;
import threads.runnable.synchronaized.SyncPrintNamesRunnable;
import threads.runnable.synchronaized.SyncPrintPricesRunnable;
import threads.runnable.synchronaized.PrintSynchronaizer;
import threads.runnable.reentrantLock.RePrintModelsRunnable;
import threads.runnable.reentrantLock.RePrintPricesRunnable;
import tools.Transport;
import vehicles.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    static final String PATH = "D:\\tgbot\\Lab3\\";
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, IOException, UnknownClassExeption, ClassNotFoundException, CloneNotSupportedException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
       Automobile automobile = new Automobile("BMW");
       automobile.addItem("M1",34333);
       automobile.addItem("M3",22323);
       automobile.addItem("X6",114141);
       automobile.addItem("X7",1231313);
        PrintPricesThread prices = new PrintPricesThread(automobile);
        PrintNamesThread names = new PrintNamesThread(automobile);
        prices.setPriority(Thread.MAX_PRIORITY);
        names.setPriority(Thread.MIN_PRIORITY);
        prices.start();
        names.start();
        try {
            prices.join();
            names.join();
        }catch (Exception e){

        }
        System.out.println("Вывод через синхронизатор");
        PrintSynchronaizer printSynchronaizer = new PrintSynchronaizer(automobile);
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
        System.out.println("Вывод через Reentrant");
        ReentrantLock reentrantLock = new ReentrantLock();
       Thread rPrintN = new Thread(new RePrintModelsRunnable(reentrantLock,automobile));
       Thread rPrintP = new Thread(new RePrintPricesRunnable(reentrantLock,automobile));
       rPrintN.start();
       rPrintP.start();
       try {
           rPrintN.join();
           rPrintP.join();
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
       System.out.println("С помощью ExecutorService");
        Scuter scuter = new Scuter("Scuter1",10);
       Motorcycle motorcycle = new Motorcycle("Moto",12);
       Moped moped = new Moped("Mop1",5);
        ExecutorService executors = Executors.newFixedThreadPool(2);
        executors.submit(new PrintNamesRunnable(automobile));
        executors.submit(new PrintNamesRunnable(scuter));
        executors.submit(new PrintNamesRunnable(motorcycle));
        executors.submit(new PrintNamesRunnable(moped));
        String[] filePaths = {""}
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