package com.tabeldata.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;

@Slf4j
@Repository
public class PersetujuanDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Update TMRBA Ketika Submit Operator
     */
    public void UpdateTmrbaSubmitOperator(Integer idOperator, Timestamp tanggalSubmitOperator, Integer idSkpd, String tahunAnggaran) throws SQLException {
        String query = "update TMRBA\n" +
                "set I_OPR_APPRV  = :vIdOperatorSubmit,\n" +
                "    D_OPR_APPRV  = :vTanggalOperatorSubmit,\n" +
                "    I_PGUN_UBAH  = :vIdPenggunaUbah,\n" +
                "    D_REKAM_UBAH = :vTanggalPenggunaUbah\n" +
                "where I_IDSKPD = :vIdskpd\n" +
                "  and C_ANGG_TAHUN = :vTahunAnggaran";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("vIdOperatorSubmit", idOperator);
        parameterSource.addValue("vTanggalOperatorSubmit", tanggalSubmitOperator);
        parameterSource.addValue("vIdPenggunaUbah", idOperator);
        parameterSource.addValue("vTanggalPenggunaUbah", tanggalSubmitOperator);
        parameterSource.addValue("vIdskpd", idSkpd);
        parameterSource.addValue("vTahunAnggaran", tahunAnggaran);
        this.namedParameterJdbcTemplate.update(query, parameterSource);
    }

    /**
     * Update TMRBAKEGIATAN Ketika Submit Operator
     */
    public void UpdateKegiatanSubmitOperatorBaru(Integer idSkpd, String tahunAnggaran) throws SQLException {
        String query = "update TMRBAKEGIATAN\n" +
                "set C_STATUS_APPV = '1'\n" +
                "where I_IDSKPD = :vIdSkpd\n" +
                "  and C_ANGG_TAHUN = :vTahunAnggaran\n" +
                "  and C_STATUS_APPV = '0'";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("vIdSkpd", idSkpd);
        parameterSource.addValue("vTahunAnggaran", tahunAnggaran);
        this.namedParameterJdbcTemplate.update(query, parameterSource);
    }

    /**
     * Update TMRBAKEGIATAN Ketika Revisi Operator
     */
    public void UpdateKegiatanSubmitOperatorRevisi(Integer idSkpd, String tahunAnggaran) throws SQLException {
        String query = "update TMRBAKEGIATAN\n" +
                "set C_STATUS_APPV = '4'\n" +
                "where I_IDSKPD = :vIdSkpd\n" +
                "  and C_ANGG_TAHUN = :vTahunAnggaran\n" +
                "  and C_STATUS_APPV = '3'";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("vIdSkpd", idSkpd);
        parameterSource.addValue("vTahunAnggaran", tahunAnggaran);
        this.namedParameterJdbcTemplate.update(query, parameterSource);
    }

    /**
     * Update TMRBA Ketika Sudah Diterima Semua Kegiatan Oleh Pejabat Bendahara Keuangan
     */
    public void UpdateTmrbaDiterimaSemuaKegiatanBendahara(Integer idBendahara, Timestamp tanggalDiterimaBendahara, Integer idSkpd, String tahunAnggaran) throws SQLException {
        String query = "update TMRBA\n" +
                "set I_PJBKEU_APPV = :vIdBendahara,\n" +
                "    D_PJBKEU_APPV = :vTanggalDiterimaBendahara,\n" +
                "    I_PGUN_UBAH   = :vIdPenggunaUbah,\n" +
                "    D_REKAM_UBAH  = :vTanggalPenggunaUbah\n" +
                "where I_IDSKPD = :vIdskpd\n" +
                "  and C_ANGG_TAHUN = :vTahunAnggaran";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("vIdBendahara", idBendahara);
        parameterSource.addValue("vTanggalDiterimaBendahara", tanggalDiterimaBendahara);
        parameterSource.addValue("vIdPenggunaUbah", idBendahara);
        parameterSource.addValue("vTanggalPenggunaUbah", tanggalDiterimaBendahara);
        parameterSource.addValue("vIdskpd", idSkpd);
        parameterSource.addValue("vTahunAnggaran", tahunAnggaran);
        this.namedParameterJdbcTemplate.update(query, parameterSource);
    }

    /**
     * Update TMRBAKEGIATAN Ketika Diterima Oleh Pejabat Bendahara Keuangan
     */
    public void UpdateKegiatanDiterimaBendahara(Integer idSkpd, String tahunAnggaran, Integer idKegiatan) throws SQLException {
        String query = "update TMRBAKEGIATAN\n" +
                "set C_STATUS_APPV = '2'\n" +
                "where I_IDSKPD = :vIdSkpd\n" +
                "  and C_ANGG_TAHUN = :vTahunAnggaran\n" +
                "  and I_ID = :vIdKegiatan";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("vIdSkpd", idSkpd);
        parameterSource.addValue("vTahunAnggaran", tahunAnggaran);
        parameterSource.addValue("vIdKegiatan", idKegiatan);
        this.namedParameterJdbcTemplate.update(query, parameterSource);
    }

    /**
     * Update TMRBAKEGIATAN Ketika Ditolak Oleh Pejabat Bendahara Keuangan
     */
    public void UpdateKegiatanDitolakBendahara(Integer idSkpd, String tahunAnggaran, Integer idKegiatan) throws SQLException {
        String query = "update TMRBAKEGIATAN\n" +
                "set C_STATUS_APPV = '3'\n" +
                "where I_IDSKPD = :vIdSkpd\n" +
                "  and C_ANGG_TAHUN = :vTahunAnggaran\n" +
                "  and I_ID = :vIdKegiatan";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("vIdSkpd", idSkpd);
        parameterSource.addValue("vTahunAnggaran", tahunAnggaran);
        parameterSource.addValue("vIdKegiatan", idKegiatan);
        this.namedParameterJdbcTemplate.update(query, parameterSource);
    }
}
