package de.tum.in.dss.server;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import de.tum.in.dss.service.addservice;
import de.tum.in.dss.service.myimpl;

public class ThriftServer {
	public static void main(String args[]) throws TTransportException {
		myimpl handler = new myimpl();
		addservice.Processor<myimpl> processor = new addservice.Processor<myimpl>(
				handler);
		
		TServerTransport serverTransport=new TServerSocket(9090);
		TServer server=new TSimpleServer(new TSimpleServer.Args (serverTransport).processor(processor));
		server.serve();

	}
}
