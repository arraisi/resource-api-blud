package com.tabeldata.dao;

import com.tabeldata.entity.UrusanEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class UrusanDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<UrusanEntity> getListUrusan() {
        //language=Oracle
        String sql = "SELECT I_ID             AS id,\n" +
                "       I_IDFUNGSI       AS idFungsi,\n" +
                "       C_WAJIB          AS wajib,\n" +
                "       I_URUSAN         AS urusan,\n" +
                "       C_URUSAN         AS kodeUrusan,\n" +
                "       N_URUSAN         AS namaUrusan,\n" +
                "       C_AKTIF          AS aktif,\n" +
                "       I_PGUN_REKAM     AS idPenggunaRekam,\n" +
                "       D_PGUN_REKAM     AS tanggalPenggunaRekam,\n" +
                "       I_PGUN_UBAH      AS idPenggunaUbah,\n" +
                "       D_PGUN_UBAH      AS tanggalPenggunaUbah,\n" +
                "       C_TAHUN_BERLAKU  AS tahunBerlaku,\n" +
                "       C_TAHUN_BERAKHIR AS tahunBerakhir\n" +
                "FROM TRURUSAN ";
        Map<String, Object> params = new HashedMap<>();

        return this.namedParameterJdbcTemplate.query(sql, params, (rs, i) -> {
            UrusanEntity urusan = new UrusanEntity();
            urusan.setId(rs.getInt("id"));
            urusan.setIdFungsi(rs.getInt("idFungsi"));
            urusan.setWajib(rs.getInt("wajib"));
            urusan.setUrusan(rs.getString("urusan"));
            urusan.setKodeUrusan(rs.getString("kodeUrusan"));
            urusan.setNamaUrusan(rs.getString("namaUrusan"));
            urusan.setAktif(rs.getString("aktif"));
            urusan.setIdPenggunaRekam(rs.getInt("idPenggunaRekam"));
            urusan.setTanggalPenggunaRekam(rs.getTimestamp("tanggalPenggunaRekam"));
            urusan.setIdPenggunaUbah(rs.getInt("idPenggunaUbah"));
            urusan.setTanggalPenggunaUbah(rs.getTimestamp("tanggalPenggunaUbah"));
            urusan.setTahunBerlaku(rs.getString("tahunBerlaku"));
            urusan.setTahunBerakhir(rs.getString("tahunBerakhir"));
            return urusan;
        });
    }
}
