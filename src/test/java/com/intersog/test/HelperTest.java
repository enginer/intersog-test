package com.intersog.test;

import com.intersog.test.model.City;
import com.intersog.test.model.Distance;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: yurec
 * Date: 06.11.12
 * Time: 15:30
 * To change this template use File | Settings | File Templates.
 */
public class HelperTest {
    @Test
    public void testHelper() throws Exception {
        Helper.init();
        Helper.mo.remove(Query.query(Criteria.where("name").is("city1")
                .orOperator(Criteria.where("name").is("city2"))), City.class);
        Helper.mo.remove(Query.query(Criteria.where("value").exists(true)), Distance.class);

        Distance d = Helper.insertDistanceForCities("city1", "city2", 75);

        assertNotNull(d);
        assertNotNull(d.getValue());
        assertNotNull(d.getId());
        assertNotNull(d.getCity1Id());
        assertNotNull(d.getCity2Id());

        assertEquals(Double.valueOf(75), Helper.findDistanceForCities("city1", "city2"));

        Helper.mo.remove(Query.query(Criteria.where("name").is("city1")
                .orOperator(Criteria.where("name").is("city2"))), City.class);
        Helper.mo.remove(Query.query(Criteria.where("value").exists(true)), Distance.class);
    }
}
