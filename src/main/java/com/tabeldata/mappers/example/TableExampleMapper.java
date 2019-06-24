package com.tabeldata.mappers.example;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import com.tabeldata.dto.example.TableExampleDto;
import com.tabeldata.entity.example.TableExample;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public class TableExampleMapper {

    @Mapper
    public interface Create extends ObjectMapper<TableExample, TableExampleDto.New> {
        TableExampleMapper.Create converter = Mappers.getMapper(TableExampleMapper.Create.class);
    }

    public interface Update extends ObjectMapper<TableExample, TableExampleDto.Update> {
        TableExampleMapper.Update converter = Mappers.getMapper(TableExampleMapper.Update.class);
    }
}
