package vehicles;

import Exeptions.DuplicateModelNameException;
import Exeptions.ModelPriceOutOfBoundsException;
import Exeptions.NoSuchModelNameException;
import interfaces.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Scuter implements Vehicle {
    private HashMap<String,Double> models = new HashMap<>();
    String brand;

    public Scuter(String brand,int size){
        this.brand = brand;
        fillModels(size);
    }

    void fillModels(int size) {
        for (int i = 0; i < size; i++) {
            try {
                addItem("Unknown_" + brand + "_" + i,0);
            }catch (Exception ignored){

            }
            }
        }
    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String[] getNames() {
        String[] strs = new String[models.size()];
        int i =0;
        for (String str:models.keySet()){
            strs[i] = str;
            i++;
        }
        return strs;
    }

    @Override
    public double[] getPrices() {
        double[] prices = new double[models.size()];
        int i =0;
        for (Double price:models.values()){
            prices[i] = price;
            i++;
        }
        return prices;
    }

    @Override
    public double getPrice(String name) throws NoSuchModelNameException {
        if(models.containsKey(name))return models.get(name);
        throw new NoSuchModelNameException(name);
    }

    @Override
    public void setPrice(String name, double price) throws NoSuchModelNameException {
        if(price<0)throw new ModelPriceOutOfBoundsException();
        if(models.containsKey(name))models.put(name,price);
        else throw new NoSuchModelNameException(name);
    }

    @Override
    public void addItem(String name, double price) throws DuplicateModelNameException {
        if(price<0)throw new ModelPriceOutOfBoundsException();
        if(!models.containsKey(name))models.put(name,price);
        else throw new DuplicateModelNameException("name");
    }

    @Override
    public int length() {
        return models.size();
    }

    @Override
    public void delItem(String name) throws NoSuchModelNameException {
        if(models.containsKey(name))models.remove(name);
        else
            throw new NoSuchModelNameException(name);
    }

    @Override
    public void setItemName(String name, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        if(!models.containsKey(name))throw new NoSuchModelNameException(name);
        if(models.containsKey(newName))throw new DuplicateModelNameException(newName);
        double temp_price = models.get(name);
        models.remove(name);
        models.put(newName,temp_price);
    }

    @Override
    public boolean equals(Object obj) {
       if(!(obj instanceof Scuter)) return  false;
       if(obj==this)return true;
       return (((Scuter) obj).brand.equals(brand))
                &&(((Scuter) obj).models.equals(models));
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand,models);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Scuter scuter = (Scuter) super.clone();
        scuter.models = new HashMap<>();
        scuter.models.putAll(this.models);
        return scuter;
    }

    @Override
    public String toString() {
        return "Scuter{" +
                "models=" + models.toString() +
                ", brand='" + brand + '\'' +
                '}';
    }
}
