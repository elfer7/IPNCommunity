package com.multiaportes.comunipn.sistema.usuarios;

import com.multiaportes.comunipn.sistema.xml.EscritorXML;
import com.multiaportes.comunipn.sistema.xml.LectorXML;

public class Respuesta {
    private final String uri;
    EscritorXML writer;
    LectorXML reader;
    
    public Respuesta(long id,long idP,long idR,String ruta){ 
        uri=ruta+"usuarios\\"+id+"\\"+idP+"\\"+idR+".xml";
        System.out.println(uri);
        writer=new EscritorXML(uri);
        reader=new LectorXML(uri);
    }
    
    public void setId(String id){
        writer.setValor("/respuesta", "id",""+id);
    }
    public void setUsuario(String id){
        writer.setValor("/respuesta", "usuario",""+id);
    }
    public void setPositivos(String votos){
        writer.setValor("/respuesta/votos/positivo",votos);
    }
    public void setNegativos(String votos){
        writer.setValor("/respuesta/votos/negativo",votos);
    }
    public void setContenido(String contenido){
        writer.setValor("/respuesta/contenido",contenido);
    }
    
    
    public String getId(){
        return reader.getValor("/respuesta","id");
    }
    public String getUsuario(){
        return reader.getValor("/respuesta","usuario");
    }
    public String getPositivos(){
        return reader.getValor("/respuesta/votos/positivos");
    }
    public String getNegativos(){
        return reader.getValor("/respuesta/votos/negativos");
    }
    public String getContenido(){
        return reader.getValor("/respuesta/contenido");
    }
    /*public static void main(String args[]){
        Respuesta user=new Respuesta(686664832,27546802,1,"C:\\Users\\zack\\Documents\\NetBeansProjects\\ComunIPN\\web\\");
        System.out.println(user.getContenido());
    }*/
}
