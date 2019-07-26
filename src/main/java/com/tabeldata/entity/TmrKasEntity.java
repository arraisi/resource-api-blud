package com.tabeldata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TmrKasEntity {

    private Integer idTmrbakasBlud;
    private Integer iidSkpd;
    private String canggTahun; //Char
    private String cskpd;
    private String nskpd;
    private Integer iid;
    private String cakun;
    private String nakun;
    private BigDecimal vkas;
    private BigDecimal vkasAudited;
}
