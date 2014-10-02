package com.multiaportes.comunipn.ui.dinamico;

import com.multiaportes.comunipn.ui.HTMLEscritor;

public class CoverEscuela implements HTMLEscritor
{
    public CoverEscuela()
    {
        
    }
    
    public String writeHTML()
    {
        return "<div class=\"col-md-11\" id=\"cover-escuela\" style=\"background-image: url(recursos/img/escom.jpg);\">\n" +
"                    <div class=\"col-md-11\" id=\"id-escuela\">\n" +
"                        ESCOM\n" +
"                    </div>\n" +
"                    <div class=\"col-md-11\" id=\"nombre-escuela\">\n" +
"                        Escuela Superior de C&oacute;mputo\n" +
"                    </div>\n" +
"                </div>";
    }
}
