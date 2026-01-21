package com.vizpay.in.merchants.controller;

import com.vizpay.in.merchants.dto.AddMerchantRequestDto;
import com.vizpay.in.merchants.dto.MerchantDto;
import com.vizpay.in.merchants.service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/merchants")
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping
    public List<MerchantDto> getMerchants(){
        return merchantService.getAllMerchants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchantDto> getMerchantById(@PathVariable Long id) {
        return ResponseEntity.ok(merchantService.getMerchantById(id));
    }

    @PostMapping
    public ResponseEntity<MerchantDto> createMerchant(
            @RequestBody @Valid AddMerchantRequestDto addMerchantRequestDto) {

        MerchantDto createdMerchant =
                merchantService.createMerchant(addMerchantRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdMerchant);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMerchant(@PathVariable Long id) {
        merchantService.deleteMerchantById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<MerchantDto> updateMerchant(
            @PathVariable Long id,
            @RequestBody @Valid AddMerchantRequestDto addMerchantRequestDto) {

        return ResponseEntity.ok(
                merchantService.updateMerchant(id, addMerchantRequestDto)
        );
    }


    @PatchMapping("/{id}")
    public ResponseEntity<MerchantDto> updatePartialMerchant(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        return ResponseEntity.ok(
                merchantService.updatePartialMerchant(id, updates)
        );
    }
}
