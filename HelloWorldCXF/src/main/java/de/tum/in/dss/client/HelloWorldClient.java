/**
 * 
 */
package de.tum.in.dss.client;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import de.tum.in.dss.service.HelloWorld;

public class HelloWorldClient {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		HelloWorldClient client = new HelloWorldClient();

		try {
			client.implementation();
		} catch (Exception e) {
			System.err.println("Problem with service");
		}

		System.exit(0);
	}

	public void implementation() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(HelloWorld.class);
		factory.setAddress("http://localhost:8080/HelloWorldCXF/services/hello_world_1");
		HelloWorld client = (HelloWorld) factory.create();

		String reply = client.sayHello("Nikanj ");
		System.out.println("Server said: " + reply);
	}

}
