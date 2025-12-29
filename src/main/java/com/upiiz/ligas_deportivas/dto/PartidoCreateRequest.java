package com.upiiz.ligas_deportivas.dto;

import java.time.LocalDate;

public class PartidoCreateRequest {

    private LocalDate fecha;
    private Integer golesLocal;
    private Integer golesVisitante;

    private Long equipoLocalId;
    private Long equipoVisitanteId;
    private Long ligaId;

    public PartidoCreateRequest() {}

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(Integer golesLocal) {
        this.golesLocal = golesLocal;
    }

    public Integer getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(Integer golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public Long getEquipoLocalId() {
        return equipoLocalId;
    }

    public void setEquipoLocalId(Long equipoLocalId) {
        this.equipoLocalId = equipoLocalId;
    }

    public Long getEquipoVisitanteId() {
        return equipoVisitanteId;
    }

    public void setEquipoVisitanteId(Long equipoVisitanteId) {
        this.equipoVisitanteId = equipoVisitanteId;
    }

    public Long getLigaId() {
        return ligaId;
    }

    public void setLigaId(Long ligaId) {
        this.ligaId = ligaId;
    }
}


