
package de.tum.in.dss.guestbook.wsdl;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.7.1
 * 2013-06-30T22:57:12.319+02:00
 * Generated source version: 2.7.1
 * 
 */
 
public class GuestBookService_GuestBookServiceStarter_Server{

    protected GuestBookService_GuestBookServiceStarter_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new GuestBookServiceImpl();
        String address = "http://wsc10:8080/GuestBookWebservice/services/guestbook_service";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new GuestBookService_GuestBookServiceStarter_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
