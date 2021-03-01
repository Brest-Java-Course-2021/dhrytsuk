package brsu.brest.dao.jdbc;

import brsu.brest.dao.SpecialDao;
import brsu.brest.model.Special;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Test
    public void findByIdTest() {
        List<Special> specialty = specialDao.findAll();
        Assert.assertNotNull(specialty);
        Assert.assertTrue(specialty.size() > 0);

        Integer specialId = specialty.get(0).getSpecialId();
        Special expSpecial = specialDao.findById(specialId).get();
        Assert.assertEquals(specialId,expSpecial.getSpecialId());
        Assert.assertEquals(specialty.get(0).getSpecialName(),expSpecial.getSpecialName());
        Assert.assertEquals(specialty.get(0),expSpecial);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void findByIdExeptionalTest() {
        specialDao.findById(999).get();
    }

    @Test
    public void createSpecialTest() {
        List<Special> specialty = specialDao.findAll();
        Assert.assertNotNull(specialty);
        Assert.assertTrue(specialty.size() > 0);

        Special special = new Special("География");
        specialDao.create(special);

        List<Special> realSpecialty = specialDao.findAll();
        Assert.assertEquals(specialty.size()+1, realSpecialty.size());
    }

}