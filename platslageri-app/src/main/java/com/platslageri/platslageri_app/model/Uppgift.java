package com.platslageri.platslageri_app.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Uppgift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titel;
    private String namn;

    @Column(length = 1000)
    private String beskrivning;

    private String adress;
    private String telefon;
    private boolean utford;
    private LocalDateTime skapad;
    private java.time.LocalDate deadline;
    private String prioritet;
    private String referens;

    @OneToMany(mappedBy = "uppgift", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UppgiftBild> bilder = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        skapad = LocalDateTime.now();
    }

    public Uppgift() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitel() { return titel; }
    public void setTitel(String titel) { this.titel = titel; }

    public String getNamn() { return namn; }
    public void setNamn(String namn) { this.namn = namn; }

    public String getBeskrivning() { return beskrivning; }
    public void setBeskrivning(String beskrivning) { this.beskrivning = beskrivning; }

    public String getAdress() { return adress; }
    public void setAdress(String adress) { this.adress = adress; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }

    public boolean isUtford() { return utford; }
    public void setUtford(boolean utford) { this.utford = utford; }

    public LocalDateTime getSkapad() { return skapad; }
    public void setSkapad(LocalDateTime skapad) { this.skapad = skapad; }

    public java.time.LocalDate getDeadline() { return deadline; }
    public void setDeadline(java.time.LocalDate deadline) { this.deadline = deadline; }

    public String getPrioritet() { return prioritet; }
    public void setPrioritet(String prioritet) { this.prioritet = prioritet; }

    public String getReferens() { return referens; }
    public void setReferens(String referens) { this.referens = referens; }

    public List<UppgiftBild> getBilder() { return bilder; }
    public void setBilder(List<UppgiftBild> bilder) { this.bilder = bilder; }

    public int getBildCount() { return bilder == null ? 0 : bilder.size(); }
}
