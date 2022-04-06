<template>
  <div class="results">
      <div v-if="!countries" class="no-results">
        <div class="void-icon"><void></void></div>
        <span class="no-results-text">No result 😞</span>
      </div>
      <div class="results-content relative" v-else>
        <a @click="selectCountry(alpha2Code)" class="country" v-for="{name , alpha2Code, isLoading} in countryList" :key="alpha2Code">
          <div class="flag-icon res-flag-icon" :class="[`flag-icon-${alpha2Code.toLowerCase()}`, {'bounce': isLoading}]"></div>
          <span class="name-country w-full overflow-hidden whitespace-nowrap">{{name}}</span>
        </a>
      </div>
  </div>
</template>

<script>
import Void from '../../assets/Void.vue'
import axios from 'axios'

export default {
  components: { Void },
  props: {
    countries: Object
  },
  data(){
    return {
      loading: false,
      selectedCountry: undefined
    }
  },
  computed: {
    countryList() {
      return this.countries.data.slice(0,20).map(country => {
        if (this.selectedCountry === country.alpha2Code && this.loading) {
          country.isLoading = true
        } else {
          country.isLoading = false
        }
         return country
      })
    }
  },
  methods: {
    async selectCountry(code) {
      try {
        this.selectedCountry = code
        this.loading = true
        const data = await axios.get(`http://localhost:8081/api/findCountry?country=${code}`)
        this.$store.dispatch('apiStore/setData', data)
        console.log(data)
        await this.$nextTick()
        this.loading = false
        document.getElementById('recipe').scrollIntoView();
      } catch (err) {
          return
      }
    },
  }

}
</script>

<style>
.results {
    @apply flex justify-center mt-14;
}
.void-icon {
  @apply text-grey-main w-36 origin-center;
  animation: spin 4s linear infinite;
}
.no-results {
    @apply text-3xl font-semibold text-grey-main flex items-center;
}
.no-results-text {
  @apply ml-5;
}
.results-content{
  @apply grid w-full md:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4 grid-cols-1 gap-4 grid-flow-row;
}
.country {
  @apply flex items-center py-5 px-6 rounded-2xl cursor-pointer;
  background: rgba(207,212,254,.30);
}
.res-flag-icon {
  @apply rounded-lg opacity-80 mr-4 w-11 h-8 flex-shrink-0 !important;
  background-size:cover !important;
}
.name-country {
  @apply text-sec font-medium;
}

.bounce {
  -webkit-animation: sk-bounce 2.0s infinite ease-in-out;
  animation: sk-bounce 2.0s infinite ease-in-out;
}
.bounce {
  -webkit-animation-delay: -1.0s;
  animation-delay: -1.0s;
}

@keyframes sk-bounce {
  0%, 100% { 
    transform: scale(0.5);
    -webkit-transform: scale(0.5);
  } 50% { 
    transform: scale(1);
    -webkit-transform: scale(1);
  }
}
</style>