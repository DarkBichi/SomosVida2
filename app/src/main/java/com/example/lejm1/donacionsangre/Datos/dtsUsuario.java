package com.example.lejm1.donacionsangre.Datos;

/**
 * Created by azulm on 21/06/2016.
 */
public class dtsUsuario {
    String ap_paterno;
    String ap_materno;
    String nombres;
    String fecha_nac;
    String email;
    String user;
    String sexo;
    String tipoSangre;
    String ciudad;
    String estado;
    String pais;

    public dtsUsuario() {
    }

    public dtsUsuario(String ap_paterno, String ap_materno, String nombres, String fecha_nac, String email, String user, String sexo, String tipoSangre, String ciudad, String estado, String pais) {
        this.ap_paterno = ap_paterno;
        this.ap_materno = ap_materno;
        this.nombres = nombres;
        this.fecha_nac = fecha_nac;
        this.email = email;
        this.user = user;
        this.sexo = sexo;
        this.tipoSangre = tipoSangre;
        this.ciudad = ciudad;
        this.estado = estado;
        this.pais = pais;
    }


    public String getAp_paterno() {
        return ap_paterno;
    }

    public void setAp_paterno(String ap_paterno) {
        this.ap_paterno = ap_paterno;
    }

    public String getAp_materno() {
        return ap_materno;
    }

    public void setAp_materno(String ap_materno) {
        this.ap_materno = ap_materno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
