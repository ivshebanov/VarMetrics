<template>
  <v-layout column>
    <v-container>
      <v-row>
        <message-row :list="usdList"/>
        <message-row :list="eurList"/>
      </v-row>
    </v-container>
  </v-layout>
</template>

<script>
import MessageRow from './CourseTable.vue'

export default {
  props: ['usdList', 'eurList'],
  components: {
    MessageRow
  },
  created: function () {
    this.$resource("/usd").get().then(result =>
        result.json().then(data =>
            data.forEach(usd => this.usdList.push(usd)),
        )
    )
    this.$resource("/eur").get().then(result =>
        result.json().then(data =>
            data.forEach(eur => this.eurList.push(eur)),
        )
    )
  }
}
</script>

<style>
</style>