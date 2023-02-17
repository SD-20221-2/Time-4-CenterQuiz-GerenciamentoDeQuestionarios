import { HttpClient } from '@angular/common/http';
import { error } from '@angular/compiler/src/util';
import {Injectable} from '@angular/core';
import { Router } from '@angular/router';
import { AppConstants } from '../app-constants';

@Injectable({
	providedIn: 'root'
})
export class LoginServiceService {
	constructor(private http: HttpClient, private router: Router) {
		
	}
	
	login(usuario) {
		// console.info("User: " + JSON.stringify(usuario))
		return this.http.post(
			AppConstants.baseLogin,
			JSON.stringify(usuario)
		).subscribe(
			data => {
				/* Retorno HTTP */
				var token = JSON.parse(JSON.stringify(data)).Authorization.split(' ')[1]
				
				localStorage.setItem("token", token)
				
				console.log("Token: " + JSON.parse(JSON.stringify(data)).Authorization.split(' ')[1])
				
				this.router.navigate(['home'])
			},
			error => {
				alert("Erro ao fazer login!")	
			}
		);
	}
}