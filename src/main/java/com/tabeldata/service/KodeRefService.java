package com.tabeldata.service;

import com.tabeldata.dao.KodeRefDao;
import com.tabeldata.dto.KodeRefDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class KodeRefService {

    @Autowired
    private KodeRefDao dao;

    public List<KodeRefDto> getKodeRef() {
        List<KodeRefDto> data = dao.getKodeRef();
        return data;
    }
}
