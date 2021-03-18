<template>
    <!-- 登陆容器 -->
    <div class="login_contianer">
        <!-- 登陆区域 -->
        <div class="login_box">
            <!-- 头像 -->
            <div class="avatar_box">
                <img src="../assets/Img/chuyin.gif">
            </div>
            <!-- 表单 -->
            <el-form :model="loginForm" :rules = "loginRule" ref="loginForm" label-width="0px" class = "login_form">
                <el-form-item label="" prop="username">
                    <el-input v-model="loginForm.username" prefix-icon="el-icon-user-solid"></el-input>
                </el-form-item>
                <el-form-item label="" prop="password">
                    <el-input v-model="loginForm.password" prefix-icon="el-icon-lock"></el-input>
                </el-form-item>
                <el-form-item label="" prop="verifyCode" class="verifycode">
                    <div class="verifycode_box">
                        <el-input v-model="loginForm.verifyCode" prefix-icon="el-icon-mobile" placeholder="请输入手机验证码" class="verifycode"></el-input>
                        <img src="../assets/Img/msF0DP.png" alt="" class="verifycode_img">
                    </div>
                </el-form-item>
                <el-form-item class="login_btn">
                    <el-button type="primary" @click="submitForm('loginForm')">立即创建</el-button>
                    <el-button @click="resetForm('loginForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>

</template>

<script>
export default {
    name: 'Login',
    data(){
        return {
            loginForm:{
                username: '',
                password: '',
                verifyCode: ''
            },
            loginRule:{
                username:[
                    {required: true, message: '请输入用户名', trigger:'blur'},
                    {min: 3, max: 9, message: '长度在3到9个字符', trigger: 'blur'}
                ],
                password:[
                    {required: true, message: '请输入密码', trigger:'blur'},
                    {min: 3, max: 9, message: '长度在3到9个字符', trigger: 'blur'}
                ],
                verifyCode:[
                    {required: true, message: '请输入验证码', trigger:'blur'}
                ]
            }
        };
    },
    methods:{
        submitForm(formname){
            this.$refs[formname].validate((valid) =>{
                if(valid){
                    this.$router.push("/main");
                }else{
                    console.log('error sumit');
                    return false;
                }
            });
        },
        resetForm(formname){
            this.$refs[formname].resetFields();
        }
    }
}
</script>

 <style scoped>
    .login_contianer{
        background-color: rgb(57, 197, 187);
        height: 100%;

    }
    .login_box{
        width: 500px;
        height: 380px;
        background-color: #fff;
        border-radius: 3px;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
    }
    .avatar_box{
        width: 130px;
        height: 130px;
        border: 1px solid #EEE;
        border-radius: 50%;
        padding: 10px;
        /* shadow 阴影 */
        box-shadow: 0 2px 12px 0 rgba(0,0,0,0.1);
        margin: -65px auto;
        background-color: #fff;
    }
    div.avatar_box img{
        width: 100%;
        height: 100%;
        border-radius: 50%;
        /* background-color: rgb(90, 224, 184); */

    }
    .login_form{
        position: absolute;
        bottom: 0px;
        padding: 0px 20px;
        width: 100%;
        box-sizing: border-box;
    }
    .login_btn{
        display: flex;
        justify-content: flex-end;
    }
    .verifycode_box, .verifycod, .verifycode_img {
        display: flex;
    }
    .verifycode{
        width: 100%;
        justify-content: left;
    }
    .verifycode_img{
        width: 40%;
        height: 35px;
        justify-content: flex-end;
    }


 </style>