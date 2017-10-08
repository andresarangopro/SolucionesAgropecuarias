package com.example.felipearango.solucionesagropecuarias;

/**
 * Created by Felipe Arango on 8/10/2017.
 */

public class Finca {

    ///////////////////////////////
    //Variables
    //////////////////////////////

    private String idFinca;
    private String nombreFinca;
    private float areaNum;
    private String areaMed;
    private String municipio;
    private String vereda;
    private String sector;
    private float distanciaCabeceraNum;
    private String distanciaCabeceraMed;
    private float tiempoACabeceraNum;
    private String tiempoCabeceraMed;
    private String viaDeAcceso;
    private String alquilada;
    private float altitud;
    private String ubicacion;
    private String terreno;
    private int cultivaAloe;

    ///////////////////////////////
    //Constructor
    //////////////////////////////

    public Finca(String idFinca, String nombreFinca, float areaNum, String areaMed,
                 String municipio, String vereda, String sector, float distanciaCabeceraNum,
                 String distanciaCabeceraMed, float tiempoACabeceraNum, String tiempoCabeceraMed,
                 String viaDeAcceso, String alquilada, float altitud, String ubicacion,
                 String terreno, int cultivaAloe) {
        this.idFinca = idFinca;
        this.nombreFinca = nombreFinca;
        this.areaNum = areaNum;
        this.areaMed = areaMed;
        this.municipio = municipio;
        this.vereda = vereda;
        this.sector = sector;
        this.distanciaCabeceraNum = distanciaCabeceraNum;
        this.distanciaCabeceraMed = distanciaCabeceraMed;
        this.tiempoACabeceraNum = tiempoACabeceraNum;
        this.tiempoCabeceraMed = tiempoCabeceraMed;
        this.viaDeAcceso = viaDeAcceso;
        this.alquilada = alquilada;
        this.altitud = altitud;
        this.ubicacion = ubicacion;
        this.terreno = terreno;
        this.cultivaAloe = cultivaAloe;
    }

    ///////////////////////////////
    //Getters and Setters
    //////////////////////////////


    public String getIdFinca() {
        return idFinca;
    }

    public void setIdFinca(String idFinca) {
        this.idFinca = idFinca;
    }

    public String getNombreFinca() {
        return nombreFinca;
    }

    public void setNombreFinca(String nombreFinca) {
        this.nombreFinca = nombreFinca;
    }

    public float getAreaNum() {
        return areaNum;
    }

    public void setAreaNum(float areaNum) {
        this.areaNum = areaNum;
    }

    public String getAreaMed() {
        return areaMed;
    }

    public void setAreaMed(String areaMed) {
        this.areaMed = areaMed;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getVereda() {
        return vereda;
    }

    public void setVereda(String vereda) {
        this.vereda = vereda;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public float getDistanciaCabeceraNum() {
        return distanciaCabeceraNum;
    }

    public void setDistanciaCabeceraNum(float distanciaCabeceraNum) {
        this.distanciaCabeceraNum = distanciaCabeceraNum;
    }

    public String getDistanciaCabeceraMed() {
        return distanciaCabeceraMed;
    }

    public void setDistanciaCabeceraMed(String distanciaCabeceraMed) {
        this.distanciaCabeceraMed = distanciaCabeceraMed;
    }

    public float getTiempoACabeceraNum() {
        return tiempoACabeceraNum;
    }

    public void setTiempoACabeceraNum(float tiempoACabeceraNum) {
        this.tiempoACabeceraNum = tiempoACabeceraNum;
    }

    public String getTiempoCabeceraMed() {
        return tiempoCabeceraMed;
    }

    public void setTiempoCabeceraMed(String tiempoCabeceraMed) {
        this.tiempoCabeceraMed = tiempoCabeceraMed;
    }

    public String getViaDeAcceso() {
        return viaDeAcceso;
    }

    public void setViaDeAcceso(String viaDeAcceso) {
        this.viaDeAcceso = viaDeAcceso;
    }

    public String getAlquilada() {
        return alquilada;
    }

    public void setAlquilada(String alquilada) {
        this.alquilada = alquilada;
    }

    public float getAltitud() {
        return altitud;
    }

    public void setAltitud(float altitud) {
        this.altitud = altitud;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    public int getCultivaAloe() {
        return cultivaAloe;
    }

    public void setCultivaAloe(int cultivaAloe) {
        this.cultivaAloe = cultivaAloe;
    }
}
