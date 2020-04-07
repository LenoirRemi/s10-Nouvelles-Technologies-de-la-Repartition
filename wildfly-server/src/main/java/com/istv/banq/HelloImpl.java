package com.istv.banq;

import java.time.Instant;
import javax.jws.WebService;

@WebService(endpointInterface="com.istv.banq.IHello")
public class HelloImpl implements IHello{
    public String say(final String name){
        System.out.println("@ "+Instant.now()+" retourne Hello "+name);
        return "Hello "+name;
    }
}