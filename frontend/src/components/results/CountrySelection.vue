<template>
  <div class="flex items-center justify-between">
        <div class="inline-flex flex-col" v-if="code">
            <div class="uppercase font-extrabold text-light mb-3">Recipe Nationality</div>
            <Title class="leading-3">{{name}}</Title>
        </div>
        <div class="r-flag">
             <div v-if="!random" class="flag-icon rec-flag-icon" :class="`flag-icon-${code.toLowerCase()}`"></div>
              <div v-if="random" class="rec-flag-icon random">Random</div>
        </div>
    </div>
</template>

<script>
import Title from '../Title.vue'
export default {
  components: { Title },
    name:'country-selection',
    props: {
        code: String,
        name: String,
        random: Boolean
    }
}
</script>

<style>
.rec-flag-icon {
  @apply rounded-2xl opacity-80 mr-4 w-20 h-14 flex-shrink-0 !important;
  background-size:cover !important;
}
.random {
  @apply bg-gray-900 text-white font-extrabold flex w-full h-full items-center justify-center;
}
</style>