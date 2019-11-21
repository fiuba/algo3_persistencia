/*
 * Created on 31/05/2005
 */
package ar.edu.fi.uba.algo3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;



import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
/**
 * @author Nicolas
 */

public class Anotador {

    /**
     *  
     * @uml.property name="nota"
     * @uml.associationEnd inverse="anotador:fiuba.algo3.ejemplos.Nota" multiplicity="(0 -1)"
     * @uml.association name="contiene"
     */
    private Collection notas;
    
    public Anotador(){
        notas = new ArrayList();
    }

    /**
     * 
     * @uml.property name="nota"
     */
    public void setNota(java.util.Collection value) {
        notas = value;
    }

    /**
     * 
     * @uml.property name="nota"
     */
    public Iterator notaIterator() {
        return notas.iterator();
    }

    /**
     * 
     * @uml.property name="nota"
     */
    public boolean addNota(Nota element) {
        return notas.add(element);
    }

    /**
     * 
     * @uml.property name="nota"
     */
    public boolean removeNota(Nota element) {
        return notas.remove(element);
    }

    /**
     * 
     * @uml.property name="nota"
     */
    public boolean isNotaEmpty() {
        return notas.isEmpty();
    }

    /**
     * 
     * @uml.property name="nota"
     */
    public void clearNota() {
        notas.clear();
    }

    /**
     * 
     * @uml.property name="nota"
     */
    public boolean containsNota(Nota element) {
        return notas.contains(element);
    }

    /**
     * 
     * @uml.property name="nota"
     */
    public boolean containsAllNota(Collection elements) {
        return notas.containsAll(elements);
    }

    /**
     * 
     * @uml.property name="nota"
     */
    public int notaSize() {
        return notas.size();
    }

    /**
     * 
     * @uml.property name="nota"
     */
    public Nota[] notaToArray() {
        return (Nota[]) notas
            .toArray(new Nota[notas.size()]);
    }

    private String nombre = "";
    public Element guardar() {
        Element elemAnotador = DocumentHelper.createElement("Anotador");
        Iterator it = notas.iterator();
        while(it.hasNext()){
            Nota nota = (Nota)it.next();
            elemAnotador.add(nota.guardar());
        }
        elemAnotador.addAttribute("nombre", this.nombre);
        return elemAnotador;
    }
    
    public void guardar(String archivo) throws IOException{
        Document doc = DocumentHelper.createDocument();
        doc.add(this.guardar());
        FileWriter writer = new FileWriter(archivo);
        doc.write( writer);
        writer.close();
    }

    public static Anotador recuperar(Element elemAnotador) {
        Anotador anotador = new Anotador();
        Iterator it = elemAnotador.elementIterator();
        while(it.hasNext()){
            Element elemNota = (Element)it.next();
            anotador.addNota(Nota.recuperar(elemNota));
        }
        anotador.nombre = elemAnotador.attributeValue("nombre");
        return anotador;
    }

    public static Anotador recuperar(String archivo) throws IOException{
        Anotador anotador;// = new Anotador();
        SAXReader reader = new SAXReader();
        try{
	        Document document = reader.read(archivo);
	        Element elemAnotador = document.getRootElement();
	        anotador = recuperar(elemAnotador);
        }catch(DocumentException ex){
            throw new IOException();
        }
        return anotador;
    }
    
    public void buscarNota(String titulo) {
    }
    
    public String toString(){
        String s = "Anotador" + nombre + ":";
        Iterator it = notas.iterator();
        while(it.hasNext()){
            s += it.next().toString() + " ";
        }
        return s;
    }
}
