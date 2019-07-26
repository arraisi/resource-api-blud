package com.tabeldata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadDpt {
    private Integer idTrx;
    private String tahunAnggaran;
    private Integer idSkpd;
    private String skpd;
    private String namaSkpd;
    private Integer idBas;
    private String kodeAkun;
    private String namaAkun;
    private Double anggaranDpa;
    private Double anggaranTapd;
}
