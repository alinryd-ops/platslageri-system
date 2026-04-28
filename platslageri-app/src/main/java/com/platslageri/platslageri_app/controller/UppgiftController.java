package com.platslageri.platslageri_app.controller;

import com.platslageri.platslageri_app.exception.NotFoundException;
import com.platslageri.platslageri_app.exception.ValidationException;
import com.platslageri.platslageri_app.model.Uppgift;
import com.platslageri.platslageri_app.model.UppgiftBild;
import com.platslageri.platslageri_app.repository.UppgiftRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/uppgifter")
public class UppgiftController {

    private final UppgiftRepository uppgiftRepository;

    public UppgiftController(UppgiftRepository uppgiftRepository) {
        this.uppgiftRepository = uppgiftRepository;
    }

    @GetMapping
    public List<Uppgift> getAllUppgifter() {
        return uppgiftRepository.findAll();
    }

    @PostMapping
    public Uppgift skapaUppgift(@RequestBody Uppgift uppgift) {
        if (uppgift.getTitel() == null || uppgift.getTitel().isBlank()) {
            throw new ValidationException("Titel måste anges");
        }
        return uppgiftRepository.save(uppgift);
    }

    @DeleteMapping("/{id}")
    public void taBortUppgift(@PathVariable Long id) {
        if (!uppgiftRepository.existsById(id)) {
            throw new NotFoundException("Uppgift med ID " + id + " hittades inte");
        }
        uppgiftRepository.deleteById(id);
    }

    @PostMapping("/{id}/bilder")
    public void laggTillBild(@PathVariable Long id, @RequestBody String data) {
        Uppgift uppgift = uppgiftRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Uppgift med ID " + id + " hittades inte"));
        UppgiftBild bild = new UppgiftBild();
        bild.setData(data);
        bild.setUppgift(uppgift);
        uppgift.getBilder().add(bild);
        uppgiftRepository.save(uppgift);
    }

    @GetMapping("/{id}/bilder")
    public List<UppgiftBild> getBilder(@PathVariable Long id) {
        Uppgift uppgift = uppgiftRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Uppgift med ID " + id + " hittades inte"));
        return uppgift.getBilder();
    }

    @PutMapping("/{id}")
    public Uppgift uppdateraUppgift(@PathVariable Long id, @RequestBody Uppgift uppdaterad) {
        Uppgift uppgift = uppgiftRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Uppgift med ID " + id + " hittades inte"));
        uppgift.setTitel(uppdaterad.getTitel());
        uppgift.setNamn(uppdaterad.getNamn());
        uppgift.setBeskrivning(uppdaterad.getBeskrivning());
        uppgift.setAdress(uppdaterad.getAdress());
        uppgift.setTelefon(uppdaterad.getTelefon());
        uppgift.setDeadline(uppdaterad.getDeadline());
        uppgift.setPrioritet(uppdaterad.getPrioritet());
        uppgift.setReferens(uppdaterad.getReferens());
        return uppgiftRepository.save(uppgift);
    }

    @PatchMapping("/{id}/utford")
    public Uppgift toggleUtford(@PathVariable Long id) {
        Uppgift uppgift = uppgiftRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Uppgift med ID " + id + " hittades inte"));
        uppgift.setUtford(!uppgift.isUtford());
        return uppgiftRepository.save(uppgift);
    }
}
