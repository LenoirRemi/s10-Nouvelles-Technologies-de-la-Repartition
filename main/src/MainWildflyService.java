import com.istv.banq.generated.BanqService;
import com.istv.banq.generated.BanqServiceImplService;

public class MainWildflyService {
    public static void main(String[] args) {
        int id = 1;
        double amountDebit = 22.5;
        double amountCredit = 16.32;

        BanqServiceImplService service = new BanqServiceImplService();
        BanqService serviceProxy = service.getBanqServiceImplPort();

        System.out.println("Balance user " + id + ": " + serviceProxy.checkBalance(id));
        System.out.println(serviceProxy.debit(id, amountDebit));
        System.out.println("Balance user " + id + ": " + serviceProxy.checkBalance(id));
        System.out.println(serviceProxy.credit(id, amountCredit));
        System.out.println("Balance user " + id + ": " + serviceProxy.checkBalance(id));
    }
}