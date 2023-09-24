/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


/* global Vue, axios */
var productsApi = "/api/products";
var categoriesApi = "/api/categories";
var categoriesFilterApi = ({category}) => `/api/categories/${category}`;
const app = Vue.createApp({

    data() {
        return {
            // models map (comma separated key/value pairs)
            products: new Array(),
            categories: new Array()
        };
    },

    mounted() {
        // semicolon separated statements
        this.getProducts();
        this.getCategories();

//		alert('Mounted method called');

    },

    methods: {
        // comma separated function declarations
        getProducts() {

            // send GET request
            axios.get(productsApi)
                    .then(response => {
                        // store response in students model
                        this.products = response.data;
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred - check the console for details.");
                    });

        },
        getCategories() {
            // send GET request
            axios.get(categoriesApi)
                    .then(response => {
                        // store response in students model
                        this.categories = response.data;
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred - check the console for details.");
                    });
        },

        filterByCategory(category) {
            axios.get(categoriesFilterApi({'category': category}))
                    .then(response => {
                        this.products = response.data;
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred - check the console for details.");
                    });
        },
        buyProduct(product) {
            console.log(product)
            dataStore.commit("selectProduct", product);
            window.location = "quantity.html";
        }
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