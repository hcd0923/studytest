package transaction.service;

import org.springframework.transaction.annotation.Transactional;
import transaction.Foo;

/**
 * Created by cdhong on 2017-12-12.
 */
public interface FooService {

    Foo getFoo(String fooName);

    Foo getFoo(String fooName, String barName);


    void insertFoo(Foo foo);

    void updateFoo(Foo foo);

}