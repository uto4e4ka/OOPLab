package vehicles;

import Exeptions.DuplicateModelNameException;
import Exeptions.ModelPriceOutOfBoundsException;
import Exeptions.NoSuchModelNameException;
import interfaces.Vehicle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Moped implements Vehicle {
    LinkedList<Model> models = new LinkedList<>();
    String brand;
    public Moped(String brand,int size){
        fillModels(size);
        this.brand = brand;
    }
    void fillModels(int size){
        for (int i =0;i<size;i++){
            try {
                addItem("Unknown_"+brand+"_"+i,0);
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
        String[] names = new String[models.size()];
        int i=0;
        for (Model model:models){
            names[i] = model.getName();
            i++;
        }
        return names;
    }

    @Override
    public double[] getPrices() {
        double[] prices = new double[models.size()];
        int i=0;
        for (Model model:models){
            prices[i] = model.getPrice();
            i++;
        }
        return prices;
    }

    @Override
    public double getPrice(String name) throws NoSuchModelNameException {
        for (Model model:models){
            if(model.getName().equals(name))return model.getPrice();
        }
        throw  new NoSuchModelNameException(name);
    }

    @Override
    public void setPrice(String name, double price) throws NoSuchModelNameException {
        for (Model model:models){
            if(model.getName().equals(name)){
                model.setPrice(price);
                return;
            }
        }
        throw  new NoSuchModelNameException(name);

    }

    @Override
    public void addItem(String name, double price) throws DuplicateModelNameException {
        for(Model model:models){
            if(model.getName().equals(name))throw new DuplicateModelNameException(name);
        }
        models.add(new Model(price,name));
    }

    @Override
    public int length() {
        return models.size();
    }

    @Override
    public void delItem(String name) throws NoSuchModelNameException {
        for (Model model:models){
            if(model.getName().equals(name)){
                models.remove(model);
                return;
            }
        }
        throw new NoSuchModelNameException(name);
    }

    @Override
    public void setItemName(String name, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        Model cmodel = null;
        for (Model model:models){
            if(model.getName().equals(newName))throw new DuplicateModelNameException(newName);
            if(model.getName().equals(name))cmodel=model;
        }
        if(cmodel==null)throw new NoSuchModelNameException(name);
        cmodel.setName(newName);
    }
    private class Model implements Serializable {
        double price;
        String name;

        public Model(double price, String name) {
            if(price<0)throw new ModelPriceOutOfBoundsException();
            this.price = price;
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            if(price<0)throw new ModelPriceOutOfBoundsException();
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Moped)) return false;
        return Objects.equals(models, ((Moped) object).models) && Objects.equals(brand, ((Moped) object).brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(models, brand);
    }

    @Override
    public String toString() {
        return "Moped{" +
                "models=" + models.toString() +
                ", brand='" + brand + '\'' +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Moped quadBike = (Moped) super.clone();
        quadBike.models = new LinkedList<>();
        for (Model model:this.models){
            quadBike.models.add(new Model(model.getPrice(),model.getName()));
        }
        return quadBike;
    }
}
