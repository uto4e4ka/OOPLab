package threads.runnable.fileInput;

import interfaces.Vehicle;
import vehicles.Automobile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class FileReadModelsRunnable implements Runnable{
String path;
BlockingQueue<Vehicle> blockingQueue;
public FileReadModelsRunnable(String path, BlockingQueue<Vehicle> blockingQueue){
    this.path = path;
    this.blockingQueue = blockingQueue;
}
    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(new FileReader(path));
            String modelName = scanner.nextLine();
            Vehicle vehicle = new Automobile(modelName);
            blockingQueue.put(vehicle);
           // System.out.println("Считано: "+modelName);
        } catch (FileNotFoundException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
