package com.foreroinc.abogacord.recycler;

public class objetoUsuario {

    private String Nombres;
    private int IDRol;
    private int Cedula;
    private String Apellidos;
    private String Usuario;
    private String Contrasena;

    public objetoUsuario() {
        Nombres = this.Nombres;
        IDRol = this.IDRol;
        Cedula = this.Cedula;
        Apellidos = this.Apellidos;
        Usuario = this.Usuario;
        Contrasena = this.Contrasena;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public int getIDRoll() {
        return IDRol;
    }

    public void setIDRol(int IDRol) {
        this.IDRol = IDRol;
    }

    public int getCedula() {
        return Cedula;
    }

    public void setCedula(int cedula) {
        Cedula = cedula;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }
}