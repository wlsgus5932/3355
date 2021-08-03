<template>
  <div>
    <div style="width:500px;">
        <b-form method="get">
            <b-form-textarea
            id="textarea-recomment"
            v-model="commentContent"
            :state="commentContent.length >= 2"
            placeholder="수정할 댓글을 2글자 이상 작성해주세요"
            rows="3">
            </b-form-textarea>
        </b-form>
         <!-- <b-button style="float:right;" @click="$store.dispatch('addReComment', 
            {bId: $store.state.boardId, uId:$session.get('userId'), cId: commentId,
            content: reCommentContent})">확인</b-button> -->
            <br>
            <b-button style="float:right;" @click="updateComment()">수정</b-button>
    </div> 
    <br><br>

  </div>
</template>

<script>
export default {
    props: {
        commentUpdateId: Number,
        commentUpdateUserId: String,
        commentUpdateContent: String,
    },
    data(){
        return{
            commentContent: this.commentUpdateContent,
        }
    },
    methods: {
        updateComment(){
            this.$store.dispatch('updateComment', 
            {cId: this.commentUpdateId, content: this.commentContent, bId:this.$store.state.boardId});
            this.$store.commit('setCommentUpdateCheck', false);
        },


        
    }

}
</script>

<style>

</style>