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
import org.jdom2.CDATA;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

public class VotarComentario extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session=request.getSession();
        String ruta=RutaComunIPN.getRutaAbs(session);
        long usuarioID = 0;
        int kk = 1;
        
        Cookie galletas[] = request.getCookies();
        if(galletas != null)
            for(Cookie tmp : galletas)
                if(tmp.getName().equals("idUsr"))
                {
                    usuarioID = Long.parseLong(tmp.getValue());
                    break;
                }
        try
        {
                       
            Document doc = new SAXBuilder().build(new File(ruta+"/preguntas/"+request.getParameter("id")+"/"+request.getParameter("kk")+".xml"));
            Element raiz = doc.getRootElement();
            Element votos = raiz.getChild("votos");
            
            int laposta = 0;
            
            if(request.getParameter("tipo").equals("+"))
            {
                laposta = Integer.parseInt(votos.getAttributeValue("positivos"));
                votos.setAttribute("positivos", Integer.toString(laposta+1));
            }
            else if(request.getParameter("tipo").equals("-"))
            {
                laposta = Integer.parseInt(votos.getAttributeValue("negativos"));
                votos.setAttribute("negativos", Integer.toString(laposta+1));
            }
            
            XMLOutputter fmt = new XMLOutputter(Format.getPrettyFormat());
            fmt.output(doc, new FileWriter(ruta+"/preguntas/"+request.getParameter("id")+"/"+kk+".xml"));
        }
        catch(JDOMException | IOException ex)
        {
            response.getWriter().println("Error JDOM: "+ex.getMessage());
        }
    }
}