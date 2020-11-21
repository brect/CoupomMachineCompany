package com.manoloscorp.coupommachine.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_USER_LEGAL")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="user_legal",sequenceName = "SQ_TB_USER_LEGAL",allocationSize = 1)
public class User{

    @Id
    @Column(name="id_user_legal")
    @GeneratedValue(generator = "user_legal", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="nm_name", nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(name="pw_password", nullable = false)
    private String password;

    @Column(nullable = false, length = 100)
    private String cnpj;

    @Column(nullable = false)
    private Integer fone;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String typeBranch;

    @Column(nullable = true)
    private Double credit;

    @OneToMany
    @JoinColumn(name = "fk_id_benefit")
    private List<Benefit> benefits;
}
