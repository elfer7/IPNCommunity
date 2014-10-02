package com.multiaportes.comunipn.sistema;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

public class Logout extends HttpServlet
{
    protected void doGet(HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException, IOException
    {
        Cookie galletas[] = peticion.getCookies();
        PrintWriter p = respuesta.getWriter();
        
        if(galletas != null)
            for(Cookie tmp : galletas)
                if(tmp.getName().equals("idUsr"))
                {
                    tmp.setMaxAge(0);
                    respuesta.addCookie(tmp);
                    peticion.getSession().invalidate();
                    p.print("Sesion destruida exitosamente"); // Redireccionar a index.html
                    break;
                }
    }
}
