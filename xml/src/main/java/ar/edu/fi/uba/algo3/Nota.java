/*
 * Created on 31/05/2005
 */
package ar.edu.fi.uba.algo3;

/**
 * @author Nicolas
 */
import org.dom4j.*;
public class Nota {
    
    public Nota(String titulo, String texto){
        this.titulo = titulo;
        this.texto = texto;
    }

    /**
     *  
     * @uml.property name="anotador"
     * @uml.associationEnd inverse="nota:fiuba.algo3.ejemplos.Anotador" multiplicity="(1 1)"
     * @uml.association name="contiene"
     */
    private Anotador anotador;

    /**
     *  
     * @uml.property name="anotador"
     */
    public Anotador getAnotador() {
        return anotador;
    }

    /**
     *  
     * @uml.property name="anotador"
     */
    public void setAnotador(Anotador anotador) {
        this.anotador = anotador;
    }

    public Element guardar() {
        Element elemNota = DocumentHelper.createElement("Nota");
        elemNota.addAttribute("titulo", this.getTitulo());
        elemNota.addAttribute("texto", this.getTexto());
        return elemNota;
    }

    public static Nota recuperar(Element elemNota) {
        String titulo = elemNota.attributeValue("titulo");
        String texto = elemNota.attributeValue("texto");
        Nota nota = new Nota(titulo, texto);
        return nota;
    }

    /**
     *  
     * @uml.property name="titulo" multiplicity="(0 1)"
     */
    private String titulo;

    /**
     *  
     * @uml.property name="titulo"
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     *  
     * @uml.property name="titulo"
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     *  
     * @uml.property name="texto" multiplicity="(0 1)"
     */
    private String texto;

    /**
     *  
     * @uml.property name="texto"
     */
    public String getTexto() {
        return texto;
    }

    /**
     *  
     * @uml.property name="texto"
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String toString(){
        String s = this.titulo + ": " + this.texto;
        return s;
    }
}
