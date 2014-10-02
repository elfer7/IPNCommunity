package com.multiaportes.comunipn.sistema.xml;

public class LectorXML extends XML
{
    public LectorXML(String ruta)
    {
        super(ruta);
//        try {
////            File file = new File(fichero);
////  DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
////  Document doc = domFactory.newDocumentBuilder().parse(file.getAbsolutePath());
////  XPath xpath = XPathFactory.newInstance().newXPath();
//
//  //String xPathStr = "/usuario/porno/xd/text()";
//  String xPathStr = "/usuario/porno/xd/@miau";
//  Node node = ((NodeList) xpath.compile(xPathStr).evaluate(doc, XPathConstants.NODESET)).item(0);
//  kk = node.getNodeValue().trim();
//  node.setNodeValue("polladura");
//    
//
//  TransformerFactory transformerFactory = TransformerFactory.newInstance();
//  Transformer transformer = transformerFactory.newTransformer();
//  transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//  transformer.transform(new DOMSource(doc), new StreamResult(file));
////} catch (Exception e) {
////  
////}
    }
    public String getValor(String instruccion)
    {
        return super.getValor(instruccion);
    }
    public String getValor(String instruccion, String atributo)
    {
        return super.getValor(instruccion, atributo);
    }
}
