package com.vizpay.in.merchants.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantDto {
    private Long id;

    private String merchantName;
    private String email;
    private boolean status;
}
