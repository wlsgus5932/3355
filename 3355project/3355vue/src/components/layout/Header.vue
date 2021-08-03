<template>
<div style="margin-bottom:100px;">
<div>
  <b-navbar toggleable="lg" type="dark" variant="dark" >
    
      <b-navbar-brand :to="'/'" style="text-decoration:none; color: white;">
      <b-img :src="require('@/assets/logo.png')" style="width:30px;"></b-img>
      3355

      <b-button @click="changeMode" variant="outline-light" style="margin:5px;">{{mode}}</b-button>
      </b-navbar-brand>
      


          
      <!-- Right aligned nav items -->
      <b-navbar-nav class="ml-auto" style="margin-left:auto">
        <span style="color:white; float:right;" v-show="$session.get('accesstoken')">
            <i class="fa fa-heart" aria-hidden="true" style="margin-right:5px;"></i>  
            <strong><span style="font-size:18px;">{{$session.get('userId')}}</span></strong>님, 안녕하세요^^* 
          </span>

        <!-- <p class="badge bg-secondary text-wrap" style="margin-top:11px; color:black; font-size:15px;">
            {{this.$session.get('userId')}}</p> -->
      </b-navbar-nav>
        
        <router-link to="/join">
        <b-button v-if="!this.$session.has('accesstoken')" variant="outline-light" style="margin:5px;">Join</b-button>
        </router-link>

        <b-button variant="outline-light" style="margin:5px" @click="logInOut">{{message}}</b-button>

        
      

  </b-navbar>
</div>


  <b-container fluid>
    <b-row>
      <b-col id="mainclass" :style="{ backgroundImage: 'url(' + MainImg + ')' }">
        <router-link to="/" style="width:50px; color: white; text-decoration:none;">
        <b-img :src="require('@/assets/logo.png')" style="width:50px;">
        </b-img>
        3355<br>
        </router-link>
      </b-col>
      
    </b-row>

  </b-container>
</div>
</template>
<script>
export default {
  data(){
    return{
      mode:'',
      message:'',
      MainImg: require('../../assets/mainimge.jpg'),
    }
  },
  methods: {
    changeImage(image){
      this.MainImg = require('../../assets/'+image)
    },
    logInOut(){
      if(this.message=='Login'){
        this.$router.push('/login').catch(()=>{});
        //this.$router.push('/login')
      } else{
      //   this.$store.commit('delAccount',{
      //     userId: '',
      //     message: 'Login',
      //     status: '',
      //     token: '',
      // })
        this.$session.destroy("accesstoken")
        this.$session.destroy("userId")
        this.$session.destroy("message")
        this.$session.destroy("status")
        alert('로그아웃되었습니다.')
        this.message = 'Login'
        this.$router.push('/login')
      }
    },

    changeMode(){
      if(this.$cookies.isKey('isDark')){
      this.mode = 'Light'
      this.$cookies.remove("isDark");
    } else{
      this.mode = 'Dark'
      this.$cookies.set("isDark", true);
    }
      this.$parent.changeColor()
    },

    
  },

  mounted(){
    if(this.$cookies.isKey('isDark')){
      this.mode = 'Dark'
    } else{
      this.mode = 'Light'
    }
    

    console.log('mounted')
    if(this.$session.has('accesstoken')){
      this.message = 'Logout'
    } else{
      this.message = 'Login'
    }
  },

  beforeUpdate(){
    console.log('updated')
    if(this.$cookies.isKey('isDark')){
      this.mode = 'Dark'
    } else{
      this.mode = 'Light'
    }
    if(this.$session.has('accesstoken')){
      this.message = 'Logout'
    } else{
      this.message = 'Login'
    }
  },
  
}
</script>
<style>
#mainclass{
  width: 100%;
  height: 300px;
  /* background-image: url('../../assets/mainimage.jpg'); */
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  text-align: center;
  line-height: 250px;
  color: rgba(30, 5, 5, 0.952);
}
</style>