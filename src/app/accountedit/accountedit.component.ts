import { Component, OnInit } from '@angular/core';
import { Account, Accounts } from '../models/accounts.model';
import { Customer } from '../models/customer.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AccountsService } from '../accounts.service';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../customer.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-accountedit',
  templateUrl: './accountedit.component.html',
  styleUrls: ['./accountedit.component.css']
})
export class AccounteditComponent implements  OnInit  {

  accounts: Accounts[] = [];
  account: Account = new Account();
  customer:Customer=new Customer();
  createForm: FormGroup;
  accountObject: any;
  id: any;

  constructor(private dialogRef: MatDialogRef<AccounteditComponent>, private accountService:AccountsService, private route:ActivatedRoute,private custServ:CustomerService,private fbs: FormBuilder,private dialog:MatDialog) {

    this.createForm = this.fbs.group({
      amount: ['', Validators.required],
      
      currency:['',Validators.required]
  })
}

ngOnInit():void{
  this.getAccounts();
}

onClose() {
  this.dialogRef.close();
  console.log(this.customer.id);
}


submitForm() {
  const custId=this.accountService.custId
  const accNum=localStorage.getItem('accNum')
  if (this.createForm.valid) {
    this.accountService.updateAccount(custId,accNum,this.createForm.value).subscribe(data=>{
      console.log(data);
      this.dialogRef.close();
      Swal.fire('Hurray', 'Account has been updated.', 'success');
      
        setTimeout(() => {
          window.location.reload();
        }, 1000);
    })
  
  } else {
    this.markFormFieldsAsTouched(this.createForm);
  }
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

getAccounts(){
  console.log(this.accountService.edit);
  this.id=this.route.snapshot.params['id'];
  this.accountService.getAccountsForCustomer(this.id).subscribe((x:any)=>{
    this.accountObject=x.find((z:any)=>z.id==this.accountService.edit);
    this.createForm.patchValue({amount:this.accountObject.amount});
    this.createForm.patchValue({currency:this.accountObject.currency});
  })
  console.log(this.accountObject);
  
}

get amount() {
  return this.createForm.get('amount');
}

get currency() {
  return this.createForm.get('currency');
}
}





