package brsu.brest.dao.jdbc;

import brsu.brest.dao.SpecialDao;
import brsu.brest.model.Special;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SpecialDaoJdbc implements SpecialDao {

    private  static final String SQL_GET_ALL_SPECIALTY = "SELECT D.SPECIAL_ID, D.SPECIAL_NAME FROM SPECIAL AS D ORDER BY D.SPECIAL_NAME";

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SpecialDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Special> findAll() {
        return namedParameterJdbcTemplate.query(SQL_GET_ALL_SPECIALTY,new SpecialRowMapper());
    }

    private class SpecialRowMapper implements RowMapper<Special> {

        @Override
        public Special mapRow(ResultSet resultSet, int i) throws SQLException {
            Special special = new Special();
            special.setSpecialId(resultSet.getInt("SPECIAL_ID"));
            special.setSpecialName(resultSet.getString("SPECIAL_NAME"));
            return special;
        }
    }
}
