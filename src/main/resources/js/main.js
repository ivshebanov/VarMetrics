import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue'
import Vuetify from "vuetify"
import 'vuetify/dist/vuetify.min.css'

Vue.use(VueResource)
Vue.use(Vuetify);

new Vue({
    el: '#app',
    render: a => a(App),
    vuetify: new Vuetify({}),
})

// var usdUrl = Vue.resource("/usd");
// var eurUrl = Vue.resource("/eur");


// Vue.component('message-row', {
//     props: ['val'],
//     template: '<div>{{ val.date }} ---> {{ val.course }}</div>'
// })
//
// Vue.component('message-list', {
//     props: ['usdList', 'eurList'],
//     template:
//
//     created: function() {
//         usdUrl.get().then(result =>
//             result.json().then(data =>
//                 data.forEach(usd => this.usdList.push(usd)),
//             )
//         )
//         eurUrl.get().then(result =>
//             result.json().then(data =>
//                 data.forEach(eur => this.eurList.push(eur)),
//             )
//         )
//     },
// })