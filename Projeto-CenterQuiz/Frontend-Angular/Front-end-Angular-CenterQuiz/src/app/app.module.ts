import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { Routes } from '@angular/router';
import { RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/compiler/src/core';
import { HttpInterceptorModule } from './service/header-interceptor.service';
import { UsuarioComponent } from './component/usuario/usuario/usuario.component';
import { HomeComponent } from './component/home/home.component';
import { LoginComponent } from './component/login/login.component';
import { CadastrarQuestionarioComponent } from './component/cadastrar-questionario/cadastrar-questionario.component';
import { QuestionarioComponent } from './component/questionario/questionario.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

export const appRouters: Routes = [
	{
		path: '',
		component: LoginComponent
	},
	{
		path: 'home',
		component: HomeComponent
	},
	{
		path: 'login',
		component: LoginComponent
	},
	{
		path: 'usuarios',
		component: UsuarioComponent
	},
	{
		path: 'cadastrar-questionario',
		component: CadastrarQuestionarioComponent
	},
	{
		path: 'questionario',
		component: QuestionarioComponent
	}
];

export const routes: ModuleWithProviders = RouterModule.forRoot(appRouters)

@NgModule({
	declarations: [
		AppComponent,
		LoginComponent,
		HomeComponent,
		UsuarioComponent,
		CadastrarQuestionarioComponent,
		QuestionarioComponent
	],
	imports: [
		BrowserModule,
		HttpClientModule,
		routes,
		HttpInterceptorModule,
		FormsModule,
		ReactiveFormsModule
	],
	providers: [],
	bootstrap: [AppComponent]
})
export class AppModule { }
