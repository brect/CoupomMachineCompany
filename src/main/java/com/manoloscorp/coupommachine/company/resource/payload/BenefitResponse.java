package com.manoloscorp.coupommachine.company.resource.payload;

import com.manoloscorp.coupommachine.company.entity.User;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BenefitResponse {

    @NotNull
    @NotEmpty
    private String productService;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    @NotEmpty
    private double value;

    @NotNull
    @NotEmpty
    private double discount;

    @NotNull
    @NotEmpty
    private double budget;

    @NotNull
    @NotEmpty
    private User userId;
}