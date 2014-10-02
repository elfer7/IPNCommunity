package com.multiaportes.comunipn.ui;

import com.multiaportes.comunipn.ui.dinamico.CoverEscuela;
import com.multiaportes.comunipn.ui.dinamico.Mainbar;
import com.multiaportes.comunipn.ui.estatico.Cabecera;
import com.multiaportes.comunipn.ui.estatico.Footer;
import com.multiaportes.comunipn.ui.estatico.Sidebar;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Editor extends HttpServlet
{
    protected void doGet(HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException, IOException
    {
        Cookie galletas[] = peticion.getCookies();
        PrintWriter p = respuesta.getWriter();
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
            respuesta.setContentType("text/html; charset=utf-8");
            p.println(new Cabecera((String) peticion.getSession().getAttribute("nickUsr")).writeHTML());
            p.println("<div class=\"container\" id=\"contenidoxDD\">\n" +
"            <div class=\"row\">");
            p.println(new Sidebar("p_u").writeHTML());
            p.println(new Mainbar("edit", usuarioID, null, null).writeHTML());
            p.println("</div></div>");
            p.println(new Footer().writeHTML());
        }
        else
        {
            p.print("No hay sesion activa"); // Redireccionar a index.html
        }
    }
}
