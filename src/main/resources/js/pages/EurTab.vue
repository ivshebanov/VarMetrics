<template>
  <v-layout column>
    <v-container>
      <v-row>
        <course-table :list="eurList"/>
      </v-row>
    </v-container>
  </v-layout>
</template>

<script>
import CourseTable from "../components/tables/CourseTable.vue";

export default {
  name: "EurTab",
  props: ['eurList'],
  components: {
    CourseTable
  },
  created: function () {
    this.$resource("/eur").get().then(result =>
        result.json().then(data =>
            data.forEach(eur => this.eurList.push(eur)),
        )
    )
  }
}
</script>

<style scoped>

</style>