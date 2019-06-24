package com.tabeldata.service.example;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.service.ServiceCrudDataTablesPattern;
import com.tabeldata.dao.example.TableExampleDao;
import com.tabeldata.entity.example.TableExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableExampleService implements ServiceCrudDataTablesPattern<TableExample, String> {

    @Autowired
    private TableExampleDao dao;

    @Override
    public Optional<TableExample> findId(String s) {
        return dao.findId(s);
    }

    @Override
    public List<TableExample> findAll() {
        return dao.findAll();
    }

    @Override
    public TableExample save(TableExample tableExample) {
        return dao.save(tableExample);
    }

    @Override
    public TableExample update(TableExample tableExample) {
        return dao.update(tableExample);
    }

    @Override
    public boolean remove(TableExample tableExample) {
        return dao.remove(tableExample);
    }

    @Override
    public boolean removeById(String s) {
        return dao.removeById(s);
    }

    @Override
    public DataTablesResponse<TableExample> datatables(DataTablesRequest<TableExample> params) {
        List<TableExample> datas = dao.datatables(params);
        Long rows = dao.datatables(params.getValue());
        return new DataTablesResponse<>(datas, params.getDraw(), rows, rows);
    }
}
