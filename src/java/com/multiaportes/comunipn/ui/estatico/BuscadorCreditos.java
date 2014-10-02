package com.multiaportes.comunipn.ui.estatico;

import com.multiaportes.comunipn.ui.HTMLEscritor;

public class BuscadorCreditos implements HTMLEscritor
{   
    public BuscadorCreditos()
    {
        
    }
    public String writeHTML()
    {
        return "<br/>\n" +
"                    <div class=\"row\">\n" +
"                        <div class=\"col-md-12 buscar\">\n" +
"                            <form>\n" +
"                                <input type=\"search\" placeholder=\"\" class=\"form-control buscador\" disabled>\n" +
"                            </form>\n" +
"                        </div>\n" +
"                    </div>\n" +
"                    <br/>\n" +
"                    <div class=\"row sidebar creditos\">\n" +
"                        <a href=\"/\" title=\"Inicio\">Inicio</a>\n" +
"                        <a href=\"creditos.html\" title=\"Cr&eacute;ditos\">Cr&eacute;ditos</a>\n" +
"                        <a href=\"tutorial.pdf\" title=\"Tutorial\">Tutorial</a>\n" +
"                        \n" +
"                    </div>";
    }
}
