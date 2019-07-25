package com.tabeldata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendapatanDpt {
    private Integer id;
    private Integer idSkpd;
    private Integer idBas;
    private String tahunAnggaran;
    private Double anggaranDpa;
    private Double anggaranTapd;
    private Double rpaBulan1;
    private Double rpaBulan2;
    private Double rpaBulan3;
    private Double rpaBulan4;
    private Double rpaBulan5;
    private Double rpaBulan6;
    private Double rpaBulan7;
    private Double rpaBulan8;
    private Double rpaBulan9;
    private Double rpaBulan10;
    private Double rpaBulan11;
    private Double rpaBulan12;
    private Integer idRekamPengguna;
    private Date tanggalRekamPengguna;
    private Integer idUbahPengguna;
    private Date tanggalUbahPengguna;
}
