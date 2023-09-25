/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


"use strict";

export const navigationMenu = {

	computed: {
		signedIn() {
			return this.customer != null;
		},
		...Vuex.mapState({
			customer: 'customer'
		})
	},

	template:
	`
	<nav>
		<div v-if="signedIn">Welcome {{customer.firstName}}</div>
		<a href="index.html">Home</a>
		<a href="view-products.html" v-if="signedIn">Browse Products</a>
		<a href="cart.html" v-if="signedIn">View Cart</a>
		<a href="index.html" v-if="signedIn" @click="signOut()">Sign Out</a>
		<a href="sign-in.html" v-if="!signedIn">Sign In</a>
                <a href="new-customer.html" v-if="!signedIn">Register Account</a>
	</nav>
	`,

	methods:{
		signOut() {
			sessionStorage.clear();
			window.location = 'index.html';
		}
	}
};	
