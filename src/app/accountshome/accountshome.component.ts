import { Component } from '@angular/core';
import { AccountcreateComponent } from '../accountcreate/accountcreate.component';
import { Account, Accounts } from '../models/accounts.model';
import { AccountsService } from '../accounts.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../customer.service';
import { Customer, Login } from '../models/customer.model';
import { AccounteditComponent } from '../accountedit/accountedit.component';
import Swal from 'sweetalert2';
import { MatDialog } from '@angular/material/dialog';


@Component({
  selector: 'app-accountshome',
  templateUrl: './accountshome.component.html',
  styleUrls: ['./accountshome.component.css']
})
export class AccountshomeComponent {
  id: number;
  accounts: Accounts[] = [];
  account:Account= new Account();
  customers: Customer = new Customer();
  noRecordsAvailable: boolean = false;

  constructor(private dialog: MatDialog, private accountsService: AccountsService, private route: ActivatedRoute, private customerService: CustomerService, private router: Router) { }

  ngOnInit(): void {
    this.getAllAccountsForCustomer();
    this.getCustomerInfo();
  }

  onCreate() {
    this.accountsService.custId = this.id;
    this.dialog.open(AccountcreateComponent, {
      width: '40%',
    });
  }


  navigateAndReload(id:number) {
    setTimeout(()=>1000)
    const url = `/accountshome/${id}`;
    this.router.navigateByUrl(url).then(() => {
      window.location.href = url;
    });
  }

  onDelete(id: number, accNo: string) {
    this.accountsService.custId = id
    Swal.fire({
      title: 'Are you sure?',
      text: 'Do you want to delete this account?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!',
    }).then((result) => {
      if (result.isConfirmed) {
        this.accountsService.deleteAccount(id, accNo).subscribe((data) => {
          this.navigateAndReload(id);
        });
      }
    });
  }

  onEdit(id: number, accNo: string) {
    this.accountsService.custId = id
    this.accountsService.edit = accNo
    this.dialog.open(AccounteditComponent, {
      width: '500px',
      height: 'max-content'
    });
  }
  getAllAccountsForCustomer() {
    this.id = this.route.snapshot.params['id'];
    console.log(this.id);
    this.accountsService.getAccountsForCustomer(this.id).subscribe(data => {
      console.log(data);
      console.log(data)
      this.accounts = data;
      this.noRecordsAvailable = this.accounts.length === 0;
    }, error => console.log(error))
  }

  getCustomerInfo() {
    this.id = this.route.snapshot.params['id'];
    this.customerService.getCustomer(this.id).subscribe(data => {
      this.customers = data;
      console.log(data);
    }, error => console.log(error))
  }

  successAlert() {
    this.customerService.openCustomAlert('Clicked!');

  }

  // orders: Order[] = [];

  // constructor(private orderService: OrderService) { }


  // ngOnInit(): void {
  //   this.getAllOrders();
  // }

  // getAllOrders() {
  //   this.orderService.getOrdersForCustomer(1).subscribe(orders => {
  //     this.orders = orders;
  //   });
  // }

}