<template>
  <v-layout column>
    <v-container>
      <v-row>
        <course-table :list="usdList"/>
      </v-row>
    </v-container>
  </v-layout>
</template>

<script>
import CourseTable from "../components/tables/CourseTable.vue";

export default {
  name: "UsdTab",
  components: {
    CourseTable
  },
  data: () => ({
    usdList: []
  }),
  created: function () {
    this.$resource("/usd").get().then(result =>
        result.json().then(data =>
            data.forEach(usd => this.usdList.push(usd)),
        )
    )
  }
}
</script>

<style scoped>

</style>