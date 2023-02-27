import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { BancoDeQuestoes } from 'src/app/model/BancoDeQuestoes';
import { Questionario } from 'src/app/model/Questionario';
import { QuestionarioService } from 'src/app/service/questionario.service';
import { DatePipe } from '@angular/common';


@Component({
	selector: 'app-home',
	templateUrl: './home.component.html',
	styleUrls: ['./home.component.css'],
	providers: [DatePipe]
})
export class HomeComponent implements OnInit {
	questionarios: Observable<Questionario[]>;

	constructor(private questionarioService: QuestionarioService, private router: Router) { }

	ngOnInit(): void {
		this.questionarioService.getListQuestionarios().subscribe(

			data => {
				this.questionarios = data;
			}
		);
	}

	public acessarQuestionario(idQuestionario: Number, nome: String) {

		localStorage.setItem("idQuestionario", String(idQuestionario));
		localStorage.setItem("nomeQuestionario", String(nome));

		this.router.navigate(['questionario'])
	}

	public isAdm() {

		if (localStorage.getItem("isAdm") == "Sim") {
			return true;
		} else {
			return false;
		}
	}

}