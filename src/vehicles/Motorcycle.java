package vehicles;

import Exeptions.DuplicateModelNameException;
import Exeptions.ModelPriceOutOfBoundsException;
import Exeptions.NoSuchModelNameException;
import interfaces.Vehicle;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Motorcycle implements Vehicle {

    private String brand;

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    private class Model implements Serializable {
        Model(String model_name,double price){
            this.model_name = model_name;
            if(price<0) throw new ModelPriceOutOfBoundsException();
            this.price = price;
        }
        private String model_name = null;
        private double price = Double.NaN;
        private Model next = null;
        private Model prev = null;
        void setModel_name(String name){
            model_name = name;
        }
        String getModel_name(){
            return model_name;
        }
        void setPrice(double price){
            if(price<0)throw new ModelPriceOutOfBoundsException();
            this.price = price;
        }
    }
    private int size =0;
    private Model head;//!!!!
    private transient long lastModified;

    {
        lastModified=new Date().getTime();
    }   ///!!!
    public Motorcycle(String brand){//!!!!
        this.brand =brand;
        head = new Model(null,Double.NaN);
        head.next = head;
        head.prev = head;
    }
    public Motorcycle(String brand,int size) {
        this.size = size;//!!!!
        this.brand = brand;
        head = new Model(null,Double.NaN);
        head.next = head;
        head.prev = head;
        fillModels(size);

    }
    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }
    void fillModels(int size){
            for (int i = 0; i < size; i++) {
                Model m;
                if(head!=null){
                    m = new Model("Unknown_" + brand + "_" + i,0);
                    m.next = head.next;
                    m.prev = head;
                    head.next.prev=m;
                    head.next = m;
                }
            }
    }
    @Override
    public String[] getNames() {
        String[] names = new String[length()];
        int i =0;
        Model m = head;
        while (m.next!=head){
            names[i] = m.next.model_name;
            i++;
            m=m.next;
        }
        return names;
    }

    @Override
    public double[] getPrices() {
        double[] prices = new double[length()];
        int i=0;
        Model m = head;
        while (m.next!=head){
            prices[i] = m.next.price;
            i++;
            m=m.next;
        }
        return prices;
    }

    @Override
    public double getPrice(String name) throws NoSuchModelNameException {
        Model m = head;
        while (m.next!=head){
            if(m.next.model_name.equals(name))return m.next.price;
            m=m.next;
        }
        throw new NoSuchModelNameException(name);
    }

    @Override
    public void setPrice(String name, double price) throws NoSuchModelNameException {
        boolean flag = false;
        Model m = head;
        while (m.next!=head){
            if(m.next.model_name.equals(name)){
                m.next.setPrice(price);
                flag = true;
                break;
            }
            m=m.next;
        }
        lastModified = new Date().getTime();
        if(!flag) throw new NoSuchModelNameException(name);
    }
    private Model getItem(String name) throws NoSuchModelNameException{
        Model m=head;//!!!
        while (m.next!=head){
            if(m.next.model_name.equals(name))return m.next;
            m = m.next;
        }
        throw new NoSuchModelNameException(name);
    }
    @Override
    public void addItem(String name, double price) throws DuplicateModelNameException {
        Model m;
        isUniq(name);
        if(head!=null){
            m = new Model(name,price);
            m.next = head.next;
            m.prev = head;
            head.next.prev=m;
            head.next = m;
            size++;
        }
    }
void isUniq(String name) throws DuplicateModelNameException{
        Model m = head;
    while (m.next!=head){
        if(m.next.model_name.equals(name)) throw new DuplicateModelNameException(m.next.model_name);
        m = m.next;
    }
}
    @Override
    public Motorcycle clone() throws CloneNotSupportedException {
        Motorcycle motorcycle = (Motorcycle) super.clone();
            motorcycle.head  = new Model(null,Double.NaN);
            Model m = head.next;
            Model tail = motorcycle.head;
            while (m!=head){
                Model cloned = new Model(m.model_name,m.price);
                tail.next = cloned;
                cloned.prev = tail;
                tail = cloned;
                m = m.next;
            }
            tail.next = motorcycle.head;
            motorcycle.head.prev = tail;
        return motorcycle;
    }
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Motorcycle)
                &&((Vehicle) obj).getBrand().equals(getBrand())&&
                Arrays.equals(getNames(),((Vehicle) obj).getNames())
                &&Arrays.equals(getPrices(), ((Vehicle) obj).getPrices());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getBrand().hashCode(),Arrays.hashCode(getPrices()),Arrays.hashCode(getNames()));
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
    public int length() {//!!!size
        return size;
    }

    @Override
    public void delItem(String name) throws NoSuchModelNameException {
        Model item = getItem(name);
     if(head!=null&&head!=item){
         item.prev.next = item.next;
         item.next.prev=item.prev;
         size--;
         lastModified = new Date().getTime();
     }
    }

    @Override
    public void setItemName(String name, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        Model m = head;
        Model buff = null;
        boolean flag = false;
        while (m.next!=head){
           if(m.next.model_name.equals(name)){
               buff = m.next;
               flag = true;
           }
           if(m.next.model_name.equals(newName))throw new DuplicateModelNameException(m.next.model_name);
           m=m.next;
        }
        if(!flag) throw new NoSuchModelNameException(name);
        buff.model_name = newName;//1
    }


}
