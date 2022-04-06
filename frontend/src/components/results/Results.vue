<template>
  <div>
    <country-selection :name="apiData.recipe.area" :code="apiData.country_alpha2" :random="apiData.random_area"></country-selection>
    <pictures></pictures>
    <div class="flex flex-col mt-24">
      <div class="instructions">
      <div class="flex flex-col">
        <div class="uppercase font-extrabold text-light leading-3">Recipe</div>
        <Title class="flex -mt-3 max-w-full" :link="apiData.recipe.strSource">{{ apiData.recipe.name }}</Title>
      </div>
        <sub-text>
          <img :src="apiData.recipe.strMealThumb" class="recipe-pic">
          {{ apiData.recipe.instructions }}
        </sub-text>
      </div>
    </div>
  </div>
</template>

<script>
import SubText from '../SubText.vue'
import Title from '../Title.vue'
import CountrySelection from './CountrySelection.vue'
import Pictures from './Pictures.vue'

export default {
  components: { CountrySelection, Pictures, SubText, Title },
  name:'recipe',
    computed: {
        apiData(){
           return this.$store.state.apiStore.apiData.data
        },
        countryCode(){
            return this.$store.state.country.alpha2Code
        },
        countryName(){
            return this.$store.state.country.name
        }
    }
}
</script>

<style>
.instructions {
  @apply float-left;
}
.recipe-pic {
  @apply rounded-3xl max-w-lg max-h-full float-right ml-10 mb-5;
  object-fit: cover;
}
</style>