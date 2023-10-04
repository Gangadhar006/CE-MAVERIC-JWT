import { Injectable } from '@angular/core';
import { Account, Accounts, Transaction, Transactions } from './models/accounts.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountsService {

  constructor(private http:HttpClient) { }

  custId:any;
  edit:any;

  private getToken(): string | null {
    // Implement a function to get the JWT token from where you store it (e.g., localStorage, cookies)
    // Return null or handle cases when the token is not available
    return localStorage.getItem("token");
  }

  private getHeaders(): HttpHeaders {
    const token = this.getToken();
    if (token) {
      return new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': `Bearer ${token}` });
    }
    return new HttpHeaders({ 'Content-Type': 'text' });
  }

 baseUrl = `http://localhost:8080`;
  // baseUrl = `http://172.16.238.163:9901`;
  getAccountsForCustomer(id: number): Observable<Accounts[]> {
        const url = `${this.baseUrl}/account/fetchALL/${id}`;
        const headers = this.getHeaders();
        console.log(url)
        console.log(this.getToken());
         return this.http.get<Accounts[]>(url, { headers });
       }

      createAccount(account: Account , customerId:number): Observable<Object> {
        const url = `${this.baseUrl}/account/create/${customerId}`; //change the url
        const headers = this.getHeaders();
        return this.http.post(url, account, { headers });
      }

      deleteAccount(customerId: number , accountNumber :string): Observable<void> {
        const url = `${this.baseUrl}/account/delete/${customerId}/${accountNumber}`;
        const headers = this.getHeaders();
         return this.http.delete<void>(url, { headers });
        
      }

      updateAccount(customerId: number, accountNo: any , updatedInfo:any): Observable<any> {
        const url = `${this.baseUrl}/account/update/${customerId}/${accountNo}`;
        const headers = this.getHeaders();
        return this.http.put(url, updatedInfo, { headers });
      }

      placeOrder(transaction: Transaction): Observable<Object> {
        console.log("making transaction" , transaction)
        const urll = `${this.baseUrl}/orders/placeorder`;
        console.log(urll);
        const headers = this.getHeaders();
        return this.http.post<Transaction>(urll, transaction,{headers});

      }

      getAllOrders(): Observable<Transactions[]> {
          const url = `${this.baseUrl}/orders/watchlist?page=0&size=10`;
          const headers = this.getHeaders();
          return this.http.get<Transactions[]>(url, { headers });
        
      }
}



