package com.example.supplier.Controller;

import com.example.supplier.Enitiy.Supplier;
import com.example.supplier.Service.InputSanitizer;
import com.example.supplier.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/api/supplier")
@Validated
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<Supplier> create(@Valid @RequestBody Supplier supplier) {
        sanitizeSupplier(supplier);
        Supplier savedSupplier = supplierService.save(supplier);
        return new ResponseEntity<>(savedSupplier, HttpStatus.CREATED);
    }

    @GetMapping("/{location}/{natureOfBusiness}/{manufacturingProcesses}")
    public ResponseEntity<List<Supplier>> search(
            @PathVariable @NotBlank(message = "Location must not be blank") String location,
            @PathVariable @NotBlank(message = "Nature of business must not be blank") String natureOfBusiness,
            @PathVariable String manufacturingProcesses) {

        // Sanitize inputs
        location = InputSanitizer.sanitize(location);
        natureOfBusiness = InputSanitizer.sanitize(natureOfBusiness);
        manufacturingProcesses = InputSanitizer.sanitize(manufacturingProcesses);

        // Perform search and return results
        List<Supplier> suppliers = supplierService.findByCriteria(location, natureOfBusiness, manufacturingProcesses);
        if (suppliers.isEmpty()) {
            throw new RuntimeException("No suppliers found for the provided criteria.");
        }
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    private void sanitizeSupplier(Supplier supplier) {
        supplier.setCompanyName(InputSanitizer.sanitize(supplier.getCompanyName()));
        supplier.setWebsite(InputSanitizer.sanitize(supplier.getWebsite()));
        supplier.setLocation(InputSanitizer.sanitize(supplier.getLocation()));
        supplier.setNatureOfBusiness(InputSanitizer.sanitize(supplier.getNatureOfBusiness()));
        supplier.setManufacturingProcesses(InputSanitizer.sanitize(supplier.getManufacturingProcesses()));
    }
}
