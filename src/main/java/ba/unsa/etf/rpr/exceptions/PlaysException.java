package ba.unsa.etf.rpr.exceptions;

public class PlaysException extends Exception{

    public PlaysException(String msg, Exception reason){
        super(msg, reason);
    }

    public PlaysException(String msg){
        super(msg);
    }
}