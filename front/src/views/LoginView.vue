<script setup lang="ts">
import { ref } from "vue";

import axios from "axios";
import { useRouter } from "vue-router";
const title = ref("");
const content = ref("");

const router = useRouter();

const login = function () {
  axios
    .get("/api/oauth2/authorization/google", {
    })
    .then(() => {
      router.replace({ name: "main" });
    });
};
</script>

<template>
  <div>
    <el-input v-model="title" placeholder="제목을 입력해주세요" />
  </div>

  <div class="mt-2">
    <div class="d-flex justify-content-end">
      <el-button type="primary" @click="login()">로그인</el-button>
    </div>
  </div>
</template>

<style></style>
