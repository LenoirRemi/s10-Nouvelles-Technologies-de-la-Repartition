package com.istv.banq;

import com.istv.banq.generated.BanqService;
import com.istv.banq.generated.BanqServiceImplService;

public class ServiceTester {
    public static void main(String[] args) {
        BanqServiceImplService service = new BanqServiceImplService();
        BanqService serviceProxy = service.getBanqServiceImplPort();
        System.out.println("Balance :" + serviceProxy.checkBalance(1));
    }
}
