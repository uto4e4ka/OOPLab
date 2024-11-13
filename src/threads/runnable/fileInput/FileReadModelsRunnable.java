package threads.runnable.fileInput;

import interfaces.Vehicle;
import vehicles.Automobile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.BlockingDeque;

public class FileReadModelsRunnable implements Runnable{
String path;
BlockingDeque<Vehicle> blockingDeque;
public FileReadModelsRunnable(String path,BlockingDeque<Vehicle> blockingDeque){
    this.path = path;
    this.blockingDeque = blockingDeque;
}
    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(new FileReader(path));
            String modelName = scanner.nextLine();
            Vehicle vehicle = new Automobile(modelName);
            blockingDeque.put(vehicle);
        } catch (FileNotFoundException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
