<template>
<div class="flex flex-col mt-24">
    <div class=" uppercase font-extrabold text-light">Pictures</div>
    <div class="w-full pic-grid">
        <div class="big-pic">
            <a :href="`https://www.flickr.com/search/people/?username=${pictures[0].owner}`" target="_blank" class="owner">{{pictures[0].owner}}</a>
            <img :src="pictures[0].url" class="pic" :title="pictures[0].owner">
        </div>
        <div v-for="pic in pictures.slice(1)" :key="pic.id" class="small-pic">
          <a :href="`https://www.flickr.com/search/people/?username=${pic.owner}`" target="_blank" class="owner">{{pic.owner}}</a>
          <img :src="pic.url" class="pic" :title="pic.owner">
        </div>
    </div>
</div>
</template>

<script>
export default {
    name: 'pictures',
     computed: {
        pictures(){
           return this.$store.state.apiStore.apiData.data.photos
        },
    }
}
</script>

<style>
.pic-grid {
  @apply grid grid-rows-2 grid-flow-col gap-2 h-96;
}
.big-pic {
  @apply row-span-2 rounded-3xl relative overflow-hidden;
  position: relative;
  overflow: hidden;
}
.small-pic {
  @apply col-span-1 row-span-1 rounded-3xl w-full h-full relative overflow-hidden;
}
.pic {
  @apply rounded-3xl bg-gray-900 w-full h-full;
  object-fit: cover;
}

.pic-grid > div > img {
  filter: blur(0);
  transition: filter 200ms;
}

.pic-grid > div:hover > img {
  filter: blur(8px);
}

.owner {
  @apply flex justify-center items-center absolute top-0 left-0 w-full h-full z-10;
  @apply text-center text-white font-bold break-all overflow-ellipsis;
  @apply opacity-0 hover:opacity-100;
  @apply cursor-pointer;


  background-color: #00000022;
  transition: opacity 200ms;
}
</style>