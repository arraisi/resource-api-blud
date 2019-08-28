package com.tabeldata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadKegiatanDatatableDto {

    private Integer idKegiatan;
    private String tahunAnggaran;
    private Integer idSkpd;
    private String kodeSkpd;
    private String namaSkpd;
    private Integer idUrusan;
    private String kodeUrusan;
    private String namaUrusan;
    private Integer idProgram;
    private String kodeProgram;
    private String namaProgram;
    private String kodeKegiatan;
    private String namaKegiatan;
    private BigDecimal anggaranDpa;
    private BigDecimal anggaranTapd;
    private String statusPersetujuan;
    private String statusName;
    private String statusBadgeColor;
    private String catatanPersetujuan;
}
