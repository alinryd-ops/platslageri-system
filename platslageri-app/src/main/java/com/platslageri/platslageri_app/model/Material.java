package com.platslageri.platslageri_app.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Representerar material som används i ett jobb.
 */
@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jobb_id", nullable = false)
    @JsonIgnore
    private Jobb jobb;

    @Enumerated(EnumType.STRING)
    private MaterialTyp typ;

    private String kategori;
    private double mangd;
    private String enhet;

    public Material() {}

    public Long getId() {
        return id;
    }

    public Jobb getJobb() {
        return jobb;
    }

    public void setJobb(Jobb jobb) {
        this.jobb = jobb;
    }

    public MaterialTyp getTyp() {
        return typ;
    }

    public void setTyp(MaterialTyp typ) {
        this.typ = typ;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public double getMangd() {
        return mangd;
    }

    public void setMangd(double mangd) {
        this.mangd = mangd;
    }

    public String getEnhet() {
        return enhet;
    }

    public void setEnhet(String enhet) {
        this.enhet = enhet;
    }
}
