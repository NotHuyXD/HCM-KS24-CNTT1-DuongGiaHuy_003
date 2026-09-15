package org.example.medicineservice.client;

import org.example.medicineservice.dto.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="category-service")
public interface CategoryClient {
    @GetMapping("/api/categories/{id}")
    ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long id);
}
