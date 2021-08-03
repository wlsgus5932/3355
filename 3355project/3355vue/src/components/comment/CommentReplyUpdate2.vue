<template>
  <div>
    <div style="width:500px;">
        <b-form method="get">
            <b-form-textarea
            id="textarea-recomment"
            v-model="RecommentContent"
            :state="RecommentContent.length >= 2"
            placeholder="수정할 댓글을 2글자 이상 작성해주세요"
            rows="3">
            </b-form-textarea>
        </b-form>
         <!-- <b-button style="float:right;" @click="$store.dispatch('addReComment', 
            {bId: $store.state.boardId, uId:$session.get('userId'), cId: commentId,
            content: reCommentContent})">확인</b-button> -->
            <br>
            <b-button style="float:right;" @click="updateReComment()">수정</b-button>
    </div> 
    <br><br>

  </div>
</template>

<script>
export default {
    props: {
        RecommentUpdateId: Number,
        RecommentUpdateUserId: String,
        RecommentUpdateContent: String,
    },
    data(){
        return{
            RecommentContent: this.RecommentUpdateContent,
        }
    },
    methods: {
        updateReComment(){
            this.$store.dispatch('updateReComment', 
            {rcId: this.RecommentUpdateId, content: this.RecommentContent, bId:this.$store.state.boardId});
            this.$store.commit('setReCommentUpdateCheck', false);
        },


        
    }

}
</script>

<style>

</style>