package com.tabeldata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KomponenBelanjaEditDto {

    private Integer id;
    private String namaAkun;
    private String kodeAkun;
    private String kodeKomponen;
    private String namaKomponen;
    private String merk;
    private String spek;
    private BigDecimal komponenHarga;
    private String satuan;
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
    private String keterangan;
    private BigDecimal totalHarga;


}
