package com.tabeldata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TmrKasEntity {

    private Integer ID_TMRBAKASBLUD;
    private String C_ANGG_TAHUN;
    private Integer I_IDSKPD;
    private String C_SKPD;
    private String N_SKPD;
    private Integer I_ID;
    private String C_AKUN;
    private String N_AKUN;
    private BigDecimal V_KAS;
    private BigDecimal V_KAS_AUDITED;

}
