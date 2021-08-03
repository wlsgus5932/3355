<template>
  <center><div id = "Box">
            <form id = "login-form1" @submit.prevent="loginUser">
                  <center>
                    <div class="flex">
                      <h2>로그인</h2><br><br>
                      <p class="text-danger fw-bold alreadyId">{{loginError}}</p>
                        <div class="input-group mb-3 login">
                            <span class="input-group-text" id="inputGroup-sizing-default">&nbsp;아이디&nbsp;&nbsp;</span>
                            <input type="text" name = "userID" class="form-control" placeholder="아이디를 입력하세요."
                            aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"
                            v-model="user.userId"
                            >
                          </div>
                          <div class="input-group mb-3 login">
                              <span class="input-group-text" id="inputGroup-sizing-default">비밀번호</span>
                              <input type="password" name = "userPW" class="form-control" placeholder="비밀번호를 입력하세요."
                              aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"
                              v-model="user.userPw">
                            </div>
                        <div>
                        <button type="submit" class="btn btn-login btn-secondary">로그인</button>
                      </div><br>

                      <button type="button" class="btn btn-light" @click="findPw()" style="margin-top:2px; width:200px;">비밀번호 찾기</button><br><br>

                        <button type="button" class="btn btn1-naver btn1-login" @click="socialLogin('naver')">
                          <img :src="require('@/assets/naverBtn.png')">
                        </button><br/>

                        <button type="button" class="btn btn1-google btn1-login" @click="socialLogin('google')">
                          <img :src="require('@/assets/googleBtn.png')">
                        </button><br/>

                        <button type="button" class="btn btn1-kakao btn1-login" @click="socialLogin('kakao')">
                          <img :src="require('@/assets/kakaoBtn.png')">
                        </button><br/><br>
                      </div>
                  </center>
                </form>
            </div></center>
</template>

<script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
<script>
import axios from 'axios';

export default {
  data(){
    return{
      loginError:'',
      user: {
          userId: '',
          userPw: '',
        }
    }
  },
  methods: {
    loginUser() {
      console.log(this.user)
      axios.post('/api/user/login', this.user)
      .then((res) => {
        console.log("res:"+ res, res.data)
        console.log("res:"+ res.headers)
        // this.$store.commit('setAccount',res)
        // this.$session.set("accesstoken", res.headers["jwt-auth-token"])
        // alert(this.user.userId + "님 환영합니다.")
        // this.$router.push('/')
      })
      .catch((err) => {
        console.log(err, this.user)
        this.loginError="아이디 또는 비밀번호가 일치하지 않습니다."
      })
    },

    socialLogin(social){
      if(social=='kakao'){
        console.log('카카오 로그인')
        this.$kakao.Auth.login({
          success: function(result) {
            console.log(result);

            Kakao.Auth.setAccessToken(result.access_token)

            Kakao.API.request({
              url: '/v2/user/me',
              success: function(response) {

                  console.log(response)
                  console.log(response.kakao_account.profile.nickname)
                  console.log(response.kakao_account.email)

                  const userInfo = {
                    userId : response.kakao_account.profile.nickname,
                    userEmail : response.kakao_account.email,
                    social : 'kakao'
                  }

                  axios.post('/api/user/'+userInfo.social, {
                    userId : userInfo.userId,
                    userEmail : userInfo.userEmail
                    })
                    .then((res) => {
                      console.log("res:"+ res, res.data)
                      console.log("res:"+ res.headers["jwt-auth-token"])
                      console.log(res.data.status)
                      
                    })
                    .catch((err) => {
                      console.log(err)
                    })

              },
              fail: function(error) {
                  console.log(error);
                  alert('사용자 정보를 가져오는데 실패했습니다.')
              }
    });
          },
          fail: function(err) {
            console.log(err);
            alert('로그인 실패')
          },
        });

      } else if(social=='naver'){
        console.log('네이버 로그인')
         var url = 'https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=MzSUmeJju1MjLR9i8y_z&redirect_uri=http://3355project.duckdns.org/login&state=1234';
            window.location.replace(url);
      } else if(social=='google'){
        console.log('구글 로그인')

        this.$gAuth.signIn()
          .then(GoogleUser => {
            //on success do something
            console.log('GoogleUser', GoogleUser)
            console.log('GoogleUser', GoogleUser.getId())
            console.log('GoogleUser', GoogleUser.getBasicProfile().getName())
            console.log('GoogleUser', GoogleUser.getBasicProfile().getEmail())
            axios.post('/api/user/google', {
                    userId : GoogleUser.getBasicProfile().getName(),
                    userEmail : GoogleUser.getBasicProfile().getEmail()
                    })
                    .then((res) => {
                      console.log("res:"+ res, res.data)
                      console.log("res:"+ res.headers["jwt-auth-token"])
                      console.log(res.data.status)
                      
                    })
                    .catch((err) => {
                      console.log(err)
                    })

          })
          .catch(error  => {
            //on fail do something
            console.log(error)
          })
      }

    },

    findPw(){
      console.log('비밀번호찾기')
      this.$router.push('/findpw')
    },
  },
  mounted(){
    var self = this;
    if(this.$route.query.code!=undefined){
      console.log(this.$route.query.code)
      axios
          .get("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&client_id=MzSUmeJju1MjLR9i8y_z&client_secret=B6ibXk0Wmi&code="+this.$route.query.code+"&state="+this.$route.query.state)
          .then(res => {
            console.log('네이버 데이터 가져옴')
            console.log(res)
            console.log("Bearer "+res.data.access_token)
            const token = "Bearer "+res.data.access_token
            const headers = {
              "Authorization": token
            }
            console.log(res.status)
            if(res.status==200){
              console.log(token)
            axios
                .get("/api/user/naver/service",{headers})
                .then(res2 => {
                  console.log(res2)
                  console.log(res2.data.response.nickname)
                  console.log(res2.data.response.email)
                  axios
                    .post("/api/user/naver",{
                      userId : res2.data.response.nickname,
                      userEmail : res2.data.response.email
                    })
                    .then(res2 => {
                      console.log(res3)
                      this.$route.go()
                    })
                  .catch(err => {
                        console.log(err)
                    });

                })
              .catch(err => {
                    console.log(err)
                });

            }
          })
        .catch(err => {
              console.log(err)
          });

      
      

    }
  }
}
</script>

<style>
  .login{
    max-width: 400px;
    height:40px;
  }

  .btn-login{
    width: 400px;
    max-height:40px;
  }

  .btn1-login{
    width: 265px;
    margin: 0px;
  }

  .btn1-naver {
    padding: 0;
    margin : 0;
    background-color: #03c75a;
    border-color: #03c75a;
    color: #FFF; 
  }

  .btn1-google {
    padding: 0;
    margin : 0;
    background-color: #ffffff;
    border-color: #ffffff;
    color: #FFF; 
  }

  .btn1-kakao {
    padding: 0;
    margin : 0;
    background-color: #F7E600;
    border-color: #F7E600;
    color: #FFF; 
    
  }
</style>