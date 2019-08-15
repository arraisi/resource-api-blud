package com.tabeldata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KinerjaSaveDto {
    private Integer id;
    private String tahunAnggaran;
    private Integer idSkpd;
    private Integer idKegiatan;
    private String kodeIndikator;
    private Integer noUrut;
    private String keteranganTolakUkur;
    private String keteranganTargetKinerja;
    private Integer idPenggunaRekam;
    private Timestamp tanggalPenggunaRekam;
    private Integer idPenggunaUbah;
    private Timestamp tanggalPenggunaUbah;
}
