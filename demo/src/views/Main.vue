<template>
  <el-container class="main_container">
    <!-- 头部布局 -->
    <el-header>
      <!-- logo和项目名称 -->
      <div class="left_box">
        <img src="../assets/Img/chuyin.gif" alt="" />
        <span>物资管理系统</span>
      </div>
      <!-- 用户登陆展示头像 -->
      <div class="right_box">
        <el-dropdown>
          <img src="../assets/Img/chuyin.gif" alt="" />
          <!-- 下拉菜单 -->
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item icon="el-icon-house">系统首页</el-dropdown-item>
            <el-dropdown-item icon="el-icon-ship">交流讨论</el-dropdown-item>
            <el-dropdown-item icon="el-icon-swich-button"
              >退出登陆</el-dropdown-item
            >
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>

    <el-container>
      <!-- 侧边栏 -->
      <el-aside :width="isCollapse ? '65px' : '200px'">
        <!-- 展开/折叠 -->
        <div class="toggle_box" @click="toggleCollapse">|||</div>
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          :collapse="isCollapse"
          :default-actives="activePath"
          :collapse-transition="false"
          :unique-opened="true"
          :router="true"
          background-color="#66CCFF"
          text-color="#fff"
          active-text-color="rgb(255, 226, 17)"
        >
          <!-- 将 Main.vue 中的 MenuList 数据传到 MenuTree 组件中去 -->
          <MenuTree :menuList="this.MenuList"></MenuTree>
        </el-menu>
      </el-aside>

      <!-- 主界面 -->
      <el-main>
        <!-- 路由视图 路由跳转的位置 要去router/index.js 下配置children -->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
//1. 引入MenuTree组件，放在componets文件夹下
import MenuTree from "../components/MenuTree.vue";

export default {
  name: "Main",
  data() {
    return {
      isCollapse: false,
      activePath: "",
      MenuList: [
        {
          id: 1,
          parentId: 1,
          menuName: "系统管理",
          url: "/system",
          icon: "el-icon-setting",
          orderNum: 2,
          open: 0,
          disable: false,
          perms: "system",
          type: 0,
          children: [
            {
              id: 226,//菜单/按钮id
              parentId: 1,//上级菜单
              menuName: "用户管理",//菜单/按钮名称
              url: "/users",//菜单url
              icon: "el-icon-user",//图标
              orderNum: 2,//排序
              open: 0,//0不展开 1展开
              disable: false,//0不可用 1可用
              perms: "users",//权限标识
              type: 0,//类型：0菜单 1按钮
              children: [],//子菜单
            },
          ],
        },
      ],
    };
  },
  // 2.将MenuTree组件添加到当前组件中
  components: {
    MenuTree,
  },
  methods: {
    toggleCollapse() {
      this.isCollapse = !this.isCollapse;
    },
  },
};
</script>

<style scoped>
/* 整个容器 */
.main_container {
  height: 100%;
}

/* 头部布局 */
.el-header {
  background-color: #39c5bb;
  /* background-color: rgb(255, 165, 0); */
  display: flex;
  justify-content: space-between;
  /* color:#39C5BB; */
  color: #fff;
  padding-left: 0;
  align-items: center;
}
.left_box,
.right_box {
  display: flex;
  align-items: center;
}
.left_box > img {
  width: 55px;
  height: 55px;
  margin: 10px 0 10px 10px;
}
.left_box > span {
  margin-left: 15px;
}
.el-dropdown > img {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  margin: 10px 0;
}
.right_box img {
  width: 55px;
  height: 55px;
}

/* 侧边栏 */
.el-aside,
.toggle_box {
  background-color: #66ccff;
  /* background-color: rgb(255, 226, 17); */
  /* color: rgb(255, 192, 203); */
  /* color: #fff; */
}
.el-menu {
  border-right: none;
}
.toggle_box {
  background-color: rgb(255, 165, 0);
  font-size: 15px;
  font-weight: bold;
  line-height: 25px;
  color: #fff;
  letter-spacing: 0.2em;
  text-align: center;
  cursor: pointer;
}

/* 内容主体 */
.el-main {
  background-color: #fff;
}

/* 下拉菜单样式 */
.el-dropdown-link {
  cursor: pointer;
  color: #39c5bb;
}
/* .el-icon-arrow-down {
  font-size: 12px;
} */
/* .demonstration{
        display: block;
        color: #444;
        font-size: 14px;
        margin-bottom: 20px;
    } */
</style>