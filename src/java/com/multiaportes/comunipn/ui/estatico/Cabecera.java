package com.multiaportes.comunipn.ui.estatico;

import com.multiaportes.comunipn.ui.HTMLEscritor;

public class Cabecera implements HTMLEscritor
{
    private String usuario;
    
    public Cabecera(String usuario)
    {
        this.usuario = usuario;
    }
    public String writeHTML()
    {
        return "<!DOCTYPE html>\n" +
"<!--\n" +
"    Diseño del proyecto ComunIPN\n" +
"    Por Eder Ortega - multiaportes.com\n" +
"-->\n" +
"<html>\n" +
"    <head>\n" +
"        <meta charset=\"utf-8\">\n" +
"        <meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\">\n" +
"        <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
"        <title>ComunIPN</title>\n" +
"        <link rel=\"stylesheet\" type=\"text/css\" href=\"recursos/css/bootstrap.css\">\n" +
"        <link rel=\"stylesheet\" type=\"text/css\" href=\"recursos/css/font-awesome.css\">\n" +
"        <link rel=\"stylesheet\" type=\"text/css\" href=\"recursos/css/estilosIPNMA.css\">\n" +
"        <link rel=\"stylesheet\" type=\"text/css\" href=\"recursos/css/bootstrap-dialog.css\">\n" +
"    </head>\n" +
"    <body>\n" +
"        <div class=\"navbar navbar-fixed-top\">\n" +
"            <div class=\"container\">\n" +
"                <div class=\"navbar-header logoCIPN-texto\">\n" +
"                    <a class=\"navbar-brand\">ComunIPN</a>\n" +
"                </div>\n" +
"                <div class=\"navbar-collapse collapse\">\n" +
"                    <ul class=\"nav navbar-nav\">\n" +
"                        <li>\n" +
"                            <a id=\"fm\" href=\"categoria?c=0\">\n" +
"                                F&iacute;sico-Matem&aacute;ticas\n" +
"                            </a>\n" +
"                        </li>\n" +
"                        <li>\n" +
"                            <a id=\"mb\" href=\"categoria?c=1\">\n" +
"                                M&eacute;dico-Biol&oacute;gicas\n" +
"                            </a>\n" +
"                        </li>\n" +
"                        <li>\n" +
"                            <a id=\"sc\" href=\"categoria?c=2\">\n" +
"                                Sociales\n" +
"                            </a>\n" +
"                        </li>\n" +
"                        <li>\n" +
"                            <a id=\"ln\" href=\"categoria?c=3\">\n" +
"                                Idiomas\n" +
"                            </a>\n" +
"                        </li>\n" +
"                        <li>\n" +
"                            <a id=\"off\" href=\"categoria?c=4\">\n" +
"                                Offtopic\n" +
"                            </a>\n" +
"                        </li>\n" +
"                        </ul>\n" +
"                    <ul class=\"nav navbar-nav navbar-right\">\n" +
"                        <li class=\"dropdown\">\n" +
"                            <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Acciones <b class=\"caret\"></b></a>\n" +
"                            <ul class=\"dropdown-menu\">\n" +
"                                <li><a href=\"#\" onclick=\"publicarPregunta()\">Publicar pregunta</a></li>\n" +
"                                <li class=\"divider\"></li>\n" +
"                                <li class=\"dropdown-header\">Cuenta</li>\n" +
"                                <li><a href=\"perfil?u="+this.usuario+"\">Mi perfil</a></li>\n" +
"                                <li><a href=\"editar\">Editar informaci&oacute;n</a></li>\n" +
"                                <li><a href=\"salir\">Cerrar sesi&oacute;n</a></li>\n" +
"                            </ul>\n" +
"                        </li>\n" +
"                    </ul>\n" +
"                </div>\n" +
"            </div>\n" +
"        </div>";
    }
}
