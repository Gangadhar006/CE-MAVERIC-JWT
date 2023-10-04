
//   public idx = 1;

//   accounts : Account[] = [];

//   submit(data : any){

//     console.log(data.form.value);

//     let transaction : Transaction = {

//       id : Math.floor(Math.random()*100000),

//       cid : Number(this.cid),

//       faid : data.form.value.from,

//       taid :data.form.value.to,

//       amount : Number(data.form.value.amount)

//     }

//     this.onSubmitTransaction.emit(transaction);

   

   

//   }

//   constructor(private customerThigs:CustomerThingsService  ){}

 

//   ngOnInit(): void {

//     this.customerThigs.getAccounts(this.cid).subscribe((res)=>{this.accounts = res.accs});

//     // this.subscribeToFormChanges();

//   }

 

//   // private subscribeToFormChanges(): void {

//   //   this.myForm.valueChanges.subscribe((form: NgForm) => {

//   //     this.selectedCredit = form.to;

//   //     this.selectedDebit = form.from;

//   //     this.givenAmount = form.amount;

//   //   });

//   // }

 

//   isDisabledSubmit(): boolean {

//     // Disable the button if the "Debit from" and "Credit to" options are the same

//     if (this.selectedCredit === this.selectedDebit) {

//       return true;

//     }

 

//     // Disable the button if the "amount" input is empty or contains invalid data

//     if (this.givenAmount<= 0||this.givenAmount===0  || isNaN(Number(this.givenAmount))) {

//       return true;

//     }

 

 

//     // Enable the button if none of the above conditions are met

//     return false;

//   }

 

//   isOptionDisabled(accCurrency: string): boolean {

//     // Implement the logic to check if an option should be disabled

//     // For example, you might want to disable the option if it matches the selected credit account

//     // if(this.selectedCredit != null){

//     //   return accCurrency === this.selectedDebit;

//     // }else{

//     //   return accCurrency === this.selectedCredit;

//     // }

//     this.creditDisable = accCurrency === this.selectedCredit;

//     return accCurrency === this.selectedCredit;

//   }

 

//   isOptionDisabledCredit(accCurrency: string): boolean{

//     this.debitDisable = accCurrency === this.selectedDebit;

//     return accCurrency === this.selectedDebit;

//   }

 

 

//   getAvailableBalance(selectedDebit: string): number | undefined {

//     const selectedAccount = this.accounts.find((account) => account.currency === selectedDebit);

//     return selectedAccount ? selectedAccount.balance : undefined;

//   }

 

 

//   // isDisabledSubmit(): boolean{

//   //   // if((this.selectedCredit !== null)||

//   //   //     this.selectedDebit!==null || this.givenAmount!==null){

//   //   //   return true;

//   //   // }

//   //   // return false;

 

//   //   return this.debitDisable === this.creditDisable;

//   // }

//   //

//   onDebitAccountSelected(accCurrency: string): void {

//     this.customerThigs.getDebitAccountBalance(this.cid, accCurrency)

//       .subscribe((balance) => {

//         console.log(`Debit account balance for ${accCurrency}: ${balance}`);

//         this.availableBalance = balance; // Set the available balance

//       });

//   }

// }

   

 

 