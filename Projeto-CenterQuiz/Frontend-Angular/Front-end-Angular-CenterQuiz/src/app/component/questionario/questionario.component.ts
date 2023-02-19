import { Component, OnInit } from '@angular/core';
import { QuestionarioService } from 'src/app/service/questionario.service';
import { Observable } from 'rxjs';
import { BancoDeQuestoes } from 'src/app/model/BancoDeQuestoes';
import { Questionario } from 'src/app/model/Questionario';

@Component({
	selector: 'app-questionario',
	templateUrl: './questionario.component.html',
	styleUrls: ['./questionario.component.css']
})
export class QuestionarioComponent implements OnInit {
	bancoDeQuestoes: BancoDeQuestoes;
	nomeQuestionario: String;

	constructor(private questionarioService: QuestionarioService) { }

	ngOnInit(): void {
		var idQuestionario = localStorage.getItem("idQuestionario");

		this.questionarioService.getBancoDeQuestoesQuestionarioIdQuestionario(Number(idQuestionario)).subscribe(
			data => {
				this.bancoDeQuestoes = data;
				console.log("BancoDeQuestoes: " + JSON.stringify(data))
			}
		);
		
		this.questionarioService.getQuestionarioId(Number(idQuestionario)).subscribe(
			data => {
				this.nomeQuestionario = JSON.parse(JSON.stringify(data)).nome;
			}
		);
	}

}
