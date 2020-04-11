package com.istv.banq;

import javax.xml.ws.Endpoint;

public class BanqServiceStarter {
    //final static String URL_WILDFLY = "http://localhost:8080/banq";
    final static String URL_WILDFLY = "http://localhost:8080/wildfly-server-1.0-SNAPSHOT/BanqServiceImpl?wsdl";
    public static void main(String[] args) {
        Endpoint.publish(URL_WILDFLY, new BanqServiceImpl());
        System.out.println(URL_WILDFLY);
    }
}