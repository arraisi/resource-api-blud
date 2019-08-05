package com.tabeldata.service;

import com.tabeldata.dao.UrusanDao;
import com.tabeldata.entity.UrusanEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UrusanService {

    @Autowired
    private UrusanDao dao;

    public List<UrusanEntity> getListUrusan() {
        List<UrusanEntity> urusan = dao.getListUrusan();
        return urusan;
    }
}
