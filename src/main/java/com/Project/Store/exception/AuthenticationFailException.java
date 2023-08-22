package com.Project.Store.exception;

public class AuthenticationFailException  extends IllegalArgumentException{
    public AuthenticationFailException(String msg) {
        super(msg);
    }
}
