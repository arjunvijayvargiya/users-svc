package org.ironstudios.userssvc.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

public class MongoAuditEntity {

    @CreatedDate
    LocalDate createdDate;

    @LastModifiedDate
    LocalDate lastModifiedDate;

}
