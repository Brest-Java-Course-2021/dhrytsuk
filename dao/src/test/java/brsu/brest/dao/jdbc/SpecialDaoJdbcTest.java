package brsu.brest.dao.jdbc;

import brsu.brest.dao.SpecialDao;
import brsu.brest.model.Special;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml","classpath*:test-dao.xml"})
public class SpecialDaoJdbcTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecialDaoJdbcTest.class);

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

    @Test(expected = IllegalArgumentException.class)
    public void createSpecialTest() {
        List<Special> specialty = specialDao.findAll();
        Assert.assertNotNull(specialty);
        Assert.assertTrue(specialty.size() > 0);

        specialDao.create(new Special("География"));

        List<Special> realSpecialty = specialDao.findAll();
        Assert.assertEquals(specialty.size()+1, realSpecialty.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createSpecialWithSameNameTest() {
        List<Special> specialty = specialDao.findAll();
        Assert.assertNotNull(specialty);
        Assert.assertTrue(specialty.size() > 0);

        specialDao.create(new Special("География"));
        specialDao.create(new Special("География"));

        List<Special> realSpecialty = specialDao.findAll();
        Assert.assertEquals(specialty.size()+1, realSpecialty.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createSpecialWithSameNameDiffCaseTest() {
        List<Special> specialty = specialDao.findAll();
        Assert.assertNotNull(specialty);
        Assert.assertTrue(specialty.size() > 0);

        specialDao.create(new Special("География"));
        specialDao.create(new Special("ГЕОГРАФИЯ"));

        List<Special> realSpecialty = specialDao.findAll();
        Assert.assertEquals(specialty.size()+1, realSpecialty.size());
    }

    @Test
    public void updateSpecialTest() {
        List<Special> specialty = specialDao.findAll();
        Assert.assertNotNull(specialty);
        Assert.assertTrue(specialty.size() > 0);

        Special special = specialty.get(0);
        special.setSpecialName("TEST_SPECIAL");
        specialDao.update(special);

        Optional<Special> realSpecial = specialDao.findById(special.getSpecialId());
        Assert.assertEquals("TEST_SPECIAL", realSpecial.get().getSpecialName());
    }

//    @Test
//    public void updateSpecialNotUniqueNameTest() {
//        List<Special> specialty = specialDao.findAll();
//        Assert.assertNotNull(specialty);
//        Assert.assertTrue(specialty.size() > 0);
//
//        Special special = specialty.get(0);
//        special.setSpecialName(specialty.get(1).getSpecialName());
//        specialDao.update(special);
//    }

//    @Test
//    public void testLogging() {
//        LOGGER.trace("Hello trace");
//        LOGGER.debug("Hello debug");
//        LOGGER.info("Hello info");
//        LOGGER.warn("Hello warn");
//        LOGGER.error("Hello error");
//    }

}