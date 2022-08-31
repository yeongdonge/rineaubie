import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      redirect: "/home",
    },
    {
      path: "/home",
      name: "home",
      component: () => import("../views/HomeView.vue"),
    },
    {
      path: "/write",
      name: "write",
      component: () => import("../views/WriteView.vue"),
    },
    {
      path: "/read/:postId",
      name: "read",
      component: () => import("../views/ReadView.vue"),
      props: true,
    },
    {
      path: "/edit/:postId",
      name: "edit",
      component: () => import("../views/EditView.vue"),
      props: true,
    },
  ],
});

export default router;
