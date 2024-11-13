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
        getModel(args);
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