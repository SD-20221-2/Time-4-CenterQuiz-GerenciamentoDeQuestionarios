import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
	selector: 'app-questionario',
	templateUrl: './questionario.component.html',
	styleUrls: ['./questionario.component.css']
})
export class QuestionarioComponent implements OnInit {
	questionario: any = { questoes: [] };
	respostas: any = {};

	constructor(private http: HttpClient) { }

	ngOnInit(): void {
		this.http.get('http://localhost:8080/centerquiz/api/adm/banco-de-questoes/obter-banco-id-questionario/id/7').subscribe((data) => {
			this.questionario = data;
			this.questionario.questoes.forEach((questao) => {
				questao.opcoes = questao.opcoes.map((opcao) => {
					return {
						valor: opcao,
						selecionada: false
					}
				});
			});
		});
	}


	selecionarOpcao(perguntaId: number, opcao: any) {
		if (typeof opcao === 'string') {
			opcao = { valor: opcao, selecionada: false };
		}
		opcao.selecionada = !opcao.selecionada;
		console.log("Selecionada a opção " + opcao.valor + " da pergunta " + perguntaId);
	}



	enviarQuestionario() {
		const body = {
			idQuestionario: this.questionario.idQuestionario,
			respostas: this.respostas
		};

		this.http.post('url-do-backend/responder-questionario', body).subscribe((data) => {
			console.log('Questionário enviado com sucesso!', data);
		});
	}

	enviarRespostas() {
		this.questionario.questoes.forEach((questao, index) => {
			const opcoesSelecionadas = questao.opcoes.filter((opcao) => opcao.selecionada).map((opcao) => opcao.valor);
			console.log(`Questão ${index + 1}: ${opcoesSelecionadas}`);
		});
	}

}

/*import { Component, OnInit } from '@angular/core';
import { QuestionarioService } from 'src/app/service/questionario.service';
import { Observable } from 'rxjs';
import { BancoDeQuestoes } from 'src/app/model/BancoDeQuestoes';
import { Questionario } from 'src/app/model/Questionario';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Questao } from 'src/app/model/Questao';
import { Resposta } from 'src/app/model/Resposta';
import { HttpClient } from '@angular/common/http';

@Component({
	selector: 'app-questionario',
	templateUrl: './questionario.component.html',
	styleUrls: ['./questionario.component.css']
})
export class QuestionarioComponent implements OnInit {
	questionario: any = { questoes: [] }; // variável para armazenar o questionário
	respostas: any = {};

	constructor(private http: HttpClient) { }

	ngOnInit(): void {
		this.http.get('http://localhost:8080/centerquiz/api/adm/banco-de-questoes/obter-banco-id-questionario/id/7').subscribe((data) => {
			this.questionario = data;
		});
	}

	selecionarOpcao(perguntaId: number, opcao: string) {
		this.respostas[perguntaId] = opcao;
		console.log("Selecionada a opção " + opcao); //+ "da pergunta " + JSON.stringify(perguntaId));
	}


	enviarQuestionario() {
		const respostas = {};
		this.questionario.questoes.forEach((questao) => {
			respostas[questao.id] = questao.opcoes.filter((opcao) => opcao.selecionada).map((opcao) => opcao.valor);
		});
		const body = {
			idQuestionario: this.questionario.idQuestionario,
			respostas: respostas
		};

		//console.log("Respostas: " + JSON.stringify(this.respostas));
		/*
		this.http.post('url-do-backend/responder-questionario', body).subscribe((data) => {
			console.log('Questionário enviado com sucesso!', data);
		});
		
	}

	enviarRespostas() {
		console.log("Respostas: " + JSON.stringify(this.respostas)); // faça o que precisar com as respostas do usuário
	}



	/*	bancoDeQuestoes: BancoDeQuestoes;
		nomeQuestionario: String;
		qtdOpcoes: Number;
		listaNameOpcaoCheckbox: String[];
	
	
		constructor(private questionarioService: QuestionarioService) {
		}
	
		ngOnInit(): void {
			var idQuestionario = localStorage.getItem("idQuestionario");
	
			this.questionarioService.getBancoDeQuestoesQuestionarioIdQuestionario(Number(idQuestionario)).subscribe(
				data => {
					this.bancoDeQuestoes = data;
					console.log("BancoDeQuestoes: " + JSON.stringify(data))
					console.log("tamanho: " + Number(JSON.parse(JSON.stringify(this.bancoDeQuestoes)).questoes.length))
					var qtdQuestoes = Number(JSON.parse(JSON.stringify(this.bancoDeQuestoes)).questoes.length);
				}
			);
	
			this.questionarioService.getQuestionarioId(Number(idQuestionario)).subscribe(
				data => {
					this.nomeQuestionario = JSON.parse(JSON.stringify(data)).nome;
				}
			);
		}
		
}
*/


