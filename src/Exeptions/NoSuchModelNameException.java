package Exeptions;

public class NoSuchModelNameException extends Exception{
    String name;
    public NoSuchModelNameException(String name){
        super("Модели с таким названием не найдено!("+name+")");
    }
}
