package com.tabeldata.dao;

import com.tabeldata.entity.KomponenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class KomponenDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public KomponenEntity getById(Integer id) {
        String query = "SELECT I_ID AS id, " +
                "I_IDBAS AS idBas, " +
                "C_AKUN AS kodeAkun, " +
                "C_KOMPONEN AS kodeKomponen, " +
                "N_KOMPONEN AS namaKomponen, " +
                "N_SATUAN AS namaSatuan, " +
                "V_KOMPONEN_HARGA AS harga, " +
                "C_STATUS AS status, " +
                "C_APPROVAL AS approval, " +
                "C_AKTIF AS aktif, " +
                "C_NONPAJAK AS nonPajak, " +
                "N_KOMPONEN_MERK AS merk, " +
                "N_KOMPONEN_SPEK AS spek, " +
                "I_PGUN_REKAM AS idPenggunaRekam, " +
                "D_PGUN_REKAM AS tglPenggunaRekam, " +
                "I_PGUN_UBAH AS idPenggunaUbah, " +
                "D_PGUN_UBAH AS tglPenggunaUbah " +
                "FROM TRBASKOMPONEN WHERE I_ID = :id";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        return jdbcTemplate.queryForObject(query, parameterSource, new RowMapper<KomponenEntity>() {
            @Override
            public KomponenEntity mapRow(ResultSet resultSet, int i) throws SQLException {
                KomponenEntity komponen = new KomponenEntity();
                komponen.setId(resultSet.getInt("id"));
                komponen.setIdBas(resultSet.getInt("idBas"));
                komponen.setKodeAkun(resultSet.getString("kodeAkun"));
                komponen.setKodeKomponen(resultSet.getString("kodeKomponen"));
                komponen.setNamaKomponen(resultSet.getString("namaKomponen"));
                komponen.setNamaSatuan(resultSet.getString("namaSatuan"));
                komponen.setHarga(resultSet.getBigDecimal("harga"));
                komponen.setStatus(resultSet.getString("status"));
                komponen.setAppoval(resultSet.getBoolean("approval"));
                komponen.setAktif(resultSet.getBoolean("aktif"));
                komponen.setNonPajak(resultSet.getBoolean("nonPajak"));
                komponen.setMerk(resultSet.getString("merk"));
                komponen.setSpek(resultSet.getString("spek"));
                komponen.setIdPenggunaRekam(resultSet.getInt("idPenggunaRekam"));
                komponen.setTglPenggunaRekam(resultSet.getTimestamp("tglPenggunaRekam"));
                komponen.setIdPenggunaUbah(resultSet.getInt("idPenggunaUbah"));
                komponen.setTglPenggunaUbah(resultSet.getTimestamp("tglPenggunaUbah"));
                return komponen;
            }
        });
    }

}
