package com.example.simpletodolist.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;
import androidx.room.TypeConverters;

import com.example.simpletodolist.activities.ConversorData;

@TypeConverters(ConversorData.class) // Adiciona o conversor de tipos
@Entity
public class Tarefa {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String titulo;
    private String descricao;
    @ColumnInfo(name = "data")
    private Date data; // Agora suportado pelo Type Converter
    private String hora;
    private String imagem; // Supondo que 'imagem' seja um caminho para a imagem
    private String tipo;
    private String status;
    private String localizacao;

    // Construtor
    public Tarefa(String titulo, String descricao, Date data, String hora, String imagem, String tipo, String status, String localizacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.hora = hora;
        this.imagem = imagem;
        this.tipo = tipo;
        this.status = status;
        this.localizacao = localizacao;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String toString() {
        return "Tarefa{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", hora='" + hora + '\'' +
                ", tipo='" + tipo + '\'' +
                ", status='" + status + '\'' +
                ", localizacao='" + localizacao + '\'' +
                '}';
    }
}