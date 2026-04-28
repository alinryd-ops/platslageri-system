package com.platslageri.platslageri_app.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jobb_id", nullable = false)
    @JsonIgnore
    private Jobb jobb;

    private String beskrivning;

    public Material() {}

    public Long getId() { return id; }

    public Jobb getJobb() { return jobb; }
    public void setJobb(Jobb jobb) { this.jobb = jobb; }

    public String getBeskrivning() { return beskrivning; }
    public void setBeskrivning(String beskrivning) { this.beskrivning = beskrivning; }
}
