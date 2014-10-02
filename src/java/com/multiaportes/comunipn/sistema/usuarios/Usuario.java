
package com.multiaportes.comunipn.sistema.usuarios;

import com.multiaportes.comunipn.sistema.xml.EscritorXML;
import com.multiaportes.comunipn.sistema.xml.LectorXML;

public class Usuario {
    private final String uri;
    EscritorXML writer;
    LectorXML reader;
    
    public Usuario(long id,String ruta){ 
        uri=ruta+"usuarios\\"+id+"\\"+id+".xml";
        System.out.println(uri);
        writer=new EscritorXML(uri);
        reader=new LectorXML(uri);
    }
    
    public void setNombre(String nombre){
        writer.setValor("/usuario/informacion/persona","nombre", nombre);
    }
    public void setApellidos(String apellidos){
        writer.setValor("/usuario/informacion/persona","apellidos", apellidos);
    }
    public void setEmail(String email){
        writer.setValor("/usuario/informacion/persona","email", email);
    }
    public void setAvatar(String url){
        writer.setValor("/usuario/informacion/avatar","url", url);
    }
    public void setFacebook(String url){
        writer.setValor("/usuario/informacion/links/facebook","url", url);
    }
    public void setTwitter(String url){
        writer.setValor("/usuario/informacion/links/twitter","url", url);
    }
    public void setWebsite(String url){
        writer.setValor("/usuario/informacion/links/website","url", url);
    }
    
    public void setNombreVocaional(String nombre){
        writer.setValor("/usuario/informacion/escuelas/vocacional","nombre", nombre);
    }
    public void setCarreraVocaional(String carrera){
        writer.setValor("/usuario/informacion/escuelas/vocacional","carrera", carrera);
    }
    public void setInicioVocacional(String inicio){
        writer.setValor("/usuario/informacion/escuelas/vocacional","inicio", inicio);
    }
    public void setFinVocaional(String fin){
        writer.setValor("/usuario/informacion/escuelas/vocacional","fin", fin);
    }
    public void setNombreSuperior(String id){
        writer.setValor("/usuario/informacion/escuelas/superior","id", id);
    }
    public void setCarreraSuperior(String carrera){
        writer.setValor("/usuario/informacion/escuelas/superior","carrera", carrera);
    }
    public void setInicioSuperior(String inicio){
        writer.setValor("/usuario/informacion/escuelas/superior","inicio", inicio);
    }
    public void setFinSuperior(String fin){
        writer.setValor("/usuario/informacion/escuelas/superior","fin", fin);
    }
    
    public void setSeguidor(String id){
        writer.setValor("/usuario/seguidores/usuario","id", id);
    }
    public void setSiguiendo(String id){
        writer.setValor("/usuario/siguiendo/usuario","id", id);
    }
    public void setFavId(String id){
        writer.setValor("/usuario/favoritos/pregunta","id", id);
    }
    public void setFavUsuario(String usuario){
        writer.setValor("/usuario/favoritos/pregunta","usuario", usuario);
    }
    
    
    public String getNombre(){
        return reader.getValor("/usuario/informacion/persona" , "nombre");
    }
    public String getApellidos(){
        return reader.getValor("/usuario/informacion/persona" , "apellidos");
    }
    public String getEmail(){
        return reader.getValor("/usuario/informacion/persona" , "email");
    }
    public String getAvatar(){
        return reader.getValor("/usuario/informacion/avatar" , "url");
    }
    public String getFacebook(){
        return reader.getValor("/usuario/informacion/links/facebook", "url");
    }
    public String getTwitter(){
        return reader.getValor("/usuario/informacion/links/twitter", "url");
    }
    public String getWebsite(){
        return reader.getValor("/usuario/informacion/links/website", "url");
    }
    
    public String getNombreVocacional(){
        return reader.getValor("/usuario/informacion/escuelas/vocacional", "nombre");
    }
    public String getCarreraVocacional(){
        return reader.getValor("/usuario/informacion/escuelas/vocacional", "carrera");
    }
    public String getInicioVocacional(){
        return reader.getValor("/usuario/informacion/escuelas/vocacional", "inicio");
    }
    public String getFinVocacional(){
        return reader.getValor("/usuario/informacion/escuelas/vocacional", "fin");
    }
    public String getIdSuperior(){
        return reader.getValor("/usuario/informacion/escuelas/superior", "id");
    }
    public String getCarreraSuperior(){
        return reader.getValor("/usuario/informacion/escuelas/superior", "carrera");
    }
    public String getInicioSuperior(){
        return reader.getValor("/usuario/informacion/escuelas/superior", "inicio");
    }
    public String getFinSuperior(){
        return reader.getValor("/usuario/informacion/escuelas/superior", "fin");
    }
    public String getIdSeguidor(){
        return reader.getValor("/usuario/seguidores/usuario", "id");
    }
    public String getIdSiguiendo(){
        return reader.getValor("/usuario/siguiendo/usuario", "id");
    }
    public String getFavId(){
        return reader.getValor("/usuario/favoritos/pregunta", "id");
    }
    public String getFavUsuario(){
        return reader.getValor("/usuario/favoritos/pregunta", "usuario");
    }
 /* public static void main(String args[]){
        Usuario user=new Usuario(686664832,"C:\\Users\\zack\\Documents\\NetBeansProjects\\ComunIPN\\web\\");
        System.out.println(user.getTwitter());
    }*/
}
