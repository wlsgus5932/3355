<template>
<div>

  <!-- 카테고리 목록 생성 -->
      <h3>Category</h3><br>
      
        <div class="text-center my-3" 
          v-for="(category,i) in $store.state.category" :key="i">
          <b-button v-b-tooltip.hover.right 
           style="width:200px; text-decoration:none; color:white;"
            @click="$store.commit('setSearchSelected', 'title')
                    $store.commit('setSearchSearch', '')
                    $store.dispatch('getData', {name: category.name, page: 0}),
                    $store.dispatch('getBestList', category.name),
                    $store.dispatch('getCount', {category: category.name}),
                    $store.commit('changePage', {page: 1, paging: 1, startnum: 1}),
                    $store.commit('setCategoryName', category.name),
                    $store.dispatch('getDayCount', ),
                    $store.commit('setSearchCheck', false),
                    $parent.changeImg(category.name)"
                    :to="`/board/`+category.name"
          
          :title="$store.state.dayCount2[i] + '개의 새 글이 있어요!'"
           variant="dark" >{{category.name}}
           
          <b-badge variant="light" style="color:gold; float:right;" 
          v-show="$store.state.dayCount2[i] != 0"
          >
          <i class="fa fa-bell-o" aria-hidden="true"></i>
          {{$store.state.dayCount2[i]}}</b-badge>
          </b-button>
        </div>

  <!-- 카테고리 목록 생성 끝-->

  </div>
  
  
             
</template>
<script>
export default {
  data(){
    return{
      dark: "dark",
    }
  },
}

</script>

