package dao;

import com.plekhanov.webService.dao.BaseDao;
import com.plekhanov.webService.entities.BaseEntity;
import com.sun.org.glassfish.external.statistics.annotations.Reset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
import java.lang.reflect.Method;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;

@RunWith(SpringJUnit4ClassRunner.class)
@BaseDaoTest.TestConfig
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


    /**
     * Логика аннотации @DisableIntegrity
     */
    public static class DisableIntegrityTestExecutionListener extends AbstractTestExecutionListener {

        private JdbcTemplate jdbcTemplate;
        @Override
        public void prepareTestInstance(TestContext testContext) {
            ApplicationContext ctx = testContext.getApplicationContext();
            this.jdbcTemplate = new JdbcTemplate(ctx.getBean(DataSource.class));
        }

        @Override
        public void beforeTestMethod(TestContext testContext) {
            Method testMethod = testContext.getTestMethod();
            if (testMethod.isAnnotationPresent(DisableIntegrity.class)) {
                jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE");
            }
        }

        @Override
        public void afterTestMethod(TestContext testContext) {
            Method testMethod = testContext.getTestMethod();
            if (testMethod.isAnnotationPresent(DisableIntegrity.class)) {
                jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE");
            }
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
    public @interface TestConfig{}

}
