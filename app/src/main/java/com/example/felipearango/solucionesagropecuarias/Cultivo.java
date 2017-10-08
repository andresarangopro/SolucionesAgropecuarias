package com.example.felipearango.solucionesagropecuarias;

/**
 * Created by Felipe Arango on 8/10/2017.
 */

public class Cultivo {


    /////////////////////
    //Variables
    /////////////////////

    private String idCultivo;
    private String numeroLote;
    private int totalPlantas;
    private String fechaSiembra;
    private float distanciaEntrePlantas;
    private float largoHojas;
    private float anchoHojas;
    private float pesoHojas;
    private String estadoCultivo;
    private float gradoBrix;
    private String origen;
    private int manejoOrganico;
    private String productos;
    private float cantidadXPlanta;
    private float cultivoAsociado;
    private String especie;
    private int manejoRiego;
    private int haceDesahoje;
    private int retiraEscapeFloral;
    private int retiraHijuelos;
    private String plantaEnferma;

    /////////////////////
    //Constructor
    /////////////////////

    public Cultivo(String idCultivo, String numeroLote, int totalPlantas,
                   String fechaSiembra, float distanciaEntrePlantas,
                   float largoHojas, float anchoHojas, float pesoHojas, String estadoCultivo,
                   float gradoBrix, String origen, int manejoOrganico, String productos,
                   float cantidadXPlanta, float cultivoAsociado, String especie,
                   int manejoRiego,int haceDesahoje, int retiraEscapeFloral, int retiraHijuelos,
                   String plantaEnferma) {

        this.idCultivo = idCultivo;
        this.numeroLote = numeroLote;
        this.totalPlantas = totalPlantas;
        this.fechaSiembra = fechaSiembra;
        this.distanciaEntrePlantas = distanciaEntrePlantas;
        this.largoHojas = largoHojas;
        this.anchoHojas = anchoHojas;
        this.pesoHojas = pesoHojas;
        this.estadoCultivo = estadoCultivo;
        this.gradoBrix = gradoBrix;
        this.origen = origen;
        this.manejoOrganico = manejoOrganico;
        this.productos = productos;
        this.cantidadXPlanta = cantidadXPlanta;
        this.cultivoAsociado = cultivoAsociado;
        this.especie = especie;
        this.manejoRiego = manejoRiego;
        this.haceDesahoje = haceDesahoje;
        this.retiraEscapeFloral = retiraEscapeFloral;
        this.retiraHijuelos = retiraHijuelos;
        this.plantaEnferma = plantaEnferma;
    }


    /////////////////////
    //getter and setter
    /////////////////////


    public String getIdCultivo() {
        return idCultivo;
    }

    public void setIdCultivo(String idCultivo) {
        this.idCultivo = idCultivo;
    }

    public String getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    public int getTotalPlantas() {
        return totalPlantas;
    }

    public void setTotalPlantas(int totalPlantas) {
        this.totalPlantas = totalPlantas;
    }

    public String getFechaSiembra() {
        return fechaSiembra;
    }

    public void setFechaSiembra(String fechaSiembra) {
        this.fechaSiembra = fechaSiembra;
    }

    public float getDistanciaEntrePlantas() {
        return distanciaEntrePlantas;
    }

    public void setDistanciaEntrePlantas(float distanciaEntrePlantas) {
        this.distanciaEntrePlantas = distanciaEntrePlantas;
    }

    public float getLargoHojas() {
        return largoHojas;
    }

    public void setLargoHojas(float largoHojas) {
        this.largoHojas = largoHojas;
    }

    public float getAnchoHojas() {
        return anchoHojas;
    }

    public void setAnchoHojas(float anchoHojas) {
        this.anchoHojas = anchoHojas;
    }

    public float getPesoHojas() {
        return pesoHojas;
    }

    public void setPesoHojas(float pesoHojas) {
        this.pesoHojas = pesoHojas;
    }

    public String getEstadoCultivo() {
        return estadoCultivo;
    }

    public void setEstadoCultivo(String estadoCultivo) {
        this.estadoCultivo = estadoCultivo;
    }

    public float getGradoBrix() {
        return gradoBrix;
    }

    public void setGradoBrix(float gradoBrix) {
        this.gradoBrix = gradoBrix;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public int getManejoOrganico() {
        return manejoOrganico;
    }

    public void setManejoOrganico(int manejoOrganico) {
        this.manejoOrganico = manejoOrganico;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public float getCantidadXPlanta() {
        return cantidadXPlanta;
    }

    public void setCantidadXPlanta(float cantidadXPlanta) {
        this.cantidadXPlanta = cantidadXPlanta;
    }

    public float getCultivoAsociado() {
        return cultivoAsociado;
    }

    public void setCultivoAsociado(float cultivoAsociado) {
        this.cultivoAsociado = cultivoAsociado;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getManejoRiego() {
        return manejoRiego;
    }

    public void setManejoRiego(int manejoRiego) {
        this.manejoRiego = manejoRiego;
    }

    public int getHaceDesahoje() {
        return haceDesahoje;
    }

    public void setHaceDesahoje(int haceDesahoje) {
        this.haceDesahoje = haceDesahoje;
    }

    public int getRetiraEscapeFloral() {
        return retiraEscapeFloral;
    }

    public void setRetiraEscapeFloral(int retiraEscapeFloral) {
        this.retiraEscapeFloral = retiraEscapeFloral;
    }

    public int getRetiraHijuelos() {
        return retiraHijuelos;
    }

    public void setRetiraHijuelos(int retiraHijuelos) {
        this.retiraHijuelos = retiraHijuelos;
    }

    public String getPlantaEnferma() {
        return plantaEnferma;
    }

    public void setPlantaEnferma(String plantaEnferma) {
        this.plantaEnferma = plantaEnferma;
    }
}
