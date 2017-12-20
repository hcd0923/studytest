package transaction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import transaction.Foo;
import transaction.service.FooService;



/**
 * Created by cdhong on 2017-12-12.
 */

public class DefaultFooService implements FooService {
    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    public Foo getFoo(String fooName) {
//        platformTransactionManager.

        throw new UnsupportedOperationException();
    }

    public Foo getFoo(String fooName, String barName) {
        throw new UnsupportedOperationException();
    }

    public void insertFoo(Foo foo) {
        throw new UnsupportedOperationException();
    }

    public void updateFoo(Foo foo) {
        throw new UnsupportedOperationException();
    }



}
