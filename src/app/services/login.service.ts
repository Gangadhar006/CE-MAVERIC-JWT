import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Login } from '../models/customer.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  token: string;
  constructor(private http:HttpClient) { }
  onLogin(body:Login){

    const apiUrl = "http://localhost:8080/login";
    console.log(apiUrl);
    return this.http.post(apiUrl, body);

  }
}
