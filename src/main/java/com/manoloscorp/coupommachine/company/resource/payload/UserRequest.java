package com.manoloscorp.coupommachine.company.resource.payload;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserRequest {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String cnpj;

    @NotNull
    @NotEmpty
    private Integer fone;

    @NotNull
    @NotEmpty
    private String location;

    @NotNull
    @NotEmpty
    private String typeBranch;

    @NotNull
    @NotEmpty
    private Double credit;
}