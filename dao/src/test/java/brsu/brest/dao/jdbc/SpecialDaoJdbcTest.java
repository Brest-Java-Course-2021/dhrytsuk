package brsu.brest.dao.jdbc;

import brsu.brest.dao.SpecialDao;
import brsu.brest.model.Special;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml","classpath*:test-dao.xml"})
public class SpecialDaoJdbcTest {

    @Autowired
    private SpecialDao specialDao;

    @Test
    public void findAllTest() {
        List<Special> specialty = specialDao.findAll();
        Assert.assertNotNull(specialty);
        Assert.assertTrue(specialty.size() > 0);
    }
}