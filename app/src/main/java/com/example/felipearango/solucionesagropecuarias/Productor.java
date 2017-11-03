package com.example.felipearango.solucionesagropecuarias;

import java.util.ArrayList;

/**
 * Created by Felipe Arango on 8/10/2017.
 */

public class Productor {

    ///////////////////////////
    //variables
    //////////////////////////

    private String idProductor;
    private String cedulaP;
    private String nombre;
    private String apellidos;
    private String sexo;
    private String fechaNacimiento;
    private String celularP;
    private String email;
    private String etnia;
    private int empleado;
    private int pensionado;
    private int casado;
    private int personasACargo;
    private int ingProvFinc;
    private ArrayList<Finca> listfinca;
    private ArrayList<Cultivo> listcultivo;

    /////////////////////////////
    //Constructor
    /////////////////////////////

    public Productor(String idProductor, String cedulaP, String nombre, String apellidos,
                     String sexo, String fechaNacimiento, String celularP, String email,
                     String etnia, int empleado, int pensionado, int casado, int personasACargo,
                     int ingProvFinc) {
        this.idProductor = idProductor;
        this.cedulaP = cedulaP;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.celularP = celularP;
        this.email = email;
        this.etnia = etnia;
        this.empleado = empleado;
        this.pensionado = pensionado;
        this.casado = casado;
        this.personasACargo = personasACargo;
        this.ingProvFinc = ingProvFinc;
    }

    public Productor(){}

    /////////////////////////////
    // getter and setter
    /////////////////////////////


    public String getIdProductor() {
        return idProductor;
    }

    public void setIdProductor(String idProductor) {
        this.idProductor = idProductor;
    }

    public String getCedulaP() {
        return cedulaP;
    }

    public void setCedulaP(String cedulaP) {
        this.cedulaP = cedulaP;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCelularP() {
        return celularP;
    }

    public void setCelularP(String celularP) {
        this.celularP = celularP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEtnia() {
        return etnia;
    }

    public void setEtnia(String etnia) {
        this.etnia = etnia;
    }

    public int getEmpleado() {
        return empleado;
    }

    public void setEmpleado(int empleado) {
        this.empleado = empleado;
    }

    public int getPensionado() {
        return pensionado;
    }

    public void setPensionado(int pensionado) {
        this.pensionado = pensionado;
    }

    public int getCasado() {
        return casado;
    }

    public void setCasado(int casado) {
        this.casado = casado;
    }

    public int getPersonasACargo() {
        return personasACargo;
    }

    public void setPersonasACargo(int personasACargo) {
        this.personasACargo = personasACargo;
    }

    public int getIngProvFinc() {
        return ingProvFinc;
    }

    public void setIngProvFinc(int ingProvFinc) {
        this.ingProvFinc = ingProvFinc;
    }

    public ArrayList<Finca> getListfinca() {
        return listfinca;
    }

    public void setListfinca(ArrayList<Finca> listfinca) {
        this.listfinca = listfinca;
    }

    public ArrayList<Cultivo> getListcultivo() {
        return listcultivo;
    }

    public void setListcultivo(ArrayList<Cultivo> listcultivo) {
        this.listcultivo = listcultivo;
    }
}
