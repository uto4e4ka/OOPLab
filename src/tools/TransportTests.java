package tools;

import Exeptions.DuplicateModelNameException;
import Exeptions.NoSuchModelNameException;
import Exeptions.UnknownClassExeption;
import interfaces.Vehicle;
import vehicles.Moped;
import vehicles.QuadBike;
import vehicles.Scuter;

import java.io.*;

public class TransportTests {
    private Vehicle vehicle;
   public TransportTests(Vehicle vehicle) {
      this.vehicle = vehicle;
   }

   public void startNormal() throws DuplicateModelNameException, NoSuchModelNameException {
       System.out.println(">");
       vehicle.addItem("M1",1000);
       vehicle.addItem("M2",19090);
       vehicle.addItem("M5",90129);
       vehicle.addItem("M10",9019);
       vehicle.delItem("M10");
       vehicle.setItemName("M5","M33");
       vehicle.setPrice("M2",100);
       Transport.printModels(vehicle);
       System.out.println(String.format("%.2f",Transport.getAverage(vehicle)));
   }
   public void startErr() throws DuplicateModelNameException, NoSuchModelNameException {
       vehicle.addItem("M1",1000);
       vehicle.addItem("M2",19090);
       vehicle.addItem("M5",90129);
       vehicle.addItem("M10",9019);
       vehicle.delItem("M10");
       vehicle.setItemName("M0","M53");
       vehicle.setPrice("M2",-100);
       Transport.printModels(vehicle);
   }
   public void startIO(String path) throws IOException, DuplicateModelNameException, UnknownClassExeption, NoSuchModelNameException {
      System.out.println("Тест: IO");
       OutputStream outputStream = new FileOutputStream(path);
       Transport.outputModels(vehicle,outputStream);
        InputStream inputStream = new FileInputStream(path);
       Transport.printModels(Transport.inputModels(inputStream));
   }
   public void startWR(String path) throws IOException, DuplicateModelNameException, UnknownClassExeption {
       System.out.println("Тест: WR");
         Transport.writeModel(vehicle,new FileWriter(path));
       Transport.printModels(Transport.readModel(new FileReader(path)));
   }
   public void startSerialize(String path) throws IOException, ClassNotFoundException {
       ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream(path));
       objectOutput.writeObject(vehicle);
       ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
       Transport.printModels((Vehicle) objectInputStream.readObject());
   }
   public static void startMultiAverage(){
       try {
           QuadBike quadBike = new QuadBike("Quad1", 2);
           quadBike.addItem("TestQuad1", 999.9);
           Scuter scuter = new Scuter("Scuter1", 3);
           scuter.addItem("Scuter2", 779.3);
           Moped moped = new Moped("Moped", 2);
           moped.addItem("TestMoped2.0", 234.2);
           System.out.println(Transport.getAverage(quadBike, scuter, moped));
       }catch (Exception e){
           System.out.println(e.toString());
       }
   }
}
