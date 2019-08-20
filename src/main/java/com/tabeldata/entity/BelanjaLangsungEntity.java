package com.tabeldata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BelanjaLangsungEntity {

    private Integer id;
    private Integer idKegiatan;
    private String tahunAnggaran;
    private  Integer idSkpd;
    private Integer idBiaya;
    private BigDecimal anggaranDpaBp;
    private BigDecimal anggaranDpaBbj;
    private BigDecimal anggaranDpaBm;
    private BigDecimal anggaranTapdBp;
    private BigDecimal anggaranTapdBbj;
    private BigDecimal anggaranTapdBm;
    private Integer idPenggunaRekam;
    private Timestamp tglPenggunaRekam;
    private Integer idPenggunaUbah;
    private Timestamp tglPenggunaUbah;

}
