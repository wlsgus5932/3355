<template>
  <div>
    <div style="width:500px;">
        <b-form method="get">
            <b-form-textarea
            id="textarea-recomment2"
            v-model="reCommentContent2"
            :state="reCommentContent2.length >= 2"
            :placeholder="reCommentUserId+'님에게 작성할 답글을 2글자 이상 작성해주세요'"
            rows="3">
            </b-form-textarea>
        </b-form>
         <!-- <b-button style="float:right;" @click="$store.dispatch('addReComment', 
            {bId: $store.state.boardId, uId:$session.get('userId'), cId: commentId,
            content: reCommentContent})">확인</b-button> -->
    </div> 
            <b-button style="float:right;" @click="addReComment()">등록</b-button>
    <br><br>

  </div>
</template>

<script>
export default {
    props: {
        reCommentId: Number,
        reCommentUserId: String,
        commentId: Number,
    },
    data(){
        return{
            reCommentContent2: '',
        }
    },
    methods: {
        addReComment(){
            this.$store.dispatch('addReComment', 
            {bId: this.$store.state.boardId, uId:this.$session.get('userId'), 
            cId: this.commentId, ruId:this.reCommentUserId, content: this.reCommentContent2});
            this.$store.commit('setRecommentCheck2', false);
            this.reCommentContent = '';
        }
    }

}
</script>

<style>

</style>