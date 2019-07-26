package com.tabeldata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TrrbaNoMax {
        private String nTabel; //Interger

        private Integer iIdMax;

        private String dEntry; //Char


}
