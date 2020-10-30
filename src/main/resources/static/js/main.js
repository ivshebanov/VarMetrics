
var messageApi = Vue.resource("/usd/all")

Vue.component('message-row', {
    props: ['message'],
    template: '<div><i>({{ message.date }})</i> {{ message.course }}</div>'
})

Vue.component('message-list', {
    props: ['messages'],
    template:
        '<div>' +
            '<message-row v-for="message in messages" :key="message.course" :message="message"/>' +
        '</div>',
    created: function() {
        messageApi.get().then(result =>
            result.json().then(data =>
                data.forEach(message => this.messages.push(message))
            )
        )
    }
})

var app = new Vue({
    el: '#app',
    template: '<message-list :messages="message"/>',
    data: {
        message: []
    }
})