<template>
  <div>
    <!-- 将传递过来的MenuList数据循环遍历，绑定到菜单栏上，并添加css样式 -->
    <template v-for="item in this.menuList">
      <el-submenu :disabled="item.disable" :index="item.id+''" :key="item.id+''" v-if="item.children.length > 0">
        <template slot="title">
          <i :class="item.icon"></i>
          <span slot="title">{{ item.menuName }}</span>
        </template>
        <MenuTree :menuList="item.children"></MenuTree>
      </el-submenu>
      <!-- 菜单项 -->
      <!-- router:是否使用vue-router模式，启用该模式会在激活导航时以 index 作为path进行路径进行路由跳转 :index用url-->
      <el-menu-item v-else :disabled="item.disable" :index="item.url" :route="item.url" :key="item.id" @click="savePath(item.url)">
        <i :class="item.icon"></i>
        <span slot="title">{{ item.menuName }}</span>
      </el-menu-item>
    </template>
  </div>
</template>

<script>
export default {
  name: "MenuTree", // 模版名称
  // 在 MenuTree 中接收传递过来的参数
  props: ["menuList"],
  methods:{
    // 保存激活路径
    savePath(path){
      window.sessionStorage.setItem("activepath",path);
      // 调用Main.vue的activePath
      this.activePath = path;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  /* 实现了一个溢出处理 */
  .el-menu--collapse span, .el-menu--collapse i.el-submenu__icon-arrow{
    height: 0;
    width: 0;
    overflow: hidden;
    visibility: hidden;
    display: inline-block;
  }
</style>
