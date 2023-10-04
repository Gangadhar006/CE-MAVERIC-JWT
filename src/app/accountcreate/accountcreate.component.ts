import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Account, Accounts } from '../models/accounts.model';
import { AccountsService } from '../accounts.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from '../models/customer.model';
import { CustomerService } from '../customer.service';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';
//import { SuccessDialogComponent } from './success-dialog.component'; // Create a SuccessDialogComponent
//import { SuccessDialogComponent } from './path-to-success-dialog-component';
//import { SuccessDialogComponent } from './path-to-success-dialog-component/success-dialog.component'; 

@Component({
  selector: 'app-accountcreate',
  templateUrl: './accountcreate.component.html',
  styleUrls: ['./accountcreate.component.css'],

})
export class AccountcreateComponent {
  message: string = 'account created successfully'
  action: string = 'close'
  //createForm: FormGroup;
  accounts: Accounts[] = [];
  account: Account = new Account();
  customer: Customer = new Customer();
  createForm: FormGroup;
  id: number;

  //constructor(private fbs: FormBuilder, private dialogRef: MatDialogRef<CreatecustomerComponent>, private customerService: CustomerService, private router: Router) {

  constructor(private router: Router, private dialogRef: MatDialogRef<AccountcreateComponent>, private accountService: AccountsService, private route: ActivatedRoute, private custServ: CustomerService, private fbs: FormBuilder, private dialog: MatDialog, private _snackBar: MatSnackBar) {

    this.createForm = this.fbs.group({
      amount: ['', Validators.required],
      accountType: ['', Validators.required],
      currency: ['', Validators.required]
    })
  }


  onClose() {
    this.dialogRef.close();
    console.log(this.customer.id);
  }


  submitForm() {
    if (this.createForm.valid) {
      console.log(this.createForm.value);
    } else {
      this.markFormFieldsAsTouched(this.createForm);
    }
  }
  openSnackBar(message: any, action: any) {
    this._snackBar.open(message, action, { duration: 3000 });
  }

  // saveAccount(id: any) {
  //   this.accountService.createAccount(this.account, this.id).subscribe(data => {
  //     this.dialogRef.close();
  //     console.log('data ',data);
  //     console.log('id in account create ',id);
  //     Swal.fire('Hurray', 'New account has been created.', 'success');
  //   },
  //     error => console.log(error))
  // }

  saveAccount(id:any) {
    // this.id=this.route.snapshot.params['id'];
    //console.log(id);
    
    id=this.accountService.custId;
    console.log(id);
    //console.log(this.customer);
    this.accountService.createAccount(this.account ,id).subscribe(data=>{
      console.log(data);
      this.dialogRef.close();
      Swal.fire('Hurray', 'New account has been created.', 'success');
      
        setTimeout(() => {
          window.location.reload();
        }, 1000); 
        
},
    error =>console.log(error))
  }



  markFormFieldsAsTouched(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach((field) => {
      const control = formGroup.get(field);
      if (control) {
        if (control instanceof FormGroup) {
          this.markFormFieldsAsTouched(control);
        } else {
          control.markAsTouched();
        }
      }
    });
  }
  // navigateAndReload(id:number) {
  //   setTimeout(()=>1000)
  //   const url = `/accountshome/${id}`;
  //   this.router.navigateByUrl(url).then(() => {
  //     window.location.href = url;
  //   });
  // }

  navigateAndReload(id: number) {
    setTimeout(() => {
      const url = `/accountshome/${id}`;
      this.router.navigateByUrl(url).then(() => {
        window.location.href = url;
      });
    }, 1000); // Add the delay time in milliseconds
  }
  
}













