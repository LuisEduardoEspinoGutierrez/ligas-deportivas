package com.upiiz.ligas_deportivas.dto;

public class EntrenadorCreateRequest {

    private String nombre;
    private int experiencia;
    private String nacionalidad;
    public EntrenadorCreateRequest(){}
    public String getNombre(){ return nombre; }
    public void setNombre(String nombre){ this.nombre = nombre; }
    public int getExperiencia(){ return experiencia; }
    public void setExperiencia(int experiencia){ this.experiencia = experiencia; }
    public String getNacionalidad(){ return nacionalidad; }
    public void setNacionalidad(String nacionalidad){ this.nacionalidad = nacionalidad; }

}
