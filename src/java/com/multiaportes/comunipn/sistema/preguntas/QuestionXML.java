package com.multiaportes.comunipn.sistema.preguntas;

import com.multiaportes.comunipn.sistema.Util;
import com.multiaportes.comunipn.sistema.ficheros.Directorio;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import org.jdom2.CDATA;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class QuestionXML {
    private String uri;
    private final Util tool;
    
    public QuestionXML(String ruta){
        uri=ruta;
        tool=new Util(uri);
    }
    public void CrearPregunta(long idP, long idU, long idC, String contenido, String titulo, List<String> tags){       
        uri=uri+"/preguntas/";
        Directorio.crearDirectorio(uri, idP);
        File xml=new File(uri+idP+"/pregunta.xml");
        Document doc;
        
        if(!xml.exists()){
            try {
                xml.createNewFile();
            } catch (IOException ex) {}
        }
        
        try{
            Calendar cal = Calendar.getInstance();
            
            doc=tool.plantilla("Pregunta");
            Element root=doc.getRootElement();
            
            Element categoria=root.getChild("categoria");
            Element content=root.getChild("contenido");
            Element ht = root.getChild("tags");
            Element fe = root.getChild("fecha");
            
            fe.setAttribute("dia", ""+cal.get(Calendar.DAY_OF_MONTH));
            fe.setAttribute("mes", ""+cal.get(Calendar.MONTH));
            fe.setAttribute("anio", ""+cal.get(Calendar.YEAR));
            fe.setAttribute("hora", ""+cal.get(Calendar.HOUR_OF_DAY));
            fe.setAttribute("minuto", ""+cal.get(Calendar.MINUTE));
            
            root.setAttribute("id",""+idP);
            root.setAttribute("titulo",titulo);
            root.setAttribute("usuario",""+idU);
            
            for(String kk: tags)
                ht.addContent(new Element("tag").setAttribute("nombre", kk));
            
            categoria.setAttribute("id",""+idC);
            
            content.setContent(new CDATA(contenido));
            
            XMLOutputter fmt = new XMLOutputter(Format.getPrettyFormat());  

                try (FileWriter writer = new FileWriter(xml.getPath())) {
                    fmt.output(doc, writer);
                    writer.flush();
                }
            }catch (IOException e){System.out.println(e);}
    }
    public void NuevaEntradaPregunta(long idP, String titulo, long idU, long idC){
         
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(uri+"/xml/preguntas.xml");
        Document document;
        
            try
            {
                document = (Document) builder.build( xmlFile );
                Element root=document.getRootElement();

                Element nuevo =new Element("pregunta");
                nuevo.setAttribute("id",""+idP);
                nuevo.setAttribute("titulo", titulo);
                nuevo.setAttribute("usuario",""+idU);
                nuevo.setAttribute("categoria",""+idC);

                root.addContent(nuevo);

                XMLOutputter fmt = new XMLOutputter(Format.getPrettyFormat());  

                    try (FileWriter writer = new FileWriter(xmlFile.getPath())) {
                        fmt.output(document, writer);
                        writer.flush();
                    }
            } catch (JDOMException | IOException ex) {System.out.println(ex);}
        }
}
