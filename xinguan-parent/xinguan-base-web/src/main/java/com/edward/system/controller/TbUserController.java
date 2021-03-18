package com.edward.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edward.response.Result;
import com.edward.system.entity.TbUser;
import com.edward.system.service.TbUserService;
import com.edward.system.viewobject.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *http://localhost:8081/swagger-ui.html swaggerUI
 *http://localhost:8081/doc.html knife4j
 * @author Edward
 * @since 2021-03-09
 */
@RestController
@RequestMapping("/tb-user")
//@CrossOrigin 解决跨域问题
@Api(value = "系统用户模块", tags = "系统用户接口")
public class TbUserController {

/*
    @Autowired
    private TbUserService tbUserService;

    @GetMapping("/findUsers")
    @ApiOperation(value = "用户列表", notes = "查询所有的用户信息")
//    public List<TbUser> findUsers(){
//        List<TbUser> list = tbUserService.list();
//        return list;
//    }
    public Result findUsers(){
        List<TbUser> list = tbUserService.list();
        return Result.ok().data("users", list);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询单个用户", notes = "通过ID查询对应的用户信息")
    public Result getUserById(@PathVariable("id") Long id){
        TbUser user = tbUserService.getById(id);
        if(user != null){
            return Result.ok().data("user", user);
        }else{
            throw new BusinessException(ResultCode.USER_NOT_FOUND_EXCEPTION.getCode(), ResultCode.USER_NOT_FOUND_EXCEPTION.getMessage());
        }
    }

*/

    /**
     * 分页查询用户列表
     * @return
     */

    @Resource
    private TbUserService userService;

    @GetMapping("/findUserList")
    @ApiOperation(value = "查询分页用户", notes = "通过页数和每页数量查询对应的用户信息")
    public Result findUserList(@RequestParam(required = true, defaultValue = "1")Integer current,
                               @RequestParam(required = true, defaultValue = "7")Integer size){
        //对用户进行分页，泛型中注入的就是用户实体类
        Page<TbUser> page = new Page<>(current, size);

        //单表的时候这个方法时非常好用的
//        LambdaQueryWrapper<TbUser> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(TbUser::getDepartmentId, 1);
//        Page<TbUser> userPage = userService.page(page, queryWrapper);

        Page<TbUser> userPage = userService.page(page);
        long total = userPage.getTotal();
        List<TbUser> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    /**
     * 分页 + 查询
     * @RequestBody 前端传给后端的是json数据 前端用post方法
     * @param current
     * @param size
     * @param userVo
     * @return
     */
    @PostMapping("/findUserPage")
    @ApiOperation(value = "", notes = "")
    public Result findUserPage(@RequestParam(required = true, defaultValue = "1")Integer current,
                               @RequestParam(required = true, defaultValue = "7")Integer size,
                               @RequestBody UserVo userVo){
        //对用户进行分页，泛型中注入的就是用户实体类
        QueryWrapper<TbUser> wrapper = getWrapper(userVo);

        Page<TbUser> page = new Page<>(current, size);
        IPage<TbUser> userPage = userService.findUserPage(page, wrapper);
        long total = userPage.getTotal();
        List<TbUser> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<TbUser> getWrapper(UserVo userVo){
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        if(userVo!=null){
            if(!StringUtils.isEmpty(userVo.getDepartmentId())){
                queryWrapper.eq("department_id", userVo.getDepartmentId());
            }
            if(!StringUtils.isEmpty(userVo.getUsername())){
                queryWrapper.eq("username", userVo.getUsername());
            }
            if(!StringUtils.isEmpty(userVo.getNickname())){
                queryWrapper.eq("nickname", userVo.getNickname());
            }
            if(!StringUtils.isEmpty(userVo.getEmail())){
                queryWrapper.eq("email", userVo.getEmail());
            }
            if(!StringUtils.isEmpty(userVo.getSex())){
                queryWrapper.eq("sex", userVo.getSex());
            }
        }
        return queryWrapper;
    }

    @PostMapping("/addUser")
    @ApiOperation(value = "添加用户", tags = "用户添加")
    public Result addUser(@RequestBody TbUser user){
        try {
            userService.addUser(user);
            return Result.ok();
        }catch (Exception e){
            //需要打印日志
            return Result.error();
        }
    }

}

