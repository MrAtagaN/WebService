package com.plekhanov.webService.persistence.dao.impl;


import com.plekhanov.webService.persistence.dao.BaseDao;
import com.plekhanov.webService.persistence.entities.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @param <T>  Тип объекта
 * @param <ID> Тип primary key
 */
public abstract class BaseDaoImpl<T extends BaseEntity<ID>, ID> implements BaseDao<T, ID> {

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    protected RowMapper<T> mapper;
    private final Object lock;


    public BaseDaoImpl(RowMapper<T> mapper) {
        this.mapper = mapper;
        this.lock = new Object();
    }



    @Override
    public T findById(@NotNull ID id) {
        return DataAccessUtils.singleResult(jdbcTemplate.query("SELECT * FROM " + getTableName() +
                " WHERE ID = ?", new Object[]{id}, mapper));
    }


    @Override
    public T findByIdForUpdate(@NotNull ID id) {
        return DataAccessUtils.singleResult(jdbcTemplate.query("SELECT * FROM " + getTableName() +
                " WHERE ID = ? FOR UPDATE ", new Object[]{id}, mapper));
    }


    @Override
    public ID saveOrUpdate(T t) {
        ID id = t.getId();
        if (id == null) {
            Map<String, Object> params = getParamMap(t);
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(getInsertQuery(), new MapSqlParameterSource(params), keyHolder, new String[]{"id"});
            return (ID) keyHolder.getKey();
        } else {
            synchronized (lock) {
                int count = namedParameterJdbcTemplate.update(getUpdateQuery(), getParamMap(t));
                if (count == 0) {
                    namedParameterJdbcTemplate.update(getInsertQuery(), getParamMap(t));
                }
            }
            return id;
        }
    }


//    @Override
//    public T save(T t) {
//        Map<String, Object> params = getParamMap(t);
//        if (t.getId == null) {
//            KeyHolder keyHolder = new GeneratedKeyHolder();
//            SqlParameterSource sqlParameterSource = new MapSqlParameterSource(params);
//            namedParameterJdbcTemplate.update(getInsertQuery(), sqlParameterSource, keyHolder, new String[]{"id"});
//            return findById((ID) keyHolder.getKey());
//        } else {
//            saveOrUpdate(t);
//            return findById(t.getId);
//        }
//    }


    @Override
    public void delete(@NotNull ID id) {
        jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ? ", id);
    }

    abstract String getTableName();

    abstract String getUpdateQuery();

    abstract String getInsertQuery();

    abstract Map<String, Object> getParamMap(T t);

}
