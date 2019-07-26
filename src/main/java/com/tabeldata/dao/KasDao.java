package com.tabeldata.dao;

import com.tabeldata.entity.KasEntity;
import com.tabeldata.entity.TmrKasEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Repository
public class KasDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<TmrKasEntity> findAll() {
        String baseQuery =
                "SELECT \n" +
                        "\tnvl(tmrbakasblud.i_id,-1) idTmrbakasBlud \n" +
                        "  \t,tmrba.c_angg_tahun  canggTahun\n" +
                        "  \t,tmrba.i_idskpd  iidSkpd\n" +
                        "  \t,trrbaskpd.c_skpd  cskpd\n" +
                        "  \t,trrbaskpd.n_skpd  nskpd\n" +
                        "  \t,trbas.i_id  iid\n" +
                        "  \t,c_akun  cakun\n" +
                        "  \t,n_akun  nakun\n" +
                        "  \t,nvl(tmrbakasblud.v_kas,0) vkas\n" +
                        "  \t,nvl(tmrbakasblud.v_kas_audited,0) vkasAudited\t\n" +
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
                tmrKasEntity.setIdTmrbakasBlud(rs.getInt("idTmrbakasBlud"));
                tmrKasEntity.setCanggTahun(rs.getString("canggTahun"));
                tmrKasEntity.setIidSkpd(rs.getInt("iidSkpd"));
                tmrKasEntity.setCskpd(rs.getString("cskpd"));
                tmrKasEntity.setNskpd(rs.getString("nskpd"));
                tmrKasEntity.setIid(rs.getInt("iid"));
                tmrKasEntity.setCakun(rs.getString("cakun"));
                tmrKasEntity.setNakun(rs.getString("nakun"));
                tmrKasEntity.setVkas(rs.getBigDecimal("vkas"));
                tmrKasEntity.setVkasAudited(rs.getBigDecimal("vkasAudited"));
                return tmrKasEntity;
            }
        });

    }


    public void save(List<TmrKasEntity> value) {
        System.out.println(value);



        for (TmrKasEntity val: value) {
            if(val.getIdTmrbakasBlud() == null){

                String insertQuery = "INSERT INTO TMRBAKASBLUD " +
                        "(I_ID," +
                        "I_IDSKPD," +
                        "C_ANGG_TAHUN," +
                        "I_IDBAS," +
                        "V_KAS," +
                        "V_KAS_AUDITED," +
                        "D_PGUN_REKAM)" +
                        "VALUES (" +
                        ":iid," +
                        ":iidSkpd," +
                        ":canggTahun," +
                        ":iidBas," +
                        ":vkas," +
                        ":vkasAudited," +
                        ":dPgunRekam)";

                List<KasEntity> listID = new ArrayList<>();

                String getID = " SELECT MAX(I_IDMAX) + 1 iid  FROM TRRBANOMAX WHERE N_TABEL = :N_TABEL ";
                MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
                mapSqlParameterSource.addValue("N_TABEL", "TMRBAKASBLUD");
                System.out.println(getID);
                Integer getI_ID = jdbcTemplate.queryForObject(getID, mapSqlParameterSource, new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                        return resultSet.getInt("iid");
                    }
                });

                KasEntity kasEntity = new KasEntity();

                kasEntity.setIId(getI_ID);
                kasEntity.setIIdSkpd(val.getIidSkpd());
                kasEntity.setCAnggTahun(val.getCanggTahun());
                kasEntity.setIIdBas(val.getIid());
                kasEntity.setVkas(val.getVkas());
                kasEntity.setVkasAudited(val.getVkasAudited());
                kasEntity.setDPgunRekam(Date.valueOf(LocalDate.now()));

                listID.add(kasEntity);

                SqlParameterSource[] sqlinsert = SqlParameterSourceUtils.createBatch(listID);

                this.jdbcTemplate.batchUpdate(insertQuery,sqlinsert);
            }
        }


        SqlParameterSource[] sqlParameterSources = SqlParameterSourceUtils.createBatch(value);
        String queryBuilder = "UPDATE TMRBAKASBLUD\n" +
                "SET V_KAS = :vkas,\n" +
                "    V_KAS_AUDITED = :vkasAudited,\n" +
                "WHERE I_ID = :iid";


        this.jdbcTemplate.batchUpdate(queryBuilder, sqlParameterSources);
    }
}
