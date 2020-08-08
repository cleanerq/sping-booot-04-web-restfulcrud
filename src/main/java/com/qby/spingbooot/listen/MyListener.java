package com.qby.spingbooot.listen;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request listener destroy");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("request listener start");
    }
}
