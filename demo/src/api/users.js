import request from "../utils/request"

/**
 * 后面的每次请求都是需要携带token的
 */

export const findUserList = (current, size, userVo)=>{
    return request({
        url: "/tb-user/findUserPage",
        method: 'post',
        // param 以问号的方式传递
        params: {
            current,
            size
        },
        // data 以json的方式传递
        data: userVo
    })
}

/**
 * 删除oss中的图片
 */

export const deleteImgFile = (file) => {
    return request({
        url: "/deleteImgFile",
        method: 'post',
        // param 以问号的方式传递
        params: {
           file
        },
    })
}

/**
 * 添加用户
 */

export const saveUser = (user) => {
    return request({
        url: "/tb-user/addUser",
        method: 'post',
        data: user
    })
}
