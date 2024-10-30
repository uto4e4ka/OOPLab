package interfaces;

import Exeptions.DuplicateModelNameException;
import Exeptions.NoSuchModelNameException;

import java.io.Serializable;

public interface Vehicle extends Serializable,Cloneable {
    String getBrand();
    void setBrand(String brand);
    String[] getNames();
    double[] getPrices();
    double getPrice(String name) throws NoSuchModelNameException;
    void setPrice(String name,double price)throws NoSuchModelNameException;
    void addItem(String name,double price) throws DuplicateModelNameException;
    int length();
    void delItem(String name) throws NoSuchModelNameException;
    public void setItemName(String name,String newName) throws DuplicateModelNameException,NoSuchModelNameException;
}

