import { Component } from '@angular/core';
import { CustomerService } from '../customer.service';
import { ActivatedRoute } from '@angular/router';
import { AccountsService } from '../accounts.service';
import { Accounts, Transactions } from '../models/accounts.model';
import { Login } from '../models/customer.model';
import { Transaction } from '../models/accounts.model';

@Component({
  selector: 'app-customer-home',
  templateUrl: './customer-home.component.html',
  styleUrls: ['./customer-home.component.css']
})
export class CustomerHomeComponent {
  id: number;
  accounts: Accounts[] = [];
  orderDetails: any;
 
  public idx = 1;
  public cutomerId: string = '';
  transactions: Transactions[] = [];
  //users:Customers[] = [];
  pageSize: number = 5; // Number of items per page

  // Pagination
  p: number = 1; // Current page, default to 1

  get pagedUsers(): any[] {
    const startIndex = (this.p - 1) * this.pageSize;

    return this.transactions.slice(startIndex, startIndex + this.pageSize);
  }


  get pages(): number[] {
    const pageCount = Math.ceil(this.transactions.length / this.pageSize);
    return Array.from({ length: pageCount }, (_, index) => index + 1);
  }

  changePage(pageNum: number): void {
    if (pageNum >= 1 && pageNum <= this.pages.length) {
      this.p = pageNum;
    }
  }

  constructor(private customerThings: CustomerService, private activatedRoute: ActivatedRoute, private accountService: AccountsService) {
  }
  ngOnInit() {
    // this.activatedRoute.params.subscribe(paramsId => {
    //     this.cutomerId = paramsId['id'];
    //     console.log(this.cutomerId);
    //     console.log(this.accountService.getAccountsForCustomer(1));


    // });
    // this.customerThings.getTransaction(this.cutomerId).subscribe((t)=>this.transactions = t);
    this.getAllAccountsForCustomer();
    this.getAllOrders();
  }

  // onSubmitTransaction(transaction : Transaction){
  //   this.customerThings.makeTransaction(transaction).subscribe((res)=>{
  //     this.toastr.success('transaction created successfully!!',"" ,{ timeOut: 3000});
  //     this.idx = 1;
  //     this.transactions.push(res)
  //   });
  // }

  getAllAccountsForCustomer() {
    this.id = this.activatedRoute.snapshot.params['id'];
    console.log(this.id);
    this.accountService.getAccountsForCustomer(this.id).subscribe(data => {
      console.log(data);

      this.accounts = data;
    }, error => console.log(error))
  }

  getAllOrders() {

    this.accountService.getAllOrders().subscribe(data => {
      this.transactions = data;
    })
  }

}