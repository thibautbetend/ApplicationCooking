<template>
  <div class="landing">
      <div >
        <Title>Cooking recipes around the world</Title>
        <sub-text class="max-w-md">
            For travelers and curious people in search of new taste sensations.
            Discover every world class cooking recipe for each country and try to reproduce them at your home.
            <b>Search a country or use the automatic location to find a special recipe from the country.</b>
        </sub-text>
        <a href="#search" class="button blue mt-8">Start</a>
      </div>
      <div class="ml-12 w-9/12 h-screen">
        <World></World>
      </div>
  </div>
      
</template>

<script>
import SubText from './SubText.vue'
import Title from './Title.vue'
import World from './World.vue'
export default {
  components: { Title, SubText, World },

}
</script>

<style>
.button {
    @apply flex justify-center cursor-pointer z-20 py-4 px-24 mt-7 2xl:mt-0
    text-white text-lg font-semibold rounded-full shadow-lg whitespace-nowrap duration-200;
  }
.landing {
    @apply flex justify-between items-center;
}
.blue {
    @apply duration-200;
    background-color:#3880FF;
}
.blue:hover {
    background-color: rgba(56,128,255,0.8);
}
</style>