package com.manzanofp.mznmeta.service;

import com.manzanofp.mznmeta.model.Sale;
import com.manzanofp.mznmeta.repository.SaleRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    public Page<Sale> findSales(String minDate, String maxDate, Pageable pageble){
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalDate min = minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);
        LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
        return saleRepository.findSales(min, max, pageble);
    }

}
