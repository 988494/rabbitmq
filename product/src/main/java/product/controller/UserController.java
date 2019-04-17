package product.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import product.aop.ThrowAop;
import product.pojo.User;
import product.service.UserService;

import javax.validation.Valid;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/index")
    @ApiOperation("创客排名第一")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id1",value = "id1",dataType = "String",paramType ="query", allowEmptyValue = false)
    })
        public Boolean index(@Valid @RequestBody User user,@RequestParam("status") String status){
        boolean equals = "".equals(status);
        System.out.println(equals);
        Boolean save =null;
//        Boolean save = userService.save(user);
        String username = user.getUsername();
        return save;
    }

    @PostMapping("/index1")
    @ThrowAop()
    @ApiOperation("创客排名第er")
    public Boolean index1(@RequestBody User user){
        Boolean save = userService.save(user);
        new RuntimeException();
        return save;
    }
}
