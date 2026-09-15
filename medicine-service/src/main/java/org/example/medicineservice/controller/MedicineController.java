package org.example.medicineservice.controller;

import feign.FeignException;
import org.example.medicineservice.client.CategoryClient;
import org.example.medicineservice.entity.Medicine;
import org.example.medicineservice.repository.MedicineRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {
    private final MedicineRepository medicineRepository;
    private final CategoryClient categoryClient;

    public MedicineController(MedicineRepository medicineRepository, CategoryClient categoryClient) {
        this.medicineRepository = medicineRepository;
        this.categoryClient = categoryClient;
    }

    @GetMapping
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        return ResponseEntity.status(200).body(medicineRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createMedicine(@RequestBody Medicine medicine) {
        try{
            categoryClient.getCategoryById(medicine.getCategoryId());
            Medicine savedMedicine = medicineRepository.save(medicine);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMedicine);
        } catch (FeignException.NotFound e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
    }
}
