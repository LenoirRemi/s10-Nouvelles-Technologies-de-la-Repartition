package com.istv.banq;

import com.istv.banq.generated.BanqService;
import com.istv.banq.generated.BanqServiceImplService;

public class ServiceTester {
    public static void main(String[] args) {
        BanqServiceImplService service = new BanqServiceImplService();
        BanqService serviceProxy = service.getBanqServiceImplPort();
        System.out.println("Balance :" + serviceProxy.checkBalance(1));
        double amount = 22.5;
        System.out.println(serviceProxy.debit(1, amount));
        System.out.println(serviceProxy.credit(1, amount));
    }
}
