package transaction;

import org.springframework.beans.factory.annotation.Autowired;
import transaction.service.FooService;

import javax.annotation.Resource;

/**
 * Created by cdhong on 2017-12-27.
 */

public class TestFakeController {
    @Resource(name = "fooServiceServlet")
    FooService fooService;

    public void comniationTransactionTest(){
        try{
            fooService.insertFoo(new Foo());
        } catch (Exception ex){
            fooService.getFoo("");
        }
    }


}
