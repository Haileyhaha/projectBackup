package com.positive.culture.seoulQuest.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tbl_cart",
        indexes= {@Index(name = "idx_cart_memberId", columnList = "member_owner")})
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;

    @OneToOne
    @JoinColumn(name = "member_owner")
    private Member owner;

    private String status; //카트 상태 (pending, active, complete)
}
