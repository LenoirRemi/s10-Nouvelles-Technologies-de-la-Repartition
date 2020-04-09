package com.istv.banq;

import java.time.Instant;
import javax.jws.WebService;

@WebService(endpointInterface="com.istv.banq.BanqService")
public class BanqServiceImpl implements BanqService{
    public String say(final String name){
        System.out.println("@ "+Instant.now()+" retourne Hello "+name);
        return "Hello "+name;
    }
}