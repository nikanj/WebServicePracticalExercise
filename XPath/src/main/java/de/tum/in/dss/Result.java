package de.tum.in.dss;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Result extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8374829244486098803L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			DocumentBuilderFactory domFactory = DocumentBuilderFactory
					.newInstance();
			domFactory.setNamespaceAware(true);
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document doc = builder.parse(getServletContext().getResourceAsStream("/premiumCustomers.xml"));
			XPath xpath = XPathFactory.newInstance().newXPath();
			// XPath Query for showing all nodes value
			XPathExpression expr1 = xpath.compile("//Kunden");
			XPathExpression expr2 = xpath
					.compile("//Kunden/Kunde[Anschrift/PLZ=48234]");

			XPathExpression expr3 = xpath
					.compile("//Kunden/Kunde[@geschlecht='M'][1]");
			XPathExpression expr4 = xpath
					.compile("//Kunden/Kunde[@geburtsjahr='1972']");

			XPathExpression expr5 = xpath
					.compile("//Kunden/Kunde[@nachname='Schmidt']/@vorname");
			XPathExpression expr6 = xpath
					.compile("//Kunden/Kunde[Hobbies='Konzert']/*");

			XPathExpression expr7 = xpath
					.compile("//Kunden/Kunde[@geburtsjahr<'1970']/Anschrift/Ort");
			XPathExpression expr8 = xpath
					.compile("//Kunden/Kunde[Hobbies[contains(.,'Golf')]]/KundenNr");

			Object result1 = expr1.evaluate(doc, XPathConstants.NODESET);
			Object result2 = expr2.evaluate(doc, XPathConstants.NODESET);
			Object result3 = expr3.evaluate(doc, XPathConstants.NODESET);
			Object result4 = expr4.evaluate(doc, XPathConstants.NODESET);
			Object result5 = expr5.evaluate(doc, XPathConstants.NODESET);
			Object result6 = expr6.evaluate(doc, XPathConstants.NODESET);
			Object result7 = expr7.evaluate(doc, XPathConstants.NODESET);
			Object result8 = expr8.evaluate(doc, XPathConstants.NODESET);

			resp.getWriter().println("----All customer nodes of the document:----");
			print(result1, "Kunde",req,resp);
			resp.getWriter().println("\n----All customers who live in postcode area postcode = 48234:----");
			print(result2, "Kunde",req,resp);
			resp.getWriter().println("\n----The first client node for which the attribute gender is not equal to M:----");
			print(result3, "Kunde",req,resp);
			resp.getWriter().println("\n----All customers who are born in 1972:----");
			print(result4, "Kunde",req,resp);
			resp.getWriter().println("\n----The names of all customers with the last name Schmidt:----");
			print(result5, "Kunde",req,resp);
			resp.getWriter().println("\n----The customer elements is their only hobby concert:----");
			print(result6, "Kunde",req,resp);
			resp.getWriter().println("\n----The City of clients who were born before 1970:----");
			print(result7, "Anschrift",req,resp);
			resp.getWriter().println("\n----The customer number of all customers who play golf:----");
			print(result8, "Kunde",req,resp);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void print(Object result, String parentNodeName,HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {

		NodeList nodes = (NodeList) result;

		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			
			if(node.getAttributes()!=null && node.getAttributes().getLength()>0){
				resp.getWriter().println(" ");
				resp.getWriter().print("nachname : "+node.getAttributes().getNamedItem("nachname").getNodeValue());
				resp.getWriter().print("  vorname : "+node.getAttributes().getNamedItem("vorname").getNodeValue());
				resp.getWriter().print("  geschlecht : "+node.getAttributes().getNamedItem("geschlecht").getNodeValue());
				resp.getWriter().println("  geburtsjahr : "+node.getAttributes().getNamedItem("geburtsjahr").getNodeValue());
			}
			
			if (node.hasChildNodes()) {

				NodeList childList = node.getChildNodes();
				// recursion goes here
				print(childList, parentNodeName + ":" + node.getNodeName(),req,resp);

			} else {
				
				String nodeValue = node.getNodeValue();
				String trimNodeValue = nodeValue.trim();

				if (!trimNodeValue.isEmpty()) {
					resp.getWriter().println(nodeValue);
				}

			}
			/*resp.getWriter().println("nachname : "+nodes.item(i).getAttributes().getNamedItem("nachname").getNodeValue());
			resp.getWriter().println("vorname : "+nodes.item(i).getAttributes().getNamedItem("vorname").getNodeValue());
			resp.getWriter().println("geschlecht : "+nodes.item(i).getAttributes().getNamedItem("geschlecht").getNodeValue());
			resp.getWriter().println("geburtsjahr : "+nodes.item(i).getAttributes().getNamedItem("geburtsjahr").getNodeValue());
			resp.getWriter().println("Details : "+nodes.item(i).getTextContent());
			resp.getWriter().println("<br>");*/
		}
	}
	}

