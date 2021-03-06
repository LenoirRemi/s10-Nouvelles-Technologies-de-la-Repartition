
package com.istv.banq.generated;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "BanqServiceImplService", targetNamespace = "http://banq.istv.com/", wsdlLocation = "http://localhost:8080/wildfly-server-1.0-SNAPSHOT/BanqServiceImpl?wsdl")
public class BanqServiceImplService
    extends Service
{

    private final static URL BANQSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException BANQSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName BANQSERVICEIMPLSERVICE_QNAME = new QName("http://banq.istv.com/", "BanqServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/wildfly-server-1.0-SNAPSHOT/BanqServiceImpl?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        BANQSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        BANQSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public BanqServiceImplService() {
        super(__getWsdlLocation(), BANQSERVICEIMPLSERVICE_QNAME);
    }

    public BanqServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), BANQSERVICEIMPLSERVICE_QNAME, features);
    }

    public BanqServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, BANQSERVICEIMPLSERVICE_QNAME);
    }

    public BanqServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, BANQSERVICEIMPLSERVICE_QNAME, features);
    }

    public BanqServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BanqServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns BanqService
     */
    @WebEndpoint(name = "BanqServiceImplPort")
    public BanqService getBanqServiceImplPort() {
        return super.getPort(new QName("http://banq.istv.com/", "BanqServiceImplPort"), BanqService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BanqService
     */
    @WebEndpoint(name = "BanqServiceImplPort")
    public BanqService getBanqServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://banq.istv.com/", "BanqServiceImplPort"), BanqService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (BANQSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw BANQSERVICEIMPLSERVICE_EXCEPTION;
        }
        return BANQSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}
