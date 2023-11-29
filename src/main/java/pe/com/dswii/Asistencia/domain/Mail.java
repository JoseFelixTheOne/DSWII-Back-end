package pe.com.dswii.Asistencia.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Mail {

    @JsonProperty("correosAEnviar")
    private List<String> correosAEnviar;

    @JsonProperty("asunto")
    private String asunto;

    @JsonProperty("contenido")
    private String contenido;

    @JsonProperty("nombresArchivos")
    private List<String> nombresArchivos;

    @JsonProperty("listabyte")
    private List<byte[]> listabyte;

    // Constructor, getters y setters

    public Mail() {
    }

    public List<String> getCorreosAEnviar() {
        return correosAEnviar;
    }

    public void setCorreosAEnviar(List<String> correosAEnviar) {
        this.correosAEnviar = correosAEnviar;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public List<String> getNombresArchivos() {
        return nombresArchivos;
    }

    public void setNombresArchivos(List<String> nombresArchivos) {
        this.nombresArchivos = nombresArchivos;
    }

    public List<byte[]> getListabyte() {
        return listabyte;
    }

    public void setListabyte(List<byte[]> listabyte) {
        this.listabyte = listabyte;
    }

}
