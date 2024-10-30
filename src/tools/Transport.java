package tools;

import Exeptions.DuplicateModelNameException;
import Exeptions.NoSuchModelNameException;
import Exeptions.UnknownClassExeption;
import interfaces.Vehicle;
import vehicles.Automobile;
import vehicles.Motorcycle;

import java.io.*;
import java.util.Arrays;

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
}
