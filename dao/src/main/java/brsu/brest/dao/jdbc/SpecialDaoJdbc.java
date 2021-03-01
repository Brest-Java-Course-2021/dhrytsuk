package brsu.brest.dao.jdbc;

import brsu.brest.dao.SpecialDao;
import brsu.brest.model.Special;
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

    private static final String SQL_GET_ALL_SPECIALTY =
            "SELECT D.SPECIAL_ID, D.SPECIAL_NAME FROM SPECIAL AS D ORDER BY D.SPECIAL_NAME";

    private static final String SQL_GET_SPECIALTY_BY_ID =
            "SELECT D.SPECIAL_ID, D.SPECIAL_NAME FROM SPECIAL AS D WHERE D.SPECIAL_ID = :SPECIAL_ID";

    private static final String SQL_CREATE_SPECIALTY =
            "INSERT INTO SPECIAL (SPECIAL_NAME) VALUES ( :SPECIAL_NAME )";

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    RowMapper rowMapper = BeanPropertyRowMapper.newInstance(Special.class);

    public SpecialDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Special> findAll() {
        return namedParameterJdbcTemplate.query(SQL_GET_ALL_SPECIALTY, rowMapper );
    }

    @Override
    public Optional<Special> findById(Integer specialId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("SPECIAL_ID", specialId);
        return Optional.ofNullable((Special) namedParameterJdbcTemplate.queryForObject(SQL_GET_SPECIALTY_BY_ID, sqlParameterSource, rowMapper));
    }

    @Override
    public Integer create(Special special) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("SPECIAL_NAME", special.getSpecialName());
        namedParameterJdbcTemplate.update(SQL_CREATE_SPECIALTY, sqlParameterSource, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey().intValue());
    }

    @Override
    public Integer update(Special special) {
        return null;
    }

    @Override
    public Integer delete(Integer specialId) {
        return null;
    }
}
