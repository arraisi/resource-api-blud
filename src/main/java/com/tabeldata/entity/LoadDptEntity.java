package com.tabeldata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadDptEntity {
    private Integer idTrx; // ID_TRX
    private String tahunAnggaran; // C_ANGG_TAHUN
    private Integer idSkpd; // I_IDSKPD
    private String skpd; // C_SKPD
    private String namaSkpd; // N_SKPD
    private Integer idBas; // I_IBAS
    private String kodeAkun; // C_AKUN
    private String namaAkun; // N_AKUN
    private Double anggaranDpa; // V_ANGG_DPA
    private Double anggaranTapd; // V_ANGG_TAPD
}
