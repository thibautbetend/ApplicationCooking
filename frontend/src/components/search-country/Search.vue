<template>
<div>
    <div class="search">
        <input class="input" type="text" name="search" autocomplete="off" v-model="country" placeholder="Search a country...">
    </div>
    <SearchResults :countries="results"></SearchResults>
</div>      
</template>

<script>
import SearchResults from './SearchResults.vue'
import axios from 'axios'

export default {
    components: { SearchResults },
    name: "search",
    data() {
        return {
            country: '',
            results: undefined
        }
    },
    watch: {
        country (){
            if (this.country.length > 1) this.searchCountry()
            else this.results = undefined
        }
    },
    methods: {
        async searchCountry(){
            try {
                this.results = await axios.get(`https://restcountries.eu/rest/v2/name/${this.country}`)
            } catch (err) {
                this.results = undefined
            }
        }
    }

}
</script>

<style >
    .search {
        @apply w-full;
    }
    .input {
        @apply py-4 px-8 w-full bg-white bg-no-repeat rounded-full text-main border-4 text-lg;
        border-color: #cdd5e6;
        -webkit-appearance: none;
        outline:none;
        background-image: url("../../assets/search.svg");
        background-position: center right 1.5vw;
    }
</style>