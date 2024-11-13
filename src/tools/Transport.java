package tools;

import Exeptions.DuplicateModelNameException;
import Exeptions.NoSuchModelNameException;
import Exeptions.UnknownClassExeption;
import interfaces.Vehicle;
import vehicles.Automobile;
import vehicles.Motorcycle;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Scanner;

public class Transport {
    public static void printNames(Vehicle vehicle){//!!!!;
       System.out.println(Arrays.toString(vehicle.getNames()));
    }
    public static double getAverage(Vehicle vehicle){
        double sum = 0;
        for (Double s:vehicle.getPrices()){
            sum+=s;
        }
        return sum/vehicle.length();
    }
    public static double getAverage(Vehicle ... vehicles){
        double sum=0.0;
        int count =0;
        for (int i=0;i<vehicles.length;i++){
            for (Double s:vehicles[i].getPrices()){
                sum+=s;
                count++;
            }
        }
        double result =  count == 0 ? 0 : sum / count;
        return Double.parseDouble(String.format("%.2f",result).replace(",","."));
    }
    public static void printModels(Vehicle vehicle) {
        String [] names = vehicle.getNames();
        try {
        for (int i =0;i<names.length;i++){
            System.out.println(i+". "+names[i]+" Цена:"+vehicle.getPrice(names[i]));
        }}catch (NoSuchModelNameException e){
            System.out.println(e.toString());
        }
    }
    public static void outputModels(Vehicle vehicle, OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream =  new DataOutputStream(outputStream);
        byte[] buff;
        String[] models = vehicle.getNames();
        double[] prices = vehicle.getPrices();

        writeStr(dataOutputStream,vehicle.getClass().toString().split("\\.")[1]);
        writeStr(dataOutputStream,vehicle.getBrand());
        dataOutputStream.writeInt(vehicle.length());
            writeStr(dataOutputStream,models);
            for (double price:prices){
                dataOutputStream.writeDouble(price);
            }

    }

    public static Vehicle inputModels(InputStream inputStream) throws IOException, UnknownClassExeption, DuplicateModelNameException, NoSuchModelNameException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        Vehicle vehicle = null;
        switch (readString(dataInputStream)){
            case "Automobile":
                vehicle = new Automobile(readString(dataInputStream));
                break;
            case "Motorcycle":
                vehicle = new Motorcycle(readString(dataInputStream));
                break;
            default:
                throw new UnknownClassExeption();
        }
        String[]models = new String[ dataInputStream.readInt()];

        for (int i=0;i<models.length;i++){
            models[i] = readString(dataInputStream);
            vehicle.addItem(models[i],0);
        }
        for (String model : models) {
            vehicle.setPrice(model, dataInputStream.readDouble());
        }

        return vehicle;

    }
    private static void writeStr(DataOutputStream dataOutputStream,String ...str) throws IOException {
        for (String s:str){
            byte[] buff = s.getBytes();
            dataOutputStream.writeInt(buff.length);
            dataOutputStream.write(buff);
        }
    }
    private static String readString(DataInputStream inputStream) throws IOException {
        byte[] buff = new byte[inputStream.readInt()];
        for (int i=0;i<buff.length;i++){
            buff[i] = inputStream.readByte();
        }
        return new String(buff);
    }
    public static void writeModel(Vehicle vehicle,Writer out){
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.println(vehicle.getClass().toString().split("\\.")[1]);
        printWriter.println(vehicle.getBrand());
        printWriter.println(vehicle.length());
        String[] models = vehicle.getNames();
        double[] prices = vehicle.getPrices();
        for (int i=0;i< vehicle.length();i++){
            printWriter.println(models[i]);
            printWriter.println(prices[i]);
        }
        printWriter.flush();

    }
    public static Vehicle readModel(Reader in) throws UnknownClassExeption, IOException, DuplicateModelNameException {
        BufferedReader bufferedReader = new BufferedReader(in);
        Vehicle vehicle = null;
            switch (bufferedReader.readLine()){
                case "Automobile":
                    vehicle = new Automobile(bufferedReader.readLine());
                    break;
                case "Motorcycle":
                    vehicle = new Motorcycle(bufferedReader.readLine());
                    break;
                default:
                    throw new UnknownClassExeption();
            }
            int size = Integer.parseInt(bufferedReader.readLine());
            for(int i=0;i<size;i++){
                vehicle.addItem(bufferedReader.readLine(),Double.parseDouble(bufferedReader.readLine()));
            }
            return vehicle;

    }
    public static Vehicle getByReflex(String brand,int size,Vehicle vehicle){
        Class<?> clazz;
        Constructor<?> constructor;
        Vehicle veh;
        try {
          clazz =  Class.forName(vehicle.getClass().getName());
        }catch (ClassNotFoundException e){
            return null;
        }
        try {
            constructor = clazz.getConstructor(String.class, int.class);
        }catch (NoSuchMethodException e){
            System.out.println("Класса с данным конструктором не найдено!");
            return null;
        }
        try {
           veh = (Vehicle) constructor.newInstance(brand,size);
        }catch (InvocationTargetException|InstantiationException|IllegalAccessException e){
            return null;
        }
        return veh;


    }
    public static void writePrintModel(Vehicle vehicle, Writer out) {
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.printf("%s%n", vehicle.getClass().getSimpleName());
        printWriter.printf("%s%n", vehicle.getBrand());
        printWriter.printf("%d%n", vehicle.length());

        String[] models = vehicle.getNames();
        double[] prices = vehicle.getPrices();
        for (int i = 0; i < vehicle.length(); i++) {
            printWriter.printf("%s%n", models[i]);
            printWriter.printf("%.2f%n", prices[i]);
        }
        printWriter.flush();
    }
    public static Vehicle inputScannerVehicle(InputStream inputStream) throws UnknownClassExeption, DuplicateModelNameException, NoSuchModelNameException {
        Scanner scanner = new Scanner(inputStream);
        Vehicle vehicle;

        String vehicleType = scanner.nextLine();
        String brand = scanner.nextLine();
        int modelCount = scanner.nextInt();
        scanner.nextLine();

        switch (vehicleType) {
            case "Automobile":
                vehicle = new Automobile(brand);
                break;
            case "Motorcycle":
                vehicle = new Motorcycle(brand);
                break;
            default:
                throw new UnknownClassExeption();
        }

        for (int i = 0; i < modelCount; i++) {
            String modelName = scanner.nextLine();
            double modelPrice = scanner.nextDouble();
            if (scanner.hasNextLine()) scanner.nextLine();
            vehicle.addItem(modelName, modelPrice);
        }

        return vehicle;
    }
}
