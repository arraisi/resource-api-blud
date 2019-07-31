package com.tabeldata.service;

import com.tabeldata.dao.KasDao;
import com.tabeldata.entity.TmrKasEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class KasService {

    @Autowired
    private KasDao dao;

    public List<TmrKasEntity> findAll(String tahunAnggaran, String skpdId) {
        return dao.findAll(tahunAnggaran, skpdId);
    }

    @Transactional
    public void save(List<TmrKasEntity> tmr)  {
        dao.save(tmr);
    }

}
