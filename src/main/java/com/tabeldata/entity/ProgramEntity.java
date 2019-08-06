package com.tabeldata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramEntity {
    private Integer id;
    private Integer idUrusan;
    private String kodeProgram;
    private String namaProgram;
    private String aktif;
    private Integer idPenggunaRekam;
    private Timestamp tanggalPenggunaRekam;
    private Integer idPenggunaUbah;
    private Timestamp tanggalPenggunaUbah;
    private String tahunBerlaku;
    private String tahunBerakhir;
}
