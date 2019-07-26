package com.tabeldata.dao;

import com.tabeldata.entity.TmrKasEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class KasDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<TmrKasEntity> findAll() {
        String baseQuery =
                "SELECT \n" +
                        "\tnvl(tmrbakasblud.i_id,-1) id_tmrbakasblud \n" +
                        "  \t,tmrba.c_angg_tahun\n" +
                        "  \t,tmrba.i_idskpd\n" +
                        "  \t,trrbaskpd.c_skpd\n" +
                        "  \t,trrbaskpd.n_skpd\n" +
                        "  \t,trbas.i_id\n" +
                        "  \t,c_akun\n" +
                        "  \t,n_akun\n" +
                        "  \t,nvl(tmrbakasblud.v_kas,0) AS v_kas\n" +
                        "  \t,nvl(tmrbakasblud.v_kas_audited,0) AS v_kas_audited\t\n" +
                        "FROM  \n" +
                        "\ttmrba \n" +
                        "  \tINNER JOIN trrbaskpd ON tmrba.i_idskpd = trrbaskpd.i_id\n" +
                        "  \tINNER JOIN trrbaskpdbas ON trrbaskpd.i_id = trrbaskpdbas.i_idskpd\n" +
                        "  \tINNER JOIN trbas ON trrbaskpdbas.i_idbas = trbas.i_id\n" +
                        "  \tLEFT JOIN tmrbakasblud \n" +
                        "\t\tON tmrba.c_angg_tahun = tmrbakasblud.c_angg_tahun\n" +
                        "    \tAND tmrbakasblud.i_idskpd = trrbaskpdbas.i_idskpd\n" +
                        "    \tAND trrbaskpdbas.i_idbas = tmrbakasblud.i_idbas\n" +
                        "WHERE \n" +
                        "  \ttrbas.c_akun BETWEEN '1' AND '1.9' \n" +
                        "     AND tmrba.c_angg_tahun = '2020'\n" +
                        "     AND tmrba.i_idskpd = '12884' \n" +
                        "ORDER BY \n" +
                        "\ttmrba.c_angg_tahun,  trrbaskpd.c_skpd, C_AKUN";


        log.info("{}", baseQuery);

        return jdbcTemplate.query(baseQuery, new RowMapper<TmrKasEntity>() {
            @Override
            public TmrKasEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                TmrKasEntity tmrKasEntity = new TmrKasEntity();
                tmrKasEntity.setI_ID(rs.getInt("id_tmrbakasblud"));
                tmrKasEntity.setC_ANGG_TAHUN(rs.getString("C_ANGG_TAHUN"));
                tmrKasEntity.setI_IDSKPD(rs.getInt("I_IDSKPD"));
                tmrKasEntity.setC_SKPD(rs.getString("C_SKPD"));
                tmrKasEntity.setN_SKPD(rs.getString("N_SKPD"));
                tmrKasEntity.setI_ID(rs.getInt("I_ID"));
                tmrKasEntity.setC_AKUN(rs.getString("C_AKUN"));
                tmrKasEntity.setN_AKUN(rs.getString("N_AKUN"));
                tmrKasEntity.setV_KAS(rs.getBigDecimal("V_KAS"));
                tmrKasEntity.setV_KAS_AUDITED(rs.getBigDecimal("V_KAS_AUDITED"));
                return tmrKasEntity;
            }
        });

    }


    public void save(List<TmrKasEntity> value) {

        SqlParameterSource[] sqlParameterSources = SqlParameterSourceUtils.createBatch(value);
        System.out.println(value);
        String queryBuilder = "UPDATE TMRBAKASBLUD\n" +
                "SET V_KAS = :V_KAS,\n" +
                "    V_KAS_AUDITED = :V_KAS_AUDITED\n" +
                "WHERE I_ID = :I_ID";

        this.jdbcTemplate.batchUpdate(queryBuilder, sqlParameterSources);
    }
}
