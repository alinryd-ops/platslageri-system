package com.platslageri.platslageri_app.repository;

import com.platslageri.platslageri_app.model.Jobb;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobbRepository extends JpaRepository<Jobb, Long> {
    // Lägg till en metod för att hitta alla jobb som inte är fakturerade
    List<Jobb> findByFaktureradFalse();
}