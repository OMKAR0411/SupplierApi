package com.example.supplier.Service;

import com.example.supplier.Dao.SupplierRepository;
import com.example.supplier.Enitiy.Supplier;
import com.example.supplier.Service.InputSanitizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier save(Supplier supplier) {
        // Validate and sanitize input
        validateSupplier(supplier);
        sanitizeSupplier(supplier);
        return supplierRepository.save(supplier);
    }

    public List<Supplier> findByCriteria(String location, String natureOfBusiness, String manufacturingProcesses) {
        // Validate input parameters
        validateCriteria(location, natureOfBusiness, manufacturingProcesses);
        // Sanitize input parameters
        location = InputSanitizer.sanitize(location);
        natureOfBusiness = InputSanitizer.sanitize(natureOfBusiness);
        manufacturingProcesses = InputSanitizer.sanitize(manufacturingProcesses);

        return supplierRepository.findByLocationAndNatureOfBusinessAndManufacturingProcesses(
                location, natureOfBusiness, manufacturingProcesses);
    }

    private void validateSupplier(Supplier supplier) {
        if (supplier == null) {
            throw new IllegalArgumentException("Supplier must not be null");
        }
        if (StringUtils.isEmpty(supplier.getCompanyName()) ||
                StringUtils.isEmpty(supplier.getLocation()) ||
                StringUtils.isEmpty(supplier.getNatureOfBusiness())) {
            throw new IllegalArgumentException("Company name, location, and nature of business must not be empty");
        }
    }

    private void sanitizeSupplier(Supplier supplier) {
        supplier.setCompanyName(InputSanitizer.sanitize(supplier.getCompanyName()));
        supplier.setWebsite(InputSanitizer.sanitize(supplier.getWebsite()));
        supplier.setLocation(InputSanitizer.sanitize(supplier.getLocation()));
        supplier.setNatureOfBusiness(InputSanitizer.sanitize(supplier.getNatureOfBusiness()));
        supplier.setManufacturingProcesses(InputSanitizer.sanitize(supplier.getManufacturingProcesses()));
    }

    private void validateCriteria(String location, String natureOfBusiness, String manufacturingProcesses) {
        if (StringUtils.isEmpty(location) || StringUtils.isEmpty(natureOfBusiness)) {
            throw new IllegalArgumentException("Location and nature of business must not be empty");
        }
    }
}

