<template>
  <v-data-table height="80vh"
                :headers="headers"
                :items="list"
                :items-per-page="5"
                class="elevation-1"
                :footer-props="{
      showFirstLastPage: true,
      firstIcon: 'mdi-arrow-collapse-left',
      lastIcon: 'mdi-arrow-collapse-right',
      prevIcon: 'mdi-minus',
      nextIcon: 'mdi-plus'
    }"
  ></v-data-table>
  <!--  <v-simple-table height="400"-->
  <!--                  v-model="selected"-->
  <!--                  :headers="headers"-->
  <!--                  fixed-header-->
  <!--                  :items="desserts"-->
  <!--                  item-key="name">-->
  <!--    <template v-slot:default>-->
  <!--      <thead>-->
  <!--      <tr>-->
  <!--        <th class="text-left">-->
  <!--          Дата-->
  <!--        </th>-->
  <!--        <th class="text-left">-->
  <!--          Курс-->
  <!--        </th>-->
  <!--      </tr>-->
  <!--      </thead>-->
  <!--      <tbody>-->
  <!--      <tr v-for="el in list" :key="el.id">-->
  <!--        <td>{{ el.date }}</td>-->
  <!--        <td>{{ el.course }}</td>-->
  <!--      </tr>-->
  <!--      </tbody>-->
  <!--    </template>-->
  <!--  </v-simple-table>-->
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