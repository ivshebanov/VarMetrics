<template>
  <v-main>
    <v-container>

      <v-form>
        <v-container>
          <v-row>
            <v-col cols="12">
              <v-text-field v-model="message" outlined clearable label="Ищем" type="text">
                <template v-slot:append>
                  <v-fade-transition leave-absolute>
                    <v-progress-circular v-if="loading" size="24" color="info" indeterminate/>
                    <img v-else
                         width="24"
                         height="24"
                         src="https://image.freepik.com/free-vector/cowboy-vector-logo-icon-illustration_1893-1431.jpg"
                         alt="">
                  </v-fade-transition>
                </template>
                <template v-slot:append-outer>
                  <v-btn outlined
                         color="info"
                         :loading="loading"
                         :disabled="loading"
                         @click="loader = loader + 1">
                    Загрузить вакансии
                    <template v-slot:loader>
                      <span class="custom-loader">
                        <v-icon light>cached</v-icon>
                      </span>
                    </template>
                  </v-btn>
                </template>
              </v-text-field>
            </v-col>
          </v-row>
        </v-container>
      </v-form>

      <v-card>
        <v-card-title>
          Вакансии
          <v-spacer/>
          <v-text-field v-model="search"
                        append-icon="mdi-magnify"
                        label="Поиск"
                        single-line
                        hide-details/>
        </v-card-title>
        <v-data-table height="70vh"
                      :headers="headers"
                      :items="vacancyList"
                      :search="search"
                      :loading="loading"
                      loading-text="Загрузка... Пожалуйста ждите">
          <template v-slot:item.companyLogo="{ item }">
            <v-img :src="item.companyLogo"/>
          </template>
          <template v-slot:item.title="{ item }">
            <a :href="item.url" target="_blank">{{ item.title }}</a>
          </template>
        </v-data-table>
      </v-card>
    </v-container>
  </v-main>
</template>

<script>
export default {
  name: "Vacancy",
  data() {
    return {
      headers: [
        {text: '', value: 'companyLogo', width: "10%", align: 'start', sortable: false},
        {text: 'Вакансия', value: 'title', width: "15%"},
        {text: 'Компания', value: 'companyName', width: "30%"},
        {text: 'З/П', value: 'salary', width: "20%"},
        {text: 'Адрес', value: 'location', width: "15%"},
        {text: 'Дата размещения', value: 'dateVacancy', width: "10%"},
      ],
      search: '',
      message: '',
      loader: null,
      loading: false,
      vacancyList: []
    }
  },
  watch: {
    loader() {
      this.loading = !this.loading

      this.$http.get(
          "/vacancies/scan",
          {params: {searchString: this.message}},
          {timeout: 50000, emulateJSON: true, emulateHTTP: true}
      ).then(response =>
          response.json().then(data => {
                data.forEach(vacancy => this.vacancyList.push(vacancy))
                this.loading = false
                this.message = ''
              }
          )
      )
    },
  },
  created: function () {
    this.$http.get("/vacancies").then(response =>
        response.json().then(data =>
            data.forEach(vacancy => this.vacancyList.push(vacancy))
        )
    )
  }
}
</script>

<style scoped>
.custom-loader {
  animation: loader 1s infinite;
  display: flex;
}

@-moz-keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}

@-webkit-keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}

@-o-keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}

@keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}
</style>