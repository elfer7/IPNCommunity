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

public class SalvarCambios extends HttpServlet
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
            Document doc = new SAXBuilder().build(new File(ruta+"/usuarios/"+request.getParameter("id")+"/"+request.getParameter("id")+".xml"));
            Element raiz = doc.getRootElement().getChild("informacion");
            Element persona = raiz.getChild("persona");
            Element avatar = raiz.getChild("avatar");
            Element links = raiz.getChild("links");
            
            persona.setAttribute("nombre", request.getParameter("a"));
            persona.setAttribute("apellidos", request.getParameter("b"));
            avatar.setAttribute("url", request.getParameter("c"));
            links.getChild("facebook").setAttribute("url", request.getParameter("d"));
            links.getChild("twitter").setAttribute("url", request.getParameter("e"));
            links.getChild("website").setAttribute("url", request.getParameter("f"));
            
            XMLOutputter fmt = new XMLOutputter(Format.getPrettyFormat());
            fmt.output(doc, new FileWriter(ruta+"/usuarios/"+request.getParameter("id")+"/"+request.getParameter("id")+".xml"));
        }
        catch(JDOMException | IOException ex)
        {
            response.getWriter().println("Error JDOM: "+ex.getMessage());
        }
    }
}