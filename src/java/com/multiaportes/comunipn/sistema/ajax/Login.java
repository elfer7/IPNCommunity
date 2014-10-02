package com.multiaportes.comunipn.sistema.ajax;

import com.multiaportes.comunipn.sistema.RutaComunIPN;
import com.multiaportes.comunipn.sistema.xml.LectorXML;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet
{
    protected void doPost(HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException, IOException
    {
        int k = 1;
        boolean usuarioExistente = false;
        PrintWriter p = respuesta.getWriter();
        String usr = peticion.getParameter("usr").toLowerCase(), hash = peticion.getParameter("hashPs"), resp = "", tmp;
        LectorXML lector = new LectorXML(RutaComunIPN.getRutaAbs(peticion.getSession())+"/xml/usuarios.xml");
        HttpSession ss = peticion.getSession();
        
        // Buscar usuario
        while(true)
        {
            tmp = lector.getValor("/usuarios/usuario["+k+"]", "nickname").toLowerCase();
            if(tmp.equals(usr))
            {
                usuarioExistente = true;
                break;
            }
            if(tmp.length() > "comunipn error:".length()-1)
                if(tmp.substring(0, "comunipn error:".length()).equals("comunipn error:"))
                    break;
            k++;
        }
        if(usuarioExistente)
        {
            if(lector.getValor("/usuarios/usuario["+k+"]", "password").equals(hash))
            {
                Cookie galletaLogin = new Cookie("idUsr", lector.getValor("/usuarios/usuario["+k+"]", "id"));
                galletaLogin.setMaxAge(50*60); // 50 minutos
                respuesta.addCookie(galletaLogin);
                ss.setAttribute("nickUsr", lector.getValor("/usuarios/usuario["+k+"]", "nickname"));
                ss.setAttribute("posicion", k);
                resp = "OK";
            }
            else
                resp = "Error";
        }
        else
            resp = "Error";
        
        p.print(resp);
    }
}
