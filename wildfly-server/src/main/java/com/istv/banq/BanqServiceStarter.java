package com.istv.banq;

import javax.xml.ws.Endpoint;

public class BanqServiceStarter {
    final static String URL_HELLO = "http://localhost:8080/banq";
    public static void main(String[] args) {
        Endpoint.publish(URL_HELLO, new BanqServiceImpl());
        System.out.println(URL_HELLO);
    }
}