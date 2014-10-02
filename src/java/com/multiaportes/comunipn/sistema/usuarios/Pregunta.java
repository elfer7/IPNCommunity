package com.multiaportes.comunipn.sistema.usuarios;

import com.multiaportes.comunipn.sistema.xml.EscritorXML;
import com.multiaportes.comunipn.sistema.xml.LectorXML;

public class Pregunta {
    private final String uri;
    EscritorXML writer;
    LectorXML reader;
    
    public Pregunta(long id,long idP,String ruta){ 
        uri=ruta+"usuarios\\"+id+"\\"+idP+"\\"+idP+".xml";
        System.out.println(uri);
        writer=new EscritorXML(uri);
        reader=new LectorXML(uri);
    }
    
    public void setIdPregunta(String id){
        writer.setValor("/pregunta", "id",""+id);
    }
    public void setTitulo(String titulo){
        writer.setValor("/pregunta", "titulo", titulo);
    }
    public void setFinalizada(String finalizada){
        writer.setValor("/pregunta", "finalizada", finalizada);
    }
    public void setIdCategoria(String id){
        writer.setValor("/pregunta/categoria", "id",""+id);
    }
    public void setPositivos(String votos){
        writer.setValor("/pregunta/votos/positivo",votos);
    }
    public void setNegativos(String votos){
        writer.setValor("/pregunta/votos/negativo",votos);
    }
    public void setContenido(String contenido){
        writer.setValor("/pregunta/contenido",contenido);
    }
    public void setTag(String tag){
        writer.setValor("/pregunta/tags/tag","nombre",tag);
    }
    public void setIdRelacionado(String id){
        writer.setValor("/pregunta/relacionados/pregunta","id",""+id);
    }
    
    public String getIdPregunta(){
        return reader.getValor("/pregunta","id");
    }
    public String getTitulo(){
        return reader.getValor("/pregunta","titulo");
    }
    public String getFinalizada(){
        return reader.getValor("/pregunta","finalizada");
    }
    public String getIdCategoria(){
        return reader.getValor("/pregunta/categoria","id");
    }
    public String getPositivos(){
        return reader.getValor("/pregunta/votos/positivos");
    }
    public String getNegativos(){
        return reader.getValor("/pregunta/votos/negativos");
    }
    public String getContenido(){
        return reader.getValor("/pregunta/contenido");
    }
    public String getTag(){
        return reader.getValor("/pregunta/tags/tag","nombre");
    }
    public String getIdRelacionado(){
        return reader.getValor("/pregunta/relacionados/pregunta","id");
    }
    /*public static void main(String args[]){
        Pregunta user=new Pregunta(686664832,27546802,"C:\\Users\\zack\\Documents\\NetBeansProjects\\ComunIPN\\web\\");
        System.out.println(user.getContenido());
    }*/
}
