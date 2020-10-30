
var usdUrl = Vue.resource("/usd/all");
var eurUrl = Vue.resource("/eur/all");

Vue.component('message-row', {
    props: ['val'],
    template: '<div>{{ val.date }} ---> {{ val.course }}</div>'
})

Vue.component('message-list', {
    props: ['usdList', 'eurList'],
    template:
        '<div align="center">' +
            '<table border="1">'+
                '<td>'+
                    '<message-row v-for="usd in usdList" :key="usd.id" :val="usd"/>' +
                '</td>' +
                '<td>'+
                    '<message-row v-for="eur in eurList" :key="eur.id" :val="eur"/>' +
                '</td>' +
            '</table >'+
        '</div>',
    created: function() {
        usdUrl.get().then(result =>
            result.json().then(data =>
                data.forEach(usd => this.usdList.push(usd)),
            )
        )
        eurUrl.get().then(result =>
            result.json().then(data =>
                data.forEach(eur => this.eurList.push(eur)),
            )
        )
    },
})

var app = new Vue({
    el: '#app',
    template: '<message-list :usdList="usdList" :eurList="eurList"/>',
    data: {
        usdList: [],
        eurList: []
    }
})