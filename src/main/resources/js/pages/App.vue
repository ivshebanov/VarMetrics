<template>
  <v-app>
    <v-card color="basil">
      <v-card-title class="text-center justify-center py-6">
        <h1 class="font-weight-bold display-3 basil--text">
          Поиск работы
        </h1>
      </v-card-title>

      <vacancy-tab/>

    </v-card>
  </v-app>
</template>

<script>
import VacancyTab from "./VacancyTab.vue";

export default {
  name: "App",
  components: {
    VacancyTab
  },
  data: () => ({
    tab: null,
    items: [
      'Vacancy'
    ]
  }),
}
</script>

<style>
.basil {
  background-color: #FFFBE6 !important;
}

.basil--text {
  color: #356859 !important;
}

.flexcard {
  display: flex;
  flex-direction: column;
}

.flexcard .v-tabs-items {
  flex: 0;
}
</style>