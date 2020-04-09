package com.istv.banq;

import com.istv.banq.generated.BanqService;
import com.istv.banq.generated.BanqServiceImplService;

public class ServiceTester {
    public static void main(String[] args) {
        int id = 2;
        double amount = 22.5;

        BanqServiceImplService service = new BanqServiceImplService();
        BanqService serviceProxy = service.getBanqServiceImplPort();

        System.out.println("Balance : " + serviceProxy.checkBalance(id));
        System.out.println(serviceProxy.debit(id, amount));
        System.out.println(serviceProxy.credit(id, amount));
    }
}
