<template>
  <v-simple-table fixed-header height="500px">
    <template v-slot:default>
      <thead>
      <tr>
        <th class="text-left">
          Дата
        </th>
        <th class="text-left">
          Курс
        </th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="el in list" :key="el.id">
        <td>{{ el.date }}</td>
        <td>{{ el.course }}</td>
      </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
export default {
  props: ['list'],
  computed: {
    sortedMessages() {
      return this.list.sort((a, b) => -(a.date - b.date))
    }
  },
}
</script>

<style>
</style>