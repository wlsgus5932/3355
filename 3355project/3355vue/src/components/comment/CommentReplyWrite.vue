<template>
  <div>
    <div style="width:500px;">
<b-form method="get">
        <b-form-textarea
        id="textarea-recomment"
        v-model="reCommentContent"
        :state="reCommentContent.length >= 2"
        :placeholder="commentUserId+'님에게 작성할 답글을 2글자 이상 작성해주세요'"
        rows="3">
        </b-form-textarea>
</b-form>
         <!-- <b-button style="float:right;" @click="$store.dispatch('addReComment', 
            {bId: $store.state.boardId, uId:$session.get('userId'), cId: commentId,
            content: reCommentContent})">확인</b-button> -->
            <b-button style="float:right;" @click="addReComment()">등록</b-button>
    </div> 
    <br><br>

  </div>
</template>

<script>
export default {
    props: {
        commentId: Number,
        commentUserId: String,
    },
    data(){
        return{
            reCommentContent: '',
        }
    },
    methods: {
        addReComment(){
            this.$store.dispatch('addReComment', 
            {bId: this.$store.state.boardId, uId:this.$session.get('userId'), 
            cId: this.commentId, ruId: this.commentUserId, content: this.reCommentContent});
            this.$store.commit('setRecommentCheck', false);
            this.reCommentContent = '';
        }
    }

}
</script>

<style>

</style>