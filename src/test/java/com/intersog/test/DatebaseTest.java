package com.intersog.test;

import com.intersog.test.model.City;
import com.intersog.test.model.Distance;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created with IntelliJ IDEA.
 * User: yurec
 * Date: 06.11.12
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
public class DatebaseTest {

    private MongoOperations mo;

    @Before
    public void setUp() {
        ApplicationContext ctx = new GenericXmlApplicationContext("mongo-config.xml");
        mo = (MongoOperations) ctx.getBean("mongoTemplate");
    }

    @Test
    public void basicCrudTest() {
        String cityName = "Test city ldgdflkglf";
        mo.remove(new Query(Criteria.where("name").is(cityName)), City.class);

        City city = new City(cityName);

        Assert.assertNull(city.getId());
        mo.save(city);
        Assert.assertNotNull(city.getId());

        City fetchedCity = mo.findOne(new Query(Criteria.where("id").is(city.getId())), City.class);
        Assert.assertEquals(city.getId(), fetchedCity.getId());

        fetchedCity = mo.findOne(new Query(Criteria.where("name").is(cityName)), City.class);
        Assert.assertEquals(city.getId(), fetchedCity.getId());
        Assert.assertEquals(cityName, fetchedCity.getName());

        mo.remove(new Query(Criteria.where("name").is(cityName)), City.class);
    }

    @Test
    public void behaviorTest() {
        City city1 = new City("Test city 1");
        mo.save(city1);
        City city2 = new City("Test city 2");
        mo.save(city2);

        Distance distance = new Distance();
        distance.setCity1Id(city1.getId());
        distance.setCity2Id(city2.getId());
        distance.setValue(1234.34);

        mo.save(distance);

        Assert.assertNotNull(distance.getId());

        Distance fetchedDistance = mo.findOne(Query.query(Criteria.where("city1Id").is(city1.getId())
                .and("city2Id").is(city2.getId())), Distance.class);

        Assert.assertEquals(distance.getValue(), fetchedDistance.getValue());
    }
}
