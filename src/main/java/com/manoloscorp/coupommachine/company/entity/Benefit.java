package com.manoloscorp.coupommachine.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TB_BENEFIT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "Benefit", sequenceName = "SQ_TB_BENEFIT", allocationSize = 1)
public class Benefit {

    @Id
    @Column(name = "id_benefit")
    @GeneratedValue(generator = "benefit", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 100)
    private String productService;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double value;

    @Column(nullable = false)
    private double discount;

    @Column(nullable = false)
    private double budget;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user_legal", nullable = false)
    private User user;

    public void updateBenefit(Benefit request) {
        this.id = request.getId();
        this.productService = request.getProductService();
        this.description = request.getDescription();
        this.value = request.getValue();
        this.discount = request.getDiscount();
        this.budget = request.getBudget();
        this.user = request.getUser();
    }
}
