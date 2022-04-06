<template>
  <div class="btn-wrap">
        <div :class="['button-locate', 'red', {animation: loading}]" @click="locateMe()">
            <Pin class="icon-pin"></Pin>
                <span class="text-locate">Locate me</span>
        </div>
  </div>
</template>

<script>
import Pin from './../assets/MapPin.vue'
import axios from 'axios'

export default {
    name: 'button-location',
    components: {
        Pin,
    },
    data() {
        return {
            loading: false
        };
    },
    methods :{
       async locateMe() {
            try {
                this.loading = true
                const data = await axios.get(`http://localhost:8081/api/findCountry`)
                this.$store.dispatch('apiStore/setData', data)
                console.log(data)
                this.loading = false
                await this.$nextTick()
                document.getElementById('recipe').scrollIntoView();
            } catch (err) {
                return
            }
           
        }
    }
}
</script>

<style>
    .icon-pin {
        @apply mr-2 w-7 h-7;
    }
    .button-locate {
        @apply flex justify-center cursor-pointer z-20 py-4 px-24 mt-7 2xl:mt-0
        text-white text-lg font-semibold rounded-full shadow-lg whitespace-nowrap duration-200;
    }
    .red {
        @apply bg-red;
    }
    .red:hover {
        @apply bg-opacity-80;
    }
    .btn-wrap {
        @apply flex items-center;
    }
    .text-locate {
        @apply overflow-hidden w-24;
    }

    .animation {
        animation: 4s ease-in-out load infinite;
    }

     @keyframes load {
        0% {
           
            stroke-dasharray: 100 100;
            stroke-dashoffset: 0;
        }
        50% {
             stroke-dashoffset: 200;
        }

        100% {
            stroke-dashoffset: 0;
        }
    }




</style>