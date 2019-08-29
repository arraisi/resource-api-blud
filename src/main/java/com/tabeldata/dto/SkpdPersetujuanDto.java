package com.tabeldata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkpdPersetujuanDto {
    private Integer id;
    private Integer idInduk;
    private String kodeSkpd;
    private String kodeUnitKerja;
    private String namaSkpd;
    private String namaSingkatSkpd;
    private String blud; // 0 = skpd | 1 = blud
    private String statusAktif; // 0 = Tidak Aktif | 1 = Aktif
    private String tahunBerlaku;
    private String tahunBerakhir;
    private Integer idRekamPengguna;
    private Timestamp tanggalRekamPengguna;
    private Integer idUbahPengguna;
    private Timestamp tanggalUbahPengguna;
    private BigDecimal totalAnggaran;
    private String statusDinasId;
    private String statusDinasName;
    private String statusDinasBadge;
}
