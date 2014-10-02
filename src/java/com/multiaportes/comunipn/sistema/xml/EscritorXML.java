package com.multiaportes.comunipn.sistema.xml;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class EscritorXML extends XML
{
    private TransformerFactory tf;
    private Transformer t;
    
    public EscritorXML(String ruta)
    {
        super(ruta);
    }
    public void setValor(String instruccion, String valor)
    {
        super.instruccion = instruccion + "/text()";
        super.crearNodo();
        
        if(nodo != null)
            super.nodo.setNodeValue(valor);
        
        this.guardar();
    }
    public void setValor(String instruccion, String atributo, String valor)
    {
        this.instruccion = instruccion + "/@"+atributo;
        super.crearNodo();
        
        if(nodo != null)
            super.nodo.setNodeValue(valor);
        
        this.guardar();
    }
    private void guardar()
    {
        try
        {
            tf = TransformerFactory.newInstance();
            t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(super.documento), new StreamResult(super.fichero));
        }
        catch(IllegalArgumentException | TransformerException x)
        {
            System.err.println(x.getMessage());
        }
    }
}
