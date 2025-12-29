package com.upiiz.ligas_deportivas.dto;

public class JugadorCreateRequest {

    private String nombre;
    private String posicion;
    private int numero;
    private Long equipoId;
    public JugadorCreateRequest(){}
    public String getNombre(){ return nombre; }
    public void setNombre(String nombre){ this.nombre = nombre; }
    public String getPosicion(){ return posicion; }
    public void setPosicion(String posicion){ this.posicion = posicion; }
    public int getNumero(){ return numero; }
    public void setNumero(int numero){ this.numero = numero; }
    public Long getEquipoId(){ return equipoId; }
    public void setEquipoId(Long equipoId){ this.equipoId = equipoId; }
}
