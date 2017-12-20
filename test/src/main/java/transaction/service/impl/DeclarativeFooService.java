package transaction.service.impl;

import org.springframework.transaction.annotation.Transactional;
import transaction.Foo;
import transaction.service.FooService;


/**
 * Created by cdhong on 2017-12-12.
 */
@Transactional
public class DeclarativeFooService implements FooService {

    public Foo getFoo(String fooName) {
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
