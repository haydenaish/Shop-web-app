/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


"use strict";

class SaleItem {
	constructor(product, quantityPurchased) {
		this.product = product;
		this.quantityPurchased = quantityPurchased;
		this.salePrice = product.listPrice;
	}
}

class Sale {
	constructor(customer, items) {
		this.customer = customer;
		this.items = items;
	}
}

var saleApi = "/api/sales";
const app = Vue.createApp({

    data() {
        return {
            // models (comma separated key/value pairs)
            quantity: 1
        };
    },

    computed: Vuex.mapState({
	    product: 'selectedProduct',
	    items: 'items',
	    customer: 'customer'
    }),


    mounted() {
        // semicolon separated statements


    },

    methods: {
        // comma separated function declarations
        addToCart(){
            dataStore.commit("addItem", new SaleItem(this.product, this.quantity));
            window.location = "view-products.html";
        },
        
        checkOut(){
            console.log(this.items.length);
            if(this.items.length==0){
                window.location = "view-products.html";
                alert("Add Items to cart first");
                return;
            }
            let sale = new Sale(this.customer, this.items);
            axios.post(saleApi, sale)
                    .then(()=> {
                        dataStore.commit("clearItems");
                        window.location = "view-products.html";
                    })
                     .catch(error => {
                        alert(error.response.data.message);
                    });
        },
        
        returnTotal(){
            let total = 0;
            for(const item of this.items){
                total += item.salePrice * item.quantityPurchased;
                console.log(item.salePrice);
                console.log(item.quantityPurchased);
//                console.log(item)
            }
            return total;
        }
    },
    mixins: [BasicAccessAuthentication]

});

/* other component imports go here */

// import data store
import { navigationMenu } from './navigation-menu.js';
import { dataStore } from './data-store.js'
app.use(dataStore);
app.component('navmenu', navigationMenu);

// import authentication module
import { BasicAccessAuthentication } from './authentication.js';

// mount the page - this needs to be the last line in the file
app.mount("main");