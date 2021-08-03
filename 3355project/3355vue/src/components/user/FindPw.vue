<template>
   <center><div class="col col-lg-8 col-sm-10" id = "Box">
    <form id = "login-form1">
            <center>
            <div class="flex">
                <h2>비밀번호 찾기</h2><br><br>
                 <div class="input-group mb-4" v-if="showId">
                          <span class="input-group-text" id="inputGroup-sizing-default">&nbsp;아이디&nbsp;&nbsp;</span>
                          <input type="text" name = "userId" class="form-control" placeholder="아이디를 입력하세요." v-model="userId"
                          aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                          <button type="button" class="btn btn-secondary join" @click="checkUserId">확인</button>
                 </div>
                 <div class="input-group mb-4">
                            <p class="text-danger fw-bold alreadyId">{{idError}}</p>
                </div>

                 <div class="input-group mb-4" v-if="showKey">
                            <span class="input-group-text" id="inputGroup-sizing-default">인증번호</span>
                            <input type="text" name = "mailCode" class="form-control" placeholder="인증번호를 입력하세요"
                            aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"
                            v-model="inputKey">
                            <button type="button" class="btn btn-secondary join" @click="checkEmail">확인</button>

                            <div class="input-group mb-4">
                                <p class="text-danger fw-bold emailAcc">{{mailError}}</p>
                            </div>
                 </div>
                
                <div v-if="showPw">
                 <div class="input-group mb-4">
                            <span class="input-group-text" id="inputGroup-sizing-default">비밀번호</span>
                            <input type="password" name = "userPw" class="form-control" placeholder="비밀번호를 입력하세요.(8자이상, 영어+숫자+특수문자)"
                            @keyup="checkUserPw"
                            v-model="userPw"
                            ref="userPw"
                            aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                 </div>

                        <div class="input-group mb-4">
                            <p class="text-danger fw-bold failPw">{{pwError}}</p>
                        </div>

                 <div class="input-group mb-4">
                            <span class="input-group-text" id="inputGroup-sizing-default">비밀번호확인</span>
                            <input type="password" name = "userPw" class="form-control" placeholder="비밀번호를 다시 한번 입력하세요."
                            @keyup="checkPwMatch"
                            v-model="userRePw"
                            aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                 </div>
                        <div class="input-group mb-4">
                            <p class="text-danger fw-bold correctPw">{{pwMatchError}}</p>
                        </div>

                 <div class="btn_list">
                          <button type="button" class="btn btn-primary join" onClick="location.href='/'">취소</button>
                          <button type="button" class="btn btn-success join" @click="setPw">확인</button>
                        </div>
                </div>

                </div>
            </center>
        </form>
    </div></center>
</template>

<script>
import axios from 'axios'

export default {
    data(){
    return{
        showKey: false,
        showPw : false,
        showId : true,
        pwError : '',
        pwMatchError : '',
        mailError : '',
        userId : '',
        mailKey : '',
        inputKey : '',
        userPw : '',
        userRePw : '',
        idError : '',
        pwCnt : 1 ,
        idCnt : 1 ,
        pwMatchCnt : 1,
    }
  },
    methods:{
        checkUserId(){
            if(this.userId==""){
                this.idError="아이디를 입력하세요.";
            } else{
            console.log(this.userId);

            axios.get('/api/user/checkUserId?userId='+this.userId)
                .then(res=>{
                    console.log(res)
                    if(res.data){
                        axios.get('/api/user/sendEmail?userId=' + this.userId)
                        .then(res2=>{
                            this.showId = false
                            this.showKey =  true
                            this.idCnt = 0
                            console.log(res2)
                            this.mailKey=res2.data;
                            alert("이메일이 전송되었습니다. 인증번호를 입력하세요")
                        }).catch(err=>{
                            console.log(err)
                        }).finally({
                            
                        });
                        
                    } else{
                        this.idError="존재하지 않는 아이디입니다."
                    }
                }).catch(err=>{
                    console.log(err)
                }).finally({
                    
                });
            }
        },

        checkEmail(){
            if(this.inputKey == this.mailKey){
                this.showKey=false
                this.showPw=true
                alert("이메일 인증이 완료되었습니다.")
            } else{
                alert("인증번호를 잘못 입력하셨습니다.")
            }
        },

        checkUserPw(){
            if(this.userPw.search(/[0-9]/g)>=0&&
            this.userPw.search(/[a-z]/ig)>=0&&
            this.userPw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi)>=0&&
            this.userPw.length>7){
                this.pwCnt=0;
            } else{
                this.pwCnt=1;
            }

            if(this.userPw.length==0){
                this.pwCnt=0;
                this.pwError="비밀번호를 입력하세요.";
            }

            if(this.pwCnt){
                this.pwError="비밀번호가 규칙에 어긋났습니다.";
            }
            
            if(this.userPw.length>0 && !this.pwCnt){
                this.pwError="";
            }
        },

        checkPwMatch(){
            if(this.userPw!=this.userRePw){
                this.pwMatchCnt=1;
            } else{
                this.pwMatchCnt=0;
            }

            if(this.pwMatchCnt){
                this.pwMatchError="비밀번호가 일치하지 않습니다."
            } else{
                this.pwMatchError=""
            }
        },

        setPw(){
            if(this.idCnt==0 && this.pwCnt==0 && this.pwMatchCnt==0){
            axios.post('/api/user/setPw',{
                userId : this.userId,
                userPw : this.userPw
            })
                .then(res=>{
                    console.log(res)
                    alert("비밀번호 변경이 완료되었습니다.")
                    this.$router.push('/login')
                }).catch(err=>{
                    console.log(err)
                }).finally({
                    
                });
            } else{
                alert("입력이 완료되지 않았습니다.")
            }
        }
    }
}
</script>

<style>

</style>