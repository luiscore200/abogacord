package com.foreroinc.abogacord.recycler;

public class objetoProceso {

    private int IDCaso;
    private String Nombre;
    private String Descripcion;
    private int CedulaCliente;
    private int Estado;


    public objetoProceso() {
        IDCaso = this.IDCaso;
        Nombre = this.Nombre;
        Descripcion = this.Descripcion;
        CedulaCliente = this.CedulaCliente;
        Estado = this.Estado;

    }

    public int getIDCaso() {
        return IDCaso;
    }

    public void setIDCaso(int IDCaso) {
        this.IDCaso = IDCaso;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getCedulaCliente() {
        return CedulaCliente;
    }

    public void setCedulaCliente(int cedulaCliente) {
        CedulaCliente = cedulaCliente;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }


}
