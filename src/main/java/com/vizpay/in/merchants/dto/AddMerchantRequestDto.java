package com.vizpay.in.merchants.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddMerchantRequestDto {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 30, message = "Name should be of length 3 to 30 characters")
    private String merchantName;
    @Email
    @NotBlank(message = "Email is Required")
    private String email;
    private boolean status;
}
