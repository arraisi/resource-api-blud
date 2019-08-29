package com.tabeldata.dao;

import com.tabeldata.dto.DataPenggunaLogin;
import com.tabeldata.dto.KegiatanGetDto;
import com.tabeldata.dto.LoadKegiatanDatatableDto;
import com.tabeldata.entity.KegiatanEntity;
import com.tabeldata.entity.ProgramEntity;
import com.tabeldata.service.ProgramService;
import com.tabeldata.service.UrusanService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class KegiatanDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private UrusanService urusanService;

    @Autowired
    private ProgramService programService;

    /**
     * Load Kegiatan List Table Awal
     */

    String QUERY_LOAD_KEG_OPPR = "SELECT NVL(xxx.i_id, -1)        AS idKegiatan\n" +
            "     , tmrba.c_angg_tahun       AS tahunAnggaran\n" +
            "     , tmrba.i_idskpd           AS idSkpd\n" +
            "     , trrbaskpd.c_skpd         AS kodeSkpd\n" +
            "     , trrbaskpd.n_skpd         AS namaSkpd\n" +
            "     , trprogram.i_idurusan     AS idUrusan\n" +
            "     , c_urusan                 AS kodeUrusan\n" +
            "     , n_urusan                 AS namaUrusan\n" +
            "     , xxx.i_idprogram          AS idProgram\n" +
            "     , c_program                AS kodeProgram\n" +
            "     , n_program                AS namaProgram\n" +
            "     , NVL(xxx.c_kegiatan, '-') AS kodeKegiatan\n" +
            "     , NVL(xxx.n_kegiatan, '-') AS namaKegiatan\n" +
            "     , NVL(xxx.v_angg_dpa, 0)   AS anggaranDpa\n" +
            "     , NVL(xxx.v_angg_tapd, 0)  AS anggaranTapd\n" +
            "     , xxx.C_STATUS_APPV        AS statusPersetujuan\n" +
            "     , xxx.E_CATATAN_APPV       AS catatanPersetujuan\n" +
            "FROM tmrba\n" +
            "         INNER JOIN trrbaskpd ON (tmrba.i_idskpd = trrbaskpd.i_id)\n" +
            "         LEFT JOIN tmrbakegiatan xxx ON (tmrba.c_angg_tahun = xxx.c_angg_tahun) AND (tmrba.i_idskpd = xxx.i_idskpd)\n" +
            "         LEFT JOIN trprogram ON trprogram.i_id = xxx.i_idprogram\n" +
            "         LEFT JOIN trurusan ON trurusan.i_id = trprogram.i_idurusan\n" +
            "WHERE tmrba.c_angg_tahun = :vTahunAnggaran\n" +
            "  and tmrba.I_idskpd = :vIdSkpd\n" +
            "ORDER BY tmrba.i_idskpd, xxx.c_kegiatan";

    String QUERY_LOAD_KEG_BENDAHARA = "SELECT NVL(xxx.i_id, -1)        AS idKegiatan\n" +
            "     , tmrba.c_angg_tahun       AS tahunAnggaran\n" +
            "     , tmrba.i_idskpd           AS idSkpd\n" +
            "     , trrbaskpd.c_skpd         AS kodeSkpd\n" +
            "     , trrbaskpd.n_skpd         AS namaSkpd\n" +
            "     , trprogram.i_idurusan     AS idUrusan\n" +
            "     , c_urusan                 AS kodeUrusan\n" +
            "     , n_urusan                 AS namaUrusan\n" +
            "     , xxx.i_idprogram          AS idProgram\n" +
            "     , c_program                AS kodeProgram\n" +
            "     , n_program                AS namaProgram\n" +
            "     , NVL(xxx.c_kegiatan, '-') AS kodeKegiatan\n" +
            "     , NVL(xxx.n_kegiatan, '-') AS namaKegiatan\n" +
            "     , NVL(xxx.v_angg_dpa, 0)   AS anggaranDpa\n" +
            "     , NVL(xxx.v_angg_tapd, 0)  AS anggaranTapd\n" +
            "     , xxx.C_STATUS_APPV        AS statusPersetujuan\n" +
            "     , xxx.E_CATATAN_APPV       AS catatanPersetujuan\n" +
            "FROM tmrba\n" +
            "         INNER JOIN trrbaskpd ON (tmrba.i_idskpd = trrbaskpd.i_id)\n" +
            "         LEFT JOIN tmrbakegiatan xxx ON (tmrba.c_angg_tahun = xxx.c_angg_tahun) AND (tmrba.i_idskpd = xxx.i_idskpd)\n" +
            "         LEFT JOIN trprogram ON trprogram.i_id = xxx.i_idprogram\n" +
            "         LEFT JOIN trurusan ON trurusan.i_id = trprogram.i_idurusan\n" +
            "WHERE tmrba.c_angg_tahun = :vTahunAnggaran\n" +
            "  and tmrba.I_idskpd = :vIdSkpd\n" +
            "  and tmrba.I_OPR_APPRV is not null\n" +
            "ORDER BY tmrba.i_idskpd, xxx.c_kegiatan";

    String QUERY_LOAD_KEG_KEPALA = "SELECT NVL(xxx.i_id, -1)        AS idKegiatan\n" +
            "     , tmrba.c_angg_tahun       AS tahunAnggaran\n" +
            "     , tmrba.i_idskpd           AS idSkpd\n" +
            "     , trrbaskpd.c_skpd         AS kodeSkpd\n" +
            "     , trrbaskpd.n_skpd         AS namaSkpd\n" +
            "     , trprogram.i_idurusan     AS idUrusan\n" +
            "     , c_urusan                 AS kodeUrusan\n" +
            "     , n_urusan                 AS namaUrusan\n" +
            "     , xxx.i_idprogram          AS idProgram\n" +
            "     , c_program                AS kodeProgram\n" +
            "     , n_program                AS namaProgram\n" +
            "     , NVL(xxx.c_kegiatan, '-') AS kodeKegiatan\n" +
            "     , NVL(xxx.n_kegiatan, '-') AS namaKegiatan\n" +
            "     , NVL(xxx.v_angg_dpa, 0)   AS anggaranDpa\n" +
            "     , NVL(xxx.v_angg_tapd, 0)  AS anggaranTapd\n" +
            "     , xxx.C_STATUS_APPV        AS statusPersetujuan\n" +
            "     , xxx.E_CATATAN_APPV       AS catatanPersetujuan\n" +
            "FROM tmrba\n" +
            "         INNER JOIN trrbaskpd ON (tmrba.i_idskpd = trrbaskpd.i_id)\n" +
            "         LEFT JOIN tmrbakegiatan xxx ON (tmrba.c_angg_tahun = xxx.c_angg_tahun) AND (tmrba.i_idskpd = xxx.i_idskpd)\n" +
            "         LEFT JOIN trprogram ON trprogram.i_id = xxx.i_idprogram\n" +
            "         LEFT JOIN trurusan ON trurusan.i_id = trprogram.i_idurusan\n" +
            "WHERE tmrba.c_angg_tahun = :vTahunAnggaran\n" +
            "  and tmrba.I_idskpd = :vIdSkpd\n" +
            "  and tmrba.I_PJBKEU_APPV is not null\n" +
            "ORDER BY tmrba.i_idskpd, xxx.c_kegiatan";

    String QUERY_LOAD_KEG_DINAS = "SELECT NVL(xxx.i_id, -1)        AS idKegiatan\n" +
            "     , tmrba.c_angg_tahun       AS tahunAnggaran\n" +
            "     , tmrba.i_idskpd           AS idSkpd\n" +
            "     , trrbaskpd.c_skpd         AS kodeSkpd\n" +
            "     , trrbaskpd.n_skpd         AS namaSkpd\n" +
            "     , trprogram.i_idurusan     AS idUrusan\n" +
            "     , c_urusan                 AS kodeUrusan\n" +
            "     , n_urusan                 AS namaUrusan\n" +
            "     , xxx.i_idprogram          AS idProgram\n" +
            "     , c_program                AS kodeProgram\n" +
            "     , n_program                AS namaProgram\n" +
            "     , NVL(xxx.c_kegiatan, '-') AS kodeKegiatan\n" +
            "     , NVL(xxx.n_kegiatan, '-') AS namaKegiatan\n" +
            "     , NVL(xxx.v_angg_dpa, 0)   AS anggaranDpa\n" +
            "     , NVL(xxx.v_angg_tapd, 0)  AS anggaranTapd\n" +
            "     , xxx.C_STATUS_APPV        AS statusPersetujuan\n" +
            "     , xxx.E_CATATAN_APPV       AS catatanPersetujuan\n" +
            "FROM tmrba\n" +
            "         INNER JOIN trrbaskpd ON (tmrba.i_idskpd = trrbaskpd.i_id)\n" +
            "         LEFT JOIN tmrbakegiatan xxx ON (tmrba.c_angg_tahun = xxx.c_angg_tahun) AND (tmrba.i_idskpd = xxx.i_idskpd)\n" +
            "         LEFT JOIN trprogram ON trprogram.i_id = xxx.i_idprogram\n" +
            "         LEFT JOIN trurusan ON trurusan.i_id = trprogram.i_idurusan\n" +
            "WHERE tmrba.c_angg_tahun = :vTahunAnggaran\n" +
            "  and tmrba.I_idskpd = :vIdSkpd\n" +
            "  and tmrba.I_PA_APPV is not null\n" +
            "ORDER BY tmrba.i_idskpd, xxx.c_kegiatan";

    public List<LoadKegiatanDatatableDto> loadKegiatanDatatable(String tahunAnggaran, Integer idSkpd, String otor) {

        String sql;
        switch (otor) {
            case "0":
                // OPERATOR Query
                sql = QUERY_LOAD_KEG_OPPR;
                break;
            case "1":
                // Bendahara Query
                sql = QUERY_LOAD_KEG_BENDAHARA;
                break;
            case "2":
                // KEPALA SKPD Query
                sql = QUERY_LOAD_KEG_KEPALA;
                break;
            case "3":
                // DINAS TEKNIS Query
                sql = QUERY_LOAD_KEG_DINAS;
                break;
            default:
                sql = QUERY_LOAD_KEG_OPPR;
        }
        Map<String, Object> params = new HashedMap<>();
        params.put("vTahunAnggaran", tahunAnggaran);
        params.put("vIdSkpd", idSkpd);

        return this.namedParameterJdbcTemplate.query(sql, params, (rs, i) -> {
            LoadKegiatanDatatableDto data = new LoadKegiatanDatatableDto();
            data.setIdKegiatan(rs.getInt("idKegiatan"));
            data.setTahunAnggaran(rs.getString("tahunAnggaran"));
            data.setIdSkpd(rs.getInt("idSkpd"));
            data.setKodeSkpd(rs.getString("kodeSkpd"));
            data.setNamaSkpd(rs.getString("namaSkpd"));
            data.setIdUrusan(rs.getInt("idUrusan"));
            data.setKodeUrusan(rs.getString("kodeUrusan"));
            data.setNamaUrusan(rs.getString("namaUrusan"));
            data.setIdProgram(rs.getInt("idProgram"));
            data.setKodeProgram(rs.getString("kodeProgram"));
            data.setNamaProgram(rs.getString("namaProgram"));
            data.setKodeKegiatan(rs.getString("kodeKegiatan"));
            data.setNamaKegiatan(rs.getString("namaKegiatan"));
            data.setAnggaranDpa(rs.getBigDecimal("anggaranDpa"));
            data.setAnggaranTapd(rs.getBigDecimal("anggaranTapd"));
            data.setStatusPersetujuan(rs.getString("statusPersetujuan"));
            data.setStatusName(mapperStatusAppv(rs.getString("statusPersetujuan")));
            data.setStatusBadgeColor(mapperStatusAppvColorBadge(rs.getString("statusPersetujuan")));
            data.setCatatanPersetujuan(rs.getString("catatanPersetujuan"));
            return data;
        });
    }

    /**
     * Get No. Kegiatan
     */
    public String getNoKegiatan(String tahunAnggaran, Integer idProgram, Integer idSkpd) throws EmptyResultDataAccessException {
        //language=Oracle
        String sql = "SELECT" +
                " CONCAT\n" +
                "           (\n" +
                "               CONCAT\n" +
                "                   (\n" +
                "                       TRP.C_PROGRAM, '.'\n" +
                "                   )\n" +
                "           , LPAD(COUNT(0) + 1, 3, '0')\n" +
                "           ) AS NO_KEGIATAN\n" +
                "FROM" +
                " TMRBAKEGIATAN \"TKG\"\n" +
                "         INNER JOIN TRPROGRAM \"TRP\" ON TRP.I_ID = TKG.I_IDPROGRAM\n" +
                "WHERE" +
                " TKG.I_IDSKPD = :vIdSkpd\n" +
                "  AND TKG.I_IDPROGRAM = :vIdProgram\n" +
                "  AND TKG.C_ANGG_TAHUN = :vTahunAnggaran\n" +
                "GROUP BY" +
                " TRP.C_PROGRAM ";

        Map<String, Object> params = new HashedMap<>();
        params.put("vTahunAnggaran", tahunAnggaran);
        params.put("vIdProgram", idProgram);
        params.put("vIdSkpd", idSkpd);

        return this.namedParameterJdbcTemplate.queryForObject(sql, params, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("NO_KEGIATAN");
            }
        });

    }


    /**
     * Save Kegiatan
     */
    public int saveKegiatan(KegiatanEntity value) throws DataAccessException {
        String sql = "INSERT\n" +
                "INTO TMRBAKEGIATAN (I_ID,\n" +
                "                    I_IDSKPD,\n" +
                "                    I_IDPROGRAM,\n" +
                "                    C_ANGG_TAHUN,\n" +
                "                    C_KEGIATAN,\n" +
                "                    N_KEGIATAN,\n" +
                "                    V_ANGG_TAPD,\n" +
                "                    V_ANGG_DPA,\n" +
                "                    C_WIL_LOKASI,\n" +
                "                    I_PGUN_REKAM,\n" +
                "                    D_PGUN_REKAM,\n" +
                "                    I_PGUN_UBAH,\n" +
                "                    D_PGUN_UBAH,\n" +
                "                    N_ANGG_SUMBDANA,\n" +
                "                    E_SASARAN,\n" +
                "                    D_MULAI,\n" +
                "                    D_SELESAI)\n" +
                "VALUES (:id,\n" +
                "        :idSkpd,\n" +
                "        :idProgram,\n" +
                "        :tahunAnggaran,\n" +
                "        :kodeKegiatan,\n" +
                "        :namaKegiatan,\n" +
                "        :anggaranTapd,\n" +
                "        :anggaranDpa,\n" +
                "        :kodeLokasiKegiatan,\n" +
                "        :idPenggunaRekam,\n" +
                "        :tanggalPenggunaRekam,\n" +
                "        :idPenggunaUbah,\n" +
                "        :tanggalPenggunaUbah,\n" +
                "        :namaSumberDana,\n" +
                "        :sasaranKegiatan,\n" +
                "        :bulanMulai,\n" +
                "        :bulanSelesai) ";
        Map<String, Object> params = new HashedMap<>();
        params.put("id", value.getId());
        params.put("idSkpd", value.getIdSkpd());
        params.put("idProgram", value.getIdProgram());
        params.put("tahunAnggaran", value.getTahunAnggaran());
        params.put("kodeKegiatan", value.getKodeKegiatan());
        params.put("namaKegiatan", value.getNamaKegiatan());
        params.put("anggaranTapd", value.getAnggaranTapd());
        params.put("anggaranDpa", value.getAnggaranDpa());
        params.put("kodeLokasiKegiatan", value.getKodeLokasiKegiatan());
        params.put("idPenggunaRekam", value.getIdPenggunaRekam());
        params.put("tanggalPenggunaRekam", value.getTanggalPenggunaRekam());
        params.put("idPenggunaUbah", value.getIdPenggunaUbah());
        params.put("tanggalPenggunaUbah", value.getTanggalPenggunaUbah());
        params.put("namaSumberDana", value.getNamaSumberDana());
        params.put("sasaranKegiatan", value.getSasaranKegiatan());
        params.put("bulanMulai", value.getBulanMulai());
        params.put("bulanSelesai", value.getBulanSelesai());
        return this.namedParameterJdbcTemplate.update(sql, params);
    }

    /**
     * Update Kegiatan (Catatan: Kalau Update Dari Service nya Tolong Get Dulu By ID biar Value Yang Lain Gak jadi Null)
     */
    public int updateKegiatan(KegiatanEntity value) {
        String sql = "UPDATE\n" +
                "    TMRBAKEGIATAN\n" +
                "SET I_IDSKPD        = :idSkpd,\n" +
                "    I_IDPROGRAM     = :idProgram,\n" +
                "    C_ANGG_TAHUN    = :tahunAnggaran,\n" +
                "    C_KEGIATAN      = :kodeKegiatan,\n" +
                "    N_KEGIATAN      = :namaKegiatan,\n" +
                "    V_ANGG_TAPD     = :anggaranTapd,\n" +
                "    V_ANGG_DPA      = :anggaranDpa,\n" +
                "    C_WIL_LOKASI    = :kodeLokasiKegiatan,\n" +
                "    I_PGUN_REKAM    = :idPenggunaRekam,\n" +
                "    D_PGUN_REKAM    = :tanggalPenggunaRekam,\n" +
                "    I_PGUN_UBAH     = :idPenggunaUbah,\n" +
                "    D_PGUN_UBAH     = :tanggalPenggunaUbah,\n" +
                "    N_ANGG_SUMBDANA = :namaSumberDana,\n" +
                "    E_SASARAN       = :sasaranKegiatan,\n" +
                "    D_MULAI         = :bulanMulai,\n" +
                "    D_SELESAI       = :bulanSelesai\n" +
                "WHERE I_ID = :id ";
        Map<String, Object> params = new HashedMap<>();
        params.put("id", value.getId());
        params.put("idSkpd", value.getIdSkpd());
        params.put("idProgram", value.getIdProgram());
        params.put("tahunAnggaran", value.getTahunAnggaran());
        params.put("kodeKegiatan", value.getKodeKegiatan());
        params.put("namaKegiatan", value.getNamaKegiatan());
        params.put("anggaranTapd", value.getAnggaranTapd());
        params.put("anggaranDpa", value.getAnggaranDpa());
        params.put("kodeLokasiKegiatan", value.getKodeLokasiKegiatan());
        params.put("idPenggunaRekam", value.getIdPenggunaRekam());
        params.put("tanggalPenggunaRekam", value.getTanggalPenggunaRekam());
        params.put("idPenggunaUbah", value.getIdPenggunaUbah());
        params.put("tanggalPenggunaUbah", value.getTanggalPenggunaUbah());
        params.put("namaSumberDana", value.getNamaSumberDana());
        params.put("sasaranKegiatan", value.getSasaranKegiatan());
        params.put("bulanMulai", value.getBulanMulai());
        params.put("bulanSelesai", value.getBulanSelesai());
        return this.namedParameterJdbcTemplate.update(sql, params);
    }

    /**
     * Get Kegiatan By ID
     */
    public KegiatanGetDto getKegiatanByID(Integer id) throws EmptyResultDataAccessException {
        String sql = "SELECT I_ID            AS id,\n" +
                "       I_IDSKPD        AS idSkpd,\n" +
                "       I_IDPROGRAM     AS idProgram,\n" +
                "       C_ANGG_TAHUN    AS tahunAnggaran,\n" +
                "       C_KEGIATAN      AS kodeKegiatan,\n" +
                "       N_KEGIATAN      AS namaKegiatan,\n" +
                "       V_ANGG_TAPD     AS anggaranTapd,\n" +
                "       V_ANGG_DPA      AS anggaranDpa,\n" +
                "       C_WIL_LOKASI    AS kodeLokasiKegiatan,\n" +
                "       I_PGUN_REKAM    AS idPenggunaRekam,\n" +
                "       D_PGUN_REKAM    AS tanggalPenggunaRekam,\n" +
                "       I_PGUN_UBAH     AS idPenggunaUbah,\n" +
                "       D_PGUN_UBAH     AS tanggalPenggunaUbah,\n" +
                "       N_ANGG_SUMBDANA AS namaSumberDana,\n" +
                "       E_SASARAN       AS sasaranKegiatan,\n" +
                "       D_MULAI         AS bulanMulai,\n" +
                "       D_SELESAI       AS bulanSelesai\n" +
                "FROM TMRBAKEGIATAN\n" +
                "WHERE I_ID = :vId ";
        Map<String, Object> param = new HashedMap<>();
        param.put("vId", id);

        return this.namedParameterJdbcTemplate.queryForObject(sql, param, (rs, i) -> {
            ProgramEntity programEntity = programService.getProgramByID(rs.getInt("idProgram"));
            KegiatanGetDto value = new KegiatanGetDto();
            value.setId(rs.getInt("id"));
            value.setIdSkpd(rs.getInt("idSkpd"));
            value.setIdProgram(rs.getInt("idProgram"));
            value.setTahunAnggaran(rs.getString("tahunAnggaran"));
            value.setKodeKegiatan(rs.getString("kodeKegiatan"));
            value.setNamaKegiatan(rs.getString("namaKegiatan"));
            value.setAnggaranTapd(rs.getBigDecimal("anggaranTapd"));
            value.setAnggaranDpa(rs.getBigDecimal("anggaranDpa"));
            value.setKodeLokasiKegiatan(rs.getString("kodeLokasiKegiatan"));
            value.setIdPenggunaRekam(rs.getInt("idPenggunaRekam"));
            value.setTanggalPenggunaRekam(rs.getTimestamp("tanggalPenggunaRekam"));
            value.setIdPenggunaUbah(rs.getInt("idPenggunaUbah"));
            value.setTanggalPenggunaUbah(rs.getTimestamp("tanggalPenggunaUbah"));
            value.setNamaSumberDana(rs.getString("namaSumberDana"));
            value.setSasaranKegiatan(rs.getString("sasaranKegiatan"));
            value.setBulanMulai(rs.getString("bulanMulai"));
            value.setBulanSelesai(rs.getString("bulanSelesai"));
            value.setProgram(programEntity);
            value.setUrusan(urusanService.getUrusanById(programEntity.getIdUrusan()));
            return value;
        });
    }

    public void saveTmrba(Integer id, Integer idSkpd, String tahunAnggaran, DataPenggunaLogin pengguna, Integer idPenggunaRekam, Timestamp tanggalPenggunaRekam) throws DataAccessException {
        String sql = "insert into TMRBA (I_ID,\n" +
                "                   I_IDSKPD,\n" +
                "                   C_ANGG_TAHUN,\n" +
                "                   C_STATUS,\n" +
                "                   I_NRK_PA,\n" +
                "                   I_NIP_PA,\n" +
                "                   N_PA,\n" +
                "                   N_PANGKAT_PA,\n" +
                "                   I_PGUN_REKAM,\n" +
                "                   D_PGUN_REKAM)\n" +
                "values (:id,\n" +
                "        :idSkpd,\n" +
                "        :tahunAnggaran,\n" +
                "        :status,\n" +
                "        :nrkPa,\n" +
                "        :nipPa,\n" +
                "        :namaPa,\n" +
                "        :pangkatPa,\n" +
                "        :idPenggunaUbah,\n" +
                "        :tanggalPenggunaRekam)";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("idSkpd", idSkpd);
        params.put("tahunAnggaran", tahunAnggaran);
        params.put("status", 0);
        params.put("nrkPa", pengguna.getNrk());
        params.put("nipPa", pengguna.getNip());
        params.put("namaPa", pengguna.getNama());
        params.put("pangkatPa", pengguna.getJabatan());
        params.put("idPenggunaUbah", idPenggunaRekam);
        params.put("tanggalPenggunaRekam", tanggalPenggunaRekam);
        this.namedParameterJdbcTemplate.update(sql, params);
    }

    public Integer getTmrbaByIdSkpdDanTahunAnggaran(Integer idSkpd, String tahunAnggaran) throws DataAccessException {
        String query = "select I_ID AS id from TMRBA WHERE I_IDSKPD = :vSkpdID AND C_ANGG_TAHUN = :vTahunAnggaran";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("vSkpdID", idSkpd);
        parameterSource.addValue("vTahunAnggaran", tahunAnggaran);
        return namedParameterJdbcTemplate.query(query, parameterSource, resultSet -> {
            Integer id = resultSet.getInt("id");
            return id >= 0 ? id : 0;
        });
    }

    /**
     * Get Total Anggaran Kegiatan By Id Skpd dan Tahun Anggaran
     */
    public BigDecimal getTotalAnggaranBySkpdIdDanTahunAnggaran(Integer idSkpd, String tahunAnggaran) {
        String sql = "select SUM(V_ANGG_TAPD) AS totalAnggKegiatan\n" +
                "from TMRBAKEGIATAN\n" +
                "where I_IDSKPD = :vIdSkpd\n" +
                "  and C_ANGG_TAHUN = :vTahun";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("vIdSkpd", idSkpd);
        parameterSource.addValue("vTahun", tahunAnggaran);

        return this.namedParameterJdbcTemplate.queryForObject(sql, parameterSource, new RowMapper<BigDecimal>() {
            @Override
            public BigDecimal mapRow(ResultSet resultSet, int i) throws SQLException {
                BigDecimal total = resultSet.getBigDecimal("totalAnggKegiatan");
                return total == null ? BigDecimal.valueOf(0) : total;
            }
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
                status = "Baru";
        }
        return status;
    }

    public String mapperStatusAppvColorBadge(String id) {
        String statusColor;
        switch (id) {
            case "0":
                statusColor = "badge-secondary";
                break;
            case "1":
                statusColor = "badge-green";
                break;
            case "2":
                statusColor = "badge-blue";
                break;
            case "3":
                statusColor = "badge-red";
                break;
            case "4":
                statusColor = "badge-yellow";
                break;
            default:
                statusColor = "badge-green";
        }
        return statusColor;
    }
}
