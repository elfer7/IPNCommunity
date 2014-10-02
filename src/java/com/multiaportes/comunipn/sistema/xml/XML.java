package com.multiaportes.comunipn.sistema.xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

abstract class XML
{
    protected File fichero;
    protected DocumentBuilderFactory dbf;
    protected Document documento;
    protected XPath xp;
    protected Node nodo;
    protected String instruccion;
    
    protected XML(String ruta)
    {
        nodo = null;
        try
        {
            fichero = new File(ruta);
            dbf = DocumentBuilderFactory.newInstance();
            documento = dbf.newDocumentBuilder().parse(ruta); //fichero.getAbsolutePath()
            xp = XPathFactory.newInstance().newXPath();
        }
        catch(IOException | ParserConfigurationException | SAXException x)
        {
            System.err.println(x.getMessage());
        }
    }
    protected String getValor(String instruccion)
    {
        this.instruccion = instruccion + "/text()"; //String xPathStr = "/usuario/xxx/xd/text()";
        this.crearNodo();
        
        if(nodo != null)
            return nodo.getNodeValue().trim();
        else
            return "ComunIPN Error: Nodo no encontrado: "+this.instruccion;
    }
    protected String getValor(String instruccion, String atributo)
    {
        this.instruccion = instruccion + "/@"+atributo; //String xPathStr = "/usuario/xxx/xd/@miau";
        this.crearNodo();
        
        if(nodo != null)
            return nodo.getNodeValue().trim();
        else
            return "ComunIPN Error: Nodo no encontrado: "+this.instruccion;
    }
    protected void crearNodo()
    {
        try
        {
            if(documento != null)
            {
                NodeList tmp = (NodeList) xp.compile(instruccion).evaluate(documento, XPathConstants.NODESET);
                nodo =  tmp.item(0);
            }
        }
        catch(XPathExpressionException x)
        {
            System.err.println(x.getMessage());
        }
    }
}
