package com.tabeldata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KomponenEntity {

    private Integer id;
    private Integer idBas;
    private String kodeAkun;
    private String kodeKomponen;
    private String namaKomponen;
    private String namaSatuan;
    private BigDecimal harga;
    private String status;
    private Boolean appoval;
    private Boolean aktif;
    private Boolean nonPajak;
    private String merk;
    private String spek;
    private Integer idPenggunaRekam;
    private Timestamp tglPenggunaRekam;
    private Integer idPenggunaUbah;
    private Timestamp tglPenggunaUbah;

}
