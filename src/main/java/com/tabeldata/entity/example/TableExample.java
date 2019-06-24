package com.tabeldata.entity.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "example_table")
public class TableExample {

    @Id
    @Column(name = "id", length = 64, nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "created_time", nullable = false)
    private Timestamp createdTime;

    @Column(name = "is_active")
    private Boolean active;

    @Column(name = "counter")
    private Integer counter;

    @Column(name = "currency")
    private BigDecimal currency;

    @Column(name = "description")
    @Type(type = "text")
    private String description;

    @Column(name = "floating")
    private Double floating;
}
