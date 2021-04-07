package Model.Entity;

import Model.Client.DTO.DTOClient;


import java.io.Serializable;

public class Inventario implements DTOClient, Serializable {

    private Long id;
    private String nombre;
    private int precio;
    private String recomendaciónes;
    private String talla;
    private String message;

    public Inventario(Long id, String nombre, int precio, String recomendaciones, String talla) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.recomendaciónes = recomendaciones;
        this.talla = talla;
    }

    public Inventario(Long id) { this.id = id;}

    public Inventario() {
    }

    @Override
    public String insert() {
        String sql = "INSERT INTO inventario_ropa(\n" +
                "\tid, nombre, precio, \"recomendaciónes\", talla)\n" +
                "\tVALUES ("
                +id +", '"
                + nombre.trim() + "',"
                + precio+ ",'" +recomendaciónes.trim()+"','"+  talla.trim()  + "');";
        System.out.println(sql);
        return sql;
    }

    @Override
    public String read() {
        return  "SELECT * FROM inventario_ropa;";
    }

    @Override
    public String update() {
        return null;
    }

    @Override
    public String delete() {
        return "DELETE FROM public.inventario_ropa\n" +
                "\tWHERE inventario_ropa.id ="+ id + ";";
    }

    @Override
    public String findById() {
        return "select * from inventario_ropa\n" +
                "\tWHERE id =" + id +";";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getRecomendaciones() {
        return recomendaciónes;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciónes = recomendaciones;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Inventario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", recomendaciones='" + recomendaciónes + '\'' +
                ", talla='" + talla + '\'' +
                '}';
    }
}
