import Exeptions.UnknownClassExeption;
import Exeptions.DuplicateModelNameException;
import Exeptions.NoSuchModelNameException;
import interfaces.Vehicle;
import reflection.ReflectTools;
import tools.Transport;
import vehicles.Automobile;
import vehicles.Motorcycle;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    static final String PATH = "D:\\tgbot\\Lab3\\";
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, IOException, UnknownClassExeption, ClassNotFoundException, CloneNotSupportedException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
       // Automobile automobile = new Automobile("BMW",4);
       // automobile.addItem("Test",9999);
      //  Motorcycle motorcycle = new Motorcycle("Suzuki",10);
       // new TransportTests(automobile).startNormal();
        //new TransportTests(new Motorcycle("Suzuki",10)).startErr();
      //new TransportTests(automobile).startIO(PATH+"test1.txt");
      //new TransportTests(automobile).startWR(PATH+"test.txt");
      //  new TransportTests(motorcycle).startSerialize(PATH+"ser.bin");
//        System.out.println(automobile.toString());
//         Automobile a1 = automobile.clone();
//        System.out.println(a1.toString());
//        System.out.println(automobile.equals(a1));
//        a1.setBrand("bbb");
//        System.out.println(automobile.equals(a1));
     //   System.out.println(motorcycle);
     //   Motorcycle m1 = motorcycle.clone();
      //  System.out.println(m1.toString());
       // System.out.println(motorcycle);
        Vehicle vehicle = Transport.getByReflex("MWV",10,new Automobile(null));
        System.out.println(vehicle.toString());
       // getModel();
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