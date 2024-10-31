package vehicles;

import Exeptions.DuplicateModelNameException;
import Exeptions.NoSuchModelNameException;
import interfaces.Vehicle;

public class Scuter implements Vehicle {
    @Override
    public String getBrand() {
        return "";
    }

    @Override
    public void setBrand(String brand) {

    }

    @Override
    public String[] getNames() {
        return new String[0];
    }

    @Override
    public double[] getPrices() {
        return new double[0];
    }

    @Override
    public double getPrice(String name) throws NoSuchModelNameException {
        return 0;
    }

    @Override
    public void setPrice(String name, double price) throws NoSuchModelNameException {

    }

    @Override
    public void addItem(String name, double price) throws DuplicateModelNameException {

    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public void delItem(String name) throws NoSuchModelNameException {

    }

    @Override
    public void setItemName(String name, String newName) throws DuplicateModelNameException, NoSuchModelNameException {

    }
}
