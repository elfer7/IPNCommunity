package com.multiaportes.comunipn.ui.dinamico;

import com.multiaportes.comunipn.sistema.xml.LectorXML;
import com.multiaportes.comunipn.ui.HTMLEscritor;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

public class Mainbar implements HTMLEscritor
{
    private String opc, cat, ruta, nickname, avatar;
    private List<Element> res;
    
    public Mainbar(String opc)
    {
        this.opc = opc;
    }
    public Mainbar(String opc, String categoria, List<Element> resultados, String ruta)
    {
        this.opc = opc;
        this.cat = categoria;
        this.res = resultados;
        this.ruta = ruta;       
    }
    
    private String getTags(String idpp)
    {
        String html = "";
        try
        {
            Document documento = new SAXBuilder().build(new File(ruta+"/preguntas/"+idpp+"/pregunta.xml"));
            XPathFactory fabrica = XPathFactory.instance();
            XPathExpression<Element> etiquetas = fabrica.compile("/pregunta/tags/*", Filters.element()); // Crashea si no utiliza los binarios de Jaxen

            List<Element> temporalTags = etiquetas.evaluate(documento);
            
            for(Element ht: temporalTags)
                html += "<a href=\"tag?h="+ht.getAttributeValue("nombre")+"\" title=\""+ht.getAttributeValue("nombre")+"\">#"+ht.getAttributeValue("nombre")+"</a>";
        }
        catch(JDOMException | IOException x)
        {
            
        }
        
        return html;
    }
    
    public String writeHTML()
    {
        if(opc.equals("cat"))
        {
            String salida = "", catt = "pregunta-", titt = "";
            if(cat.equals("0"))
            {
                catt += "fm";
                titt = "Físico-Matemáticas";
            }
            else if(cat.equals("1"))
            {
                catt += "mb";
                titt = "Médico-Biológicas";
            }
            else if(cat.equals("2"))
            {
                catt += "sc";
                titt = "Sociales";
            }
            else if(cat.equals("3"))
            {
                catt += "ln";
                titt = "Idiomas";
            }
            else if(cat.equals("4"))
            {
                catt += "off";
                titt = "Offtopic";
            }
            Element tmp = null;
            for(int k = res.size()-1; k >= 0; k--)
            {
                tmp = res.get(k);
                
                try
                {
                    Document documento = new SAXBuilder().build(new File(ruta+"/xml/usuarios.xml"));
                    XPathFactory fabrica = XPathFactory.instance();
                    XPathExpression<Element> elementos = fabrica.compile("/usuarios/usuario[@id=\""+tmp.getAttributeValue("usuario")+"\"]", Filters.element()); // Crashea si no utiliza los binarios de Jaxen

                    this.nickname = elementos.evaluateFirst(documento).getAttributeValue("nickname");
                }
                catch(JDOMException | IOException x)
                {

                }
                try
                {
                    Document documento = new SAXBuilder().build(new File(ruta+"/usuarios/"+tmp.getAttributeValue("usuario")+"/"+tmp.getAttributeValue("usuario")+".xml"));
                    this.avatar = documento.getRootElement().getChild("informacion").getChild("avatar").getAttributeValue("url");
                }
                catch(JDOMException | IOException x)
                {

                }
                
                salida = salida + "<div class=\"col-md-12 pregunta "+catt+"\"><div class=\"row\"><div class=\"col-md-1\"><img class=\"avatar-preview\" src=\""+this.avatar+"\" /></div><div class=\"col-md-11\"><div class=\"col-md-12 usuario-preview\"><a href=\"perfil?u="+tmp.getAttributeValue("usuario")+"\" title=\""+this.nickname+"\">"+this.nickname+"</a></div><div class=\"col-md-12 titulo-preview\"><a href=\"pregunta?p="+tmp.getAttributeValue("id")+"\" title=\""+tmp.getAttributeValue("titulo")+"\">"+tmp.getAttributeValue("titulo")+"</a></div><div class=\"col-md-12 tags-preview\">"+this.getTags(tmp.getAttributeValue("id"))+"</div></div></div></div>";
            }
            return "<div class=\"col-md-7\" id=\"cnt\"><div class=\"row\" id=\"contenido\"><div class=\"col-md-12\"><h2>"+titt+"</h2></div>"+salida+"</div></div>";
        }
        else if(opc.equals("hasht"))
        {
            String salida = "", catt = "pregunta-off", titt = cat;
            Element tmp = null;
            for(int k = res.size()-1; k >= 0; k--)
            {
                tmp = res.get(k);
                if(tmp.getAttributeValue("categoria").equals("0"))
                    catt = "pregunta-fm";
                else if(tmp.getAttributeValue("categoria").equals("1"))
                    catt = "pregunta-mb";
                else if(tmp.getAttributeValue("categoria").equals("2"))
                    catt = "pregunta-sc";
                else if(tmp.getAttributeValue("categoria").equals("3"))
                    catt = "pregunta-ln";
                else if(tmp.getAttributeValue("categoria").equals("4"))
                    catt = "pregunta-off";
                
                try
                {
                    Document documento = new SAXBuilder().build(new File(ruta+"/xml/usuarios.xml"));
                    XPathFactory fabrica = XPathFactory.instance();
                    XPathExpression<Element> elementos = fabrica.compile("/usuarios/usuario[@id=\""+tmp.getAttributeValue("usuario")+"\"]", Filters.element()); // Crashea si no utiliza los binarios de Jaxen

                    this.nickname = elementos.evaluateFirst(documento).getAttributeValue("nickname");
                }
                catch(JDOMException | IOException x)
                {

                }
                try
                {
                    Document documento = new SAXBuilder().build(new File(ruta+"/usuarios/"+tmp.getAttributeValue("usuario")+"/"+tmp.getAttributeValue("usuario")+".xml"));
                    this.avatar = documento.getRootElement().getChild("informacion").getChild("avatar").getAttributeValue("url");
                }
                catch(JDOMException | IOException x)
                {

                }
                
                salida = salida + "<div class=\"col-md-12 pregunta "+catt+"\"><div class=\"row\"><div class=\"col-md-1\"><img class=\"avatar-preview\" src=\""+this.avatar+"\" /></div><div class=\"col-md-11\"><div class=\"col-md-12 usuario-preview\"><a href=\"perfil?u="+tmp.getAttributeValue("usuario")+"\" title=\""+this.nickname+"\">"+this.nickname+"</a></div><div class=\"col-md-12 titulo-preview\"><a href=\"pregunta?p="+tmp.getAttributeValue("id")+"\" title=\""+tmp.getAttributeValue("titulo")+"\">"+tmp.getAttributeValue("titulo")+"</a></div><div class=\"col-md-12 tags-preview\">"+this.getTags(tmp.getAttributeValue("id"))+"</div></div></div></div>";
            }
            return "<div class=\"col-md-7\" id=\"cnt\"><div class=\"row\" id=\"contenido\"><div class=\"col-md-12\"><h2>#"+titt+"</h2></div>"+salida+"</div></div>";
        }
        else if(opc.equals("perf"))
        {
            String salida = "", catt = "pregunta-off";
            Element tmp = null;
            for(int k = res.size()-1; k >= 0; k--)
            {
                tmp = res.get(k);
                
                if(tmp.getAttributeValue("categoria").equals("0"))
                    catt = "pregunta-fm";
                else if(tmp.getAttributeValue("categoria").equals("1"))
                    catt = "pregunta-mb";
                else if(tmp.getAttributeValue("categoria").equals("2"))
                    catt = "pregunta-sc";
                else if(tmp.getAttributeValue("categoria").equals("3"))
                    catt = "pregunta-ln";
                else if(tmp.getAttributeValue("categoria").equals("4"))
                    catt = "pregunta-off";
                
                try
                {
                    Document documento = new SAXBuilder().build(new File(ruta+"/xml/usuarios.xml"));
                    XPathFactory fabrica = XPathFactory.instance();
                    XPathExpression<Element> elementos = fabrica.compile("/usuarios/usuario[@id=\""+cat+"\"]", Filters.element()); // Crashea si no utiliza los binarios de Jaxen

                    this.nickname = elementos.evaluateFirst(documento).getAttributeValue("nickname");
                }
                catch(JDOMException | IOException x)
                {

                }
                try
                {
                    Document documento = new SAXBuilder().build(new File(ruta+"/usuarios/"+tmp.getAttributeValue("usuario")+"/"+tmp.getAttributeValue("usuario")+".xml"));
                    this.avatar = documento.getRootElement().getChild("informacion").getChild("avatar").getAttributeValue("url");
                }
                catch(JDOMException | IOException x)
                {

                }
                
                salida = salida + "<div class=\"col-md-12 pregunta "+catt+"\"><div class=\"row\"><div class=\"col-md-1\"><img class=\"avatar-preview\" src=\""+this.avatar+"\" /></div><div class=\"col-md-11\"><div class=\"col-md-12 usuario-preview\"><a href=\"perfil?u="+tmp.getAttributeValue("usuario")+"\" title=\""+this.nickname+"\">"+this.nickname+"</a></div><div class=\"col-md-12 titulo-preview\"><a href=\"pregunta?p="+tmp.getAttributeValue("id")+"\" title=\""+tmp.getAttributeValue("titulo")+"\">"+tmp.getAttributeValue("titulo")+"</a></div><div class=\"col-md-12 tags-preview\">"+this.getTags(tmp.getAttributeValue("id"))+"</div></div></div></div>";
            }
            return "<div class=\"col-md-7\" id=\"cnt\"><div class=\"row\" id=\"contenido\"><div class=\"col-md-12\"><h2>Preguntas</h2></div>"+salida+"</div></div>";
        }
        else if(opc.equals("esc"))
        {
            String salida = "";
            Element tmp = null;
            for(int k = res.size()-1; k >= 0; k--)
            {
                tmp = res.get(k);
                cat = tmp.getAttributeValue("id");
                try
                {
                    Document documento = new SAXBuilder().build(new File(ruta+"/xml/usuarios.xml"));
                    XPathFactory fabrica = XPathFactory.instance();
                    XPathExpression<Element> elementos = fabrica.compile("/usuarios/usuario[@id=\""+cat+"\"]", Filters.element()); // Crashea si no utiliza los binarios de Jaxen

                    this.nickname = elementos.evaluateFirst(documento).getAttributeValue("nickname");
                }
                catch(JDOMException | IOException x)
                {

                }
                try
                {
                    Document documento = new SAXBuilder().build(new File(ruta+"/usuarios/"+tmp.getAttributeValue("usuario")+"/"+tmp.getAttributeValue("usuario")+".xml"));
                    this.avatar = documento.getRootElement().getChild("informacion").getChild("avatar").getAttributeValue("url");
                }
                catch(JDOMException | IOException x)
                {

                }
                
                salida = salida + "<div class=\"col-md-2\"><a href=\"perfil?u="+cat+"\" title=\"@"+nickname+"\"><img class=\"usuario\" src=\""+avatar+"\" /></a></div>";
            }
            return "<div class=\"col-md-7\" id=\"cnt\"><div class=\"row\" id=\"contenido\"><div class=\"col-md-12\"><h2>Miembros</h2></div>"+salida+"</div></div>";
        }
        else if(opc.equals("edit"))
        {
            return "<div class=\"col-md-7\" id=\"cnt\">\n" +
"                    <div class=\"row\" id=\"contenido\">\n" +
"                        <div class=\"col-md-12\">\n" +
"                            <h2>Editar perfil</h2>\n" +
"                        </div>\n" +
"                        <div class=\"col-md-12 seccion\">\n" +
"                            <div class=\"row\">\n" +
"                                <div class=\"col-md-12\"><h3>Informaci&oacute;n del perfil</h3></div>\n" +
"                                <div class=\"col-md-6\">\n" +
"                                    <div class=\"input-group ediciones\"><span class=\"input-group-addon\"></span><input type=\"text\" class=\"form-control\" id=\"a\" placeholder=\"Nombre\" value=\"\"></div>\n" +
"                                </div>\n" +
"                                <div class=\"col-md-6\">\n" +
"                                    <div class=\"input-group ediciones\"><span class=\"input-group-addon\"></span><input type=\"text\" class=\"form-control\" id=\"b\" placeholder=\"Apellido\" value=\"\"></div>\n" +
"                                </div>\n" +
"                                <div class=\"col-md-6 col-md-offset-3\">\n" +
"                                    <div class=\"input-group ediciones\"><span class=\"input-group-addon\"></span><input type=\"text\" class=\"form-control\" id=\"c\" placeholder=\"URL Avatar\" value=\"\"></div>\n" +
"                                </div>\n" +
"                            </div>\n" +
"                        </div>\n" +
"                        <div class=\"col-md-12 seccion\">\n" +
"                            <div class=\"row\">\n" +
"                                <div class=\"col-md-12\"><h3>Redes sociales</h3></div>\n" +
"                                <div class=\"col-md-12\">\n" +
"                                    <div class=\"input-group ediciones\"><span class=\"input-group-addon\">facebook.com/</span><input type=\"text\" class=\"form-control\" id=\"d\" placeholder=\"URL Facebook\" value=\"\"></div>\n" +
"                                </div>\n" +
"                                <div class=\"col-md-12\">\n" +
"                                    <div class=\"input-group ediciones\"><span class=\"input-group-addon\">twitter.com/</span><input type=\"text\" class=\"form-control\" id=\"e\" placeholder=\"URL Twitter\" value=\"\"></div>\n" +
"                                </div>\n" +
"                                <div class=\"col-md-12\">\n" +
"                                    <div class=\"input-group ediciones\"><span class=\"input-group-addon\">http://</span><input type=\"text\" class=\"form-control\" id=\"f\" placeholder=\"Sitio web\" value=\"\"></div>\n" +
"                                </div>\n" +
"                            </div>\n" +
"                        </div>\n" +
"                        <div class=\"col-md-12 seccion\">\n" +
"                            <div class=\"row\">\n" +
"                                <!--<div class=\"col-md-12\"><h3>Escuelas</h3></div>\n" +
"                                <div class=\"col-md-12 ediciones\">\n" +
"                                    <label class=\"checkbox-inline\" for=\"vocacional\"><input id=\"vocacional\" type=\"checkbox\" value=\"voca\" disabled> Vocacional</label>\n" +
"                                    <label class=\"checkbox-inline\" for=\"superior\"><input id=\"superior\" type=\"checkbox\" value=\"sup\" disabled> Superior</label>\n" +
"                                </div>\n" +
"                                <div class=\"col-md-12 ediciones\">\n" +
"                                    <form role=\"form\">\n" +
"                                        <fieldset>\n" +
"                                            <div class=\"col-md-6\">\n" +
"                                                <select id=\"w\" class=\"form-control\">\n" +
"                                                    <option>Voca 3</option>\n" +
"                                                </select>\n" +
"                                            </div>\n" +
"                                            <div class=\"col-md-6\">\n" +
"                                                <select id=\"x\" class=\"form-control\">\n" +
"                                                    <option>T&eacute;cnico en Sistemas Digitales</option>\n" +
"                                                </select>\n" +
"                                            </div>\n" +
"                                        </fieldset>\n" +
"                                    </form>\n" +
"                                </div>\n" +
"                                <div class=\"col-md-12 ediciones\">\n" +
"                                    <form role=\"form\">\n" +
"                                        <fieldset disabled>\n" +
"                                            <div class=\"col-md-6\">\n" +
"                                                <select id=\"y\" class=\"form-control\">\n" +
"                                                    <option>ESCOM</option>\n" +
"                                                </select>\n" +
"                                            </div>\n" +
"                                            <div class=\"col-md-6\">\n" +
"                                                <select id=\"z\" class=\"form-control\">\n" +
"                                                    <option>Ing. en Sistemas Computacionales</option>\n" +
"                                                </select>\n" +
"                                            </div>\n" +
"                                        </fieldset>\n" +
"                                    </form>\n" +
"                                </div>-->\n" +
"                            </div>\n" +
"                        </div>\n" +
"                        <!--<div class=\"col-md-12 seccion\">\n" +
"                            <div class=\"row\">\n" +
"                                <div class=\"col-md-12\"><h3>Seguridad</h3></div>\n" +
"                                <div class=\"col-md-12 ediciones\">\n" +
"                                    <label class=\"checkbox-inline\" for=\"changePswd\"><input id=\"changePswd\" type=\"checkbox\" value=\"voca\"> Cambiar contrase&ntilde;a</label>\n" +
"                                </div>\n" +
"                                <div class=\"col-md-12 form-group has-error\">\n" +
"                                    <form role=\"form\">\n" +
"                                        <fieldset disabled>\n" +
"                                            <div class=\"col-md-4\"><input type=\"password\" class=\"form-control\" placeholder=\"Contraseña actual\"></div>\n" +
"                                            <div class=\"col-md-4\"><input type=\"password\" class=\"form-control\" placeholder=\"Contraseña nueva\"></div>\n" +
"                                            <div class=\"col-md-4\"><input type=\"password\" class=\"form-control\" placeholder=\"Confirmar contraseña\"></div>\n" +
"                                        </fieldset>\n" +
"                                    </form>\n" +
"                                </div>\n" +
"                            </div>\n" +
"                        </div>-->\n" +
"                        <div class=\"col-md-12 seccion\">\n" +
"                            <!--<button style=\"float: left; margin-top: 15px;\" type=\"button\" class=\"btn btn-default btn-lg\" onclick=\"desactivarPerfil("+this.cat+")\">Desactivar cuenta</button>-->\n" +
"                            <button style=\"float: right; margin-top: 15px;\" type=\"button\" class=\"btn btn-success btn-lg\" onclick=\"editarPerfil("+this.cat+", document.getElementById('a').value, document.getElementById('b').value, document.getElementById('c').value, document.getElementById('d').value, document.getElementById('e').value, document.getElementById('f').value)\">Guardar cambios</button>\n" +
"                        </div>\n" +
"                    </div>\n" +
"                </div>";
        }
        else if(opc.equals("preg"))
        {
            String respuestas = "", dia = "", mes = "", anio = "", hora = "", minuto = "", numCategoria = "", contenido = "", positivos = "", negativos = "", contenidoResp = "", titulo = new LectorXML(ruta+"/preguntas/"+cat+"/pregunta.xml").getValor("/pregunta", "titulo");
            Element tmp = null;
            int kkkkk = 1;
            String formularioR = "<div class=\"row respuesta\" id=\"newReply\">\n" +
"                            <div class=\"col-md-12\">\n" +
"                                <form>\n" +
"                                    <textarea id=\"nuevaRespuesta\" maxlength=\"300\" class=\"form-control buscador\" rows=\"3\" placeholder=\"Escribe tu respuesta\"></textarea>\n" +
"                                    <br/>\n" +
"                                    <button type=\"button\" id=\"responderBtn\" class=\"btn btn-primary\" style=\"float: right;\" onclick=\"makeComentario('"+cat+"')\">Publicar comentario</button>\n" +
"                                </form>\n" +
"                            </div>\n" +
"                        </div>";
            
            for(int k = res.size()-1; k >= 0; k--)
            {
                tmp = res.get(k);
                if(tmp.getName().equals("fecha"))
                {
                    dia = tmp.getAttributeValue("dia");
                    mes = tmp.getAttributeValue("mes");
                    anio = tmp.getAttributeValue("anio");
                    hora = tmp.getAttributeValue("hora");
                    minuto = tmp.getAttributeValue("minuto");
                }
                else if(tmp.getName().equals("categoria"))
                    numCategoria = tmp.getAttributeValue("id");
                else if(tmp.getName().equals("contenido"))
                    contenido = tmp.getText();
            }
                
            
            while(true)
            {
                if(!new File(ruta+"/preguntas/"+cat+"/"+kkkkk+".xml").exists())
                    break;
                try
                {
                    Document documento = new SAXBuilder().build(new File(ruta+"/preguntas/"+cat+"/"+kkkkk+".xml"));
                    XPathFactory fabrica = XPathFactory.instance();
                    XPathExpression<Element> elementos = fabrica.compile("/respuesta/*", Filters.element()); // Crashea si no utiliza los binarios de Jaxen
                    
                    List<Element> listaTemporal = elementos.evaluate(documento);
                    
                    for(int k = listaTemporal.size()-1; k >= 0; k--)
                    {
                        tmp = listaTemporal.get(k);
                        if(tmp.getName().equals("votos"))
                        {
                            positivos = tmp.getAttributeValue("positivos");
                            negativos = tmp.getAttributeValue("negativos");
                        }
                        else if(tmp.getName().equals("contenido"))
                            contenidoResp = tmp.getText();
                    }
                }
                catch(JDOMException | IOException x)
                {

                }
                respuestas += "<div class=\"row respuesta\">\n" +
"                            <div class=\"col-md-1\">\n" +
"                                <div class=\"row voto up\">\n" +
"                                    <a onclick=\"votarComment('"+cat+"', '"+kkkkk+"', '+')\" href=\"#\" title=\"+"+positivos+"\"><i class=\"fa fa-arrow-up\"></i></a>\n" +
"                                </div>\n" +
"                                <div class=\"row voto down\">\n" +
"                                    <a onclick=\"votarComment('"+cat+"', '"+kkkkk+"', '-')\" href=\"#\" title=\"-"+negativos+"\"><i class=\"fa fa-arrow-down\"></i></a>\n" +
"                                </div>\n" +
"                            </div>\n" +
"                            <div class=\"col-md-11\">\n" +
"                                <div class=\"col-md-12\" id=\"usuario-perfil-2\">Respuesta</div>\n" +
"                                <div class=\"col-md-12\">"+contenidoResp+"</div>\n" +
"                            </div>\n" +
"                        </div>";
                kkkkk++;
            }
            //if(new File(ruta+"/preguntas/"+cat+"/"+kkkkk+".xml").exists())
                //respuestas = ""+ruta+"/preguntas/"+cat+"/"+kkkkk+".xml";
            String peloconcha = "";
            if("0".equals(numCategoria))
                peloconcha = "Físico-Matemáticas";
            else if("1".equals(numCategoria))
                peloconcha = "Médico-Biológicas";
            else if("2".equals(numCategoria))
                peloconcha = "Sociales";
            else if("3".equals(numCategoria))
                peloconcha = "Idiomas";
            else if("4".equals(numCategoria))
                peloconcha = "Offtopic";
            return "<div class=\"col-md-8\" id=\"cnt\">\n" +
"                    <div class=\"row\" id=\"contenido\">\n" +
"                        <div class=\"col-md-12\">\n" +
"                            <h1>"+titulo+"</h1>\n" +
"                        </div>\n" +
"                        <div class=\"col-md-12 detalles-pregunta\">\n" +
"                            <a href=\"categoria?c="+numCategoria+"\" title=\""+peloconcha+"\">"+peloconcha+"</a> | <b>"+dia+"/"+mes+"/"+anio+"</b> a las <b>"+hora+":"+minuto+"</b>\n" +
"                        </div>\n" +
"                    </div>\n" +
"                    <div class=\"row\" id=\"informacion\">\n" +
"                        <div class=\"col-md-1\">\n" +
"                        </div>\n" +
"                        <div class=\"col-md-11\">\n" +
"                            <div class=\"col-md-12\" id=\"usuario-perfil-2\">Pregunta</div>\n" +
"                            <div class=\"col-md-12\">"+contenido+"</div>\n" +
"                        </div>\n" +
"                    </div>\n" +
"                    <div class=\"col-md-11 col-md-offset-1\">"+respuestas+formularioR+"</div></div>";
        }
        return "";
    }
}
