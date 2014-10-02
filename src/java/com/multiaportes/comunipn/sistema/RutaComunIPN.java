package com.multiaportes.comunipn.sistema;

import javax.servlet.http.HttpSession;

public class RutaComunIPN
{
    private static RutaComunIPN rcipn = null;
    private static String rutaAbsoluta;
    
    private RutaComunIPN(HttpSession s)
    {
        rutaAbsoluta = s.getServletContext().getRealPath("/");
    }
    
    public static String getRutaAbs(HttpSession s)
    {
        if(rcipn == null)
            rcipn = new RutaComunIPN(s);
        return rutaAbsoluta;
    }
}
