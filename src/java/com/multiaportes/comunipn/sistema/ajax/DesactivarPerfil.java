package com.multiaportes.comunipn.sistema.ajax;

import com.multiaportes.comunipn.sistema.RutaComunIPN;
import com.multiaportes.comunipn.sistema.preguntas.QuestionXML;
import com.multiaportes.comunipn.sistema.xml.EscritorXML;
import com.multiaportes.comunipn.sistema.xml.LectorXML;
import com.multiaportes.comunipn.ui.dinamico.Mainbar;
import com.multiaportes.comunipn.ui.estatico.Cabecera;
import com.multiaportes.comunipn.ui.estatico.Footer;
import com.multiaportes.comunipn.ui.estatico.Sidebar;
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

public class DesactivarPerfil extends HttpServlet
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
                Document documento = new SAXBuilder().build(new File(ruta+"/xml/usuarios.xml"));
                boolean crearNodoTag = false;
                
                XPathFactory fabrica = XPathFactory.instance();
                XPathExpression<Element> elementos = fabrica.compile("/usuarios/usuario[@id='"+request.getParameter("id")+"']", Filters.element()); // Crashea si no utiliza los binarios de Jaxen
                Element resultado = elementos.evaluateFirst(documento);
                
                documento.getRootElement().setContent(resultado);
                resultado.setAttribute("eliminado", "true");
                
                XMLOutputter fmt = new XMLOutputter(Format.getPrettyFormat());
                fmt.output(documento, new FileWriter(ruta+"/xml/usuarios.xml"));
            }
            catch(JDOMException | IOException ex)
            {
                System.err.println("Error JDOM");
            }
    }
}