<template>
    <messages-list :usdList="usdList" :eurList="eurList"/>
</template>

<script>
import MessagesList from '../components/messages/GetCourse.vue'

export default {
  components: {
    MessagesList
  },
  data: () => ({
    usdList: [],
    eurList: []
  }),
}
</script>

<style>

</style>