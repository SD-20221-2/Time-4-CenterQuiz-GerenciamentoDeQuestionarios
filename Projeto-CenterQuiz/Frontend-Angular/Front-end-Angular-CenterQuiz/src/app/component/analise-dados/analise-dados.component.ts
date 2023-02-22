import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
	selector: 'app-analise-dados',
	templateUrl: './analise-dados.component.html',
	styleUrls: ['./analise-dados.component.css']
})
export class AnaliseDadosComponent implements OnInit {

	constructor(private http: HttpClient) { }

	ngOnInit() {
	}

}
