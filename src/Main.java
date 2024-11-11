import Exeptions.UnknownClassExeption;
import Exeptions.DuplicateModelNameException;
import Exeptions.NoSuchModelNameException;
import vehicles.Automobile;
import vehicles.Motorcycle;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    static final String PATH = "D:\\tgbot\\Lab3\\";
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, IOException, UnknownClassExeption, ClassNotFoundException, CloneNotSupportedException, NoSuchMethodException {
        Automobile automobile = new Automobile("BMW",4);
        automobile.addItem("Test",9999);
        Motorcycle motorcycle = new Motorcycle("Suzuki",10);
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
        System.out.println(motorcycle);
        Motorcycle m1 = motorcycle.clone();
        System.out.println(m1.toString());
        System.out.println(motorcycle);

    }
    void invokeMethod(String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
      Class<?> clazz = Class.forName(className);
      Constructor<?> constructor = clazz.getConstructor(String.class,Integer.class);
      Automobile automobile = (Automobile) constructor.newInstance("",2);
      clazz.getMethod("",String.class,Integer.class).invoke(automobile,"",1);
    }
}