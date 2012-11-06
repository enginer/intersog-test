package com.intersog.test.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created with IntelliJ IDEA.
 * User: yurec
 * Date: 06.11.12
 * Time: 13:12
 * To change this template use File | Settings | File Templates.
 */
@Document
public class City {

    @Id
    private String id;
    private String name;

    public City() {
        this(null);
    }

    public City(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
