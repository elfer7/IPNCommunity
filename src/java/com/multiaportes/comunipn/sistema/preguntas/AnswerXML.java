package com.multiaportes.comunipn.sistema.preguntas;

import com.multiaportes.comunipn.sistema.Util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class AnswerXML {
    private final String uri;
    private final Util tool;
    
    public AnswerXML(String ruta){
        uri=ruta;
        tool=new Util(uri);
    }
    public void CrearRespuesta(long idR, long idU,long idP,long idUR, String contenido){       
        File xml=new File(uri+"usuarios\\"+idU+"\\"+idP+"\\"+idR+".xml");
        Document doc;
        
        if(!xml.exists()){
            try {
                xml.createNewFile();
            } catch (IOException ex) {}
        }
        
        try{
            doc=tool.plantilla("Respuesta");
            Element root=doc.getRootElement();
            
            Element content=root.getChild("contenido");
            
            root.setAttribute("id",""+idR);
            root.setAttribute("usuario",""+idUR);
            
            content.setText(contenido);
            
            XMLOutputter fmt = new XMLOutputter(Format.getPrettyFormat());  

                try (FileWriter writer = new FileWriter(xml.getPath())) {
                    fmt.output(doc, writer);
                    writer.flush();
                }
            }catch (IOException e){System.out.println(e);}
    }
}
