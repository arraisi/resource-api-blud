package com.tabeldata.service;

import com.tabeldata.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    LoginDao dao;


    public List<String> getTahunAnggaranByNrk(String nrk) {

        List<String> listTahunAnggaran = dao.getTahunAnggaranByNrk(nrk);
        Integer listLengt = listTahunAnggaran.size();
        Integer maxTahunAnggaranPlus1 = Integer.parseInt(listTahunAnggaran.get(listLengt - 1)) + 1;
        Integer minTahunAnggaranMinus1 = Integer.parseInt(listTahunAnggaran.get(0)) - 1;
        listTahunAnggaran.add(0, minTahunAnggaranMinus1.toString());
        listTahunAnggaran.add(listLengt - 1, maxTahunAnggaranPlus1.toString());

        Collections.sort(listTahunAnggaran);
        return listTahunAnggaran;
    }
}
