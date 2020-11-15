<template>
  <div align="center">
    <table border="1">
      <td>
        <message-row v-for="usd in usdList" :key="usd.id" :val="usd"/>
      </td>
      <td>
        <message-row v-for="eur in eurList" :key="eur.id" :val="eur"/>
      </td>
    </table>
  </div>
</template>

<script>
import MessageRow from 'MessageRow.vue'

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