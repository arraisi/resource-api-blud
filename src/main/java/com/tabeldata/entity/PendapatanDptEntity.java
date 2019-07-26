package com.tabeldata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendapatanDptEntity {
    private Integer id; // I_ID
    private Integer idSkpd; // I_IDSKPD
    private Integer idBas; // I_IDBAS
    private String tahunAnggaran; // C_ANGG_TAHUN
    private Double anggaranDpa; // V_ANGG_DPA
    private Double anggaranTapd; // V_ANGG_TAPD
    private Double rpaBulan1; // V_RPA_BULAN1
    private Double rpaBulan2; // V_RPA_BULAN2
    private Double rpaBulan3; // V_RPA_BULAN3
    private Double rpaBulan4; // V_RPA_BULAN4
    private Double rpaBulan5; // V_RPA_BULAN5
    private Double rpaBulan6; // V_RPA_BULAN6
    private Double rpaBulan7; // V_RPA_BULAN7
    private Double rpaBulan8; // V_RPA_BULAN8
    private Double rpaBulan9; // V_RPA_BULAN9
    private Double rpaBulan10; // V_RPA_BULAN10
    private Double rpaBulan11; // V_RPA_BULAN11
    private Double rpaBulan12; // V_RPA_BULAN12
    private Integer idRekamPengguna; // I_PGUN_REKAM
    private Date tanggalRekamPengguna; // D_PGUN_REKAM
    private Integer idUbahPengguna; // I_PGUN_UBAN
    private Date tanggalUbahPengguna; // D_PGUN_UBAH
    private String jenis; // C_RPA_JENIS
}
