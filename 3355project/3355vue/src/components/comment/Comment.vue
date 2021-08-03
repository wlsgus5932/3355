<template>
<b-form method = "get">
    <div v-for="(com, i) in $store.state.commentList" :key="i"
    v-show="com.comment_board_id == $store.state.boardId"
    class="row" style="text-align:left;">

    <div style="float:left;">
      <b-button variant="secondary">
      <b-badge variant="light">
        <i class="fa fa-user" aria-hidden="true"></i>
        {{com.comment_user_id}} 님
        </b-badge>
      </b-button>

      <!-- 댓글 수정 버튼-->
        <b-button pill variant="light" 
        v-show="com.comment_user_id == $session.get('userId')"
        @click="commentUpdate(com.comment_id, com.comment_user_id, com.content)">
        <i class="fa fa-pencil" aria-hidden="true"></i>
        </b-button>
      <!-- 끝 -->

    <!-- 댓글 삭제 버튼-->
        <b-button pill variant="light" v-show="com.comment_user_id == $session.get('userId')"
        @click="commentDelete(com.comment_id)">
        <i class="fa fa-trash-o" aria-hidden="true"></i>
        </b-button>
    </div>
    <br><br>

    <!-- 댓글 수정 인풋 -->
        <div v-if="$store.state.commentUpdateCheck == true && commentUpdateId == com.comment_id">
        <CommentReplyUpdate :commentUpdateId="commentUpdateId" :commentUpdateUserId="commentUpdateUserId"
        :commentUpdateContent="commentUpdateContent"/>
        </div>
      <!-- 끝 -->

    <!-- 댓글 내용 출력 -->
      <table style="text-align:left; margin-left:10px;">
        <td style="">
        <span style="word-break:break-all; width:100px;"> {{com.content}} </span>
        </td>
      </table>
    <!-- 댓글 내용 출력 끝-->

      <br><br>
      <span> | {{com.date}} |


      <!-- 답글 버튼-->
      <b-button variant="light" @click="checks(com.comment_id, com.comment_user_id)">
        <b-badge variant="light" style="color:black;">
        답글
          <!-- <i class="fa fa-level-down fa-2x" aria-hidden="true"></i> -->
        </b-badge>
      </b-button>
      </span>
      <!-- 답글 버튼 끝-->

      <br>
      <br>
      <hr>

      <!-- 답글 추가 인풋 -->
        <div v-if="$store.state.reCommentCheck == true && commentId == com.comment_id">
        <CommentReplyWrite :commentId="commentId" :commentUserId="commentUserId"/>
        </div>
        <!-- -->


        <!-- 답글 리스트 -->
        <div class="row" style="">
            <div v-for="(reco, i) in $store.state.reCommentList" :key="i"
            v-show="reco.recomment_comment_id === com.comment_id" style="margin-left:50px;">
            <i class="fa fa-arrow-right" aria-hidden="true" style="float:left;"> </i>
        <!-- -->

            <!-- 답글 리스트 아이디 출력부분-->
            <span style="float:left;">
                <b-button variant="secondary">
                  <b-badge variant="light">
                    <i class="fa fa-user" aria-hidden="true"></i>
                    {{reco.recomment_user_id}} 님
                    </b-badge>
                  </b-button>
                  <span v-if="reco.recommnet_ruser_id !== reco.recomment_user_id">
                  <strong>"{{reco.recomment_ruser_id}}"</strong>님에게
                  </span>
              <!--  -->

                  <!-- 답글 수정 버튼-->
                    <b-button pill variant="light" 
                    v-show="reco.recomment_user_id == $session.get('userId')"
                    @click="RecommentUpdate(reco.recomment_id, reco.recomment_user_id, reco.recommentContent)">
                    <i class="fa fa-pencil" aria-hidden="true"></i>
                    </b-button>
                  <!-- 끝 -->

                  <!-- 답글 삭제 버튼 -->
                  <b-button pill variant="light" v-show="reco.recomment_user_id == $session.get('userId')"
                  @click="deleteReComment($store.state.boardId, reco.recomment_id)">
                    <i class="fa fa-trash-o" aria-hidden="true"></i>
                  </b-button>
                  <!-- -->

                  <!-- 답글 수정 인풋 -->
                  <div v-if="$store.state.RecommentUpdateCheck == true 
                  && RecommentUpdateId == reco.recomment_id">
                  <CommentReplyUpdate2 :RecommentUpdateId="RecommentUpdateId" :RecommentUpdateUserId="RecommentUpdateUserId"
                  :RecommentUpdateContent="RecommentUpdateContent"/>
                  </div>
                <!-- 끝 -->

                  <!-- 답글 내용 출력 -->
                  <table style="text-align:left; margin-left:10px;">
                    <td style="">
                    <span style="word-break:break-all; width:100px;"> {{reco.recommentContent}} </span>
                    </td>
                  </table>
                  <!-- -->

                  <span> | {{reco.recomment_date}} | 

                    <!-- 답글의 답글 버튼-->
                    <b-button variant="light" 
                    @click="recoChecks(reco.recomment_id, reco.recomment_user_id, com.comment_id)">
                      <b-badge variant="light" style="color:black;">
                      답글
                      </b-badge>
                    </b-button>

                  </span>
                  <hr>

                  <!-- 답글 추가 인풋 -->
                  <div v-if="$store.state.reCommentCheck2 == true && reCommentId == reco.recomment_id">
                  <CommentReplyWrite2 :commentId="commentId" :reCommentId="reCommentId" :reCommentUserId="reCommentUserId"/>
                  </div>
                  <!-- -->
                  


                
            </span>
            </div>
        </div>
        <!-- 답글 리스트 끝-->
      </div>

        <br>
        <br>

    <div>
        <b-form-textarea
        id="textarea-state"
        style="height:150px;"
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
import CommentReplyWrite from '@/components/comment/CommentReplyWrite.vue'
import CommentReplyWrite2 from '@/components/comment/CommentReplyWrite2.vue'
import CommentReplyUpdate from '@/components/comment/CommentReplyUpdate.vue'
import CommentReplyUpdate2 from '@/components/comment/CommentReplyUpdate2.vue'
export default {
    data() {
      return {
        content: '',
        comId: 0,
        commentId: 0,
        commentUserId: '',
        reCommentId: 0,
        reCommentUserId: '',
        commentId2: 0,
        commentUpdateId: 0,
        commentUpdateUserId: '',
        commentUpdateContent: '',
        RecommentUpdateId: 0,
        RecommentUpdateUserId: '',
        RecommentUpdateContent: '',
        
      }
    },

    components: {
        CommentReplyWrite,
        CommentReplyWrite2,
        CommentReplyUpdate,
        CommentReplyUpdate2,
    },

    // mounted() {
    //     this.$store.dispatch('getCommentList', this.$store.state.boardId)
    // },

    methods: {
      addComment(){
        this.$store.dispatch('addComment', 
        {content: this.content, uId: this.$session.get('userId'), bId:this.$store.state.boardId});
        this.content = '';
      },

      commentDelete(v){
        this.$store.dispatch('deleteComment', {bId: this.$store.state.boardId, cId: v})
      },

      checks(a,b){
        this.$store.commit('setRecommentCheck', !this.$store.state.reCommentCheck);
        this.commentId = a;
        this.commentUserId = b;
      },

      recoChecks(a, b, c){
        this.$store.commit('setRecommentCheck2', !this.$store.state.reCommentCheck2);
        this.reCommentId = a;
        this.reCommentUserId = b;
        this.commentId = c;
      },

      deleteReComment(a, b){
        this.$store.dispatch('deleteReComment', {bId: a, rId:b});
      },

      commentUpdate(a, b, c){
        this.$store.commit('setCommentUpdateCheck', !this.$store.state.commentUpdateCheck);
        this.commentUpdateId = a;
        this.commentUpdateUserId = b;
        this.commentUpdateContent = c;
      },

      RecommentUpdate(a, b, c){
        this.$store.commit('setReCommentUpdateCheck', !this.$store.state.RecommentUpdateCheck);
        this.RecommentUpdateId = a;
        this.RecommentUpdateUserId = b;
        this.RecommentUpdateContent = c;
      },

    }
  }
</script>

<style>

</style>