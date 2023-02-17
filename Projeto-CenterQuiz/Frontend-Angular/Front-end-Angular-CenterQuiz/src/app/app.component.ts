import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginServiceService } from './service/LoginService.service';


@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
})



export class AppComponent implements OnInit {
	title = 'Front-end-Angular-CenterQuiz';
	constructor(private router: Router) {

	}
	ngOnInit(): void {
		console.log("teste: " + localStorage.getItem('token'));
		if (localStorage.getItem('token') == null) {
			this.router.navigate(['login'])
		}
	}

	public sair() {
		localStorage.clear();
		this.router.navigate(['login'])
	}

	public esconderMenu() {
    	console.log("Aqui: " + localStorage.getItem('token'))
    
		if (localStorage.getItem('token') == null) {
			return true;
		} else {
			return false;
		}
	}


}
