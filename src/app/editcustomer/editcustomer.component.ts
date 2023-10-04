import { Component, Injectable, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, AbstractControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CustomerService } from '../customer.service';
import { Customer, Customers } from '../models/customer.model';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { timer } from 'rxjs';
//import { AdminmodifycustomerComponent } from '../adminmodifycustomer/adminmodifycustomer.component';

function passwordValidator(control: AbstractControl): { [key: string]: boolean } | null {
  const value: string = control.value;

  // Check for at least one uppercase letter
  if (!/[A-Z]/.test(value)) {
    return { 'uppercaseLetterMissing': true };
  }

  // Check for at least one lowercase letter
  if (!/[a-z]/.test(value)) {
    return { 'lowercaseLetterMissing': true };
  }

  // Check for at least one digit
  if (!/[0-9]/.test(value)) {
    return { 'digitMissing': true };
  }

  // Check for at least one special character
  if (!/[!@#$%^&*]/.test(value)) {
    return { 'specialCharacterMissing': true };
  }

  // Check for minimum length of 8 characters
  if (value.length < 8) {
    return { 'minLengthViolation': true };
  }

  const password = control.get('password');
  const confirmPassword = control.get('confirmpassword');

  if (password && confirmPassword && password.value !== confirmPassword.value) {
    return { 'passwordMismatch': true };
  }

  return null;
}

function passwordMatchValidator(control: AbstractControl): { [key: string]: boolean } | null {
  const password = control.get('password');
  const confirmPassword = control.get('confirmpassword');

  if (password && confirmPassword && password.value !== confirmPassword.value) {
    return { 'passwordMismatch': true };
  }

  return null;
}


@Injectable({
  providedIn: 'root',
})
export class SharedDataService {
  // Define properties or methods to share data or functionality
  pagedUsers: any[] = [];

  // Add methods to update or retrieve data as needed
}
@Component({
  selector: 'app-editcustomer',
  templateUrl: './editcustomer.component.html',
  styleUrls: ['./editcustomer.component.css']
})
export class EditcustomerComponent implements OnInit{
  
  
  customer: Customer = new Customer();
  id:number;
  customerObject:any
  
  createForm: FormGroup;
  constructor(private fbs: FormBuilder, private dialogRef: MatDialogRef<EditcustomerComponent>, private customerService: CustomerService, private router: Router, private route:ActivatedRoute) {
    this.createForm = this.fbs.group({
      firstname: ['', Validators.required],
      lastname: [''],
      phone: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      email: ['', [Validators.required, Validators.email]],
      dob: [''],
      gender: [''],
      age: [''],
      password: ['', [Validators.required, passwordValidator]],
      confirmpassword: ['', [Validators.required, passwordMatchValidator]],
    }, {
      validator: passwordMatchValidator
    });
  }
  
  ngOnInit() :void{
    this.id = this.route.snapshot.params['id'];
    this.getCustomers()

  }
  onClose() {
    this.dialogRef.close();
  }
  getCustomers(){
    console.log(this.customerService.edit);
    
    this.customerService.getAllCustomers().subscribe((x:any)=>{
      this.customerObject=x.find((z:any)=>z.id==this.customerService.edit);
      this.createForm.patchValue({firstname:this.customerObject.firstName});
      this.createForm.patchValue({lastname:this.customerObject.lastName});
      this.createForm.patchValue({email:this.customerObject.email});
      this.createForm.patchValue({phone:this.customerObject.phone});
      this.createForm.patchValue({dob:this.customerObject.dob});
      this.createForm.patchValue({gender:this.customerObject.gender});
      this.createForm.patchValue({password:this.customerObject.password});
      this.createForm.patchValue({confirmpassword:this.customerObject.confirmPassword});
      this.customerObject=x.find((y:any)=>y.id==this.customerService.edit);
    })
    console.log(this.customerObject);
    
  }

  get firstname() {
    return this.createForm.get('firstname');
  }
  get email() {
    return this.createForm.get('email');
  }
  get phone() {
    return this.createForm.get('phone');
  }
  get password() {
    return this.createForm.get('password');
  }
  get confirmpassword() {
    return this.createForm.get('confirmpassword');
  }

  get lastname(){
    return this.createForm.get('lastname');
  }

  get gender(){
    return this.createForm.get('gender');
  }
  updateCustomer() {
    console.log(this.id);
    console.log(this.customerService.edit);
    console.log(this.customer);
    this.customerService.updateCustomer(this.customerService.edit,this.customer).subscribe(data=>{
      
      console.log(data);
       this.dialogRef.close();
      
      Swal.fire({
        icon:'success',
        title:'Updated',
        timer:1000,
        showConfirmButton:false
      }
      
      
      
      );
      this.router.navigateByUrl('/modifycustomer')
      this.customerService.getAllCustomers().subscribe(data=>{
      console.log(data)
      setTimeout(() => {
        window.location.reload();
      }, 1000); 
      });

      //this.router.navigateByUrl('/modifycustomer')
   
    },
    error =>console.log(error))
  }

  submitForm() {
    if (this.createForm.valid) {
      console.log(this.createForm.value);
    
    } else {
      this.markFormFieldsAsTouched(this.createForm);
    }
  }

  updateCustomerInfo(customerId: number, updatedInfo: any): void {
    this.customerService.updateCustomer(customerId, updatedInfo)
      .subscribe(
        (response) => {
          // Handle successful update response here
          console.log('Customer information updated successfully:', response);
        },
        (error) => {
          // Handle error response here
          console.error('Error updating customer information:', error);
        }
      );
  }

  successAlert() {
    this.customerService.openCustomAlert('Clicked!');
    
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
}

