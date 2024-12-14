package com.foodwastesavior.webapp.exception;

public class ExistedException extends IllegalArgumentException{
     public ExistedException(String message) {
         super(message);
     }

    public ExistedException(String message, Throwable cause) {
         super(message, cause);
    }

}
