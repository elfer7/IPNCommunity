package com.multiaportes.comunipn.sistema.usuarios;

import com.multiaportes.comunipn.sistema.Util;
import com.multiaportes.comunipn.sistema.ficheros.Directorio;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class UserXML {
    private String uri;
    private final Util tool;
    
    public UserXML(String ruta){
        uri=ruta;
        tool=new Util(uri);
    }
    
    public void CrearUsuario(long id, String nombre, String apellidos, String email){
        String uri2=uri+"/usuarios/";
        Directorio.crearDirectorio(uri2, id);
        File xml=new File(uri2+id+"/"+id+".xml");
        Document doc;
        
        if(!xml.exists()){
            try {
                xml.createNewFile();
            } catch (IOException ex) {}
        }
        
        try{
            doc=tool.plantilla("Usuario");
            Element root=doc.getRootElement();
            Element informacion=root.getChild("informacion");
            Element persona=informacion.getChild("persona");
            Element avatar=informacion.getChild("avatar");

            root.setAttribute("id",""+id);
            persona.setAttribute("email", email);
            persona.setAttribute("nombre", nombre);
            persona.setAttribute("apellidos", apellidos);
            avatar.setAttribute("url", "http://www.multiaportes.com/");

            XMLOutputter fmt = new XMLOutputter(Format.getPrettyFormat());  

                try (FileWriter writer = new FileWriter(xml.getPath())) {
                    fmt.output(doc, writer);
                    writer.flush();
                }
            }catch (IOException e){System.out.println(e);}
    }

     public void NuevaEntradaUsuario(long id, String nick, String password){
         
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(uri+"/xml/usuarios.xml");
        Document document;
        if(tool.ExistenciaUsuario(nick)==false){
            try
            {
                document = (Document) builder.build( xmlFile );
                Element root=document.getRootElement();

                Element nuevo =new Element("usuario");
                nuevo.setAttribute("id",""+id);
                nuevo.setAttribute("nickname", nick);
                nuevo.setAttribute("password", password);
                nuevo.setAttribute("eliminado", "false");

                root.addContent(nuevo);

                XMLOutputter fmt = new XMLOutputter(Format.getPrettyFormat());  

                    try (FileWriter writer = new FileWriter(xmlFile.getPath())) {
                        fmt.output(document, writer);
                        writer.flush();
                    }
            } catch (JDOMException | IOException ex) {System.out.println(ex);}
        }
    }
}
