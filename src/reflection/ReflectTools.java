package reflection;

import vehicles.Automobile;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectTools {
    public static Object setPrice(String className,String brand,int size,String methodName,String name,double price) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName(className);
        Constructor<?> constructor = clazz.getConstructor(String.class,int.class);
        Object foundObject = constructor.newInstance(brand,size);
        clazz.getMethod(methodName,String.class,double.class).invoke(foundObject,name,price);
        return foundObject;
    }
}
