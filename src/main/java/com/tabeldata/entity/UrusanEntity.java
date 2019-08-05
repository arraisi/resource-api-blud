package com.tabeldata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrusanEntity {
    private Integer id;
    private Integer idFungsi;
    private Integer wajib;
    private String urusan;
    private String kodeUrusan;
    private String namaUrusan;
    private String aktif;
    private Integer idPenggunaRekam;
    private Timestamp tanggalPenggunaRekam;
    private Integer idPenggunaUbah;
    private Timestamp tanggalPenggunaUbah;
    private String tahunBerlaku;
    private String tahunBerakhir;
}
