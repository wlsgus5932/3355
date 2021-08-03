<template>
  <div>
    <div v-for="(getDetails, i) in getDetails" :key="i">
    <b-form method = "get">

      <!-- 제목 입력 칸 -->
      <b-form-input  :state="validation" style="width: 95%"
      v-model="getDetails.title"
      placeholder="1글자 이상 입력해주세요">
      </b-form-input>
      <b-form-invalid-feedback :state="validation">
        <div style="float: left;">1글자 이상 입력해주세요</div>
      </b-form-invalid-feedback>
      <b-form-valid-feedback :state="validation">
      </b-form-valid-feedback>
      <!-- 제목 입력 칸 끝 -->

      <br>

      <!-- 셀렉트 폼 -->
      <select class="form-select" aria-label="Default select example" style="width:30%" 
      v-model="getDetails.category">
        <option selected>{{getDetails.category}}</option>
        <option 
        v-for="(category, i) in $store.state.category" v-bind:key="i" :value="category.name" 
        v-show="getDetails.category != category.name">
          {{category.name}}
        </option>
      </select>
      <br>
      <!-- 셀렉트 폼 끝 -->


      <!-- 파일 등록 폼 -->
      <div>
        <input name="uploadFile" class="form-control" type="file" id="uploadFile" style="width:95%;">
      </div>
      <br>
      <!-- 파일 등록 폼 끝-->


      <!-- 내용 입력 폼 -->
      <div>
        <!-- <b-form-textarea
          :state="validationContent"
          placeholder="내용을 5글자 이상 입력해주세요"
          rows="15"
          style="width:95%"
          v-model="getDetails.content"
          >
          
        </b-form-textarea> -->
        <vue-editor id="editor" v-model="getDetails.content"
        style="width:1200px; height:300px;"> </vue-editor>
      </div>
      <br>
      <br>
      <br>
      <!-- 내용 입력 폼 끝 -->
    

        <!-- submit 버튼 & 취소 버튼-->
          <div style="margin-left:75%">
            <b-button pill v-b-modal.update variant="dark" style="margin:5px"
              v-show="$session.has('userId')">
              확인
             </b-button>

              <b-modal id="update" 
              hide-backdrop content-class="shadow" 
              @ok="update()" >
              <!-- title=""  -->

                  정말로 <code>게시글</code>을
                  수정 하시겠습니까?
              </b-modal>
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
import axios from 'axios'
import { VueEditor } from "vue2-editor";

export default {
  components: {
    VueEditor
  },
    data(){
      return{
        str: '카테고리를 선택해주세요',
        text: '',
        getDetails: [],
       }
  },
  mounted(){
      this.getDetail();
      
  },
  computed: {
      validation() {
        return this.getDetails[0].title.length > 1 && this.getDetails[0].title.length < 50
      },
      validationContent(){
        return this.getDetails[0].content.length > 5 && this.getDetails[0].content.length < 1500
      },
  },
  methods: {
      getDetail(){
        axios
          .get("/api/board/detail/"+this.$store.state.boardId)
          .then(res => {
            this.getDetails = res.data
            console.log("Detail res:",res)
            console.log("Detail res.data:",res.data)
            console.log("getDetails:", this.getDetails[0].title)
          })
          .catch(err => {
            console.log(err)
          });
      },
      update(){
            axios.post('/api/board/update',{
              bId : this.getDetails[0].board_id,
              t : this.getDetails[0].title,
              c : this.getDetails[0].category,
              con : this.getDetails[0].content,
            })
                .then(res=>{
                    this.$store.dispatch('getDetail', {bId:this.getDetails[0].board_id, uId:this.$session.get('userId')})
                    alert('게시글이 수정되었습니다')
                    this.$router.push('/detail')
                    console.log(this.getDetails)
                }).catch(err=>{
                    alert(err+' 다시 등록해주세요')
                    console.log(err)
                    var router = this.$router;
                });
    },
  }

}
</script>

<style>

</style>