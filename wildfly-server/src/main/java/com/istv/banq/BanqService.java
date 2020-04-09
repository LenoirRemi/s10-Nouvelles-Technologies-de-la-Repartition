package com.istv.banq;

import javax.jws.*;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style=Style.RPC)
public  interface BanqService {
    @WebMethod
    Double checkBalance(@WebParam(name="id") final Integer id);

    @WebMethod
    String credit(@WebParam(name="nom") final String name);

    @WebMethod
    String debit(@WebParam(name="nom") final String name);
}