package com.tabeldata.dao;

import com.tabeldata.entity.ProgramEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ProgramDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
}
