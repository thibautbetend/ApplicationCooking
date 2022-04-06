<template>
  <div class="title">
        <div class="text" v-if="link">
            <a :href="link" target="_blank">
                <slot></slot>
            </a>
        </div>
        <div v-else>
          <slot></slot>
        </div>
  </div>
</template>

<script>
export default {
    name: 'Title',
    props: {
      link: String
    }
}
</script>

<style >
.title {
    @apply font-bold text-main text-5xl inline-flex max-w-md;
}
.text {
    @apply mt-4;
}
</style>