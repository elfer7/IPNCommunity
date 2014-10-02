package com.multiaportes.comunipn.ui.estatico;

import com.multiaportes.comunipn.ui.HTMLEscritor;

public class Footer implements HTMLEscritor
{
    public Footer()
    {
        
    }
    
    public String writeHTML()
    {
        return "<div class=\"fin-de-pagina\"></div>\n" +
"        <script type=\"text/javascript\" src=\"recursos/js/jquery-2.1.1.js\"></script>\n" +
"        <script type=\"text/javascript\" src=\"recursos/js/bootstrap.js\"></script>\n" +
"        <script type=\"text/javascript\" src=\"recursos/js/comunipn-bootstrap.js\"></script>\n" +
"        <script type=\"text/javascript\" src=\"recursos/js/comunipn-validar.js\"></script>\n" +
"        <script type=\"text/javascript\" src=\"recursos/js/comunipn-(in)seguridad.js\"></script>\n" +
"        <script type=\"text/javascript\" src=\"recursos/js/comunipn-ajax.js\"></script>\n" +
"        <script type=\"text/javascript\" src=\"recursos/js/multiaportes-sha.js\"></script>\n" +
"        <script type=\"text/javascript\" src=\"recursos/js/multiaportes-sha1.js\"></script>\n" +
"        <script type=\"text/javascript\" src=\"recursos/js/bootstrap-dialog.js\"></script>\n" +
"    </body>\n" +
"</html>";
    }
}
