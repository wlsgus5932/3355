<template>
  <div>
    <div>
    <b-form method = "POST">

      <!-- 제목 입력 칸 -->
      <b-form-input v-model="board.title" :state="validation" id="feedback-user" style="width: 95%"
      placeholder="제목을 입력하세요">
      </b-form-input>
      <b-form-invalid-feedback :state="validation">
        <div style="float: left;">1글자 이상 입력해주세요</div>
      </b-form-invalid-feedback>
      <b-form-valid-feedback :state="validation">
      </b-form-valid-feedback>
      <!-- 제목 입력 칸 끝 -->

      <br>

      <!-- 셀렉트 폼 -->
      <select class="form-select" aria-label="Default select example" style="width:30%" v-model="board.category">
        <option selected>{{board.category}}</option>
        <option 
        v-for="(category, i) in $store.state.category" v-bind:key="i" :value="category.name" 
        v-show="board.category != category.name">
          {{category.name}}
        </option>
      </select>
      <br>
      <!-- 셀렉트 폼 끝 -->


      <!-- 파일 등록 폼 -->
      <!-- <div>
        <input name="uploadFile" class="form-control" type="file" id="uploadFile" style="width:95%;">
      </div> -->
      <br>
      <!-- 파일 등록 폼 끝-->


      <!-- 내용 입력 폼 -->
      <div>
        <!-- <b-form-textarea
          id="textarea"
          v-model="board.content"
          :state="validationContent"
          placeholder="내용을 5글자 이상 입력해주세요"
          rows="15"
          style="width:95%">
        </b-form-textarea> -->
        <vue-editor id="editor" v-model="htmlForEditor" style="width:1200px; height:500px;"> </vue-editor>
      </div>
      <br><br><br>
      <!-- 내용 입력 폼 끝 -->
    

        <!-- submit 버튼 & 취소 버튼-->

          <div style="margin-left:75%">
            <b-button pill v-b-modal.write variant="dark" style="margin:5px"
              v-show="$session.has('userId')">
              확인
             </b-button>

              <b-modal id="write" 
              hide-backdrop content-class="shadow" 
              title="" @ok="write()">

                  정말로 <code>게시글</code>을
                  등록 하시겠습니까?
              </b-modal>

        <!-- <b-button type="submit" variant="danger" style="margin-left:10px;"
        @click="addComment()">확인</b-button> -->
          <router-link :to="`/board/`+$store.state.url">
          <b-button pill variant="danger" style="margin-left:5px;">취소</b-button>
          </router-link>
    </div>
        <!-- submit 버튼 & 취소 버튼 끝 -->

  </b-form>

        <br>
        <br>

    </div>
  </div>
</template>
 
<script>
import { VueEditor } from "vue2-editor";
 
export default {
  components: {
    VueEditor
  },
    data(){
      return{
        str: '카테고리를 선택해주세요',
        text: '',
        board: {
            title: '', 
            content: '', 
            hit: 0, 
            pop: 0, 
            category: this.$store.state.categoryName, 
            user_id: this.$session.get('userId')
            },
        htmlForEditor: "",
       }
  },
    computed: {
      validation() {
        return this.board.title.length > 1 && this.board.title.length < 50
      },
      validationContent(){
        return this.board.content.length > 5 && this.board.content.length < 1500
      }
  },
    methods: {
      write(){
        this.board.content = this.htmlForEditor

        if(this.board.title.length > 1 && this.board.category != this.str 
          && this.board.content.length > 5){
            this.$store.dispatch('boardWrite', {board: this.board, category: this.board.category});
            alert('게시글이 등록되었습니다')
            this.$router.push('/board/'+this.board.category)
      }else if(this.board.title.length < 1){
        alert('제목을 1자 이상 입력해주세요')
      }else if(this.board.category === this.str){
        alert('카테고리를 선택해주세요')
      }else if(this.board.content.length < 5){
        alert('내용을 5자 이상 입력해주세요')
      }
      
    },
     

  }
}

</script>