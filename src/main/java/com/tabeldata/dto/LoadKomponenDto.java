package com.tabeldata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadKomponenDto {

    private Integer idBas;
    private Integer idBasKomponen;
    private String kodeKomponen;
    private String namaSatuan;
    private BigDecimal komponenHarga;
    private String komponenMerk;
    private String komponenSpek;
}
