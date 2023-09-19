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
                        customer: new Object()
		};
	},

	mounted() {
		// semicolon separated statements

//		alert('Mounted method called');

	},

	methods: {
		// comma separated function declarations
            addCustomer() {
            axios.post(customerApi, this.customer)
                    .then(() => {
                        window.location = 'view-products.html';
                    })
                    .catch(error => {
                        alert(error.response.data.message);
                    });
        }
	},

	// other modules
	mixins: []

});

// other component imports go here
import { navigationMenu } from './navigation-menu.js';
import { sessionStore } from './session-store.js';
app.use(sessionStore);

app.component('navmenu', navigationMenu);

// mount the page - this needs to be the last line in the file
app.mount("main");