package com.positive.culture.seoulQuest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DisplayDTO {
    private Long cino;  // Cart Item ID

    // Product information
    private String pname;
    private int pprice;
    private int pqty;
    private String pimageFile;

    // Tour information
    private String tname;
    private int tprice;
    private int tqty;
    private String timageFile;
    private LocalDate tDate;
}

