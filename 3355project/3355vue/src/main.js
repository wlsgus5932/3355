import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import BootstrapVue from 'bootstrap-vue'
import { Editor, Viewer } from '@toast-ui/vue-editor'
import VueSession from 'vue-session'
import VBTogglePlugin from 'bootstrap-vue'
import ModalPlugin from 'bootstrap-vue'
import FormFilePlugin from 'bootstrap-vue'
import BootstrapVueIcons from  'bootstrap-vue'
import IconsPlugin from 'bootstrap-vue'
import { JumbotronPlugin } from 'bootstrap-vue'
import { VueEditor } from "vue2-editor";
import VueCookies from "vue-cookies";


Vue.use(JumbotronPlugin)

Vue.use(VueCookies);

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import axios from 'axios'
import VueKakaoSdk from 'vue-kakao-sdk'
import GAuth from 'vue-google-oauth2'

const apiKey = '1137912f879fc634c5912ba913312b6d'

Vue.use(GAuth, {clientId: '680276129280-7f77qkv0brab5v0u2dp3nq3bs43mi65k.apps.googleusercontent.com', scope: 'profile email https://www.googleapis.com/auth/plus.login'})


var sessionOptions = {
  persist: true
}

Vue.use(VueSession, sessionOptions)
Vue.use(BootstrapVue)
Vue.use(VBTogglePlugin)
Vue.use(ModalPlugin)
Vue.use(FormFilePlugin)
Vue.use(BootstrapVueIcons)
Vue.use(IconsPlugin)


var sessionOptions = {
  persist: true
}
Vue.use(VueSession, sessionOptions)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

axios.defaults.headers.common = {
  "jwt-auth-token": router.app.$session.get('accesstoken'),
  'Access-Control-Allow-Origin' : '*'
};

Vue.use(VueKakaoSdk, { apiKey })

//인터셉터
axios.interceptors.request.use(
    config => {
        console.log('request 인터셉터 동작')
    // Request 보내기 전에 수행됨
    // config 자체에는 request의 정보가 다 들어있음. url, headers, data 등
    // config를 반환하거나, Promise.resolve(config)으로 반환하면 되는 듯
    console.log(config)
    return config
  },
  error => {
    // Request 수행 중 오류 발생 시 수행됨
    return Promise.reject(error);
  },
)

axios.interceptors.response.use(
    function (response) {
        console.log('response 인터셉터 동작')
//        http status가 200인 경우 응답 성공 직전 실행. 
//        .then()
      if (response.data.status) {
          // router.app.$store.commit('setAccount',response)
          router.app.$session.set("accesstoken", response.headers["jwt-auth-token"])
          router.app.$session.set("userId", response.data.data.userId)
          router.app.$session.set("message", 'Logout')
          router.app.$session.set("status", response.data.status)
          alert(response.data.data.userId + "님 환영합니다.")
          
          router.app.$router.go();
          router.app.$router.push('/')
        }
    
    
        return response;
    },

  function (error) {
    if (error.response.status == '500') {
      router.app.$router.push('/login')
      }
//        http status가 200이 아닌 경우 응답 에러 직전 실행.
//        .catch()
        return Promise.reject(error);
    }
);