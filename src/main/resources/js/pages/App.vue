<template>
  <v-card color="basil">
    <v-card-title class="text-center justify-center py-6">
      <h1 class="font-weight-bold display-3 basil--text">
        VarMetrics
      </h1>
    </v-card-title>

    <v-tabs v-model="tab" background-color="basil" color="basil" grow>
      <v-tab v-for="item in items" :key="item">
        {{ item }}
      </v-tab>
    </v-tabs>

    <v-tabs-items v-model="tab">
      <v-tab-item v-for="item in items" :key="item">
        <v-card color="basil" flat height="100vh">
          <v-container v-if="item==='USD'">
            <usd-tab/>
          </v-container>
          <v-container v-if="item==='EUR'">
            <eur-tab/>
          </v-container>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
  </v-card>
</template>

<script>
import EurTab from "./EurTab.vue";
import UsdTab from "./UsdTab.vue";

export default {
  name: "App",
  components: {
    UsdTab,
    EurTab
  },
  data: () => ({
    tab: null,
    items: [
      'USD', 'EUR',
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