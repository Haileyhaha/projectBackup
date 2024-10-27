package com.positive.culture.seoulQuest.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tbl_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String categoryName;
    private String categoryType; // product, tour 구분하기 위함

    //setter 사용대신 필드 수정위한 메서드 생성
    public void ChangeCategoryName(String categoryName){
        this.categoryName = categoryName;
    }

    public void ChangeCategoryType(String categoryType){
        this.categoryType = categoryType;
    }
}
