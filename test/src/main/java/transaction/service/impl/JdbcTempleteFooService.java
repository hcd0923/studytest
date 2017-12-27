package transaction.service.impl;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import transaction.Foo;
import transaction.service.FooService;


/**
 * Created by cdhong on 2017-12-12.
 */

public class JdbcTempleteFooService implements FooService {

    Logger logger = LoggerFactory.getLogger(JdbcTempleteFooService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Foo getFoo(String fooName) {
        logger.debug(jdbcTemplate.queryForList("SELECT * FROM USERS WHERE NAME = 'cdhong' ").toString());
        return null;
    }

    public Foo getFoo(String fooName, String barName) {
        throw new UnsupportedOperationException();

    }

    @Transactional
    public void insertFoo(Foo foo) {
        jdbcTemplate.update("INSERT INTO USERS VALUES (1, 'cdhong', 'cdhong@gmail.com')");
        jdbcTemplate.update("INSERT INTO USERS VALUES (2, 'alex', 'alex@yahoo.com')");
        jdbcTemplate.update("INSERT INTO USERS VALUES (3, 'joel', 'joel@gmail.com')");
//        getFoo("", "");
    }

    public void updateFoo(Foo foo) {
        throw new UnsupportedOperationException();
    }



}
