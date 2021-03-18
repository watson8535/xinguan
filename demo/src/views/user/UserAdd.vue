<template>
  <div>
    <el-dialog v-bind="$attrs" v-on="$listeners" :visible.sync="showDialog" @open="onOpen" @close="onClose" title="添加用户">
      <el-row :gutter="15">
        <el-form ref="elForm" :model="user" :rules="rules" size="medium" label-width="80px">
          <el-col :span="24">
            <!-- 用户头像 -->
            <el-form-item label="用户头像">

              <!-- 头衔缩略图 -->
              <pan-thumb :image="image"/>
              <!-- 文件上传按钮 -->
              <el-button type="primary" icon="el-icon-upload" @click="imagecropperShow=true">更换头像
              </el-button>

              <!--
                v-show：是否显示上传组件
                :key：类似于id，如果一个页面多个图片上传控件，可以做区分
                :url：后台上传的url地址
                @close：关闭上传组件
                @crop-upload-success：上传成功后的回调 -->
              <image-cropper
                v-show="imagecropperShow"
                :width="300"
                :height="300"
                :key="imagecropperKey"
                :url="'/uploadImage'"
                field="file"
                @close="closeImage"
                @crop-upload-success="cropSuccess"/>

            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="用户名" prop="username">
              <el-input v-model="user.username" placeholder="请输入用户名" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
            <el-form-item label="昵称" prop="nickname">
              <el-input v-model="user.nickname" placeholder="请输入昵称" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input v-model="user.password" placeholder="请输入密码" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
            <el-form-item label="手机" prop="phoneNumber">
              <el-input v-model="user.phoneNumber" placeholder="请输入手机" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
             <el-form-item label="部门" prop="departmentId">
              <el-select v-model="user.departmentId" placeholder="请选择部门" clearable
                         :style="{width: '100%'}">
                <el-option
                  v-for="(item, index) in departments"
                  :key="index"
                  :label="item.name"
                  :value="item.id"
                  :disabled="item.disabled"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="性别" prop="sex">
              <el-radio-group v-model="user.sex" size="medium">
                <el-radio v-for="(item, index) in sexOptions" :key="index" :label="item.value"
                          :disabled="item.disabled">{{item.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="user.email" placeholder="请输入邮箱" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
            <el-form-item label="生日" prop="birth">
              <el-date-picker v-model="user.birth" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                              :style="{width: '100%'}" placeholder="请选择生日" clearable></el-date-picker>
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
      <div slot="footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import ImageCropper from '../../components/ImageCropper'
  import PanThumb from '../../components/PanThumb'
  import {deleteImgFile, saveUser} from '../../api/users'
  import {findDeptAndCount} from '../../api/dept'

  export default {
    name: 'UserAdd',
    components: {
      ImageCropper,
      PanThumb
    },
    // 接受父组件传递的值
    props:{
      addOrUpdateVisible:{
        type: Boolean,
        default: false
      },
      //获取不了父组件的值
      // departments: {
      //   type: Array,
      //   default: () => []
      // }
    },
    data() {
      return {
        user: {
          avatar: null,
          username: undefined,
          departmentId: undefined,
          nickname: undefined,
          sex: undefined,
          password: undefined,
          email: undefined,
          phoneNumber: undefined,
          birth: null,
        },
        departments: [],
        // 控制弹出框显示隐藏
        showDialog:false,
        imagecropperShow: false, // 是否显示上传组件
        imagecropperKey: 0, // 上传组件id
        image: 'https://wpimg.wallstcn.com/577965b9-bb9e-4e02-9f0c-095b41417191',
        rules: {
          username: [{
            required: true,
            message: '请输入用户名',
            trigger: 'blur'
          }],
          departmentId: [{
            required: true,
            message: '请选择部门',
            trigger: 'change'
          }],
          nickname: [{
            required: true,
            message: '请输入昵称',
            trigger: 'blur'
          }],
          sex: [{
            required: true,
            message: '性别不能为空',
            trigger: 'change'
          }],
          password: [{
            required: true,
            message: '请输入密码',
            trigger: 'blur'
          }],
          email: [{
            required: true,
            message: '请输入邮箱',
            trigger: 'blur'
          }],
          phoneNumber: [{
            required: true,
            message: '请输入手机',
            trigger: 'blur'
          }],
          birth: [{
            required: true,
            message: '请选择生日',
            trigger: 'change'
          }],
        },
        field101Action: 'https://jsonplaceholder.typicode.com/posts/',
        field101fileList: [],
        sexOptions: [{
          "label": "男",
          "value": 0
        },
        {
          "label": "女",
          "value": 1
        }],
      }
    },
    computed: {},
    watch: {
      // 监听 addOrUpdateVisible 改变
      addOrUpdateVisible(oldVal,newVal){
        this.showDialog = this.addOrUpdateVisible
      },
    },
    created() {
      this.getDeptAndCount()
    },
    mounted() {},
    methods: {
      onOpen() {

      },
      onClose() {
        this.$refs['elForm'].resetFields()
        this.$emit('changeShow', 'false')
      },
      close() {
        this.$emit('changeShow', 'false')
      },
      handelConfirm () {
        this.$refs['elForm'].validate(valid => {
          if (!valid) {
            return
          }
          console.log(this.user)
          this.addUser()
        })
      },
      field101BeforeUpload(file) {
        let isRightSize = file.size / 1024 / 1024 < 2
        if (!isRightSize) {
          this.$message.error('文件大小超过 2MB')
        }
        let isAccept = new RegExp('image/*').test(file.type)
        if (!isAccept) {
          this.$message.error('应该选择image/*类型的文件')
        }
        return isRightSize && isAccept
      },
      // 上传成功后的回调函数
      cropSuccess(data) {
        console.log(data)
        this.imagecropperShow = false
        var oldimage = this.image;
        this.image = data.data.url;
        this.user.avatar = this.image;
        // 上传成功后，重新打开上传组件时初始化组件，否则显示上一次的上传结果
        this.imagecropperKey = this.imagecropperKey + 1
        // 上传成功后删除之前的图片
        this.deleteAvatar(oldimage);
      },
      // 删除之前的图片
      async deleteAvatar(oldimage){
          const {data} = await deleteImgFile(oldimage);
          console.log(data);
      },
      // 关闭上传组件
      closeImage() {
        this.imagecropperShow = false
        // 上传失败后，重新打开上传组件时初始化组件，否则显示上一次的上传结果
        this.imagecropperKey = this.imagecropperKey + 1
      },
      // 上传用户
      async addUser () {
        const { data: res } = await saveUser(this.user)
        if (res.code == 200) {
          this.$notify.success({
            title: '操作成功',
            message: '用户添加成功',
          })
          //关闭
          this.$emit('changeShow', 'false')
          this.user = {}
          //this.$parent.getUserList();
          this.$emit('getUserList');
          this.$emit('getDeptAndCount');
          //this.$parent.getDeptAndCount();
        } else {
          return this.$message.error('用户添加失败:' + res.message)
        }
      },
      async getDeptAndCount(){
        const {data} = await findDeptAndCount();
        this.departments = data.data.department;
      },
    }

  }

</script>
<style>
  .el-upload__tip {
    line-height: 1.2;
  }

</style>