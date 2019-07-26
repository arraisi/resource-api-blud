package com.tabeldata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KasEntity {
    // default model entity
    private Integer iId; //Interger

    private Integer iIdSkpd;

    private String cAnggTahun; //Char

    private Integer iIdBas;

    private BigDecimal vkas; // BigDecimal

    private BigDecimal vkasAudited;

    private Integer iPgunRekan;

    private Date dPgunRekam;

    private Integer iPgunUbah;

    private Date dPgunUbah;


}
