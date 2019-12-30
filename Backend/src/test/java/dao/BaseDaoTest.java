package dao;

import com.plekhanov.webService.dao.BaseDao;
import com.plekhanov.webService.entities.BaseEntity;
import com.sun.org.glassfish.external.statistics.annotations.Reset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import javax.sql.DataSource;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;

@RunWith(SpringJUnit4ClassRunner.class)
public abstract class BaseDaoTest<T extends BaseEntity<ID>, DAO extends BaseDao<T, ID>, ID> {

    @Autowired
    private DataSource dataSource;

    public abstract DAO getDao();

    public abstract T create();

    public abstract void modify(T t);

    public abstract void assertObjects(T t1, T t2);


    /**
     * Отключает проверку ссылочной целостности (ограничение foreign key)
     */
    @Target(METHOD)
    @Retention(RUNTIME)
    public @interface DisableIntegrity{}


    public static class DisableIntegrityTestExecutionListener extends AbstractTestExecutionListener {

        private JdbcTemplate jdbcTemplate;
        @Override
        public void prepareTestInstance(TestContext testContext) throws Exception {
            super.prepareTestInstance(testContext);
        }

        @Override
        public void beforeTestMethod(TestContext testContext) throws Exception {
            super.beforeTestMethod(testContext);
        }

        @Override
        public void afterTestMethod(TestContext testContext) throws Exception {
            super.afterTestMethod(testContext);
        }
    }


    public static class ResetDbTestExecutionListener extends AbstractTestExecutionListener {
        @Override
        public void afterTestMethod(TestContext testContext) throws Exception {
            //DB.RESET_DB_STATEMENT.execute();
        }
    }

    @Retention(RUNTIME)
    @ContextConfiguration(classes = TestConfig.class)
    @TestExecutionListeners(listeners = {
            ResetDbTestExecutionListener.class,
            DisableIntegrityTestExecutionListener.class}, mergeMode = MERGE_WITH_DEFAULTS)
    public static @interface TestConfig{}

}
