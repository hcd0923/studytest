package test.transaction;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.context.support.StaticWebApplicationContext;
import transaction.Foo;
import transaction.service.FooService;
import transaction.service.impl.CombinationTransactionServletFooService;

/**
 * Created by cdhong on 2017-12-11.
 */

public class TransactionTest {


    @Test
    public void testTransactionPlatformTransactionManagerBasic(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/transaction/application-config.xml");

//      트렌젝션의 정의를 만듬
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//      정의 이름을 줌
        def.setName("SomeTxName");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        PlatformTransactionManager txManager =  ctx.getBean("txManager", PlatformTransactionManager.class);

//      정의를 주면 매니저가 현재 처리되야하는 status를 줌.
        TransactionStatus status = txManager.getTransaction(def);

        try {
            // execute your business logic here
        }
        catch (Exception ex) {
            txManager.rollback(status);

        }
        txManager.commit(status);

    }


    @Test
    public void testDefaultTransactionXmlConfig(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/transaction/application-config.xml");
        FooService fooService = (FooService) ctx.getBean("fooService");
        fooService.insertFoo (new Foo());
    }

    @Test
    public void testDeclarativeTransactionXmlConfig(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/transaction/declarative-transaction-application-config.xml");
        FooService fooService = (FooService) ctx.getBean("fooService");
        fooService.insertFoo (new Foo());
    }

    public void createUserTable(JdbcTemplate jdbcTemplate){
        jdbcTemplate.update("CREATE TABLE users (id INTEGER PRIMARY KEY, name VARCHAR(30), email  VARCHAR(50) )");
    }

    @Test
    public void testJdbcTempleteTransactionXmlConfig(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/transaction/jdbctemplete-transaction-application-config.xml");

        createUserTable(ctx.getBean("jdbcTemplete", JdbcTemplate.class));
        FooService fooService = (FooService) ctx.getBean("fooService");
        fooService.insertFoo (new Foo());
        fooService.getFoo("cdhong");
    }

    @Test
    public void testCombinationTempleteTransactionXmlConfig(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/transaction/jdbctemplete-transaction-application-config.xml");

        createUserTable(ctx.getBean("jdbcTemplete", JdbcTemplate.class));
        FooService fooService = (FooService) ctx.getBean("fooService");
        fooService.insertFoo (new Foo());
//        fooService.getFoo("cdhong");


        ClassPathXmlApplicationContext cacSevlet = new ClassPathXmlApplicationContext ("spring/transaction/servlet-context/combination-transaction-servlet-config.xml");
//        cacSevlet.refresh();
//        createUserTable(cacSevlet.getBean("jdbcTempleteServlet", JdbcTemplate.class));

//        cacSevlet.setConfigLocation();
        ClassPathXmlApplicationContext cac = new ClassPathXmlApplicationContext ("spring/transaction/combination-transaction-application-config.xml");
//        cacSevlet.setParent(cac);


          fooService = cacSevlet.getBean("fooServiceServlet", CombinationTransactionServletFooService.class);
//        fooService.insertFoo (new Foo());
        fooService.getFoo("cdhong");
    }

    @Test
    public void testRemindXmlConfig(){
        GenericApplicationContext gwc = new GenericXmlApplicationContext ("spring/transaction/combination-transaction-application-config.xml");
        gwc.refresh();
    }













}