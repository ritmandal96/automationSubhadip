package com.ritm.wrapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLValidation 
{

	DocumentBuilder dbuilder;
	DocumentBuilderFactory dbfactory;
	Document xmlDocument;
	HashMap<String,String> Type=new HashMap<String,String>();
	HashMap<String, String> ReverseHash=new HashMap<String,String>();
	
	public void ParseXMLFile(File xmlFile) throws ParserConfigurationException, SAXException, IOException
	{
		dbfactory=DocumentBuilderFactory.newInstance();
		dbuilder=dbfactory.newDocumentBuilder();
		xmlDocument=dbuilder.parse(xmlFile);
		xmlDocument.getDocumentElement().normalize();
	}	
	
	public String[] XPathGen(String[][] xpathMap,String[][] AppValue)
	{
		String[] xpath=new String[AppValue.length-1];
		HashMap<String,String> hmap=new HashMap<String, String>();
		for(int i=1;i<xpathMap.length;i++)
		{
			hmap.put(xpathMap[i][0],xpathMap[i][1]); // Field Name and Xpath 
			
		}
		
		for(int i=1;i<xpathMap.length;i++)
		{
			Type.put(xpathMap[i][0],xpathMap[i][2]); // FieldName and Datatype
			
		}
		
		String[] name=new String[AppValue.length-1];
		String[] Borrowerindex=new String[AppValue.length-1];
		String[] Valueindex=new String[AppValue.length-1];
		for(int i=1;i<AppValue.length;i++)
		{
			name[i-1]=AppValue[i][0].split("-")[0]; // ac-1 means ac will store in name[]
			Borrowerindex[i-1]=(AppValue[i][0].split("-").length>1)?AppValue[i][0].split("-")[1]:"";
			Valueindex[i-1]=(AppValue[i][0].split("-").length>2)?AppValue[i][0].split("-")[2]:"";
			
			
		}
		
		for(int i=0;i<AppValue.length-1;i++)
		{
			try
			{
		       xpath[i]=hmap.get(name[i]).replace("$", Borrowerindex[i]).replace("#", Valueindex[i]);
		       ReverseHash.put(xpath[i], name[i]);
			}
			catch(Exception e)
			{
				System.out.println(name[i]);
			}
		}
		
		return xpath;
	}
	
	
	public String readxml(String Xpath) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException  
	{
		try 
		{
		    String xmlValue = null;
    		XPath xPath =  XPathFactory.newInstance().newXPath();
		    NodeList nodeList = (NodeList)xPath.compile(Xpath).evaluate(xmlDocument, XPathConstants.NODESET);
		    if(nodeList.getLength()>0) 
		    {
		        Node node=(Node) nodeList.item(0);
		        xmlValue=(node.getNodeValue()!=null)?node.getNodeValue():node.getTextContent();
		    }
	        else
	        {
	        	xmlValue="Can't Fetch Element";
		    }
		 
		return xmlValue;
		}
		
		catch(Exception e)
		{
			return e.toString();
		}
			
	}
	
	
	
}
			

