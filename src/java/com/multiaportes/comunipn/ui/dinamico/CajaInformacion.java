package com.multiaportes.comunipn.ui.dinamico;

import com.multiaportes.comunipn.ui.HTMLEscritor;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

public class CajaInformacion implements HTMLEscritor
{
    private String opcion, ruta, categoria, nickname;
    
    public CajaInformacion(String opcion)
    {
        this.opcion = opcion;
    }
    public CajaInformacion(String opcion, String r, String cat)
    {
        this.opcion = opcion;
        this.ruta = r;
        this.categoria = cat;
    }
    
    public String writeHTML()
    {
        if(opcion.equals("escuelas"))
        {
            String html = "";
            if(categoria.equals("0") || categoria.equals("1") || categoria.equals("2"))
            {
                try
                {
                    Document documento = new SAXBuilder().build(new File(ruta+"/xml/escuelas.xml"));
                    XPathFactory fabrica = XPathFactory.instance();
                    XPathExpression<Element> etiquetas = fabrica.compile("/escuelas/escuela[@categoria=\""+categoria+"\"]", Filters.element()); // Crashea si no utiliza los binarios de Jaxen

                    List<Element> temporalTags = etiquetas.evaluate(documento);

                    for(Element ht: temporalTags)
                        html += "<div class=\"col-md-3\"><a href=\"escuela?e="+ht.getAttributeValue("id")+"\" title=\""+ht.getAttributeValue("id").toUpperCase()+"\"><img class=\"escuela\" src=\"escuelas/"+ht.getAttributeValue("id")+"/avatar.jpg\" /></a></div>";
                }
                catch(JDOMException | IOException x)
                {

                }
                return "<div class=\"row sidebar\"><div class=\"col-md-12\"><h2>Escuelas</h2></div>"+html+"</div>";
            }
            else
                return "";
        }
        else if(opcion.equals("perfil_usuario")) // Pregunta, perfil, favoritos
        {
            String html = "", imagen = "";
            if(categoria != null)
            {
                try
                {
                    Document documento = new SAXBuilder().build(new File(ruta+"/xml/usuarios.xml"));
                    XPathFactory fabrica = XPathFactory.instance();
                    XPathExpression<Element> elementos = fabrica.compile("/usuarios/usuario[@id=\""+categoria+"\"]", Filters.element()); // Crashea si no utiliza los binarios de Jaxen

                    this.nickname = elementos.evaluateFirst(documento).getAttributeValue("nickname");
                }
                catch(JDOMException | IOException x)
                {

                }
                try
                {
                    Document documento = new SAXBuilder().build(new File(ruta+"/usuarios/"+categoria+"/"+categoria+".xml"));
                    XPathFactory fabrica = XPathFactory.instance();
                    XPathExpression<Element> etiquetas = fabrica.compile("/usuario/informacion", Filters.element()); // Crashea si no utiliza los binarios de Jaxen

                    Element informacion = etiquetas.evaluateFirst(documento);
                    imagen = "<img id=\"avatar-perfil\" src=\""+informacion.getChild("avatar").getAttributeValue("url")+"\" />";

                    html = "<div class=\"col-md-8\"><div class=\"col-md-12\" id=\"nombre-perfil\">"+informacion.getChild("persona").getAttributeValue("nombre")+" "+informacion.getChild("persona").getAttributeValue("apellidos")+"</div><div class=\"col-md-12\" id=\"usuario-perfil\">@"+nickname+"</div><div style=\"margin-top: 15px;\"class=\"col-md-12\"><a href=\"escuela?e=escom\" title=\"Ing. en Sistemas Computacionales\" class=\"escuela-perfil\">ESCOM</a> <a href=\"escuela?e=voca3\" title=\"T&eacute;cnico en Computaci&oacute;n\" class=\"escuela-perfil\">CECyT 3</a></div></div><div class=\"col-md-12\" style=\"text-align: center; margin-top: 5px;\"><div class=\"col-md-3 redes-perfil\"><a href=\"http://www.facebook.com/"+informacion.getChild("links").getChild("facebook").getAttributeValue("url")+"\" title=\"Facebook\"><i class=\"fa fa-facebook-square\" id=\"fb-perfil\"></i></a></div><div class=\"col-md-3 redes-perfil\"><a href=\"http://www.twitter.com/"+informacion.getChild("links").getChild("twitter").getAttributeValue("url")+"\" title=\"Twitter\"><i class=\"fa fa-twitter\" id=\"tw-perfil\"></i></a></div><div class=\"col-md-3 redes-perfil\"><a href=\"http://"+((informacion.getChild("links").getChild("website").getAttributeValue("url")==null)?"www.multiaportes.com/":informacion.getChild("links").getChild("website").getAttributeValue("url"))+"\" title=\"Sitio web\"><i class=\"fa fa-eye\" id=\"ws-perfil\"></i></a></div><div class=\"col-md-3 redes-perfil\"><a title=\"MultiAportes\"><i class=\"fa fa-heart\" id=\"fv-perfil\"></i></a></div></div>";
                }
                catch(JDOMException | IOException x)
                {

                }
                return "<div class=\"row sidebar\"><div class=\"col-md-4\">"+imagen+"</div>"+html+"</div>";
            }
            else
                return "";
        }
        else if(opcion.equals("perfil_escuela")) // Escuela
        {
            String html = "", html2 = "";
            if(categoria != null)
            {
                try
                {
                    Document documento = new SAXBuilder().build(new File(ruta+"/escuelas/"+categoria+"/"+categoria+".xml"));
                    XPathFactory fabrica = XPathFactory.instance();
                    XPathExpression<Element> elementos = fabrica.compile("/escuela/informacion/ubicacion", Filters.element()); // Crashea si no utiliza los binarios de Jaxen

                    html = "<div class=\"col-md-8\"><div class=\"col-md-12\" id=\"shortname-escuela\">"+categoria.toUpperCase()+"</div><div class=\"col-md-12\" id=\"ubicacion-escuela\">"+elementos.evaluateFirst(documento).getText()+"</div></div>";
                    
                    elementos = fabrica.compile("/escuela/carreras/*", Filters.element()); // Crashea si no utiliza los binarios de Jaxen
                    List<Element> kkk = elementos.evaluate(documento);
                    for(Element e: kkk)
                        html2 += "<div class=\"col-md-12 carrera\"><i class=\"fa fa-check-square-o icono-carrera\"></i>"+e.getText()+"</div>";
                }
                catch(JDOMException | IOException x)
                {

                }
                return "<div class=\"row sidebar\"><div class=\"col-md-4\"><div class=\"col-md-4\"><img id=\"avatar-perfil\" src=\"escuelas/"+categoria+"/avatar.jpg\" /></div></div>"+html+"</div><br/><div class=\"row sidebar\" style=\"padding: 0\"><div class=\"col-md-12\"><h2>Carreras</h2></div>"+html2+"</div>";
            }
            else
                return "";
        }
        return "";
    }
}
