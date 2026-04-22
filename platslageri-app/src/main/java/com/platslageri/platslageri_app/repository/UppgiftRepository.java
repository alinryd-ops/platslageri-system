package com.platslageri.platslageri_app.repository;

import com.platslageri.platslageri_app.model.Uppgift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UppgiftRepository extends JpaRepository<Uppgift, Long> {}
