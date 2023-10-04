import { Component } from '@angular/core';
import { FormBuilder, FormGroup, AbstractControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CustomerService } from '../customer.service';
import { Customer } from '../models/customer.model';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

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

@Component({
  selector: 'app-createcustomer',
  templateUrl: './createcustomer.component.html',
  styleUrls: ['./createcustomer.component.css']
})
export class CreatecustomerComponent {
  customer: Customer = new Customer();
  
  createForm: FormGroup;
  constructor(private fbs: FormBuilder, private dialogRef: MatDialogRef<CreatecustomerComponent>, private customerService: CustomerService, private router: Router) {
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

  onClose() {
    this.dialogRef.close();
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

  saveCustomer() {
    this.customerService.createCustomer(this.customer).subscribe(data=>{
      console.log(data);
      this.dialogRef.close();
      Swal.fire({
        icon:'success',
        title:'Customer created successfully!!',
        timer:2000,
        showConfirmButton:false
      });
      setTimeout(() => {
        window.location.reload();
      }, 1000);
    },
    error =>console.log(error))
  }

  goToCustomersList(){
    this.router.navigate(['']);
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
}
