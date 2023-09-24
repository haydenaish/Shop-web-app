/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


/* global Vue, axios */
var customerApi = "/api/register";
const app = Vue.createApp({

    data() {
        return {
            // models map (comma separated key/value pairs)
//                        customer: new Object()
        };
    },
    computed: Vuex.mapState({
        product: 'selectedProduct'
    }),

    mounted() {
        // semicolon separated statements

//        alert('Mounted method called');
//        alert(dataStore.state)

    },

    methods: {
        // comma separated function declarations
    },

    // other modules
    mixins: []

});

// other component imports go here
import { navigationMenu } from './navigation-menu.js';
import { dataStore } from './data-store.js'
app.use(dataStore);

app.component('navmenu', navigationMenu);

// mount the page - this needs to be the last line in the file
app.mount("main");