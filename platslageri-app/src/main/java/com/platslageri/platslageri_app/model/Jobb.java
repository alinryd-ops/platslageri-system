package com.platslageri.platslageri_app.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Representerar ett jobb/projekt
 */
@Entity
public class Jobb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ========================
    // GRUNDINFO
    // ========================
    private String kund;
    private String projektBeskrivning;
    private String littra;

    @Enumerated(EnumType.STRING)
    private KundTyp kundTyp; // behåll om du vill kategorisera kund

    // ========================
    // TID
    // ========================
    private int timmar;
    private int minuter;

    // ========================
    // MATERIAL
    // ========================
    @OneToMany(mappedBy = "jobb", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Material> materialLista = new ArrayList<>();

    // ========================
    // STATUS
    // ========================
    private boolean fakturerad;

    // ========================
    // LITTRA/MÄRKNING
    // ========================
    private String littraMarkning;

    public Jobb() {}

    // ========================
    // LÄGG TILL DATUM FÖR JOBB
    // ========================  
    private LocalDate datum;  

    // ========================
    // GETTERS & SETTERS
    // ========================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getKund() { return kund; }
    public void setKund(String kund) { this.kund = kund; }

    public String getProjektBeskrivning() {
        return projektBeskrivning;
    }

    public void setProjektBeskrivning(String projektBeskrivning) {
        this.projektBeskrivning = projektBeskrivning;
    }

    public String getLittra() { return littra; }
    public void setLittra(String littra) { this.littra = littra; }

    public KundTyp getKundTyp() { return kundTyp; }
    public void setKundTyp(KundTyp kundTyp) { this.kundTyp = kundTyp; }

    public int getTimmar() { return timmar; }
    public void setTimmar(int timmar) { this.timmar = timmar; }

    public int getMinuter() { return minuter; }
    public void setMinuter(int minuter) { this.minuter = minuter; }

    public List<Material> getMaterialLista() { return materialLista; }
    public void setMaterialLista(List<Material> materialLista) { this.materialLista = materialLista; }

    public boolean isFakturerad() { return fakturerad; }
    public void setFakturerad(boolean fakturerad) { this.fakturerad = fakturerad; }

    public String getLittraMarkning() { return littraMarkning; }
    public void setLittraMarkning(String littraMarkning) { this.littraMarkning = littraMarkning; }

    public String getAnteckning() {
        return anteckning;
    }

    public void setAnteckning(String anteckning) {
        this.anteckning = anteckning;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public List<ArbetsRad> getArbetsrader() {
    return arbetsrader;
    }

    public void setArbetsrader(List<ArbetsRad> arbetsrader) {
        this.arbetsrader = arbetsrader;
    }

    // ========================
    // RELATIONSHANTERING
    // ========================
    public void addMaterial(Material material) {
        material.setJobb(this);   // SÄTT RELATION FÖRST
        materialLista.add(material);
    }

    public void removeMaterial(Long materialId) {
        materialLista.removeIf(material -> material.getId().equals(materialId));
    }

    @OneToMany(mappedBy = "jobb", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<ArbetsRad> arbetsrader = new ArrayList<>();
        
        public void addArbetsRad(ArbetsRad rad) {
        arbetsrader.add(rad);
        rad.setJobb(this);
    }

    public void removeArbetsRad(Long arbetsRadId) {
        arbetsrader.removeIf(rad -> rad.getId().equals(arbetsRadId));
    }



    // ========================
    // ANTECKNING FÖR SPECIAL BESKRVININGAR
    // ========================

    @Column(length = 1000)
    private String anteckning;
}