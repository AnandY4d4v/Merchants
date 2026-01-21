package com.vizpay.in.merchants.service;

import com.vizpay.in.merchants.dto.AddMerchantRequestDto;
import com.vizpay.in.merchants.dto.MerchantDto;

import java.util.List;
import java.util.Map;

public interface MerchantService {
    List<MerchantDto> getAllMerchants();
    MerchantDto getMerchantById(Long id);
    MerchantDto createMerchant(AddMerchantRequestDto addMerchantRequestDto);

    void deleteMerchantById(Long id);

    MerchantDto updateMerchant(Long id, AddMerchantRequestDto addMerchantRequestDto);

    MerchantDto updatePartialMerchant(Long id, Map<String, Object> updates);
}
