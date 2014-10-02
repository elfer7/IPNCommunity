package com.multiaportes.comunipn.sistema.usuarios;

import com.multiaportes.comunipn.sistema.RutaComunIPN;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditarDatos extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session=request.getSession();
        String ruta=RutaComunIPN.getRutaAbs(session);
        
        //long idU=686664832;
        long idU=15455525;
        
        Usuario user=new Usuario(idU,ruta);
  
        String nombre=user.getNombre();
        String apellidos=user.getApellidos();
        
        String avatar=user.getAvatar();
        
        String facebook=user.getFacebook();
        String twitter=user.getTwitter();
        String webpage=user.getWebsite();
        
        String nombrev=user.getNombreVocacional();
        String carrerav=user.getCarreraVocacional();
        String iniciov=user.getInicioVocacional();
        String finv=user.getFinVocacional();
        
        String nombres=user.getIdSuperior();
        String carreras=user.getCarreraSuperior();
        String inicios=user.getInicioSuperior();
        String fins=user.getFinSuperior();
                
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>\n" +
"    <head>\n" +
"        <title></title>\n" +
"    </head>\n" +
"    <body>\n" +
"        <form action=\"Edit\" method=\"POST\">\n" +
"            Nombre\n" +
"            <input type=\"text\" name=\"nombre\" value=\""+nombre+"\"/><br>\n" +
"            Apellidos\n" +
"            <input type=\"text\" name=\"apellidos\" value=\""+apellidos+"\" /><br>\n" +
"            <br>\n" +
             "Avatar\n" +
"            <input type=\"text\" name=\"avatar\" value=\""+avatar+"\" /><br>\n" +
"            <br>\n" +
"            facebook\n" +
"            <input type=\"text\" name=\"f\" value=\""+facebook+"\" /><br>\n" +
"            Twitter\n" +
"            <input type=\"text\" name=\"t\" value=\""+twitter+"\" /><br>\n" +
"            Webpage\n" +
"            <input type=\"text\" name=\"w\" value=\""+webpage+"\" /><br>\n" +
"            <br>\n" +
"            Vocacional<br>\n" +
"            Nombre<input type=\"text\" name=\"nv\" value=\""+nombrev+"\" /><br>\n" +
"            Carrera<input type=\"text\" name=\"cv\" value=\""+carrerav+"\" /><br>\n" +
"            Inicio<input type=\"text\" name=\"iv\" value=\""+iniciov+"\" /><br>\n" +
"            Fin<input type=\"text\" name=\"fv\" value=\""+finv+"\" /><br>\n" +
"            <br>\n" +
"            Superior<br>\n" +
"            Nombre<input type=\"text\" name=\"ns\" value=\""+nombres+"\" /><br>\n" +
"            Carrera<input type=\"text\" name=\"cs\" value=\""+carreras+"\" /><br>\n" +
"            Inicio<input type=\"text\" name=\"is\" value=\""+inicios+"\" /><br>\n" +
"            Fin<input type=\"text\" name=\"fs\" value=\""+fins+"\" /><br>\n" +
"            \n" +
"            <input type=\"submit\" value=\"Enviar\" />\n" +
"        </form>\n" +
"    </body>\n" +
"</html>");

    }

}