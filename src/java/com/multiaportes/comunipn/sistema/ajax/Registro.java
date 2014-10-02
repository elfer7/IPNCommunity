package com.multiaportes.comunipn.sistema.ajax;

import com.multiaportes.comunipn.sistema.RutaComunIPN;
import com.multiaportes.comunipn.sistema.Util;
import com.multiaportes.comunipn.sistema.usuarios.UserXML;
import com.multiaportes.comunipn.sistema.xml.LectorXML;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Registro extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session=request.getSession();
        String ruta=RutaComunIPN.getRutaAbs(session);
        LectorXML lector = new LectorXML(ruta+"/xml/usuarios.xml");
        long id = Long.parseLong(lector.getValor("/usuarios/usuario[last()]", "id")) + 1;
        
        UserXML user=new UserXML(ruta);
        Util tool=new Util(ruta);
        
        String nick=request.getParameter("usr");
        String nombre=request.getParameter("nm");
        String apellidos=request.getParameter("ap");
        String password=request.getParameter("hashPs");
        String email=request.getParameter("ml").toLowerCase();
        String respuesta;
        
        if(tool.ExistenciaUsuario(nick)==false&&tool.IDmatch(id,"usuarios")==false)
        {
            user.NuevaEntradaUsuario(id, nick, password); // Crea entrada en usuarios.xml
            user.CrearUsuario(id, nombre, apellidos, email); // Crea la carpeta y fichero nuevo
            respuesta="OK";
        }
        else
            respuesta="Error";
        
        PrintWriter out = response.getWriter();
        out.print(respuesta);
    }

}