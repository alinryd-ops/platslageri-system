package com.platslageri.platslageri_app.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UppgiftBild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uppgift_id", nullable = false)
    @JsonIgnore
    private Uppgift uppgift;

    @Column(columnDefinition = "TEXT")
    private String data;

    public UppgiftBild() {}

    public Long getId() { return id; }

    public Uppgift getUppgift() { return uppgift; }
    public void setUppgift(Uppgift uppgift) { this.uppgift = uppgift; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
