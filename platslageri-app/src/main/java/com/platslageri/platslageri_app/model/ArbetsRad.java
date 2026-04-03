package com.platslageri.platslageri_app.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ArbetsRad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String beskrivning;

    @ManyToOne
    @JoinColumn(name = "jobb_id")
    @JsonIgnore
    private Jobb jobb;

    public ArbetsRad() {}

    public Long getId() {
        return id;
    }

    public String getBeskrivning() {
        return beskrivning;
    }

    public void setBeskrivning(String beskrivning) {
        this.beskrivning = beskrivning;
    }

    public Jobb getJobb() {
        return jobb;
    }

    public void setJobb(Jobb jobb) {
        this.jobb = jobb;
    }
}
