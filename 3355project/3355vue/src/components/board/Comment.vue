<template>
<b-form method = "get">
    <div v-for="(com, i) in $store.state.commentList" :key="i"
    v-show="com.comment_board_id == $store.state.boardId"
    >
    <div style=" float:left;">
      <div class="">
      <b-button variant="secondary">
      <b-badge variant="light">
        <i class="fa fa-user" aria-hidden="true"></i>
        {{com.comment_user_id}} 님
        </b-badge>
      </b-button>

      
        <span style="word-break:break-all; width:100px;"> | {{com.content}} </span>
    </div>

    </div>
        <div>
        <span style="float:right;">| {{com.date}} |
        
        <!-- 댓글 수정 버튼-->
        <!-- <b-button pill variant="light" 
        v-show="com.comment_user_id = $session.get('userId')"
        @click="commentIdcheck(com.comment_id)">
        <i class="fa fa-pencil" aria-hidden="true"></i>
        </b-button> -->

        <!-- 댓글 삭제 버튼-->
        <b-button pill variant="light" v-show="com.comment_user_id == $session.get('userId')"
        @click="commentDelete(com.comment_id)">
        <i class="fa fa-trash-o" aria-hidden="true"></i>
        </b-button>
        </span>
        </div>
        <br>
        <br>
          
      </div>

        <br>

    <div>
      <div>
        
      </div>
        <b-form-textarea
        id="textarea-state"
        v-model="content"
        :state="content.length >= 2"
        placeholder="댓글을 2글자 이상 작성해주세요"
        rows="3">
        </b-form-textarea>
    </div> <br>
    <div style="float:right;">
        <b-button pill v-b-modal.modal-no variant="dark" style=""
          v-show="$session.has('userId')">
            확인 </b-button>

          <b-modal id="modal-no" 
          hide-backdrop content-class="shadow" 
          title="" @ok="addComment()" >

              정말로 <code>댓글</code>을
              등록 하시겠습니까?
          </b-modal>
        <br><br><br>
    </div>
</b-form>
</template>

<script>
export default {
    data() {
      return {
        content: '',
      }
    },

    mounted() {
        this.$store.dispatch('getCommentList', this.$store.state.boardId)
    },

    methods: {
      addComment(){
        this.$store.dispatch('addComment', 
        {content: this.content, uId: this.$session.get('userId'), bId:this.$store.state.boardId});
        this.content = '';
      },

      commentDelete(v){
        this.$store.dispatch('deleteComment', {bId: this.$store.state.boardId, cId: v})
      },

    }
  }
</script>

<style>

</style>