<template>
  <div>
      <center>
      <br>
      <h3 style="font-height:bold;">광고 <i class="fa fa-exclamation-circle" aria-hidden="true"></i></h3>

      <div v-if="$store.state.blurbRandom == 1">
        <b-card 
            title="Nike Shoes"
            :img-src="Nike"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 10rem; color:black;"
            class="mb-2">

            <b-card-text> <hr>
            매우 저렴하게 나이키 축구화를 구매하세요!
            </b-card-text>

            <b-button :href="'http://www.nike.com'" target="_blank" variant="primary" 
            style="text-decoration-line: none; color:white;">Go</b-button>
        </b-card>
        </div>  

        <div v-if="$store.state.blurbRandom == 2">
        <b-card 
            title="Bicycle"
            :img-src="Bicycle"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 10rem; color:black;"
            class="mb-2">

            <b-card-text> <hr>
            멋과 실속을 챙긴 자전거, 최고의 성능 30%세일중
            </b-card-text>

            <b-button href="http://www.bikeselling.co.kr/" target="_blank" variant="primary" 
            style="text-decoration-line: none; color:white;">Go</b-button>
        </b-card>
        </div>

        <div v-if="$store.state.blurbRandom == 3">
        <b-card 
            title="헬스케어시계"
            :img-src="HealthCare"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 10rem; color:black;"
            class="mb-2">

            <b-card-text> <hr>
            스포츠인들을 위한 진정한 헬스케어, 샘성 watch3 (saemsung Watch3)
            </b-card-text>

            <b-button href="http://www.samsung.com" target="_blank" variant="primary" 
            style="text-decoration-line: none; color:white;">Go</b-button>
        </b-card>
        </div>
        
        <div v-if="$store.state.blurbRandom == 4">
        <b-card 
            title="niceShot golf"
            :img-src="golfclub"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 10rem; color:black;"
            class="mb-2">

            <b-card-text> <hr>
            Tiger Woods' coveted golf club. This golf club is amazing. Hurry up and buy it.
            </b-card-text>

            <b-button href="http://www.golf.com" target="_blank" variant="primary" 
            style="text-decoration-line: none; color:white;">Go</b-button>
        </b-card>
        </div>

        <div v-if="$store.state.blurbRandom == 5">
        <b-card 
            title="venity tenis set"
            :img-src="tenis"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 10rem; color:black;"
            class="mb-2">

            <b-card-text> <hr>
            탄력이 남다른 테니스 공과 테니스채를 구매해보세요. -venityTenis
            </b-card-text>

            <b-button href="http://www.tenis.com" target="_blank" variant="primary" 
            style="text-decoration-line: none; color:white;">Go</b-button>
        </b-card>
        </div>

        <div v-if="$store.state.blurbRandom == 6">
        <b-card 
            title="Nike basketball"
            :img-src="basketball"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 10rem; color:black;"
            class="mb-2">

            <b-card-text> <hr>
            당신은 원하고 있습니다. <br>
            NBA도 반한 나이키 농구공
            </b-card-text>

            <b-button href="http://www.nba.com" target="_blank" variant="primary" 
            style="text-decoration-line: none; color:white;">Go</b-button>
        </b-card>
        </div>

        <div v-if="$store.state.blurbRandom == 7">
        <b-card 
            title="Adidas"
            :img-src="adidas"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 10rem; color:black;"
            class="mb-2">

            <b-card-text> <hr>
            Playing soccer with Adidas shoes. D/C 20%
            </b-card-text>

            <b-button href="http://www.nba.com" target="_blank" variant="primary" 
            style="text-decoration-line: none; color:white;">Go</b-button>
        </b-card>
        </div>


      </center>
      <br>
      <br>
  </div>
</template>

<script>
export default {
    data(){
        return{
            Nike: require('../../assets/축구화.jpg'),
            Bicycle: require('../../assets/자전거.jpg'),
            HealthCare: require("../../assets/헬스케어시계.png"),
            golfclub: require("../../assets/골프채.jpg"),
            tenis: require("../../assets/테니스.jpg"),
            basketball: require("../../assets/농구.jpg"),
            adidas: require("../../assets/아디다스.jpg"),
        }
    }

}
</script>

<style>

</style>