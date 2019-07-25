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
    private Integer I_ID; //Interger

    private Integer I_IDSKPD;

    private String C_ANGG_TAHUN; //Char

    private Integer I_IDBAS;

    private BigDecimal V_KAS; // BigDecimal

    private BigDecimal V_KAS_AUDITED;

    private Integer I_PGUN_REKAM;

    private Date D_PGUN_REKAM;

    private Integer I_PGUN_UBAH;

    private Date D_PGUN_UBAH;


}
