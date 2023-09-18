/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


export const sessionStore = Vuex.createStore({

	state () {
		selectedStudent: null;
	},

	mutations: {

		selectStudent(state, student) {
			state.selectedStudent = student;
		}

	},

	plugins: [window.createPersistedState({storage: window.sessionStorage})]

});