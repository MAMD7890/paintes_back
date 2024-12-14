package com.example.back.Dtos;

import java.util.List;

public class FormulaWithArrayDTO {
    private Integer producto;
    private String nameProducto;
    List<IngredientDTO> ingredientes;
    private String urlImagen;

    public Integer getProducto() {
        return producto;
    }

    public void setProducto(Integer producto) {
        this.producto = producto;
    }

    public String getNameProducto() {
        return nameProducto;
    }

    public void setNameProducto(String nameProducto) {
        this.nameProducto = nameProducto;
    }

    public List<IngredientDTO> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredientDTO> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
