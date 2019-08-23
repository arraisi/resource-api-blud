package com.tabeldata.dao;

import com.tabeldata.entity.ProgramEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ProgramDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    String QUERY_PROGRAL_BY_URUSAN_ALL = "SELECT TRP.I_ID    AS id,\n" +
            "       TRP.C_PROGRAM    AS kodeProgram,\n" +
            "       TRP.N_PROGRAM    AS namaProgram\n" +
            "FROM TRPROGRAM \"TRP\"\n" +
            "         INNER JOIN TRURUSAN \"TRU\" ON TRU.I_ID = TRP.I_IDURUSAN\n" +
            "WHERE\n" +
            "    (\n" +
            "                TRP.C_AKTIF = '1'\n" +
            "            AND TO_NUMBER(:vTahunAnggaran) BETWEEN TO_NUMBER(TRP.C_TAHUN_BERLAKU) AND TO_NUMBER(TRP.C_TAHUN_BERAKHIR)\n" +
            "        )\n" +
            "  AND\n" +
            "    (\n" +
            "                TRU.I_IDFUNGSI = 7\n" +
            "            AND TRU.C_AKTIF = '1'\n" +
            "            AND TO_NUMBER(:vTahunAnggaran) BETWEEN TO_NUMBER(TRU.C_TAHUN_BERLAKU) AND TO_NUMBER(TRU.C_TAHUN_BERAKHIR)\n" +
            "        )\n" +
            "  AND TRU.I_ID = :vIdUrusan";

    String QUERY_PROGRAM_BY_URUSAN_DAN_NOT_IN = "SELECT TRP.I_ID      AS id,\n" +
            "       TRP.C_PROGRAM AS kodeProgram,\n" +
            "       TRP.N_PROGRAM AS namaProgram\n" +
            "FROM TRPROGRAM \"TRP\"\n" +
            "         INNER JOIN TRURUSAN \"TRU\" ON TRU.I_ID = TRP.I_IDURUSAN\n" +
            "WHERE (\n" +
            "        TRP.C_AKTIF = '1'\n" +
            "        AND TO_NUMBER(:vTahunAnggaran) BETWEEN TO_NUMBER(TRP.C_TAHUN_BERLAKU) AND TO_NUMBER(TRP.C_TAHUN_BERAKHIR)\n" +
            "    )\n" +
            "  AND (\n" +
            "        TRU.I_IDFUNGSI = 7\n" +
            "        AND TRU.C_AKTIF = '1'\n" +
            "        AND TO_NUMBER(:vTahunAnggaran) BETWEEN TO_NUMBER(TRU.C_TAHUN_BERLAKU) AND TO_NUMBER(TRU.C_TAHUN_BERAKHIR)\n" +
            "    )\n" +
            "  AND TRU.I_ID = :vIdUrusan\n" +
            "  AND TRP.I_ID NOT IN (:vIdPogramList)";

    /**
     * Getl All List Program
     */
    public List<ProgramEntity> getListProgram() {
        String sql = "SELECT I_ID             AS id,\n" +
                "       I_IDURUSAN       AS idUrusan,\n" +
                "       C_PROGRAM        AS kodeProgram,\n" +
                "       N_PROGRAM        AS namaProgram,\n" +
                "       C_AKTIF          AS aktif,\n" +
                "       I_PGUN_REKAM     AS idPenggunaRekam,\n" +
                "       D_PGUN_REKAM     AS tanggalPenggunaRekam,\n" +
                "       I_PGUN_UBAH      AS idPenggunaUbah,\n" +
                "       D_PGUN_UBAH      AS tanggalPenggunaUbah,\n" +
                "       C_TAHUN_BERLAKU  AS tahunBerlaku,\n" +
                "       C_TAHUN_BERAKHIR AS tahunBerakhir\n" +
                "FROM TRPROGRAM ";
        Map<String, Object> param = new HashedMap<>();

        return this.namedParameterJdbcTemplate.query(sql, param, (rs, i) -> {
            ProgramEntity program = new ProgramEntity();
            program.setId(rs.getInt("id"));
            program.setIdUrusan(rs.getInt("idUrusan"));
            program.setKodeProgram(rs.getString("kodeProgram"));
            program.setNamaProgram(rs.getString("namaProgram"));
            program.setAktif(rs.getString("aktif"));
            program.setIdPenggunaRekam(rs.getInt("idPenggunaRekam"));
            program.setTanggalPenggunaRekam(rs.getTimestamp("tanggalPenggunaRekam"));
            program.setIdPenggunaUbah(rs.getInt("idPenggunaUbah"));
            program.setTanggalPenggunaUbah(rs.getTimestamp("tanggalPenggunaUbah"));
            program.setTahunBerlaku(rs.getString("tahunBerlaku"));
            program.setTahunBerakhir(rs.getString("tahunBerakhir"));
            return program;
        });
    }

    /**
     * Get List Program By Id Urusan
     */
    public List<ProgramEntity> getListProgramByIdUrusan(Integer idUrusan, String tahunAnggaran, List<Integer> listIdProgram) {
        String sql = listIdProgram.size() > 0 ? QUERY_PROGRAM_BY_URUSAN_DAN_NOT_IN : QUERY_PROGRAL_BY_URUSAN_ALL;
        Map<String, Object> param = new HashedMap<>();
        param.put("vIdUrusan", idUrusan);
        param.put("vTahunAnggaran", tahunAnggaran);
        param.put("vIdPogramList", listIdProgram);

        return this.namedParameterJdbcTemplate.query(sql, param, (rs, i) -> {
            ProgramEntity program = new ProgramEntity();
            program.setId(rs.getInt("id"));
            program.setKodeProgram(rs.getString("kodeProgram"));
            program.setNamaProgram(rs.getString("namaProgram"));
            return program;
        });
    }

    /**
     * Get Program By ID
     */
    public ProgramEntity getProgramByID(Integer id) throws EmptyResultDataAccessException {
        String sql = "SELECT I_ID             AS id,\n" +
                "       I_IDURUSAN       AS idUrusan,\n" +
                "       C_PROGRAM        AS kodeProgram,\n" +
                "       N_PROGRAM        AS namaProgram,\n" +
                "       C_AKTIF          AS aktif,\n" +
                "       I_PGUN_REKAM     AS idPenggunaRekam,\n" +
                "       D_PGUN_REKAM     AS tanggalPenggunaRekam,\n" +
                "       I_PGUN_UBAH      AS idPenggunaUbah,\n" +
                "       D_PGUN_UBAH      AS tanggalPenggunaUbah,\n" +
                "       C_TAHUN_BERLAKU  AS tahunBerlaku,\n" +
                "       C_TAHUN_BERAKHIR AS tahunBerakhir\n" +
                "FROM TRPROGRAM WHERE I_ID = :vId ";
        Map<String, Object> param = new HashedMap<>();
        param.put("vId", id);

        return this.namedParameterJdbcTemplate.queryForObject(sql, param, (rs, i) -> {
            ProgramEntity program = new ProgramEntity();
            program.setId(rs.getInt("id"));
            program.setIdUrusan(rs.getInt("idUrusan"));
            program.setKodeProgram(rs.getString("kodeProgram"));
            program.setNamaProgram(rs.getString("namaProgram"));
            program.setAktif(rs.getString("aktif"));
            program.setIdPenggunaRekam(rs.getInt("idPenggunaRekam"));
            program.setTanggalPenggunaRekam(rs.getTimestamp("tanggalPenggunaRekam"));
            program.setIdPenggunaUbah(rs.getInt("idPenggunaUbah"));
            program.setTanggalPenggunaUbah(rs.getTimestamp("tanggalPenggunaUbah"));
            program.setTahunBerlaku(rs.getString("tahunBerlaku"));
            program.setTahunBerakhir(rs.getString("tahunBerakhir"));
            return program;
        });
    }


    public List<Integer> getIDProgramByIdSkpdDanTahunAnggaran(Integer idSkpd, String tahunAnggaran) {

        String query = "select distinct I_IDPROGRAM as program\n" +
                "from TMRBAKEGIATAN\n" +
                "where I_IDSKPD = :idSkpd\n" +
                "  and C_ANGG_TAHUN = :tahunAnggaran";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue("idSkpd", idSkpd);
        parameterSource.addValue("tahunAnggaran", tahunAnggaran);

        return namedParameterJdbcTemplate.query(query, parameterSource, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                log.info("Data ID PROGRAM : {}", resultSet.getInt("program"));
                return resultSet.getInt("program");
            }
        });
    }
}
