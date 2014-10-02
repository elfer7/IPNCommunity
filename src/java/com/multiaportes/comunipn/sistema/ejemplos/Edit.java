package com.multiaportes.comunipn.sistema.usuarios;

import com.multiaportes.comunipn.sistema.RutaComunIPN;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Edit extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session=request.getSession();
        String ruta=RutaComunIPN.getRutaAbs(session);
        
        //long idU=686664832;
        long idU=15455525;
        
        Usuario user=new Usuario(idU,ruta);
  
        String nombre=request.getParameter("nombre");
        if(!nombre.equals("")){ user.setNombre(nombre);}
        String apellidos=request.getParameter("apellidos");
        if(!apellidos.equals("")){user.setApellidos(apellidos);}
        
        String avatar=request.getParameter("avatar");
        if(!avatar.equals("")){user.setAvatar(avatar);}
        
        String facebook=request.getParameter("f");
        if(!facebook.equals("")){user.setFacebook(facebook);}
        String twitter=request.getParameter("t");
        if(!twitter.equals("")){user.setTwitter(twitter);}
        String website=request.getParameter("w");
        if(!website.equals("")){user.setWebsite(website);}
        
        String nombrev=request.getParameter("nv");
        if(!nombrev.equals("")){user.setNombreVocaional(nombrev);}
        String carrerav=request.getParameter("cv");
        if(!carrerav.equals("")){user.setCarreraVocaional(carrerav);}
        String iniciov=request.getParameter("iv");
        if(!iniciov.equals("")){user.setInicioVocacional(iniciov);}
        String finv=request.getParameter("fv");
        if(!finv.equals("")){user.setFinVocaional(finv);}
        
        String nombres=request.getParameter("ns");
        if(!nombres.equals("")){user.setNombreSuperior(nombres);}
        String carreras=request.getParameter("cs");
        if(!carreras.equals("")){user.setCarreraSuperior(carreras);}
        String inicios=request.getParameter("is");
        if(!inicios.equals("")){user.setInicioSuperior(inicios);}
        String fins=request.getParameter("fs");
        if(!fins.equals("")){user.setFinSuperior(fins);}
    }
}
