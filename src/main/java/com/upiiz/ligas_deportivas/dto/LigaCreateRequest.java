package com.upiiz.ligas_deportivas.dto;

public class LigaCreateRequest {

    private String nombre;
    private String pais;
    private String temporadaActual;
    public LigaCreateRequest(){}
    public String getNombre(){ return nombre; }
    public void setNombre(String nombre){ this.nombre = nombre; }
    public String getPais(){ return pais; }
    public void setPais(String pais){ this.pais = pais; }
    public String getTemporadaActual(){ return temporadaActual; }
    public void setTemporadaActual(String temporadaActual){ this.temporadaActual = temporadaActual; }

}
