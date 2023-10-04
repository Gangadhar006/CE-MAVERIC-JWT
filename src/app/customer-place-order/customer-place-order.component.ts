import { Transaction } from '../models/accounts.model';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Accounts } from '../models/accounts.model';
//import { CustomerThingsService } from 'src/app/services/customer/customer-things.service';
import { CustomerService } from '../customer.service';
import { AccountsService } from '../accounts.service';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Login } from '../models/customer.model';
import Swal from 'sweetalert2';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-place-order',
  templateUrl: './customer-place-order.component.html',
  styleUrls: ['./customer-place-order.component.css']
})
export class CustomerPlaceOrderComponent implements OnInit {
  createForm: FormGroup;
  transaction: Transaction = new Transaction();
  @Input() cid: string = "";
  selectedDebit: string = '';
  transactionDetails:any;
  availableBalanceSrc: number =0;
  availableBalanceDest: number=0;
  enableFetchRates: boolean = false;
  creditDisable: boolean = false;

  debitDisable: boolean = false;

  amountDisable: boolean = false;

  givenAmount: string | any = '';

  selectedCredit: string = '';
  debitCurrency:any=""
  creditCurrency:any=""
  conversionRates:any;
  crCurrency:any =null;
  id: any = localStorage.getItem('id')
  customerAccounts: any
  @Output() onIdxChange: EventEmitter<number> = new EventEmitter();
  @Output() onSubmitTransaction: EventEmitter<Transaction> = new EventEmitter();
  public idx = 1;
  accounts: Accounts[] = [];
  currencyRatesMessage: string;
  // submit(data: any) {
  //   console.log(data.form.value);
  //   let transaction: Transaction = {
  //     id: Math.floor(Math.random() * 100000),
  //     cid: Number(this.cid),
  //     faid: data.form.value.from,
  //     taid: data.form.value.to,
  //     amount: Number(data.form.value.amount)
  //   }
  //   this.onSubmitTransaction.emit(transaction);


  // }
  accountCheck(ac:AbstractControl){
    let debitaccount=ac.get('srcAccount')?.value
    let creditaccount=ac.get('destAccount')?.value
    console.log(debitaccount+"-"+creditaccount);
    if(debitaccount==creditaccount){
      return{account:true}
    }
    return {account:false}
    
    
  }
  get destAccount(){
    return this.createForm.get('destAccount')
  }
   get srcAccount(){
    return this.createForm.get('srcAccount')
   }


  constructor(private accservice:AccountsService,private customerThigs: CustomerService, private fbs:FormBuilder,private http:HttpClient,private router:Router) { 
   
    this.createForm = this.fbs.group({
      
      srcAccount: ['',Validators.required],
      destAccount:['',Validators.required],
      amount: ['', Validators.required]
  },{validators:this.accountCheck})
}
  



  ngOnInit(): void {
    console.log(this.createForm.value);
    
    this.getAccounts()

  }

  isDisabledSubmit(): boolean {
    if (this.selectedCredit === this.selectedDebit) {
      return true;
    }

    if (this.givenAmount <= 0 || this.givenAmount === 0 || isNaN(Number(this.givenAmount))) {

      return true;

    }





    // Enable the button if none of the above conditions are met

    return false;

  }



  isOptionDisabled(accCurrency: string): boolean {

    // Implement the logic to check if an option should be disabled

    // For example, you might want to disable the option if it matches the selected credit account

    // if(this.selectedCredit != null){

    //   return accCurrency === this.selectedDebit;

    // }else{

    //   return accCurrency === this.selectedCredit;

    // }

    this.creditDisable = accCurrency === this.selectedCredit;

    return accCurrency === this.selectedCredit;

  }



  isOptionDisabledCredit(accCurrency: string): boolean {

    this.debitDisable = accCurrency === this.selectedDebit;

    return accCurrency === this.selectedDebit;

  }





  getAccounts() {
    this.accservice.getAccountsForCustomer(this.id).subscribe(x => {
      console.log(x);
      this.customerAccounts = x
    })
  }

  placeOrder() {
    console.log(this.transaction);
    console.log(this.createForm);
    
    
    this.accservice.placeOrder(this.createForm.value).subscribe(data=>{

    console.log(data);
    console.log(data)
    Swal.fire('Order Placed!', 'Your order has been placed.', 'success');
    this.transactionDetails=data;  
    this.router.navigateByUrl(`/customerHome/${this.id}`) 
},
    (error) =>console.error(error))
  }

  


  // isDisabledSubmit(): boolean{

  //   // if((this.selectedCredit !== null)||

  //   //     this.selectedDebit!==null || this.givenAmount!==null){

  //   //   return true;

  //   // }

  //   // return false;



  //   return this.debitDisable === this.creditDisable;

  // }

  //


  updateAvailableBalanceSrcAccount(accountNumber: string) {
    const selectedAccount = this.customerAccounts.find((acc: { accountNumber: string; }) => acc.accountNumber === accountNumber);
    if (selectedAccount) {
      this.debitCurrency=selectedAccount.currency
      this.availableBalanceSrc = selectedAccount.amount;
    } else {
      this.debitCurrency=" ";
      this.availableBalanceSrc = 0;
    }
  }

  updateAvailableBalanceDestAccount(accountNumber: string) {
    const selectedAccount = this.customerAccounts.find((acc: { accountNumber: string; }) => acc.accountNumber === accountNumber);
    if (selectedAccount) {
      this.creditCurrency=selectedAccount.currency
      this.availableBalanceDest = selectedAccount.amount;
      console.log(this.creditCurrency);
      console.log(this.debitCurrency)
      {
        this.enableFetchRates = true;
        
        const baseCurrency = this.debitCurrency; // Set the base currency
        const targetCurrency = this.creditCurrency; // Set the target currency
        console.log(baseCurrency);
        console.log(targetCurrency)
        
        const conversionRateUrl = `https://open.er-api.com/v6/latest/${baseCurrency}`;
        
        this.http.get<any>(conversionRateUrl).subscribe(data => {
          this.crCurrency = data.rates;
          const crType = targetCurrency;
          this.currencyRatesMessage = `INFO : 1 ${baseCurrency} = ${this.crCurrency[crType]} ${targetCurrency}`;
        }, error => {
          console.error('Error fetching conversion rates:', error);
        });
      }
    } else {
      this.creditCurrency=' '
      this.availableBalanceDest = 0; // Handle the case where the account is not found
    }
  }

  submitForm() {
    if (this.createForm.valid) {
      console.log(this.createForm.value);
    
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

  // async fetchRates() {
  //   console.log("inside fetchRates");
    
  //   if (
  //     this.selectedCredit !== undefined &&
  //     this.selectedDebit !== undefined &&
  //     this.selectedCredit !== this.selectedDebit
  //   ) {
      
  //     this.enableFetchRates = true;
  //     const conversionRate = `https://open.er-api.com/v6/latest/${this.debitCurrency[this.selectedDebit]}`;
  //     const crType = this.creditCurrency[this.selectedCredit];
   
  //       this.conversionRates = this.http.get<any>(conversionRate);
  //       try {
  //         const data = await this.conversionRates.toPromise();
  //         console.log(data);
  //         this.crCurrency = data.rates;
  //         console.log(this.crCurrency); 
  //       } catch (error) {
  //         console.error('Error fetching conversion rates:', error);
  //       }
      
  //     this.currencyRatesMessage = `1 ${this.debitCurrency[this.selectedDebit]} = ${this.crCurrency[crType]} ${this.creditCurrency[this.selectedCredit]}<sup>*</sup>`;
  //   }
  // }
  

  // async fetchRates() {
  //   if (
  //     this.selectedCredit !== undefined &&
  //     this.selectedDebit !== undefined &&
  //     this.selectedCredit !== this.selectedDebit
  //   ) {
  //     this.enableFetchRates = true;
      
  //     const baseCurrency = this.debitCurrency; // Set the base currency
  //     const targetCurrency = this.creditCurrency; // Set the target currency
  //     console.log(baseCurrency);
  //     console.log(targetCurrency)
      
  //     const conversionRateUrl = `https://open.er-api.com/v6/latest/${baseCurrency}`;
      
  //     this.http.get<any>(conversionRateUrl).subscribe(data => {
  //       this.crCurrency = data.rates;
  //       const crType = targetCurrency;
  //       this.currencyRatesMessage = `1 ${baseCurrency} = ${this.crCurrency[crType]} ${targetCurrency}`;
  //     }, error => {
  //       console.error('Error fetching conversion rates:', error);
  //     });
  //   }
  // }
}