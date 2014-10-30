package de.tum.in.dss.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import de.tum.in.dss.service.addservice;

public class ThriftClient {
	public static void main(String args[]) throws TException {
		TTransport transport = new TSocket("localhost", 9090);
		transport.open();
		TProtocol protocol = new TBinaryProtocol(transport);
		addservice.Client object = new addservice.Client(protocol);
		
	}
}
