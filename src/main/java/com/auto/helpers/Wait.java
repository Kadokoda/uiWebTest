package com.auto.helpers;

import java.util.concurrent.TimeUnit;

public class Wait {

    public void start(int second){
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (Exception ex){

        }
    }
}
