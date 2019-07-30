package com.tabeldata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RincianPendapatanEntity {
    private Integer id; // I_ID
    private Integer idDpt; // I_IDDPT
    private Integer idSkpd; // I_IDSKPD
    private Integer idBas; // I_IDBAS
    private String tahunAnggaran; // C_ANGG_TAHUN
    private Integer anggaranNoUrut; // I_ANGG_NOURUT
    private String rincianAnggaran; // E_ANGG_RINCI
    private String spesifikasiAnggaran; // E_ANGG_SPESIFIKASI
    private Integer jumlahBarangDpa; // Q_BRG_VOLUMEDPA
    private String namaBarangSatuanDpa; // N_BRG_SATUANDPA
    private BigDecimal hargaBarangSatuanDpa; // V_BRG_HARGADPA
    private BigDecimal anggaranDpa; // V_ANGG_DPA
    private Integer jumlahBarangTapd; // Q_BRG_VOLUMETAPD
    private String namaBarangSatuanTapd; // N_BRG_SATUANTAPD
    private BigDecimal hargaBarangSatuanTapd; // V_BRG_HARGATAPD
    private BigDecimal anggaranTapd; // V_ANGG_TAPD
    private Integer jumlahBarang1Dpa; // Q_BRG1_VOLUMEDPA
    private String namaBarangSatuan1Dpa; // N_BRG1_SATUANDPA
    private Integer jumlahBarang2Dpa; // Q_BRG2_VOLUMEDPA
    private String namaBarangSatuan2Dpa; // N_BRG2_SATUANDPA
    private Integer jumlahBarang3Dpa; // Q_BRG3_VOLUMEDPA
    private String namaBarangSatuan3Dpa; // N_BRG3_SATUANDPA
    private Integer jumlahBarang1Tapd; // Q_BRG1_VOLUMETAPD
    private String namaBarangSatuan1Tapd; // N_BRG1_SATUANTAPD
    private Integer jumlahBarang2Tapd; // Q_BRG2_VOLUMETAPD
    private String namaBarangSatuan2Tapd; // N_BRG2_SATUANTAPD
    private Integer jumlahBarang3Tapd; // Q_BRG3_VOLUMETAPD
    private String namaBarangSatuan3Tapd; // N_BRG3_SATUANTAPD
    private Integer idRekamPengguna; // I_PGUN_REKAM
    private Timestamp tanggalRekamPengguna; // D_PGUN_REKAM
    private Integer idUbahPengguna; // I_PGUN_UBAN
    private Timestamp tanggalUbahPengguna; // D_PGUN_UBAH
}
