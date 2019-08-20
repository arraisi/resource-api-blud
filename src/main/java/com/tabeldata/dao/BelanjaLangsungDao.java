package com.tabeldata.dao;

import com.tabeldata.dto.KomponenBelanjaGetDto;
import com.tabeldata.entity.BelanjaLangsungEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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

}
