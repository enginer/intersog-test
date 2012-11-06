package com.intersog.test.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created with IntelliJ IDEA.
 * User: yurec
 * Date: 06.11.12
 * Time: 13:13
 * To change this template use File | Settings | File Templates.
 */
@Document
public class Distance {
    @Id
    private String id;
    private double value;
    private String city1Id;
    private String city2Id;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCity1Id() {
        return city1Id;
    }

    public void setCity1Id(String city1Id) {
        this.city1Id = city1Id;
    }

    public String getCity2Id() {
        return city2Id;
    }

    public void setCity2Id(String city2Id) {
        this.city2Id = city2Id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
