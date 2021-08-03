<template>
<div>
<center><div class="col col-lg-8 col-sm-10" id = "Box">
              <h2>회원가입</h2><br><br>
            <form id = "login-form" method = "POST" @submit.prevent="addUser">
                      <div class="input-group mb-4">
                          <span class="input-group-text" id="inputGroup-sizing-default">&nbsp;아이디&nbsp;&nbsp;</span>
                          <input type="text" name = "userId" class="form-control" placeholder="아이디를 입력하세요." v-model="account.userId"
                          aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                          <button type="button" class="btn btn-secondary join" @click="checkUserId">중복확인</button>
                        </div>

                        <div class="input-group mb-4">
                            <p class="text-danger fw-bold alreadyId">{{idError}}</p>
                            <p class="text-success fw-bold alreadyId">{{idCorrect}}</p>
                        </div>

                        <div class="input-group mb-4">
                            <span class="input-group-text" id="inputGroup-sizing-default">비밀번호</span>
                            <input type="password" name = "userPw" class="form-control" placeholder="비밀번호를 입력하세요.(8자이상, 영어+숫자+특수문자)"
                            @keyup="checkUserPw"
                            v-model="account.userPw"
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
                        
                        <div class="input-group mb-4">
                            <span class="input-group-text" id="inputGroup-sizing-default">이메일</span>
                            <input type="text" name = "userEmail" class="form-control" placeholder="이메일을 입력하세요."
                            v-model="account.userEmail"
                            aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                              <span class="input-group-text" id="inputGroup-sizing-default">@</span>
                              <select class="form-control" name ="mailAddr"
                              v-model="account.mailAddr">
                                <option value="naver.com" selected>naver.com</option>
                                <option value="hanmail.net">hanmail.net</option>
                                <option value="hotmail.com">hotmail.com</option>
                                <option value="nate.com">nate.com</option>
                                <option value="yahoo.co.kr">yahoo.co.kr</option>
                                <option value="empas.com">empas.com</option>
                                <option value="dreamwiz.com">dreamwiz.com</option>
                                <option value="freechal.com">freechal.com</option>
                                <option value="lycos.co.kr">lycos.co.kr</option>
                                <option value="korea.com">korea.com</option>
                                <option value="gmail.com">gmail.com</option>
                                <option value="hanmir.com">hanmir.com</option>
                                <option value="paran.com">paran.com</option>
                                <option value="tmpbox.net">tmpbox.net</option>
                              </select>
                            <button type="button" class="btn btn-secondary join" @click="sendEmail">인증</button>
                        </div>

                        <div class="input-group mb-4" v-if="show">
                            <span class="input-group-text" id="inputGroup-sizing-default">인증번호</span>
                            <input type="text" name = "mailCode" class="form-control" placeholder="인증번호를 입력하세요"
                            aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"
                            v-model="inputKey">
                            <button type="button" class="btn btn-secondary join" @click="checkEmail">확인</button>
                        </div>

                        <div class="input-group mb-4">
                            <p class="text-danger fw-bold emailAcc">{{mailError}}</p>
                        </div>

                        <div class="btn_list">
                          <button type="button" class="btn btn-primary join" onClick="location.href='/'">취소</button>
                          <button type="submit" class="btn btn-success join">확인</button>
                        </div>
                </form>
        </div></center>
</div>
</template>

<script>
import axios from 'axios'

export default {
    data(){
    return{
        idError:"",
        idCorrect:"",
        idCnt:1,
        pwError:"",
        pwMatchError: "",
        pwCnt:1,
        pwMatchCnt:1,
        userRePw:"",
        mailCnt:1,
        show:false,
        mailError:"",
        mailKey:"",
        inputKey:"",

        account: {
        userId: "",
        userPw: "",
        userEmail: "",
        mailAddr: "naver.com"
        },
    }
  },
    methods:{
        addUser(){
            console.log(this.account);
            if(this.idCnt==0 && this.pwCnt==0 && this.pwMatchCnt==0 && this.mailCnt==0){
            axios.post('/api/user/accountReg',this.account)
                .then(res=>{
                    console.log(res)
                    alert("회원가입이 완료되었습니다.")
                    this.$router.push('/login')
                }).catch(err=>{
                    console.log(err)
                }).finally({
                    
                });
            } else{
                alert("입력이 완료되지 않았습니다.")
                return false
            }
        },

        checkUserId(){
            if(this.account.userId==""){
                this.idError="아이디를 입력하세요.";
            } else{
            console.log(this.account.userId);

            axios.get('/api/user/checkUserId?userId='+this.account.userId)
                .then(res=>{
                    console.log(res)
                    this.idCnt = res.data
                    if(this.idCnt){
                    this.idError="중복된 아이디입니다."
                    this.idCorrect=""
                } else{
                    this.idError=""
                    this.idCorrect="새로운 아이디 입니다."
                }
                }).catch(err=>{
                    console.log(err)
                }).finally({
                    
                });
            }
        },

        checkUserPw(){
            if(this.account.userPw.search(/[0-9]/g)>=0&&
            this.account.userPw.search(/[a-z]/ig)>=0&&
            this.account.userPw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi)>=0&&
            this.account.userPw.length>7){
                this.pwCnt=0;
            } else{
                this.pwCnt=1;
            }

            if(this.account.userPw.length==0){
                this.pwCnt=0;
                this.pwError="비밀번호를 입력하세요.";
            }

            if(this.pwCnt){
                this.pwError="비밀번호가 규칙에 어긋났습니다.";
            }
            
            if(this.account.userPw.length>0 && !this.pwCnt){
                this.pwError="";
            }
        },

        checkPwMatch(){
            if(this.account.userPw!=this.userRePw){
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

        sendEmail(){
            this.show = true;
            this.userEmailAddr = this.account.userEmail + '@' + this.account.mailAddr

            if(this.account.userEmail.length==0){
                mail.Error="이메일을 입력하세요."
            } else{
                axios.get('/api/user/checkEmail?userEmailAddr=' + this.userEmailAddr)
                .then(res=>{
                    console.log(res)
                    this.mailKey=res.data;
                    alert("이메일이 전송되었습니다. 인증번호를 입력하세요")
                }).catch(err=>{
                    console.log(err)
                }).finally({
                    
                });
            }
        },

        checkEmail(){
            if(this.inputKey == this.mailKey){
                alert("이메일 인증이 완료되었습니다.")
                this.mailCnt=0;
                this.show="false"
            } else{
                alert("인증번호를 잘못 입력하셨습니다.")
            }
        }
    }
}
</script>

<style>
    .joinput{
        margin-top: 40px;
    }
    .join{
        margin-left: 20px;
    }
</style>