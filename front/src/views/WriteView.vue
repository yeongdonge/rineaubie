<script setup lang="ts">

import {ref} from "vue";

const title = ref("")
const content = ref("")


const write = function () {

}

</script>

<template>
  <div>
    <el-input v-model="title" placeholder="제목을 입력해주세요" />
  </div>

  <div class="mt-2">
    <el-input v-model="content" type="textarea" rows="15" />
  </div>

  <div class="mt-2">
    <el-button type="primary" @click="write()">글 작성완료</el-button>
  </div>

</template>

<style></style>
