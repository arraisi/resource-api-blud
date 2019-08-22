package com.tabeldata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KomponenBelanjaGetDto {

    private Integer id;
    private Integer idKegiatan;
    private String tahunAnggaran;
    private Integer idSkpd;
    private String kodeKegiatan;
    private Integer idBas;
    private Integer idBasKomponen;
    private Integer idAnggaranNoUrut;
    private String entryAnggaranRinci;
    private String entryAnggaranSpesifikasi;
    private String kodeKomponen;
    private String namaKomponen;
    private String merk;
    private String spek;
    private BigDecimal komponenHarga;
    private Integer pajak;
    private Integer volume;
    private String koefisien;
    private Integer volume1;
    private String satuan1;
    private Integer volume2;
    private String satuan2;
    private Integer volume3;
    private String satuan3;
    private Integer volume4;
    private String satuan4;
    private String rmksSubrincian;
    private String rmks;
    private String swakelola;
    private BigDecimal anggaranDpa;
    private BigDecimal anggaranTapd;
    private Integer idPenggunaRekam;
    private Timestamp tglPenggunaRekam;
    private Integer idPenggunaUbah;
    private Timestamp tglPenggunaUbah;
    private BigDecimal rpaBulan1;
    private BigDecimal rpaBulan2;
    private BigDecimal rpaBulan3;
    private BigDecimal rpaBulan4;
    private BigDecimal rpaBulan5;
    private BigDecimal rpaBulan6;
    private BigDecimal rpaBulan7;
    private BigDecimal rpaBulan8;
    private BigDecimal rpaBulan9;
    private BigDecimal rpaBulan10;
    private BigDecimal rpaBulan11;
    private BigDecimal rpaBulan12;
    private String kodeAkun;
    private String namaAkun;
}
