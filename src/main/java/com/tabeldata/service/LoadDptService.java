package com.tabeldata.service;

import com.tabeldata.dao.LoadDptDao;
import com.tabeldata.entity.LoadDptEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class LoadDptService {

    @Autowired
    private LoadDptDao dao;

    public List<LoadDptEntity> getLoadPendapatan(String tahunAnggaran, String skpdId) {
        return dao.getLoadPendapatan(tahunAnggaran, skpdId);
    }
}
