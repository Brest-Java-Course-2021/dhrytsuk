package brsu.brest.dao.jdbc;

import brsu.brest.dao.SpecialDao;
import brsu.brest.model.Special;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SpecialDaoJdbc implements SpecialDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecialDaoJdbc.class);

    private static final String SQL_GET_ALL_SPECIALTY =
            "SELECT D.SPECIAL_ID, D.SPECIAL_NAME FROM SPECIAL AS D ORDER BY D.SPECIAL_NAME";

    private static final String SQL_GET_SPECIAL_BY_ID =
            "SELECT D.SPECIAL_ID, D.SPECIAL_NAME FROM SPECIAL AS D WHERE D.SPECIAL_ID = :SPECIAL_ID";

    private static final String SQL_CREATE_SPECIAL =
            "INSERT INTO SPECIAL (SPECIAL_NAME) VALUES ( :SPECIAL_NAME )";

    private static final String SQL_CHECK_SPECIAL_NAME =
            "SELECT COUNT(SPECIAL_ID) FROM SPECIAL WHERE lower(SPECIAL_NAME) = lower(:SPECIAL_NAME)";

    private static final String SQL_UPDATE_SPECIAL =
            "UPDATE SPECIAL SET SPECIAL_NAME = :SPECIAL_NAME WHERE SPECIAL_ID = :SPECIAL_ID";

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    RowMapper rowMapper = BeanPropertyRowMapper.newInstance(Special.class);

    public SpecialDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Special> findAll() {
        LOGGER.debug("Find all specialty");
        return namedParameterJdbcTemplate.query(SQL_GET_ALL_SPECIALTY, rowMapper );
    }

    @Override
    public Optional<Special> findById(Integer specialId) {
        LOGGER.debug("Find special by id: {}", specialId);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("SPECIAL_ID", specialId);
        return Optional.ofNullable((Special) namedParameterJdbcTemplate.queryForObject(SQL_GET_SPECIAL_BY_ID, sqlParameterSource, rowMapper));
    }

    @Override
    public Integer create(Special special) {
        long startTime = System.nanoTime();
        LOGGER.debug("Create special: {}", special);
        if (!isSpecialNameUnique(special)) {
            throw new IllegalArgumentException("Special with the same name already exists in BD.");
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("SPECIAL_NAME", special.getSpecialName());
        namedParameterJdbcTemplate.update(SQL_CREATE_SPECIAL, sqlParameterSource, keyHolder);
        Integer specialId = Objects.requireNonNull(keyHolder.getKey()).intValue();
        special.setSpecialId(specialId);
        long stopTime = System.nanoTime();
        LOGGER.debug("Execution time: {}", startTime-startTime);
        return specialId;
    }

    private boolean isSpecialNameUnique(Special special){
        return namedParameterJdbcTemplate.queryForObject(SQL_CHECK_SPECIAL_NAME,
                new MapSqlParameterSource("SPECIAL_NAME", special.getSpecialName()), Integer.class) == 0;
    }

    @Override
    public Integer update(Special special) {
        LOGGER.debug("Update special: {}", special);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("SPECIAL_NAME", special.getSpecialName())
                        .addValue("SPECIAL_ID", special.getSpecialId());
        return namedParameterJdbcTemplate.update(SQL_UPDATE_SPECIAL,sqlParameterSource);
    }

    @Override
    public Integer delete(Integer specialId) {
        LOGGER.debug("Delete special by id: {}", specialId);
        return null;
    }
}
