package com.tabeldata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendapatanDptEntity {
    private Integer id; // I_ID
    private Integer idSkpd; // I_IDSKPD
    private Integer idBas; // I_IDBAS
    private String tahunAnggaran; // C_ANGG_TAHUN
    private BigDecimal anggaranDpa; // V_ANGG_DPA
    private BigDecimal anggaranTapd; // V_ANGG_TAPD
    private BigDecimal rpaBulan1; // V_RPA_BULAN1
    private BigDecimal rpaBulan2; // V_RPA_BULAN2
    private BigDecimal rpaBulan3; // V_RPA_BULAN3
    private BigDecimal rpaBulan4; // V_RPA_BULAN4
    private BigDecimal rpaBulan5; // V_RPA_BULAN5
    private BigDecimal rpaBulan6; // V_RPA_BULAN6
    private BigDecimal rpaBulan7; // V_RPA_BULAN7
    private BigDecimal rpaBulan8; // V_RPA_BULAN8
    private BigDecimal rpaBulan9; // V_RPA_BULAN9
    private BigDecimal rpaBulan10; // V_RPA_BULAN10
    private BigDecimal rpaBulan11; // V_RPA_BULAN11
    private BigDecimal rpaBulan12; // V_RPA_BULAN12
    private Integer idRekamPengguna; // I_PGUN_REKAM
    private Timestamp tanggalRekamPengguna; // D_PGUN_REKAM
    private Integer idUbahPengguna; // I_PGUN_UBAN
    private Timestamp tanggalUbahPengguna; // D_PGUN_UBAH
    private String jenis; // C_RPA_JENIS
}
