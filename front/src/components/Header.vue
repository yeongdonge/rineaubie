<template>
  <el-header>
    <nav>
      <el-menu
        mode="horizontal"
        :default-active="activeIndex"
        class="el-menu-demo"
        @select="handleSelect"
        router
      >
        <el-menu-item index="/login">Login</el-menu-item>
        <el-menu-item index="/home">Home</el-menu-item>
        <el-menu-item index="/write">글 작성</el-menu-item>
      </el-menu>
    </nav>
  </el-header>
</template>

<script setup lang="ts">
import { ref } from "vue";

const activeIndex = ref("1");
const activeIndex2 = ref("1");
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};
</script>

<style scoped>
.header {
  padding: 0;
  height: 60px;
}
</style>
