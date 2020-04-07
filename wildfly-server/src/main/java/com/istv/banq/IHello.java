package com.istv.banq;

import javax.jws.*;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style=Style.RPC)
public  interface IHello {
    @WebMethod
    String say(@WebParam(name="nom") final String name);
}