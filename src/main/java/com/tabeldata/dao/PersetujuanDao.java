package com.tabeldata.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class PersetujuanDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Update TMRBA Ketika Submit Operator
     */
    public void updateTmrbaSubmitOperator(Integer idOperator, Timestamp tanggalSubmitOperator, Integer idSkpd, String tahunAnggaran) throws SQLException {
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
    public void updateKegiatanSubmitOperatorBaru(Integer idSkpd, String tahunAnggaran) throws SQLException {
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
    public void updateKegiatanSubmitOperatorRevisi(Integer idSkpd, String tahunAnggaran) throws SQLException {
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
    public void updateTmrbaDiterimaSemuaKegiatanBendahara(Integer idBendahara, Timestamp tanggalDiterimaBendahara, Integer idSkpd, String tahunAnggaran, String status) throws SQLException {
        String query = "update TMRBA\n" +
                "set I_PJBKEU_APPV = :vIdBendahara,\n" +
                "    D_PJBKEU_APPV = :vTanggalDiterimaBendahara,\n" +
                "    C_STATPA_APPV = :status,\n" +
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
        parameterSource.addValue("status", status);
        this.namedParameterJdbcTemplate.update(query, parameterSource);
    }

    /**
     * Update TMRBAKEGIATAN Ketika Diterima Oleh Pejabat Bendahara Keuangan
     */
    public void updateKegiatanDiterimaBendahara(Integer idSkpd, String tahunAnggaran, Integer idKegiatan, Integer idBendahara, Timestamp tanggal) throws SQLException {
        String query = "update TMRBAKEGIATAN\n" +
                "set C_STATUS_APPV = '2', E_CATATAN_APPV = '-', I_IDPJBKEU_APPV = :vIdBendahara, D_PJBKEU_APPV = :vTanggalAppv\n" +
                "where I_IDSKPD = :vIdSkpd\n" +
                "  and C_ANGG_TAHUN = :vTahunAnggaran\n" +
                "  and I_ID = :vIdKegiatan";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("vIdSkpd", idSkpd);
        parameterSource.addValue("vTahunAnggaran", tahunAnggaran);
        parameterSource.addValue("vIdKegiatan", idKegiatan);
        parameterSource.addValue("vIdBendahara", idBendahara);
        parameterSource.addValue("vTanggalAppv", tanggal);
        this.namedParameterJdbcTemplate.update(query, parameterSource);
    }

    /**
     * Update TMRBAKEGIATAN Ketika Ditolak Oleh Pejabat Bendahara Keuangan
     */
    public void updateKegiatanDitolakBendahara(Integer idSkpd, String tahunAnggaran, Integer idKegiatan, String catatan) throws SQLException {
        String query = "update TMRBAKEGIATAN\n" +
                "set C_STATUS_APPV = '3', E_CATATAN_APPV = :vCatatan\n" +
                "where I_IDSKPD = :vIdSkpd\n" +
                "  and C_ANGG_TAHUN = :vTahunAnggaran\n" +
                "  and I_ID = :vIdKegiatan";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("vIdSkpd", idSkpd);
        parameterSource.addValue("vTahunAnggaran", tahunAnggaran);
        parameterSource.addValue("vIdKegiatan", idKegiatan);
        parameterSource.addValue("vCatatan", catatan);
        this.namedParameterJdbcTemplate.update(query, parameterSource);
    }

    /**
     * Update TMRBA Ketika Sudah Diterima Oleh Kepala SKPD
     */
    public void updateTmrbaDiterimaKepalaSkpd(Integer id, Timestamp tanggal, Integer idSkpd, String tahunAnggaran, String statusPa, String statusDinas) throws SQLException {
        String query = "update TMRBA\n" +
                "set I_PA_APPV = :vId,\n" +
                "    D_PA_APPV = :vTanggal,\n" +
                "    C_STATPA_APPV = :statusPa,\n" +
                "    C_STATDINAS_APPV = :statusDinas,\n" +
                "    E_CATPA_APPV = '-',\n" +
                "    I_PGUN_UBAH   = :vIdPenggunaUbah,\n" +
                "    D_REKAM_UBAH  = :vTanggalPenggunaUbah\n" +
                "where I_IDSKPD = :vIdskpd\n" +
                "  and C_ANGG_TAHUN = :vTahunAnggaran";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("vId", id);
        parameterSource.addValue("vTanggal", tanggal);
        parameterSource.addValue("vIdPenggunaUbah", id);
        parameterSource.addValue("vTanggalPenggunaUbah", tanggal);
        parameterSource.addValue("vIdskpd", idSkpd);
        parameterSource.addValue("vTahunAnggaran", tahunAnggaran);
        parameterSource.addValue("statusPa", statusPa);
        parameterSource.addValue("statusDinas", statusDinas);
        this.namedParameterJdbcTemplate.update(query, parameterSource);
    }

    /**
     * Update TMRBA Ketika Ditolak Oleh Kepala SKPD
     */
    public void updateTmrbaDitolakKepalaSkpd(Integer id, Timestamp tanggal, Integer idSkpd, String tahunAnggaran, String catatan) throws SQLException {
        String query = "update TMRBA\n" +
                "set I_PA_APPV = :vId,\n" +
                "    D_PA_APPV = :vTanggal,\n" +
                "    C_STATPA_APPV = '3',\n" +
                "    E_CATPA_APPV = :catatan,\n" +
                "    I_PGUN_UBAH   = :vIdPenggunaUbah,\n" +
                "    D_REKAM_UBAH  = :vTanggalPenggunaUbah\n" +
                "where I_IDSKPD = :vIdskpd\n" +
                "  and C_ANGG_TAHUN = :vTahunAnggaran";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("vId", id);
        parameterSource.addValue("vTanggal", tanggal);
        parameterSource.addValue("vIdPenggunaUbah", id);
        parameterSource.addValue("vTanggalPenggunaUbah", tanggal);
        parameterSource.addValue("vIdskpd", idSkpd);
        parameterSource.addValue("vTahunAnggaran", tahunAnggaran);
        parameterSource.addValue("catatan", catatan);
        this.namedParameterJdbcTemplate.update(query, parameterSource);
    }

    /**
     * Update TMRBA Ketika Sudah Diterima Oleh Dinas Teknis
     */
    public void updateTmrbaDiterimaDinasTeknis(Integer id, Timestamp tanggal, Integer idSkpd, String tahunAnggaran) throws SQLException {
        String query = "update TMRBA\n" +
                "set I_DINAS_APPV = :vId,\n" +
                "    D_DINAS_APPV = :vTanggal,\n" +
                "    C_STATDINAS_APPV = '2',\n" +
                "    E_CATDINAS_APPV = '-',\n" +
                "    I_PGUN_UBAH   = :vIdPenggunaUbah,\n" +
                "    D_REKAM_UBAH  = :vTanggalPenggunaUbah\n" +
                "where I_IDSKPD = :vIdskpd\n" +
                "  and C_ANGG_TAHUN = :vTahunAnggaran";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("vId", id);
        parameterSource.addValue("vTanggal", tanggal);
        parameterSource.addValue("vIdPenggunaUbah", id);
        parameterSource.addValue("vTanggalPenggunaUbah", tanggal);
        parameterSource.addValue("vIdskpd", idSkpd);
        parameterSource.addValue("vTahunAnggaran", tahunAnggaran);
        this.namedParameterJdbcTemplate.update(query, parameterSource);
    }

    /**
     * Update TMRBA Ketika Ditolak Oleh Dinas Teknis
     */
    public void updateTmrbaDitolakDinasTeknis(Integer id, Timestamp tanggal, Integer idSkpd, String tahunAnggaran, String catatan) throws SQLException {
        String query = "update TMRBA\n" +
                "set I_DINAS_APPV = :vId,\n" +
                "    D_DINAS_APPV = :vTanggal,\n" +
                "    C_STATDINAS_APPV = '3',\n" +
                "    E_CATDINAS_APPV = :catatan,\n" +
                "    I_PGUN_UBAH   = :vIdPenggunaUbah,\n" +
                "    D_REKAM_UBAH  = :vTanggalPenggunaUbah\n" +
                "where I_IDSKPD = :vIdskpd\n" +
                "  and C_ANGG_TAHUN = :vTahunAnggaran";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("vId", id);
        parameterSource.addValue("vTanggal", tanggal);
        parameterSource.addValue("vIdPenggunaUbah", id);
        parameterSource.addValue("vTanggalPenggunaUbah", tanggal);
        parameterSource.addValue("vIdskpd", idSkpd);
        parameterSource.addValue("vTahunAnggaran", tahunAnggaran);
        parameterSource.addValue("catatan", catatan);
        this.namedParameterJdbcTemplate.update(query, parameterSource);
    }

    /**
     * Check Jumlah Kegiatan Yang Belum Di Setujui
     */
    public Long checkKegiatanBelumDisetujui(Integer skpdId, String tahunAnggaran) throws DataAccessException {
        String query = "select count(I_ID) as total\n" +
                "from TMRBAKEGIATAN\n" +
                "where I_IDSKPD = :vIdSkpd\n" +
                "  and C_ANGG_TAHUN = :vTahunAnggaran\n" +
                "  and C_STATUS_APPV not in ('2')";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("vIdSkpd", skpdId);
        parameterSource.addValue("vTahunAnggaran", tahunAnggaran);
        return this.namedParameterJdbcTemplate.queryForObject(query, parameterSource, (resultSet, i) -> resultSet.getLong("total") >= 0 ? resultSet.getLong("total") : 0);
    }

    public Map<String, Object> checkStatusAppvKepalaDanDinas(Integer skpdId, String tahunAnggaran) throws DataAccessException {
        String query = "select C_STATPA_APPV    AS statusAppvKepala,\n" +
                "       E_CATPA_APPV     AS catatanAppvKepala,\n" +
                "       C_STATDINAS_APPV AS statusAppvDinas,\n" +
                "       E_CATDINAS_APPV  AS catatanAppvDinas\n" +
                "from TMRBA\n" +
                "where I_IDSKPD = :vIdSkpd\n" +
                "  and C_ANGG_TAHUN = :vTahunAnggaran";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("vIdSkpd", skpdId);
        parameterSource.addValue("vTahunAnggaran", tahunAnggaran);

        return this.namedParameterJdbcTemplate.queryForObject(query, parameterSource, (resultSet, i) -> {
            Map<String, Object> data = new HashMap<>();
            data.put("statusAppvKepala", resultSet.getString("statusAppvKepala"));
            data.put("statusNameAppvKepala", mapperStatusAppv(resultSet.getString("statusAppvKepala") != null ? resultSet.getString("statusAppvKepala") : "-"));
            data.put("statusButtonAppvKepala", mapperStatusAppvColorButton(resultSet.getString("statusAppvKepala") != null ? resultSet.getString("statusAppvKepala") : "-"));
            data.put("catatanAppvKepala", resultSet.getString("catatanAppvKepala"));
            data.put("statusAppvDinas", resultSet.getString("statusAppvDinas"));
            data.put("statusNameAppvDinas", mapperStatusAppv(resultSet.getString("statusAppvDinas") != null ? resultSet.getString("statusAppvDinas") : "-"));
            data.put("statusButtonAppvDinas", mapperStatusAppvColorButton(resultSet.getString("statusAppvDinas") != null ? resultSet.getString("statusAppvDinas") : "-"));
            data.put("catatanAppvDinas", resultSet.getString("catatanAppvDinas"));
            return data;
        });
    }


    public String mapperStatusAppv(String id) {
        String status;
        switch (id) {
            case "0":
                status = "Baru";
                break;
            case "1":
                status = "Dikirim";
                break;
            case "2":
                status = "Diterima";
                break;
            case "3":
                status = "Ditolak";
                break;
            case "4":
                status = "Revisi";
                break;
            default:
                status = "-";
        }
        return status;
    }

    public String mapperStatusAppvColorButton(String id) {
        String statusColor;
        switch (id) {
            case "0":
                statusColor = "btn-secondary";
                break;
            case "1":
                statusColor = "btn-success";
                break;
            case "2":
                statusColor = "btn-primary";
                break;
            case "3":
                statusColor = "btn-danger";
                break;
            case "4":
                statusColor = "btn-warning";
                break;
            default:
                statusColor = "btn-secondary";
        }
        return statusColor;
    }
}
