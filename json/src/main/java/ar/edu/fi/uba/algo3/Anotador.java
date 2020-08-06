/*
 * Created on 31/05/2005
 */
package ar.edu.fi.uba.algo3;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Anotador {

    /**
     *  
     * @uml.property name="nota"
     * @uml.associationEnd inverse="anotador:fiuba.algo3.ejemplos.Nota" multiplicity="(0 -1)"
     * @uml.association name="contiene"
     */
    private Collection<Nota> notas;
    private String nombre;

    public Anotador() {
        notas = new ArrayList();
        nombre = "anotador";
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

    public JsonObject guardar() {
        JsonObject jsonObjectAnotador = new JsonObject();

        JsonArray jsonArrayNotas = new JsonArray();
        for (Nota nota : this.notas) {
            jsonArrayNotas.add(nota.guardar());
        }
        jsonObjectAnotador.add("notas", jsonArrayNotas);
        jsonObjectAnotador.addProperty("nombre", this.nombre);

        return jsonObjectAnotador;
    }

    public void guardar(String archivo) throws IOException{
        Gson gson = new Gson();

        String json = gson.toJson(this.guardar());

        FileWriter writer = new FileWriter(archivo);
        writer.write(json);
        writer.close();
    }

    public static Anotador recuperar(JsonObject jsonObjectAnotador) {
        Anotador anotador = new Anotador();

        anotador.nombre = jsonObjectAnotador.get("nombre").getAsString();

        JsonArray arrayNotas = jsonObjectAnotador.getAsJsonArray("notas");
        for (JsonElement jsonNota : arrayNotas) {
            JsonElement jsonObjectNota = jsonNota;
            Nota nota = Nota.recuperar(jsonObjectNota.getAsJsonObject());
            anotador.addNota(nota);
        }

        return anotador;
    }

    public static Anotador recuperar(String archivo) throws IOException{
        String texto = Files.readString(Path.of(archivo));

        JsonObject jsonObject = JsonParser.parseString(texto).getAsJsonObject();

        return recuperar(jsonObject);
    }
    
    public void buscarNota(String titulo) {
    }
    
    public String toString(){
        String s = "Anotador" + nombre + ": ";
        Iterator it = notas.iterator();
        while(it.hasNext()){
            s += it.next().toString() + "; ";
        }
        return s;
    }
}
