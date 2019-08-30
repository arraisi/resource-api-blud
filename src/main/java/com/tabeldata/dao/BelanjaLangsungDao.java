package com.tabeldata.dao;

import com.tabeldata.dto.KomponenBelanjaGetDto;
import com.tabeldata.entity.BelanjaLangsungEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BelanjaLangsungDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public void saveBelanjaLangsung(BelanjaLangsungEntity belanjaLangsungEntity) {

        String sql = "INSERT INTO TMRBABL (\n" +
                "I_ID,\n" +
                "I_IDKEGIATAN,\n" +
                "C_ANGG_TAHUN,\n" +
                "I_IDSKPD,\n" +
                "I_IDBIAYA,\n" +
                "V_ANGG_DPABP,\n" +
                "V_ANGG_DPABBJ,\n" +
                "V_ANGG_DPABM,\n" +
                "V_ANGG_TAPDBP,\n" +
                "V_ANGG_TAPDBBJ,\n" +
                "V_ANGG_TAPDBM,\n" +
                "I_PGUN_REKAM,\n" +
                "D_PGUN_REKAM,\n" +
                "I_PGUN_UBAH,\n" +
                "D_PGUN_UBAH\n" +
                ") VALUES" +
                "(:id, \n" +
                ":idKegiatan, \n" +
                ":tahunAngg, \n" +
                ":idSkpd, \n" +
                "0, \n" +
                ":anggaranDpaBp, \n" +
                ":anggaranDpaBbj, \n" +
                ":anggaranDpaBm, \n" +
                ":anggaranTapdBp, \n" +
                ":anggaranTapdBbj, \n" +
                ":anggaranTapdBm, \n" +
                ":idPenggunaRekam, \n" +
                ":tglPenggunaRekam, \n" +
                ":idPenggunaUbah, \n" +
                ":tglPenggunaUbah)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", belanjaLangsungEntity.getId());
        parameterSource.addValue("idKegiatan", belanjaLangsungEntity.getIdKegiatan());
        parameterSource.addValue("tahunAngg", belanjaLangsungEntity.getTahunAnggaran());
        parameterSource.addValue("idSkpd", belanjaLangsungEntity.getIdSkpd());
        parameterSource.addValue("anggaranDpaBp", belanjaLangsungEntity.getAnggaranDpaBp());
        parameterSource.addValue("anggaranDpaBbj", belanjaLangsungEntity.getAnggaranDpaBbj());
        parameterSource.addValue("anggaranDpaBm", belanjaLangsungEntity.getAnggaranDpaBm());
        parameterSource.addValue("anggaranTapdBp", belanjaLangsungEntity.getAnggaranTapdBp());
        parameterSource.addValue("anggaranTapdBbj", belanjaLangsungEntity.getAnggaranTapdBbj());
        parameterSource.addValue("anggaranTapdBm", belanjaLangsungEntity.getAnggaranTapdBm());
        parameterSource.addValue("idPenggunaRekam", belanjaLangsungEntity.getIdPenggunaRekam());
        parameterSource.addValue("tglPenggunaRekam", belanjaLangsungEntity.getTglPenggunaRekam());
        parameterSource.addValue("idPenggunaUbah", belanjaLangsungEntity.getIdPenggunaUbah());
        parameterSource.addValue("tglPenggunaUbah", belanjaLangsungEntity.getTglPenggunaUbah());

        jdbcTemplate.update(sql, parameterSource);
    }

    public void updateAnggaran(Integer id, Integer idKegiatan, String tahunAnggaran, Integer idSkpd, String tipeKomponen) {
        String sqlPegawai = "UPDATE " +
                "   TMRBABL SET\n" +
                "               V_ANGG_DPABP = (SELECT " +
                "                                     SUM(RINCI.V_ANGG_TAPD) " +
                "                               FROM TMRBABLRINCI RINCI JOIN TRBAS TRB ON RINCI.I_IDBAS = TRB.I_ID \n" +
                "                               WHERE I_IDKEGIATAN = :idKegiatan AND C_ANGG_TAHUN = :tahunAngg AND I_IDSKPD = :idSkpd AND TRB.C_AKUN LIKE '5.2.1%'),\n" +
                "              V_ANGG_TAPDBP = (SELECT " +
                "                                     SUM(RINCI.V_ANGG_TAPD) " +
                "                               FROM TMRBABLRINCI RINCI JOIN TRBAS TRB ON RINCI.I_IDBAS = TRB.I_ID \n" +
                "                               WHERE I_IDKEGIATAN = :idKegiatan AND C_ANGG_TAHUN = :tahunAngg  AND I_IDSKPD = :idSkpd AND TRB.C_AKUN LIKE '5.2.1%')\n" +
                "WHERE I_ID = :id";

        String sqlBarang = "UPDATE " +
                "   TMRBABL SET\n" +
                "               V_ANGG_DPABBJ = (SELECT " +
                "                                     SUM(RINCI.V_ANGG_TAPD) " +
                "                               FROM TMRBABLRINCI RINCI JOIN TRBAS TRB ON RINCI.I_IDBAS = TRB.I_ID \n" +
                "                               WHERE I_IDKEGIATAN = :idKegiatan AND C_ANGG_TAHUN = :tahunAngg AND I_IDSKPD = :idSkpd AND TRB.C_AKUN LIKE '5.2.2%'),\n" +
                "              V_ANGG_TAPDBBJ = (SELECT " +
                "                                     SUM(RINCI.V_ANGG_TAPD) " +
                "                               FROM TMRBABLRINCI RINCI JOIN TRBAS TRB ON RINCI.I_IDBAS = TRB.I_ID \n" +
                "                               WHERE I_IDKEGIATAN = :idKegiatan AND C_ANGG_TAHUN = :tahunAngg  AND I_IDSKPD = :idSkpd AND TRB.C_AKUN LIKE '5.2.2%') \n" +
                "WHERE I_ID = :id";

        String sqlModal = "UPDATE " +
                "   TMRBABL SET\n" +
                "               V_ANGG_DPABM = (SELECT " +
                "                                     SUM(RINCI.V_ANGG_TAPD) " +
                "                               FROM TMRBABLRINCI RINCI JOIN TRBAS TRB ON RINCI.I_IDBAS = TRB.I_ID \n" +
                "                               WHERE I_IDKEGIATAN = :idKegiatan AND C_ANGG_TAHUN = :tahunAngg AND I_IDSKPD = :idSkpd AND TRB.C_AKUN LIKE '5.2.3%'),\n" +
                "              V_ANGG_TAPDBM = (SELECT " +
                "                                     SUM(RINCI.V_ANGG_TAPD) " +
                "                               FROM TMRBABLRINCI RINCI JOIN TRBAS TRB ON RINCI.I_IDBAS = TRB.I_ID \n" +
                "                               WHERE I_IDKEGIATAN = :idKegiatan AND C_ANGG_TAHUN = :tahunAngg  AND I_IDSKPD = :idSkpd AND TRB.C_AKUN LIKE '5.2.3%') \n" +
                "WHERE I_ID = :id";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        parameterSource.addValue("idKegiatan", idKegiatan);
        parameterSource.addValue("tahunAngg", tahunAnggaran);
        parameterSource.addValue("idSkpd", idSkpd);

        switch (tipeKomponen) {
            case "pegawai":
                jdbcTemplate.update(sqlPegawai, parameterSource);
                break;
            case "barang":
                jdbcTemplate.update(sqlBarang, parameterSource);
                break;
            case "modal":
                jdbcTemplate.update(sqlModal, parameterSource);
                break;
            default:
                break;
        }
    }


    public Integer getIdByParam(Integer idKegiatan, String tahunAnggaran, Integer idSkpd) {
        String sql = "SELECT I_ID AS id FROM TMRBABL WHERE I_IDKEGIATAN = :idKegiatan AND C_ANGG_TAHUN = :tahunAngg AND I_IDSKPD = :idSkpd";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idKegiatan", idKegiatan);
        parameterSource.addValue("tahunAngg", tahunAnggaran);
        parameterSource.addValue("idSkpd", idSkpd);

        List<Integer> listId = jdbcTemplate.query(sql, parameterSource, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("id");
            }
        });

        if (!listId.isEmpty()) {
            return listId.get(0);
        } else {
            return null;
        }
    }

    public BelanjaLangsungEntity getById(Integer id) {
        String sql = "SELECT\n" +
                "I_ID               AS id,\n" +
                "I_IDKEGIATAN       AS idKegiatan,\n" +
                "C_ANGG_TAHUN       AS tahunAngg,\n" +
                "I_IDSKPD           AS idSkpd,\n" +
                "I_IDBIAYA          AS idBiaya,\n" +
                "V_ANGG_DPABP       AS anggaranDpaBp,\n" +
                "V_ANGG_DPABBJ      AS anggaranDpaBbj,\n" +
                "V_ANGG_DPABM       AS anggaranDpaBm,\n" +
                "V_ANGG_TAPDBP      AS anggaranTapdBp,\n" +
                "V_ANGG_TAPDBBJ     AS anggaranTapdBbj,\n" +
                "V_ANGG_TAPDBM      AS anggaranTapdBm,\n" +
                "I_PGUN_REKAM       AS idPenggunaRekam,\n" +
                "D_PGUN_REKAM       AS tglPenggunaRekam,\n" +
                "I_PGUN_UBAH        AS idPenggunaUbah,\n" +
                "D_PGUN_UBAH        AS tglPenggunaUbah\n" +
                "FROM TMRBABL WHERE I_ID = :id";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);

        return jdbcTemplate.queryForObject(sql, parameterSource, new RowMapper<BelanjaLangsungEntity>() {
            @Override
            public BelanjaLangsungEntity mapRow(ResultSet resultSet, int i) throws SQLException {
                BelanjaLangsungEntity belanjaLangsung = new BelanjaLangsungEntity();
                belanjaLangsung.setId(resultSet.getInt("id"));
                belanjaLangsung.setIdKegiatan(resultSet.getInt("idKegiatan"));
                belanjaLangsung.setTahunAnggaran(resultSet.getString("tahunAngg"));
                belanjaLangsung.setIdSkpd(resultSet.getInt("idSkpd"));
                belanjaLangsung.setAnggaranDpaBp(resultSet.getBigDecimal("anggaranDpaBp"));
                belanjaLangsung.setAnggaranDpaBbj(resultSet.getBigDecimal("anggaranDpaBbj"));
                belanjaLangsung.setAnggaranDpaBm(resultSet.getBigDecimal("anggaranDpaBm"));
                belanjaLangsung.setAnggaranTapdBp(resultSet.getBigDecimal("anggaranTapdBp"));
                belanjaLangsung.setAnggaranTapdBbj(resultSet.getBigDecimal("anggaranTapdBbj"));
                belanjaLangsung.setAnggaranTapdBm(resultSet.getBigDecimal("anggaranTapdBm"));
                belanjaLangsung.setIdPenggunaRekam(resultSet.getInt("idPenggunaRekam"));
                belanjaLangsung.setTglPenggunaRekam(resultSet.getTimestamp("tglPenggunaRekam"));
                belanjaLangsung.setIdPenggunaUbah(resultSet.getInt("idPenggunaUbah"));
                belanjaLangsung.setTglPenggunaUbah(resultSet.getTimestamp("tglPenggunaUbah"));
                return belanjaLangsung;
            }
        });
    }

    public BigDecimal getAnggaranByTipeKomponen(Integer idKegiatan, String tahunAnggaran, Integer idSkpd, String tipeKomponen) {
        String sqlPegawai = "SELECT V_ANGG_TAPDBP anggaran FROM TMRBABL WHERE I_IDKEGIATAN = :idKegiatan AND C_ANGG_TAHUN = :tahunAngg AND I_IDSKPD = :idSkpd";
        String sqlBarang = "SELECT V_ANGG_TAPDBBJ anggaran FROM TMRBABL WHERE I_IDKEGIATAN = :idKegiatan AND C_ANGG_TAHUN = :tahunAngg AND I_IDSKPD = :idSkpd";
        String sqlModal = "SELECT V_ANGG_TAPDBM anggaran FROM TMRBABL WHERE I_IDKEGIATAN = :idKegiatan AND C_ANGG_TAHUN = :tahunAngg AND I_IDSKPD = :idSkpd";
        String sql = "";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idKegiatan", idKegiatan);
        parameterSource.addValue("tahunAngg", tahunAnggaran);
        parameterSource.addValue("idSkpd", idSkpd);


        switch (tipeKomponen) {
            case "pegawai":
                sql = sqlPegawai;
                break;
            case "barang":
                sql = sqlBarang;
                break;
            case "modal":
                sql = sqlModal;
                break;
            default:
                break;
        }
        try {

            return jdbcTemplate.queryForObject(sql, parameterSource, new RowMapper<BigDecimal>() {
                @Override
                public BigDecimal mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getBigDecimal("anggaran");
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return new BigDecimal(0);
        }
    }

    public BelanjaLangsungEntity getAllAnggaran(Integer id) {
        String sql = "SELECT \n" +
                "V_ANGG_DPABP   AS anggaranPegawai,\n" +
                "V_ANGG_DPABBJ  AS anggaranBarang,\n" +
                "V_ANGG_DPABM   AS anggaranModal \n" +
                "FROM TMRBABL WHERE I_ID = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);

        try {
            return jdbcTemplate.queryForObject(sql, parameterSource, new RowMapper<BelanjaLangsungEntity>() {
                @Override
                public BelanjaLangsungEntity mapRow(ResultSet resultSet, int i) throws SQLException {
                    BelanjaLangsungEntity belanjaLangsung = new BelanjaLangsungEntity();
                    belanjaLangsung.setId(id);
                    belanjaLangsung.setAnggaranDpaBp(resultSet.getBigDecimal("anggaranPegawai"));
                    belanjaLangsung.setAnggaranDpaBbj(resultSet.getBigDecimal("anggaranBarang"));
                    belanjaLangsung.setAnggaranDpaBm(resultSet.getBigDecimal("anggaranModal"));
                    return belanjaLangsung;
                }
            });
        } catch (EmptyResultDataAccessException e) {
                    BelanjaLangsungEntity belanjaLangsung = new BelanjaLangsungEntity();
                    belanjaLangsung.setId(id);
                    belanjaLangsung.setAnggaranDpaBp(new BigDecimal(0));
                    belanjaLangsung.setAnggaranDpaBbj(new BigDecimal(0));
                    belanjaLangsung.setAnggaranDpaBm(new BigDecimal(0));
                    return belanjaLangsung;
        }
    }

}
