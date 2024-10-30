package vehicles;

import Exeptions.DuplicateModelNameException;
import Exeptions.ModelPriceOutOfBoundsException;
import Exeptions.NoSuchModelNameException;
import interfaces.Vehicle;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Automobile implements Vehicle {
    private String brand;
    private Model[] models;
   public Automobile(String brand,int size)  {
        this.brand=brand;////!
       models = fillModels(size);

    }
    public Automobile(String brand) {
        this.brand=brand;
        models = new Model[0];
        //
    }
    public String getBrand() {
        return brand;
    }
    private Model[] fillModels(int size){
       Model[] model = new Model[size];
       for (int i=0;i<model.length;i++){
           model[i]= new Model("Unknown_"+brand+"_"+i,0);
       }
       return model;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String[] getNames(){
        String[] names = new String[models.length];
        for(int i=0;i<models.length;i++){
            names[i]=models[i].model_name;
        }
        return names;
    }
    public double[] getPrices(){
        double[] prices = new double[models.length];
        for(int i=0;i<models.length;i++){
            prices[i]=models[i].price;
        }
        return prices;
    }
    public double getPrice(String name)throws NoSuchModelNameException {
        for(Model model:models)
            if(model.model_name.equals(name))
                return model.price;
        throw new NoSuchModelNameException(name);
    }
    public void setPrice(String name,double price)throws NoSuchModelNameException{
       boolean flag = false;
        for (Model model : models)
            if (model.model_name.equals(name)) {
                model.setPrice(price);//!!!
                flag = true;
                break;
            }
        if(!flag)
            throw new NoSuchModelNameException(name);
    }
    public void setItemName(String name,String newName) throws DuplicateModelNameException,NoSuchModelNameException {
        boolean flag = false;
        int id = -1;//!!!
        for (int i=0;i<models.length;i++){
            if(models[i].model_name.equals(name)){
                id = i;
                flag = true;
            }
            if(models[i].model_name.equals(newName))throw new DuplicateModelNameException(models[i].model_name);
        }
        if(!flag) throw new NoSuchModelNameException(name);
        models[id].model_name=newName;
    }
    public void addItem(String name,double price) throws DuplicateModelNameException {
       checkUniq(name,models);
       models = Arrays.copyOf(models,models.length+1);
       models[models.length-1]=new Model(name,price);
    }
    private void checkUniq(String name,Model[] model)throws DuplicateModelNameException{
        for (Model value : model)
            if (value!=null&&value.model_name.equals(name)) throw new DuplicateModelNameException(value.model_name);
    }
    public int length(){
        return models.length;
    }

    @Override
    public void delItem(String name) throws NoSuchModelNameException {
       int index = 0;
       boolean flag = false;
       for (Model model:models){
           if(model.model_name.equals(name)){
               flag = true;
               break;
           }
           index++;
       }
        if(!flag)throw new NoSuchModelNameException(name);
        System.arraycopy(models,index+1,models,index,models.length-index-1);//!!!
        models = Arrays.copyOf(models,models.length-1);
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getBrand()).append(" [");
        for (int i =0;i<getNames().length;i++){
            stringBuilder.append(getNames()[i])
                    .append(" ")
                    .append(getPrices()[i])
                    .append(i!=getNames().length-1?",":"");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Automobile)
                &&((Vehicle) obj).getBrand().equals(getBrand())&&
                Arrays.equals(getNames(),((Vehicle) obj).getNames())
                &&Arrays.equals(getPrices(), ((Vehicle) obj).getPrices());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBrand().hashCode(),Arrays.hashCode(getPrices()),Arrays.hashCode(getNames()));
    }

    @Override
    public Automobile clone() throws CloneNotSupportedException {
       Automobile automobile = (Automobile) super.clone();
       automobile.models = new Model[models.length];
        for (int i =0;i<models.length;i++){
            automobile.models[i] = new Model(models[i].model_name,models[i].price);
        }
        return automobile;
    }

    private class Model implements Serializable {
        private String model_name;
        private double price;
        public Model(String model_name, double price){
            this.model_name=model_name;
            if(price<0) throw new ModelPriceOutOfBoundsException();
            this.price=price;
        }

        public String getModel_name() {
            return model_name;
        }

        public void setModel_name(String model_name) {
               this.model_name = model_name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            if(price<0)throw new ModelPriceOutOfBoundsException();
            this.price = price;
        }
    }
}
