package com.platslageri.platslageri_app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Uppgift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titel;

    @Column(length = 1000)
    private String beskrivning;

    private String adress;
    private String telefon;
    private boolean utford;
    private LocalDateTime skapad;

    @PrePersist
    protected void onCreate() {
        skapad = LocalDateTime.now();
    }

    public Uppgift() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitel() { return titel; }
    public void setTitel(String titel) { this.titel = titel; }

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
}
