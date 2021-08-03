import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  //-------- Main Page ----------//
  { path: '/', name: 'Main', component: () => import('@/components/main/Main.vue'), meta:{unauthorized : true} },


  //-------- Board Page -----------//
  { path: '/board/*', name: 'list', component: () => import('@/components/board/List.vue'), 
  meta:{unauthorized : true}},
  { path: '/detail', name: 'detail', component: () => import('@/components/board/Detail.vue'), 
  meta:{unauthorized : true}},
  { path: '/comment', name: 'comment', component: () => import('@/components/comment/Comment.vue'), 
  meta:{unauthorized : true}},
  { path: '/write', name: 'write', component: () => import('@/components/board/Write.vue'), 
  meta:{unauthorized : true}},
  { path: '/update', name: 'update', component: () => import('@/components/board/Update.vue'), 
    meta: { unauthorized: true }
  },


  //--------- User Page ------------//
  { path: '/join', name: 'joing', component: () => import('@/components/user/Join.vue'), meta:{unauthorized : true, sign : true} },
  { path: '/login', name: 'login', component: () => import('@/components/user/Login.vue'), meta:{unauthorized : true, sign : true} },
  { path: '/findpw', name: 'findPw', component: () => import('@/components/user/FindPw.vue'), meta:{unauthorized : true, sign : true} },
];





const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

router.beforeEach(async (to, from, next) => {
    if (to.matched.some(record => record.meta.unauthorized) || router.app.$session.has("accesstoken")) {
      if (to.matched.some(record => record.meta.sign) && router.app.$session.has("accesstoken")) {
        return next('/');
      }
      return next();
    }
    alert('로그인 해주세요');
    return next('/login');
})

export default router