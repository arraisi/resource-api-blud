package com.tabeldata.service;

import com.tabeldata.dao.KomponenDao;
import com.tabeldata.entity.KomponenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class KomponenService {

    @Autowired
    KomponenDao dao;

    KomponenEntity getKomponenById(Integer id) {
        return dao.getById(id);
    }

}
