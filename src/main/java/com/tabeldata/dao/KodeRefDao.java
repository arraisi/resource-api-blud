package com.tabeldata.dao;

import com.tabeldata.dto.KodeRefDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class KodeRefDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<KodeRefDto> getKodeRef() {
        String sql = "SELECT c_org_fungsiref AS kodeFungsi,\n" +
                "       e_org_fungsi    AS keteranganKode\n" +
                "FROM trkoderef\n" +
                "WHERE trkoderef.c_org_fungsi = '02'\n" +
                "ORDER BY 1 ";

        Map<String, Object> param = new HashedMap<>();

        return this.namedParameterJdbcTemplate.query(sql, param, (rs, i) -> {
            KodeRefDto data = new KodeRefDto();
            data.setKodeFungsi(rs.getString("kodeFungsi"));
            data.setKeteranganKode(rs.getString("keteranganKode"));
            return data;
        });
    }
}
