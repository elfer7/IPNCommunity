package com.multiaportes.comunipn.ui.estatico;

import com.multiaportes.comunipn.ui.HTMLEscritor;
import com.multiaportes.comunipn.ui.dinamico.CajaInformacion;

public class Sidebar implements HTMLEscritor
{
    private String opc, ruta, categoria;
    
    public Sidebar(String opc)
    {
        this.opc = opc;
    }
    public Sidebar(String opc, String r, String cat)
    {
        this.opc = opc;
        this.ruta = r;
        this.categoria = cat;
    }
    
    public String writeHTML()
    {
        String cajaInfo = "", busCred = "";
        if(opc.equals("e"))
            cajaInfo = new CajaInformacion("escuelas", ruta, categoria).writeHTML();
        else if(opc.equals("p_u"))
            cajaInfo = new CajaInformacion("perfil_usuario", ruta, categoria).writeHTML();
        else if(opc.equals("p_e"))
            cajaInfo = new CajaInformacion("perfil_escuela", ruta, categoria).writeHTML();
        
        busCred = new BuscadorCreditos().writeHTML();
        
        return "<div class=\"col-md-4\">"+cajaInfo+busCred+"</div>";
    }
}
