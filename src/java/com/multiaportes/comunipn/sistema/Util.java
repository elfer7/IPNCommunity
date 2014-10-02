package com.multiaportes.comunipn.sistema;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Util {
    
    private final String uri;
    
    public Util(String ruta){uri=ruta;}
    
    public Document plantilla(String opcion){
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(uri+"/xml/plantilla"+opcion+".xml");
        Document document= new Document();
        try
        {
            document = (Document) builder.build( xmlFile );
            
        } catch (JDOMException | IOException ex) {System.out.println(ex);}
        return document;
    }
    
    public boolean ExistenciaUsuario(String nick){ 
        SAXBuilder builder = new SAXBuilder();
        boolean respuesta=true;
        ArrayList Nicks=new ArrayList();
        File xmlFile = new File(uri+"/xml/usuarios.xml");
        try
        {
            Document document = (Document) builder.build( xmlFile );
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren( "usuario" );            
            for (int i=0;i<list.size();i++)
            {
                Element usuario = (Element) list.get(i);
                Nicks.add(usuario.getAttributeValue("nickname").toLowerCase());
            }
        } catch (JDOMException | IOException ex) {System.out.println(ex);}
        respuesta=Nicks.contains(nick.toLowerCase()); 
        return respuesta;
    }
    public boolean IDmatch(long id, String opcion){
        SAXBuilder builder = new SAXBuilder();
        boolean respuesta=true;
        ArrayList ids=new ArrayList();
        File xmlFile = new File(uri+"/xml/"+opcion+".xml");
        try
        {
            Document document = (Document) builder.build( xmlFile );
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren( "usuario" );            
            for (int i=0;i<list.size();i++)
            {
                Element usuario = (Element) list.get(i);
                ids.add(usuario.getAttributeValue("id"));
            }
            
        } catch (JDOMException | IOException ex) {System.out.println(ex);}
        respuesta=ids.contains(""+id); 
        return respuesta;
    }
    /* public static void main(String args[]){
        Util user=new Util("C:\\Users\\zack\\Documents\\NetBeansProjects\\ComunIPN\\web\\");
        System.out.println(user.IDmatch(686664832,"preguntas"));
    }*/
}
