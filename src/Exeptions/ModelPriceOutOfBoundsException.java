package Exeptions;
public class ModelPriceOutOfBoundsException extends RuntimeException{
    public ModelPriceOutOfBoundsException(){
        super("Неверная цена модели!");
    }
}
