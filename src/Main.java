import Exeptions.UnknownClassExeption;
import Exeptions.DuplicateModelNameException;
import Exeptions.NoSuchModelNameException;
import interfaces.Vehicle;
import reflection.ReflectTools;
import tools.Transport;
import tools.TransportTests;
import vehicles.*;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    static final String PATH = "D:\\tgbot\\Lab3\\";
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, IOException, UnknownClassExeption, ClassNotFoundException, CloneNotSupportedException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Vehicle vehicle = Transport.getByReflex("MWV",10,new Automobile(null));
        System.out.println(vehicle.toString());
       TransportTests.startMultiAverage();
       Transport.writePrintModel(new Automobile("Toyota",10),new FileWriter(PATH+"test.txt"));
       System.out.println(Transport.inputScannerVehicle(new FileInputStream(PATH+"test.txt")).toString());
        getModel();
    }
   static void getModel() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя класса");
        String name = scanner.nextLine();
        System.out.println("Введите бренд");
        String brand = scanner.nextLine();
        System.out.println("Введите размер");
        int size = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Название метода.");
        String methodName = scanner.nextLine();
        System.out.println("Название модели.");
        String nameModel = scanner.nextLine();
        System.out.println("Цена");
        double price = scanner.nextDouble();
        Vehicle veh = (Vehicle)ReflectTools.setPrice("vehicles."+name,brand,size,methodName,nameModel,price);
        System.out.println(veh.getBrand());
        Transport.printModels(veh);
    }

}