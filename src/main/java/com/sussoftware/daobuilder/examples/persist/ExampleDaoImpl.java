package com.sussoftware.daobuilder.examples.persist;
/**
* Auto generated dao implementation class by DAO-Builder : Mon Nov 27 09:55:05 GMT 2023
*/

import com.sussoftware.daobuilder.examples.ExampleBO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ExampleDaoImpl implements ExampleDao {

	private static final Logger logger = LoggerFactory.getLogger(ExampleDaoImpl.class);

	private final NamedParameterJdbcTemplate jdbcTemplate;
	private final DataRowMapper dataRowMapper;

	public ExampleDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.dataRowMapper = new DataRowMapper();
	}
	@Override
	 public boolean create(ExampleBO data) throws SQLException {
		 logger.debug("Creating row {}",data);
		 final long l = data.getCreated();
		 final Timestamp from = Timestamp.from(Instant.ofEpochMilli(l));
		 Map<String, Object> parameters = new HashMap<>();
		 parameters.put(ExampleConstants.ID, data.getId());
		 parameters.put(ExampleConstants.NAME, data.getName());
		 parameters.put(ExampleConstants.SURNAME, data.getSurname());
		 parameters.put(ExampleConstants.CREATED, from);

		 this.jdbcTemplate.update(ExampleConstants.INSERT_STATEMENT, parameters);
	 return true;
	}
	@Override
	 public void update(ExampleBO data) throws SQLException {
		 logger.debug("Updating row {}",data);
		 final long l = data.getCreated();
		 final Timestamp from = Timestamp.from(Instant.ofEpochMilli(l));
		 Map<String, Object> parameters = new HashMap<>();
		 parameters.put(ExampleConstants.ID, data.getId());
		 parameters.put(ExampleConstants.NAME, data.getName());
		 parameters.put(ExampleConstants.SURNAME, data.getSurname());
		 parameters.put(ExampleConstants.CREATED, from);

		 this.jdbcTemplate.update(ExampleConstants.UPDATE_STATEMENT, parameters);
	}
	@Override
	 public List<ExampleBO> findAll() {
		 final List<ExampleBO> query = this.jdbcTemplate.query(ExampleConstants.SELECT_ALL_SQL, this.dataRowMapper);
	 return query;

	}
	@Override
	 public void delete(long id) {
		 logger.debug("Attempting to delete {}",id);
		 Map<String, Object> parameters = new HashMap<>();
		 parameters.put(ExampleConstants.ID, id);
		 this.jdbcTemplate.update(ExampleConstants.DELETE_ID_SQL, parameters);
	}
	@Override
	 public ExampleBO findById(long id) {
		 logger.debug("Attempting to findBy {}",id);
		 Map<String, Object> parameters = new HashMap<>();
		 parameters.put(ExampleConstants.ID, id);
		 final List<ExampleBO> query = this.jdbcTemplate.query(ExampleConstants.SELECT_BY_ID, parameters, this.dataRowMapper);
		 final ExampleBO data = (!query.isEmpty()) ? query.get(0) : null;
		 return data;
	}
	@Override
	 public ExampleBO findByName(String key) {
		 Map<String, Object> parameters = new HashMap<>();
		 parameters.put(ExampleConstants.NAME, key);
		 final List<ExampleBO> query = this.jdbcTemplate.query(ExampleConstants.SELECT_BY_CODE, parameters, this.dataRowMapper);
		 final ExampleBO data = (!query.isEmpty()) ? query.get(0) : null;
		 return data;
	}

	 private class DataRowMapper implements RowMapper<ExampleBO> {
		public ExampleBO mapRow(ResultSet rs, int rowNum) throws SQLException {
			long id = rs.getLong(ExampleConstants.ID);
			String name = rs.getString(ExampleConstants.NAME);
			long surname = rs.getLong(ExampleConstants.SURNAME);
			long created = rs.getLong(ExampleConstants.CREATED);
			 return new ExampleBO(id,name,surname,created);
		}
	}
}