package com.vizpay.in.merchants.service.impl;

import com.vizpay.in.merchants.dto.MerchantDto;
import com.vizpay.in.merchants.dto.AddMerchantRequestDto;
import com.vizpay.in.merchants.entity.Merchant;
import com.vizpay.in.merchants.repository.MerchantRepository;
import com.vizpay.in.merchants.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;
    private final ModelMapper modelMapper;



    @Override
    public List<MerchantDto> getAllMerchants() {
        List<Merchant> merchants = merchantRepository.findAll();
        return merchants
                .stream()
                .map(merchant -> new MerchantDto(merchant.getId(),merchant.getMerchantName(),merchant.getEmail(),merchant.isStatus()))
                .toList();
    }

    @Override
    public MerchantDto getMerchantById(Long id) {
        Merchant merchant = merchantRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with ID: "+id));
        return modelMapper.map(merchant, MerchantDto.class);
    }
    @Override
    public MerchantDto createMerchant(AddMerchantRequestDto addMerchantRequestDto) {
        Merchant merchant = modelMapper.map(addMerchantRequestDto, Merchant.class);
        Merchant savedMerchant = merchantRepository.save(merchant);
        return modelMapper.map(savedMerchant, MerchantDto.class);
    }

    // Dele
    @Override
    public void deleteMerchantById(Long id) {
        if (!merchantRepository.existsById(id)) {
            throw new IllegalArgumentException("Merchant does not exist with ID: " + id);
        }
        merchantRepository.deleteById(id);
    }

    // Updt
    @Override
    public MerchantDto updateMerchant(Long id, AddMerchantRequestDto addMerchantRequestDto) {
        Merchant merchant = merchantRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Merchant not found with ID: " + id));

        modelMapper.map(addMerchantRequestDto, merchant);
        Merchant updatedMerchant = merchantRepository.save(merchant);

        return modelMapper.map(updatedMerchant, MerchantDto.class);
    }

    // Patch
    @Override
    public MerchantDto updatePartialMerchant(Long id, Map<String, Object> updates) {
        Merchant merchant = merchantRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Merchant not found with ID: " + id));

        updates.forEach((field, value) -> {
            switch (field) {
                case "merchantName":
                    merchant.setMerchantName((String) value);
                    break;
                case "email":
                    merchant.setEmail((String) value);
                    break;
                case "status":
                    merchant.setStatus((Boolean) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field not supported: " + field);
            }
        });

        Merchant savedMerchant = merchantRepository.save(merchant);
        return modelMapper.map(savedMerchant, MerchantDto.class);
    }

}
