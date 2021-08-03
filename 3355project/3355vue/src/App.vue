<template>
<div id="app">
  <div v-bind:style="{color: fcolor, backgroundColor: bcolor}">
    <Header/> <br>
        <div class="row">
            <div class="cols col-md-4 col-lg-3" style="margin-top: 30px">
              <Category/>

              <div>
              <Blurb/>
              </div>



            </div>

            <div class="cols col-sm-12 col-md-8 col-lg-9" style="margin-top: 30px">
              <router-view/>
            </div>
        </div> 

        <Footer/>
  </div>
</div>
</template>

<script>
import Header from '@/components/layout/Header.vue'
import Category from '@/components/layout/Category.vue'
import Blurb from '@/components/layout/blurb.vue'
import Footer from '@/components/layout/Footer.vue'

export default {
  data(){
    return{
      fcolor : 'black',
      bcolor : 'white',
      name: 'App',
      category2: ['freeboard', 'soccer', 'baseball', 'basketball', 'bollyball', 'tenis', 'golf']
    }
  },
  mounted(){
    this.$store.dispatch('getDayCount',)

    if(this.$cookies.isKey('isDark')){
      this.fcolor = 'white'
      this.bcolor = 'black'
    } else{
      this.bcolor = 'white'
      this.fcolor = 'black'
    }

  },


  methods:{
    changeColor(){
    console.log('app updated')
     if(this.$cookies.isKey('isDark')){
      this.fcolor = 'white'
      this.bcolor = 'black'
    } else{
      this.bcolor = 'white'
      this.fcolor = 'black'
    }
    this.$children[4].changeMainColor()
  },
  changeImg(image){
        console.log(image)
        if(image=='freeboard'){
          image = 'mainimge'
        }

        this.$children[0].changeImage(image+'.jpg')
      }
    //   getDayCount(){
    // for(var i=0; i<this.category2.length; i++)
    //     axios
    //     .get("/api/board/dayCount?c="+this.category2[i])
    //     .then(res => {
    //       // if(this.$store.state.dayCountt = res.data)
    //       this.$store.state.dayCount2.push(res.data)
    //         console.log("this.soccer:",this.$store.state.dayCount2)
    //         console.log("res:",res.data)
    //     })
    //     .catch(err => {
    //       console.log(err)
    //     })
    //   }
  },

  components: {
    Header,
    Category,
    Blurb,
    Footer,
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
}
/* 
#nav a.router-link-exact-active {
} */

.Light {
  background-color : #ffffff;
  color: #000000;
}

.Dark {
  background-color : #000000;
  color: #ffffff;
}

</style>
