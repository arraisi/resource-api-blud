package com.tabeldata.service;

import com.tabeldata.dao.LoginDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class LoginService {

    @Autowired
    private LoginDao dao;

    Calendar now = Calendar.getInstance();
    int year = now.get(Calendar.YEAR);
    String yearInString = String.valueOf(year + 1);

    public List<String> getTahunAnggaranByNrk(String nrk) throws SQLException {

        List<String> listTahunAnggaran = dao.getTahunAnggaranByNrk(nrk);
        if (listTahunAnggaran.isEmpty()) {
            return listTahunAnggaran;
        }
        Integer listLengt = listTahunAnggaran.size();
        log.info("TAHUN ANGGARAN: {}, LENGT: {}", listTahunAnggaran, listLengt);
        Integer maxTahunAnggaranPlus1 = Integer.parseInt(listTahunAnggaran.get(listLengt - 1)) + 1;
        Integer minTahunAnggaranMinus1 = Integer.parseInt(listTahunAnggaran.get(0)) - 1;
        listTahunAnggaran.add(0, minTahunAnggaranMinus1.toString());
        listTahunAnggaran.add(listLengt - 1, maxTahunAnggaranPlus1.toString());

        Collections.sort(listTahunAnggaran);
        return listTahunAnggaran;
    }
}
