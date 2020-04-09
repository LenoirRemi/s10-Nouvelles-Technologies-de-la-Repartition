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
    String credit(@WebParam(name="id") final Integer id, @WebParam(name="amount") final Double amount);

    @WebMethod
    String debit(@WebParam(name="id") final Integer id, @WebParam(name="amount") final Double amount);
}