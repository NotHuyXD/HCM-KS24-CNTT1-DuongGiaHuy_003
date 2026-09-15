package org.example.medicineservice.repository;

import org.example.medicineservice.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MedicineRepository extends JpaRepository<Medicine,Long> {
}
