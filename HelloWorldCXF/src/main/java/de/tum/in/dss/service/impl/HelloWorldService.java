/**
 * 
 */
package de.tum.in.dss.service.impl;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import org.apache.cxf.BusException;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import de.tum.in.dss.service.HelloWorld;

@WebService(endpointInterface = "de.tum.in.dss.service.HelloWorld", serviceName = "HelloWorld")
public class HelloWorldService implements HelloWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting Server");

		HelloWorldService implementor = new HelloWorldService();
		String address = "http://localhost:8080/HelloWorldCXF/services/hello_world_1";

		try {
			implementor.implementation(address);
		} catch (Exception e) {
			System.err.println("Problem while publishing webservice");
		}

		System.out.println("Server ready...");
		try {
			Thread.sleep(5 * 60 * 1000);
		} catch (InterruptedException e) {
			System.err.println("Problem found with running server");
		}
		System.out.println("Server exiting");
		System.exit(0);

	}

	public void implementation(String address) throws BusException {
		Endpoint.publish(address, this);
		JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
		svrFactory.setServiceClass(HelloWorldService.class);
		svrFactory.setAddress(address);
		svrFactory.setServiceBean(this);
		svrFactory.getInInterceptors().add(new LoggingInInterceptor());
		svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
		svrFactory.create();

	}

	@Override
	public String sayHello(String message) {
		System.out.println("Service Executing: Hello " + message);
		return "Hello " + message;
	}

}
