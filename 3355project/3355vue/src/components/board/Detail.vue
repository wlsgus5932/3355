<template>
  <div>
    <div v-for="(detail,i) in $store.state.detail" :key="i">
      <b-button style="float:left;" @click="backButton()">목록</b-button>
      
      <br><br>
   <hr>

   <table class="table" style="width:98%">
            <thead>
                <tr class="table-active">
                    <th scope="col" style="text-align:left;">[{{$store.state.url}}] 제목:{{detail.title}}<br>
          {{detail.date}}</th>
          <th scope="col" style="text-align:right;">작성자:{{detail.user_id}}<br>
          조회: {{detail.hit}} / 댓글수: {{$store.state.commentCount}} / 
            추천수: {{$store.state.countPop}}</th>
				</tr>
			</thead>
		</table>

   <hr>
    <div id="content" style="text-align:left; height: 500px" v-html="detail.content">
    </div>

    <div style="margin-right:40%;">
      <!-- 하얀색 좋아요-->
      <i class="fa fa-heart-o fa-2x" aria-hidden="true" style="color: red; float:left;"
      v-show="$store.state.popCheck != true"
      @click="addPop()">
      <span style="color:black; font-size:20px;" 
      >{{$store.state.countPop}}</span>
      </i>
      
      <!-- 채워진 좋아요-->
      <i class="fa fa-heart fa-2x" aria-hidden="true" style="color: red; float:left;"
      v-show="$store.state.popCheck == true"
      @click="deletePop()">
      <span style="color:black; font-size:20px;"
      >{{$store.state.countPop}}</span>
      </i>
    </div>

    <div v-show="detail.user_id === userid" style="">
      <!-- 게시글 수정 버튼 -->
      <b-button pill variant="dark" @click="updateWrite()">
        <i class="fa fa-pencil" aria-hidden="true"></i>
      </b-button>

      <!-- 게시글 삭제 버튼+모달 -->
      <b-button pill v-b-modal.deletes variant="danger" style="margin:5px"
              v-show="$session.has('userId')">
              <i class="fa fa-trash-o" aria-hidden="true"></i>
             </b-button>

              <b-modal id="deletes" 
              hide-backdrop content-class="shadow" 
              @ok="deleteWrite()" >

                  정말로 <code>게시글</code>을
                  삭제 하시겠습니까?
              </b-modal>
        <!-- 게시글 삭제 버튼+모달 -->
    
    </div>
    <br>
    <hr>

    <Comment/>

  </div>

</div>
</template>

<script>
import Comment from '@/components/comment/Comment.vue'
import axios from 'axios'
export default {
  data(){
    return{
      userid: this.$session.get('userId'),
      like: 0,
      likebl: true,
      sss: '',
    }
  },
  components:{
    Comment
  },
  
  methods: {
    updateWrite(){
      var router = this.$router;
      router.push('/update')
    },

    addPop(){
      if(!this.$session.has('accesstoken')){
        alert('로그인을 해주세요')
      }else if(this.$store.state.detail[0].user_id !== this.userid){
      this.$store.dispatch('addPop', {bId: this.$store.state.boardId, uId: this.$session.get('userId')});
      alert('좋아요를 누르셨습니다')
      console.log(this.$session.get('userId'))
      }else{
        alert('내 게시글입니다')
      }
    },
    deletePop(){
      this.$store.dispatch("deletePop", {bId: this.$store.state.boardId, uId: this.$session.get('userId')});
      alert('좋아요가 취소되었습니다');
    },

    deleteWrite(){
      console.log(this.$store.state.detail[0].board_id)
      axios
        .get("/api/board/deleteWrite?bId="+this.$store.state.detail[0].board_id)
        .then(res => {
          console.log(this.$store.state.detail[0].board_id)
          alert('삭제되었습니다')
          this.$store.dispatch('getBestList', this.$store.state.categoryName)
          this.$store.dispatch('getData', {name: this.$store.state.categoryName, 
          page: 0, f:this.$store.state.searchSelected, s:this.$store.state.searchSearch})
          this.$store.dispatch('getDayCount', )
          this.$store.commit('changePage', {page:1, paging:1})

          this.$router.push('/board/'+this.$store.state.categoryName)
          })
        .catch(err => {
          console.log(err)
          console.log(this.$store.state.detail[0].board_id)
        });
    },

    backButton(){
      this.$store.dispatch('getData', {name: this.$store.state.categoryName, 
      page:this.$store.state.page, f:this.$store.state.searchSelected, s:this.$store.state.searchSearch})
      this.$store.dispatch('getBestList', this.$store.state.categoryName)
      this.$router.push('/board/'+this.$store.state.categoryName)
    }
  }
  

  
}
</script>

<style>
#content{
  word-break:break-word;
}
.tdcontent{
  word-break:break-word;
}

</style>