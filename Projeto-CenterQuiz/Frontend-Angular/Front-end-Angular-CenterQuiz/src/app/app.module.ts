import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { Routes } from '@angular/router';
import { RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/compiler/src/core';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { HttpInterceptorModule } from './service/header-interceptor.service';


export const appRouters: Routes = [
  	{	
    	path : '',
		component : LoginComponent
	},
	{	
    	path : 'home',
		component : HomeComponent
	},
	{
    	path: 'login',
    	component: LoginComponent
  	}
];

export const routes : ModuleWithProviders = RouterModule.forRoot(appRouters)

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    routes,
    HttpInterceptorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
