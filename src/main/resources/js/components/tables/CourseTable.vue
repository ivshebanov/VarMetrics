<template>
  <v-data-table :headers="headers" :items="list" :items-per-page="5" class="elevation-1"/>
</template>

<script>
export default {
  name: "CourseTable",
  props: ['list'],
  data() {
    return {
      headers: [
        {text: 'date', value: 'date'},
        {text: 'course', value: 'course'},
      ],
    }
  },
  computed: {
    sortedMessages() {
      return this.list.sort((a, b) => -(a.date - b.date))
    }
  },
}
</script>

<style>
</style>