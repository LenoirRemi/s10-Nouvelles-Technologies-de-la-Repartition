package com.istv.banq;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.jws.WebService;

import org.apache.commons.io.IOUtils;
import org.json.*;

@WebService(endpointInterface="com.istv.banq.BanqService")
public class BanqServiceImpl implements BanqService{
    @Override
    public Double checkBalance(final Integer id) {
        double balance = 0;
        try {
            URL url = new URL("http://localhost:8081/users/" + id.toString());
            String a = IOUtils.toString(url, StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(a);
            balance = json.getDouble("balance");
        } catch (Exception e){
            System.out.println(e);
        }
        return balance;
    }

    @Override
    public String credit(final String name) {
        return "";
    }

    @Override
    public String debit(final String name) {
        return "";
    }
}