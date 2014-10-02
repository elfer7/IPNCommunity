package com.multiaportes.comunipn.ui;

import com.multiaportes.comunipn.sistema.RutaComunIPN;
import com.multiaportes.comunipn.ui.dinamico.CoverEscuela;
import com.multiaportes.comunipn.ui.dinamico.Mainbar;
import com.multiaportes.comunipn.ui.estatico.Cabecera;
import com.multiaportes.comunipn.ui.estatico.Footer;
import com.multiaportes.comunipn.ui.estatico.Sidebar;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

public class Pregunta extends HttpServlet
{
    protected void doGet(HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException, IOException
    {
        Cookie galletas[] = peticion.getCookies();
        PrintWriter p = respuesta.getWriter();
        HttpSession session=peticion.getSession();
        String ruta=RutaComunIPN.getRutaAbs(session);
        String usuarioID = "";
        boolean sesionActiva = false;
        
        if(galletas != null)
            for(Cookie tmp : galletas)
                if(tmp.getName().equals("idUsr"))
                {
                    usuarioID = tmp.getValue();
                    sesionActiva = true;
                    break;
                }
        
        if(sesionActiva)
        {
            try
            {
                Document documento = new SAXBuilder().build(new File(ruta+"/preguntas/"+peticion.getParameter("p")+"/pregunta.xml"));
                boolean crearNodoTag = false;
                
                XPathFactory fabrica = XPathFactory.instance();
                XPathExpression<Element> elementos = fabrica.compile("/pregunta/*", Filters.element()); // Crashea si no utiliza los binarios de Jaxen
                List<Element> resultados = elementos.evaluate(documento);
                
                respuesta.setContentType("text/html;charset=UTF-8");
                p.println(new Cabecera(usuarioID).writeHTML());
                p.println("<div class=\"container\" id=\"contenidoxDD\"><div class=\"row\">");
                p.println(new Sidebar("xxxxxxxxxx").writeHTML());
                if(peticion.getParameter("p") != null)
                    p.println(new Mainbar("preg", peticion.getParameter("p"), resultados, ruta).writeHTML());
                p.println("</div></div>");
                p.println(new Footer().writeHTML());
            }
            catch(JDOMException | IOException ex)
            {
                p.println("Error JDOM"+ex.getMessage());
            }
        }
        else
        {
            p.print("No hay sesion activa"); // Redireccionar a index.html
        }
    }
}
