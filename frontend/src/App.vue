<template>
  <div class="bg-white-main" id="app">
    <div class="row flex items-center h-full bg-gradient-to-tr from-white-sec to-grey-main">
      <div class="content-landing">
         <landing></landing>
      </div>
    </div>
    <div id="search" class="row">
      <div class="content">
        <div class="top">
            <Title>
               Search a country or <span class="text-underline">locate yourself</span>
            </Title>
            <button-location></button-location>
        </div>
        <search></search>
      </div>
    </div>
    <div v-if="dataInStore" class="row w-full h-full" id="recipe">
      <div class="content">
        <Results></Results>
      </div>
    </div>
  </div>
</template>

<script>
import ButtonLocation from './components/ButtonLocation.vue'
import Landing from './components/Landing.vue'
import Results from './components/results/Results.vue'
import Search from './components/search-country/Search.vue'
import Title from './components/Title.vue'


export default {
  name: 'App',
  components: {
    Title,
    Search,
    Results,
    ButtonLocation,
    Landing
  },
  computed: {
    dataInStore() {
      return this.$store.state.apiStore.apiData
    }
  },
}
</script>

<style >
  html, body, #app{
    @apply w-full h-full overflow-y-auto;
    scroll-behavior: smooth;
  }
  .top {
    @apply flex justify-center 2xl:justify-between mb-14 flex-wrap;
  }
  .content {
    @apply flex flex-col justify-center w-7/12 p-5 mx-auto py-36;
  }
  .content-landing {
    @apply flex flex-col justify-center w-10/12 p-5 mx-auto py-36;
  }
  .row {
    @apply w-full;
  }
  .text-underline {
    @apply underline text-red;
    text-decoration-color:rgba(199, 65, 87, 0.5) ;
}

</style>
