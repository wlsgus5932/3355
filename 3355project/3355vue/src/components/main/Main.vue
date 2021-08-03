
<template>
<div>
  

<br>
<center>
<h2 style="margin-bottom:45px; margin-right:25%;">카테고리별 인기글 TOP3</h2>
</center>
<div v-bind:style="{color: fcolor}">
  
    <div class="container">
      <div class="row">
        <hr style="width:1000px;"> 
      <table class="table" style="width:300px; margin-right:50px; ">
        <thead>
          <tr>
            <th scope="col" colspan="2">자유게시판
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(freeboard, i) in $store.state.freeboardBestList" :key="i">
            <th scope="row">no.{{i+1}}</th>
            <td
            @click="
            $store.dispatch('getDetail', {bId: freeboard.board_id, uId: $session.get('userId')}), 
            $store.commit('setBoardId', freeboard.board_id),
            $store.commit('setCategoryName', freeboard.category)">
            <router-link v-bind:style="{color: fcolor}" :to="'/detail'" style="text-decoration: none; color:black;">{{freeboard.title}}</router-link>
              <span style="color:pink; float:right;">
                  <i class="fa fa-commenting-o" aria-hidden="true">
                    {{freeboard.commentCount}} </i>
                  | 
                    <i class="fa fa-heart" aria-hidden="true" style="color: #FF6464;">
                    {{freeboard.pop}}
                    </i>
                  </span>
            </td>
          </tr>
        </tbody>
      </table>

      <table class="table" style="width:300px; margin-right:50px;"
      >
        <thead>
          <tr>
            <th scope="col" colspan="2">축구게시판
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(soccer, i) in $store.state.soccerBestList" :key="i">
            <th scope="row">no.{{i+1}}</th>
            <td
            @click="$store.dispatch('getDetail', {bId: soccer.board_id, uId: soccer.user_id}), 
            $store.commit('setBoardId', soccer.board_id),
            $store.commit('setCategoryName', soccer.category)">
            <router-link :to="'/detail'" style="text-decoration: none; color:black;">{{soccer.title}}</router-link>
              <span style="color:pink; float:right;">
                  <i class="fa fa-commenting-o" aria-hidden="true">
                    {{soccer.commentCount}} </i>
                  | 
                    <i class="fa fa-heart" aria-hidden="true" style="color: #FF6464;">
                    {{soccer.pop}}
                    </i>
                  </span>
            </td>
          </tr>
        </tbody>
      </table>

      <table class="table" style="width:300px;">
        <thead>
          <tr>
            <th scope="col" colspan="2">야구게시판
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(baseball, i) in $store.state.baseballBestList" :key="i">
            <th scope="row">{{i+1}}</th>
            <td
            @click="$store.dispatch('getDetail', {bId: baseball.board_id, uId: baseball.user_id}), 
            $store.commit('setBoardId', baseball.board_id),
            $store.commit('setCategoryName', baseball.category)">
            <router-link :to="'/detail'" style="text-decoration: none; color:black;">{{baseball.title}}</router-link>
              <span style="color:pink; float:right;">
                  <i class="fa fa-commenting-o" aria-hidden="true">
                    {{baseball.commentCount}} </i>
                  | 
                    <i class="fa fa-heart" aria-hidden="true" style="color: #FF6464;">
                    {{baseball.pop}}
                    </i>
                  </span>
            </td>
          </tr>
        </tbody>
      </table>

      <table class="table" style="width:300px; margin-right:50px;">
        <thead>
          <tr>
            <th scope="col" colspan="2">농구게시판
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(basketball, i) in $store.state.basketballBestList" :key="i">
            <th scope="row">{{i+1}}</th>
            <td
            @click="$store.dispatch('getDetail', {bId: basketball.board_id, uId: basketball.user_id}),
            $store.commit('setBoardId', basketball.board_id),
            $store.commit('setCategoryName', basketball.category)">
            <router-link :to="'/detail'" style="text-decoration: none; color:black;">{{basketball.title}}</router-link>
              <span style="color:pink; float:right;">
                  <i class="fa fa-commenting-o" aria-hidden="true">
                    {{basketball.commentCount}} </i>
                  | 
                    <i class="fa fa-heart" aria-hidden="true" style="color: #FF6464;">
                    {{basketball.pop}}
                    </i>
                  </span>
            </td>
          </tr>
        </tbody>
      </table>

      <table class="table" style="width:300px; margin-right:50px;">
        <thead>
          <tr>
            <th scope="col" colspan="2">배구게시판
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(bollyball, i) in $store.state.bollyballBestList" :key="i">
            <th scope="row">{{i+1}}</th>
            <td
            @click="$store.dispatch('getDetail', {bId: bollyball.board_id, uId: bollyball.user_id}),
            $store.commit('setBoardId', bollyball.board_id),
            $store.commit('setCategoryName', bollyball.category)">
            <router-link :to="'/detail'" style="text-decoration: none; color:black;">{{bollyball.title}}</router-link>
              <span style="color:pink; float:right;">
                  <i class="fa fa-commenting-o" aria-hidden="true">
                    {{bollyball.commentCount}} </i>
                  | 
                    <i class="fa fa-heart" aria-hidden="true" style="color: #FF6464;">
                    {{bollyball.pop}}
                    </i>
                  </span>
            </td>
          </tr>
        </tbody>
      </table>

      <table class="table" style="width:300px;">
        <thead>
          <tr>
            <th scope="col" colspan="2">테니스게시판
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(tenis, i) in $store.state.tenisBestList" :key="i">
            <th scope="row">{{i+1}}</th>
            <td
            @click="$store.dispatch('getDetail', {bId: tenis.board_id, uId: tenis.user_id}),
            $store.commit('setBoardId', tenis.board_id),
            $store.commit('setCategoryName', tenis.category)">
            <router-link :to="'/detail'" style="text-decoration: none; color:black;">{{tenis.title}}</router-link>
              <span style="color:pink; float:right;">
                  <i class="fa fa-commenting-o" aria-hidden="true">
                    {{tenis.commentCount}} </i>
                  | 
                    <i class="fa fa-heart" aria-hidden="true" style="color: #FF6464;">
                    {{tenis.pop}}
                    </i>
                  </span>
            </td>
          </tr>
        </tbody>
      </table>

      <table class="table" style="width:300px;">
        <thead>
          <tr>
            <th scope="col" colspan="2">골프게시판
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(golf, i) in $store.state.golfBestList" :key="i">
            <th scope="row">{{i+1}}</th>
            <td
            @click="$store.dispatch('getDetail', {bId: golf.board_id, uId: golf.user_id}),
            $store.commit('setBoardId', golf.board_id),
            $store.commit('setCategoryName', golf.category)">
            <router-link :to="'/detail'" style="text-decoration: none; color:black;">{{golf.title}}</router-link>
              <span style="color:pink; float:right;">
                  <i class="fa fa-commenting-o" aria-hidden="true">
                    {{golf.commentCount}} </i>
                  | 
                    <i class="fa fa-heart" aria-hidden="true" style="color: #FF6464;">
                    {{golf.pop}}
                    </i>
                  </span>
            </td>
          </tr>
        </tbody>
      </table>
      </div>
<hr style="width:1000px;"> 
      <br>
      
      <br>

      <!-- <div class="container" >
        <b-embed
        style="margin-right:350px; width:1000px; height:500px;"
          type="iframe"
          aspect="16by9"
          src="https://www.youtube.com/embed/D4uM926h55w"
          
          allowfullscreen
        ></b-embed>
        <br>
        <br>
      </div> -->

    </div>

    
</div>

</div>
</template>

<script>
  export default {
    data() {
      return {
        fcolor : 'black'
      }
    },

    mounted() {
      this.$store.dispatch('getMainBestList', );

    if(this.$cookies.isKey('isDark')){
      this.fcolor = 'white'
    } else{
      this.fcolor = 'black'
    }

    },
    methods:{
      changeMainColor(){
        console.log('Main updated')
        if(this.$cookies.isKey('isDark')){
          this.fcolor = 'white'
        } else{
          this.fcolor = 'black'
        }
      },

      changeImage(image){
        
      }
    }
  }
</script>

