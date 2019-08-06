package com.tabeldata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KegiatanEntity {

    private Integer id;
    private Integer idSkpd;
    private Integer idProgram;
    private String tahunAnggaran;
    private String kodeKegiatan;
    private String namaKegiatan;
    private BigDecimal anggaranTapd;
    private BigDecimal anggaranDpa;
    private String kodeLokasiKegiatan;
    private Integer idPenggunaRekam;
    private Timestamp tanggalPenggunaRekam;
    private Integer idPenggunaUbah;
    private Timestamp tanggalPenggunaUbah;
    private String namaSumberDana;
    private String sasaranKegiatan;
}
