package ru.kpfu.itis.azat_khayrullin.exception;

public class AlreadyExistException extends Exception {

    public AlreadyExistException(){
        super();
    }

    public AlreadyExistException(String s){
        super(s);
    }
}
