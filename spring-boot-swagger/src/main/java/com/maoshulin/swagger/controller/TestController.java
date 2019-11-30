package com.maoshulin.swagger.controller;

import com.maoshulin.swagger.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 描述: 测试接口类，使用注解版 @Api  @ApiOperation
 *
 * @author maoshulin 毛树林
 * @create 2017-10-26
 **/
@RestController
@RequestMapping("/user")
@Api(value = "TestController 测试代码，可以删除")
public class TestController {

    @ApiOperation(value = "hello测试方法", notes = "根据ID返回结果")
    @RequestMapping(value = "hello/{id}",method = RequestMethod.GET)
    public String SayHello(@PathVariable("id") String  IDS)
    {
        return "hello SwaggerUI !"+IDS.toString();
    }

    @ApiOperation(value = "添加用户", notes = "根据参数添加用户")
    @PostMapping(value = "/add")
    public  Object addUser()  {
            User user = new User();
            user.setId((long)12222222);
            user.setName("大神");
            user.setAge(20);
            return user;


    }

}
