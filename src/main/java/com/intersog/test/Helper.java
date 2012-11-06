package com.intersog.test;

import com.intersog.test.model.City;
import com.intersog.test.model.Distance;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created with IntelliJ IDEA.
 * User: yurec
 * Date: 06.11.12
 * Time: 15:24
 * To change this template use File | Settings | File Templates.
 */
public class Helper {
    public static MongoOperations mo;

    public static void init() {
        if (mo == null) {
            ApplicationContext ctx = new GenericXmlApplicationContext("mongo-config.xml");
            mo = (MongoOperations) ctx.getBean("mongoTemplate");
        }
    }

    public static double findDistanceForCities(String cityName1, String cityName2) throws Exception {
        init();

        City city1 = mo.findOne(new Query(Criteria.where("name").is(cityName1)), City.class);

        if (city1 == null) {
           throw new Exception("City "+cityName1+" not found");
        }

        City city2 = mo.findOne(new Query(Criteria.where("name").is(cityName2)), City.class);

        if (city2 == null) {
           throw new Exception("City "+cityName2+" not found");
        }

        Distance distance = mo.findOne(new Query(Criteria.where("city1Id").is(city1.getId())
                .and("city2Id").is(city2.getId())), Distance.class);

        if (distance == null){
            throw new Exception("Distance not specified");
        }

        return distance.getValue();
    }

    public static Distance insertDistanceForCities(String cityName1, String cityName2, double distanceValue){
        init();

        City city1 = mo.findOne(new Query(Criteria.where("name").is(cityName1)), City.class);

        if (city1 == null) {
            city1 = new City(cityName1);
            mo.save(city1);
        }

        City city2 = mo.findOne(new Query(Criteria.where("name").is(cityName2)), City.class);

        if (city2 == null) {
            city2 = new City(cityName2);
            mo.save(city2);
        }

        Distance distance = mo.findOne(new Query(Criteria.where("city1Id").is(city1.getId())
                .and("city2Id").is(city2.getId())), Distance.class);

        if (distance == null) {
            distance = new Distance();
            distance.setCity1Id(city1.getId());
            distance.setCity2Id(city2.getId());
        }
        distance.setValue(distanceValue);
        mo.save(distance);

        return distance;
    }
}
