<template>
          <div container style="col-lg-12 col-md-8 col-sm-3 col-xs-1 margin-bottom:900px;"  >
            <div>
                <h1 style="margin-right:200px;">인기글 TOP3</h1>

                <!-- 인기글 리스트 테이블 -->
              <table class="type06" width="90%" style="margin-top:10px; weight:bold;">
              <tr>
                  <th scope="row">no.</th>
                  <td class="title">title</td>
                  <td>ID</td>
                  <td>date</td>
                  <td>hit</td>
              </tr>
              <tr v-for="(bestlist,i) in $store.state.bestlist" :key="i">
                  <th scope="row">best{{i+1}}</th>
                  <td class="title" 
                  @click="titleCheck(bestlist.board_id, bestlist.category)">
                    {{bestlist.title}} 
                  <span style="color:pink; float:right;">
                  <i class="fa fa-commenting-o" aria-hidden="true">
                    {{bestlist.commentCount}} </i>
                  | 
                    <i class="fa fa-heart" aria-hidden="true" style="color: #FF6464;">
                    {{bestlist.pop}}
                    </i>
                  </span>

                  </td>
                  <td style="width: 150px">{{bestlist.user_id}}</td>
                  <td style="width: 200px">{{bestlist.date}}</td>
                  <td style="width: 130px">{{bestlist.hit}}</td>
              </tr>
              </table>
              <!-- 인기글 리스트 끝 -->
                
            </div>




              <!-- 리스트 테이블 -->
              <h2 style="margin-right:200px;">{{$store.state.categoryName}}</h2>
              <b-form method = "get" >
              <div class="container" style="">
                <div class="row" style="float:right;">
                      <!-- <select class="form-select" aria-label="Default select example" 
                      style="width:100px; margin-right:10px;" v-model="selected">
                        <option selected>{{selected}}</option>
                        <option value="user_id">작성자</option>
                      </select>
                      {{select}} -->
                          <b-form-select 
                          v-model="selected" 
                          :options="options" 
                          size="sm" 
                          style="width:100px; margin-right:10px;"
                          class="form-select"
                          ></b-form-select>

                      <b-form-input
                        id="inline-form-input-name"
                        class="mb-2 mr-sm-2 mb-sm-0"
                        style="width:300px;"
                        placeholder="검색어를 입력해주세요"
                        v-model='search'
                      ></b-form-input>
                      <b-button style="width:70px;" @click="goSearch()">검색</b-button>
              </div>
              </div>
                      </b-form>
              <span v-show="$store.state.searchCheck == true"
              style="font-size:20px; float:left;">"<strong>{{$store.state.searchQuery}}</strong>"의 검색결과</span>
              <br>
              <br>

              <table class="type06" width="90%" style="margin-top:10px; weight:bold;">
              <tr>
                  <th scope="row">no.</th>
                  <td class="title">title</td>
                  <td>ID</td>
                  <td>date</td>
                  <td>hit</td>
              </tr>
              <tr v-for="(boardlist,i) in $store.state.boardlist" :key="i"
              v-show="$store.state.getDataError == true">
                  <th scope="row">{{boardlist.rownum}}</th>
                  <td class="title" 
                  @click="titleCheck(boardlist.board_id, boardlist.category)">
                    {{boardlist.title}} 
                  <span style="color:pink; float:right;">
                  <i class="fa fa-commenting-o" aria-hidden="true">
                    {{boardlist.commentCount}} </i>
                  | 
                    <i class="fa fa-heart" aria-hidden="true" style="color: #FF6464;">
                    {{boardlist.pop}}
                    </i>
                  </span>

                    
                  </td>
                  <!-- </router-link> -->
                  <td style="width: 150px">{{boardlist.user_id}}</td>
                  <td style="width: 200px">{{boardlist.date}}</td>
                  <td style="width: 130px">{{boardlist.hit}}</td>

              </tr>
                  <td v-show="$store.state.getDataError == false" 
                  style="width:800px; font-size:50px;">
                    검색결과가 없습니다</td>
              </table>
              <!-- 리스트 테이블 끝 -->


              <!-- 게시글 글쓰기 버튼-->
                  <div style="dispaly:inline-block; float:left;">
                <router-link :to="`/write`">
                  <button type="button" class="btn btn-dark" style="height:40px; width:80px "
                  v-show="$session.has('userId')">
                    <i class="fa fa-pencil" aria-hidden="true"> write</i>
                  </button>
                </router-link>
                  </div>
              <!-- 게시글 글쓰기 버튼 끝-->


              <!-- 이전페이지 -->
                  <div style="text-align:center; margin-bottom:50px;" >
              <nav aria-label="Page navigation example" style="margin-left:35%">
                <ul class="pagination" >
                  <li class="page-item">
                    <a class="page-link" aria-label="Previous"
                    @click="
                    $store.commit('changePage', { startnum: $store.state.startnum-1, 
                    page: $store.state.startnum-1, paging: $store.state.startnum-1}),

                    $store.dispatch('getData', 
                    {name: $store.state.url, page: $store.state.page, 
                    f: $store.state.searchSelected, s: $store.state.searchSearch})"

                    v-if="$store.state.startnum != 1">
                      <span aria-hidden="false">&laquo;</span>
                    </a>
                  </li>
                <!-- 이전페이지 끝 -->


                <!-- 페이징 넘버-->
                  <li class="page-item" v-for="(pages,i) in 5" :key="i">
                    <a class="page-link" 
                    @click="$store.commit('changePage', { startnum: $store.state.startnum, 
                    page: $store.state.startnum+i, paging: $store.state.startnum+i}),
                    $store.dispatch('getData', {name: $store.state.url, page: $store.state.page,
                    f: $store.state.searchSelected, s: $store.state.searchSearch})"
                    v-if="$store.state.startnum+i <= $store.state.count">

                    {{$store.state.startnum+i}}
                    </a>
                  </li>
                 <!-- 페이징 넘버 끝-->


                 <!-- 다음페이지 -->
                  <li class="page-item">
                    <a class="page-link" aria-label="Next"
                    @click="$store.commit('changePage', { startnum: $store.state.startnum+5, 
                    page: $store.state.startnum+5, paging: $store.state.startnum+5}),
                    $store.dispatch('getData', 
                    {name: $store.state.url, page: $store.state.page,
                    f: $store.state.searchSelected, s: $store.state.searchSearch})
                    "
                    v-if="$store.state.startnum+5 <= $store.state.count">
                      <span aria-hidden="true">&raquo;</span>
                    </a>
                  </li>
                  <!-- 다음 페이지 끝 -->
              <span style="margin-left:20px;"> page: {{$store.state.paging}} / {{$store.state.count}}</span>
                </ul>
              </nav>
              </div>
          </div>

</template>
<script>
import Category from '@/components/layout/Category.vue'
export default {
  components:{
    Category
  },
  data(){
    return{
      page: 1,
      paging: 1,
      startnum: 1,
      selected: 'title',
        options: [
          { value: 'title', text: '제목' },
          { value: 'user_id', text: '작성자' },
        ],
      search: '',

    }
  },
  methods: {
    titleCheck(v, b){
        this.$store.dispatch('getDetail', {bId:v, uId:this.$session.get('userId')});
        this.$router.push('/detail')
        this.$store.commit('setCategoryName', b)
        console.log("v:",v)
    },
    goSearch(){
      this.$store.commit('setSearchSelected', this.selected)
      this.$store.commit('setSearchSearch', this.search)
      this.$store.commit('changePage', {startnum: 1, page: 1, paging: 1})
      this.$store.dispatch('getCount', {category: this.$store.state.categoryName, 
      f:this.selected, s:this.search})
      this.$store.dispatch('getData', {name: this.$store.state.categoryName, page:0, 
      f:this.selected, s:this.search})
      
      this.$store.commit('setSearchQuery', this.search)
      this.$store.commit('setSearchCheck', true)
      this.search = ''
    },
  }
}

</script>
<style>
.title{
    width: 500px;
}

table.type06 {
  border-collapse: collapse;
  text-align: left;
  line-height: 1.5;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
  margin: 20px 10px;
}
table.type06 th {
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  width: 30px;
}
table.type06 td {
  padding: 10px;
  margin-right: 50px;
  vertical-align: top;
}
 a:link { color: red; text-decoration: none;}
 a:visited { color: black; text-decoration: none;}
 a:hover { color: black; text-decoration: underline;}

</style>
