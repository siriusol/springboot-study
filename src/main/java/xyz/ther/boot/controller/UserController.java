package xyz.ther.boot.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import xyz.ther.boot.pojo.User;
import xyz.ther.boot.service.UserService;

@RestController
@Api(tags = "用户数据接口")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "查询用户", notes = "根据 id 查询用户")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户 id", required = true)
    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @ApiResponses({
    @ApiResponse(code = 200, message ="删除成功"),
    @ApiResponse(code = 500, message = "删除失败")})
    @ApiOperation(value = "删除用户",  notes = "通过 id 删除用户")
    @DeleteMapping("/user/{id}")
    public Integer deleteUserByid(@PathVariable Integer id) {
        return id;
    }

    @ApiOperation(value = "添加用户", notes ="传入用户名和地址添加一个用户")
    @ApiImplicitParams({
    @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, defaultValue = "Ther"),
    @ApiImplicitParam(paramType = "query", name = "address", value = "用户地址", required = true, defaultValue = "China")})
    @PostMapping("/user")
    public String addUser(@RequestParam String username, @RequestParam String address) {
        return username + ":" + address;
    }

    @ApiOperation(value = "修改用户", notes ="传入用户信息修改用户")
    @PutMapping("/user")
    public String updateUser(@RequestBody User user) {
        return user.toString();
    }

    @GetMapping("/ignore")
    @ApiIgnore
    public void ignoreMethod() {}
}
