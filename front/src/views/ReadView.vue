<script setup lang="ts">
import AlertModal from "../common/AlertModal.vue";
import { onMounted, ref } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

const router = useRouter();

const props = defineProps({
  postId: {
    type: [Number, String],
    required: true,
  },
});


const post = ref({
  id: 0,
  title: "",
  content: "",
});

const moveToEdit = () => {
  router.push({ name: "edit", params: { postId: props.postId } });
};

const deletePost = () => {
  axios.delete(`/api/posts/${props.postId}`).then(() => {
    router.replace({ name: "home" });
  });
}

onMounted(() => {
  axios.get(`/api/posts/${props.postId}`).then((response) => {
    post.value = response.data;
  });
});
</script>
<template>
  <el-row>
    <el-col>
      <h2 class="title">{{ post.title }}</h2>

      <div class="sub d-flex">
        <div class="category">개발</div>
        <div class="regDate">2020-07-30 20:33:59</div>
      </div>
    </el-col>
  </el-row>

  <el-row class="mt-3">
    <el-col>
      <div class="content">{{ post.content }}</div>
    </el-col>
  </el-row>

  <el-row class="mt-3">
    <el-col>
      <div class="d-flex justify-content-end">
        <el-button type="warning" @click="moveToEdit()">수정</el-button>
        <el-button type="danger" @click="deletePost()">삭제</el-button>
        <AlertModal v-if="showModal" @close="showModal = false">
            <h3 slot="header">
                경고!
                <i class="fa-solid fa-xmark closeModalBtn" @click="showModal = false"></i>
            </h3>
            <div slot="body">
                할 일을 입력해주세요.
            </div>
        </AlertModal>
      </div>
    </el-col>
  </el-row>

</template>

<style scoped lang="scss">
.title {
  font-size: 1.6rem;
  font-weight: 600;
  color: #383838;
  margin: 0;
}

.sub {
  margin-top: 10px;
  font-size: 0.78rem;

  .regDate {
    margin-left: 10px;
    color: #6b6b6b;
  }
}

.content {
  font-size: 0.95rem;
  margin-top: 12px;
  color: #616161;
  white-space: break-spaces;
  line-height: 1.5;
}
</style>
