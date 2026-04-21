package com.platslageri.platslageri_app.controller;

import com.platslageri.platslageri_app.model.ArbetsRad;
import com.platslageri.platslageri_app.model.Jobb;
import com.platslageri.platslageri_app.model.Material;
import com.platslageri.platslageri_app.model.MaterialTyp;
import com.platslageri.platslageri_app.repository.JobbRepository;

import org.springframework.web.bind.annotation.*;
import com.platslageri.platslageri_app.exception.NotFoundException;
import com.platslageri.platslageri_app.exception.ValidationException;

import java.util.List;

/**
 * Controller för att hantera jobb (logg/anteckningar)
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/jobb")
public class JobbController {

    private final JobbRepository jobbRepository;

    public JobbController(JobbRepository jobbRepository) {
        this.jobbRepository = jobbRepository;
    }

    /**
     * TEST endpoint
     */
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }



    /**
     * Hämta alla jobb
     */

    @GetMapping
    public List<Jobb> getAllJobb() {
        return jobbRepository.findAll();
    }

    /**
     * Hämta jobb via ID
     */
    @GetMapping("/id/{jobbId}")
    public Jobb getJobbById(@PathVariable Long jobbId) {
        return jobbRepository.findById(jobbId)
                .orElseThrow(() -> new NotFoundException("Jobb med ID " + jobbId + " hittades inte"));
    }

    /**
     * Skapa nytt jobb
     */
    @PostMapping
    public Jobb skapaJobb(@RequestBody Jobb jobb) {

        if (jobb.getKund() == null || jobb.getKund().isEmpty()) {
            throw new ValidationException("Kund måste anges");
        }

        if (jobb.getProjektBeskrivning() == null || jobb.getProjektBeskrivning().isEmpty()) {
            throw new ValidationException("Beskrivning måste anges");
        }

        return jobbRepository.save(jobb);
    }

    /**
     * Uppdatera jobb
     */
    @PutMapping("/{jobbId}")
    public Jobb uppdateraJobb(
            @PathVariable Long jobbId,
            @RequestBody Jobb uppdateradJobb) {

        Jobb jobb = jobbRepository.findById(jobbId)
                .orElseThrow(() -> new NotFoundException("Jobb med ID " + jobbId + " hittades inte"));

        jobb.setKund(uppdateradJobb.getKund());
        jobb.setProjektBeskrivning(uppdateradJobb.getProjektBeskrivning());
        jobb.setLittra(uppdateradJobb.getLittra());
        jobb.setKundTyp(uppdateradJobb.getKundTyp());
        jobb.setTimmar(uppdateradJobb.getTimmar());
        jobb.setMinuter(uppdateradJobb.getMinuter());
        jobb.setFakturerad(uppdateradJobb.isFakturerad());
        jobb.setLittraMarkning(uppdateradJobb.getLittraMarkning());

        return jobbRepository.save(jobb);
    }

    /**
     * Ta bort jobb
     */
    @DeleteMapping("/{jobbId}")
    public void taBortJobb(@PathVariable Long jobbId) {
        jobbRepository.deleteById(jobbId);
    }

    /**
     * Lägg till material
     */
    @PostMapping("/{jobbId}/material")
    public Jobb addMaterial(
            @PathVariable Long jobbId,
            @RequestBody Material material) {

        Jobb jobb = jobbRepository.findById(jobbId)
                .orElseThrow(() -> new NotFoundException("Jobb med ID " + jobbId + " hittades inte"));

        jobb.addMaterial(material);

        return jobbRepository.save(jobb);
    }

    /**
     * Hämta materialtyper för frontend
     */

    @GetMapping("/material-typer")
    public MaterialTyp[] getMaterialTyper() {
        return MaterialTyp.values();
    }

    /**
     * Ta bort material
     */
    @DeleteMapping("/{jobbId}/material/{materialId}")
    public Jobb deleteMaterial(
            @PathVariable Long jobbId,
            @PathVariable Long materialId) {

        Jobb jobb = jobbRepository.findById(jobbId)
                .orElseThrow(() -> new NotFoundException("Jobb med ID " + jobbId + " hittades inte"));

        boolean exists = jobb.getMaterialLista().stream()
                .anyMatch(material -> material.getId().equals(materialId));

        if (!exists) {
            throw new NotFoundException("Material med ID " + materialId + " hittades inte");
        }

        jobb.removeMaterial(materialId);

        return jobbRepository.save(jobb);
    }

    /**
     * Lägg till arbetsrad
     */
    @PostMapping("/{jobbId}/arbetsrad")
    public Jobb addArbetsRad(
            @PathVariable Long jobbId,
            @RequestBody ArbetsRad rad) {

        Jobb jobb = jobbRepository.findById(jobbId)
                .orElseThrow(() -> new NotFoundException("Jobb med ID " + jobbId + " hittades inte"));

        jobb.addArbetsRad(rad);

        return jobbRepository.save(jobb);
    }

    /**
     * Ta bort arbetsrad
     */
    @DeleteMapping("/{jobbId}/arbetsrad/{radId}")
    public Jobb deleteArbetsRad(
            @PathVariable Long jobbId,
            @PathVariable Long radId) {

        Jobb jobb = jobbRepository.findById(jobbId)
                .orElseThrow(() -> new NotFoundException("Jobb med ID " + jobbId + " hittades inte"));

        boolean exists = jobb.getArbetsrader().stream()
                .anyMatch(rad -> rad.getId().equals(radId));

        if (!exists) {
            throw new NotFoundException("Arbetsrad med ID " + radId + " hittades inte");
        }

        jobb.removeArbetsRad(radId);

        return jobbRepository.save(jobb);
    }

    /**
     * Hämta ej fakturerade jobb
     */
    @GetMapping("/ej-fakturerade")
    public List<Jobb> getEjFaktureradeJobb() {
        return jobbRepository.findByFaktureradFalse();
    }

    @PostMapping("/test-material")
    public String testMaterial() {
        return "MATERIAL FUNKAR";
    }
}