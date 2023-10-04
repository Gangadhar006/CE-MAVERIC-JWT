import { Injectable } from '@angular/core';
import { Customers, Login } from './models/customer.model'; 
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError } from 'rxjs';
//import { Account } from 'src/app/interface/Account';
import { Transaction } from './models/accounts.model';
//import { Customer } from './createcustomer/createcustomer.component';
import { Customer
 } from './models/customer.model';
 import { MatDialog } from '@angular/material/dialog';
import { CustomAlertComponent } from './custom-alert/custom-alert.component';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  
  constructor(private http:HttpClient,private dialog: MatDialog) { }
  edit:any;
  loggedIn:boolean = false;
  adminLoggedIn:boolean = false;
  customerLoggedIn:boolean=false;


  private getToken(): string | null {
    // Implement a function to get the JWT token from where you store it (e.g., localStorage, cookies)
    // Return null or handle cases when the token is not available
    return localStorage.getItem("token");
  }
 

  setToken(token: string): void {
    localStorage.setItem("token", token);
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  // Get the user's role from the token
  getUserRole(): string | null {
    const token = this.getToken();
    if (token) {
      
      const decodedToken = this.decodeToken(token);
      if (decodedToken && decodedToken.role) {
        console.log(decodedToken.role);
        
        return decodedToken.role; 
      }
    }
    return null;
  }


  private getHeaders(): HttpHeaders {
    const token = this.getToken();
    console.log(token);
    if (token) {
      return new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': `Bearer ${token}` });
    }
    return new HttpHeaders({ 'Content-Type': 'application/json' , 'Authorization': `Bearer ${token}` });
  }

 baseUrl='http://localhost:8080';
//  baseUrl = `http://172.16.238.163:9901`;
  //GET all customers - working fine
  getAllCustomers(): Observable<Customers[]> {
    const url = `${this.baseUrl}/customer/fetchAll`;
    const headers = this.getHeaders();
    return this.http.get<Customers[]>(url, { headers });
  }

  //EDIT customer - not working fine
  updateCustomer(customerId: number, updatedInfo: any): Observable<any> {
    const url = `${this.baseUrl}/customer/update/${customerId}`; // Replace with your API endpoint for updating customers
    const headers = this.getHeaders();

    return this.http.put(url, updatedInfo, { headers });
  }

  deleteCustomer(id: number): Observable<void> {
    const url = `${this.baseUrl}/customer/delete/${id}`;
    const headers = this.getHeaders();
    return this.http.delete<void>(url, { headers });
  }

  createCustomer(customer: Customer): Observable<Object> {
    const url = `${this.baseUrl}/customer/create`;
    const headers = this.getHeaders();
    return this.http.post(url, customer, { headers });
  }

  getCustomer(id: number): Observable<Customer> {
    const url = `${this.baseUrl}/customer/fetch/${id}`;
    const headers = this.getHeaders();
    return this.http.get<Customers>(url, { headers });
  }

 
 


onLogin(body:Login){

  const loginUrl = "${baseUrl}/login";
  console.log(loginUrl);
  return this.http.post(loginUrl, body);

}

login(data: any): Observable<any> {
  
  
  
  console.log(`${this.baseUrl}/authenticate/login`);
  
  return this.http.post(`${this.baseUrl}/authenticate/login`, data)
  
}

openCustomAlert(message: string): void {
  this.dialog.open(CustomAlertComponent, {
    data: { message },
    
    width: '400px',
  });
  setTimeout(() => {
    this.dialog.closeAll();
  }, 3000);

}

  // Remove the token (logout)
  logout(): void {
    localStorage.removeItem("token");
  }


  private decodeToken(token: string): any {
    try {
      const payload = token.split('.')[1];
      console.log(payload);
      const decodedPayload = atob(payload);
      return JSON.parse(decodedPayload);
    } catch (error) {
      console.error('Error decoding token:', error);
      return null;
    }
  }
}










