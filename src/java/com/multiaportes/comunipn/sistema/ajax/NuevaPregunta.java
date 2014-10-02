package com.multiaportes.comunipn.sistema.ajax;

import com.multiaportes.comunipn.sistema.RutaComunIPN;
import com.multiaportes.comunipn.sistema.preguntas.QuestionXML;
import com.multiaportes.comunipn.sistema.xml.LectorXML;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

public class NuevaPregunta extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session=request.getSession();
        String ruta=RutaComunIPN.getRutaAbs(session);
        LectorXML lector = new LectorXML(ruta+"/xml/preguntas.xml");
        long id = Long.parseLong(lector.getValor("/preguntas/pregunta[last()]", "id")) + 1;
        
        QuestionXML pregunta = new QuestionXML(ruta);
        
        String titulo = request.getParameter("tt");
        String detalles = request.getParameter("dts");
        String categoria = request.getParameter("ctg");
        long usuarioID = 0;
        
        Cookie galletas[] = request.getCookies();
        if(galletas != null)
            for(Cookie tmp : galletas)
                if(tmp.getName().equals("idUsr"))
                {
                    usuarioID = Long.parseLong(tmp.getValue());
                    break;
                }
        
        List<String> hashtags = new ArrayList();
        Matcher m = Pattern.compile("#[\\w]+").matcher(detalles);
        while(m.find())
            hashtags.add(m.group().substring(1));
        
        try
        {
            Document documento = new SAXBuilder().build(new File(ruta+"/xml/tags.xml"));
            boolean crearNodoTag = false;
            for(String kk: hashtags)
            {
                XPathFactory fabrica = XPathFactory.instance();
                XPathExpression<Element> elementos = fabrica.compile("/tags/tag[@nombre=\""+kk.toLowerCase()+"\"]", Filters.element()); // Crashea si no utiliza los binarios de Jaxen
                
                Element etiquetag = (Element) elementos.evaluateFirst(documento);
                
                if(etiquetag == null)
                {
                    etiquetag = new Element("tag");
                    etiquetag.setAttribute("nombre", kk.toLowerCase());
                    crearNodoTag = true;
                }
                
                Element tmp = new Element("pregunta");
                tmp.setAttribute("id", ""+id);
                tmp.setAttribute("titulo", titulo);
                tmp.setAttribute("usuario", ""+usuarioID);
                tmp.setAttribute("categoria", categoria);
                
                etiquetag.addContent(tmp);
                if(crearNodoTag)
                    documento.getRootElement().addContent(etiquetag);
                
                XMLOutputter formateador = new XMLOutputter(Format.getPrettyFormat());
                FileWriter salidaTags = new FileWriter(ruta+"/xml/tags.xml");
                formateador.output(documento, salidaTags);
                salidaTags.flush();
            }
        }
        catch(JDOMException | IOException ex)
        {
            System.err.println("Error JDOM");
        }
        
        pregunta.NuevaEntradaPregunta(id, titulo, usuarioID, Long.parseLong(categoria));// Crea entrada en preguntas.xml
        pregunta.CrearPregunta(id, usuarioID, Long.parseLong(categoria), detalles, titulo, hashtags); // Crea la carpeta y fichero nuevo
    }
}