import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { CustomerService } from 'src/app/customer.service';
import { Login } from 'src/app/models/customer.model';
import { LoginService } from 'src/app/services/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./bootstrap.css','./login.component.css']
})
export class LoginComponent {
  login: Login = new Login();
  username:any;
  

 
  loginForm = this.fbs.group({
    email: ['', Validators.required],
    password: ['',Validators.required]   
})
  constructor(private fbs:FormBuilder, private loginService:LoginService, private router:Router, private customerService:CustomerService){}
  get email() {
    return this.loginForm.get('email')
  }
  get password() {
    return this.loginForm.get('password')
  }

  onClickLog(value: any){
    console.log(value);
  }
  onLogin(){
    // this.loginService.onLogin(this.createForm.value).subscribe((data:any)=>{
    //   localStorage.setItem("token",data.token);
    //   this.router.navigateByUrl('/customerHome')
    // })
    console.log(this.loginForm.value);
    this.customerService.login(this.loginForm.value).pipe(catchError((error) => {
      if (error.status === 403) {
        // Swal.fire({
        //   icon: 'error',
        //   text: error.error.errorMessgae,
        //   showConfirmButton: true,
        //   confirmButtonColor: 'red'
        // })
        Swal.fire({
          icon:'error',
          title:'Invalid credentials !!',
          timer:2000,
          showConfirmButton:false
        });
   
        
      }
       else {
        console.error('An error occurred:', error.statusText);
        Swal.fire({
          icon:'error',
          title:'Invalid credentials !!',
          timer:2000,
          showConfirmButton:false
        });
      }
      return throwError(error);
    })).subscribe((x: any) => {
      try {
        console.log(x)
        console.log(x.token);
        console.log(x.role);
        localStorage.setItem('loggedIn', '1')
        this.customerService.loggedIn = true;
        localStorage.setItem("token", x.token);
        console.log("TOKENNN"+ x.token);
        if (x.role == "ADMIN") {
          // localStorage.setItem('adminLoggedIn', '1')
          // console.log(localStorage.getItem('adminLoggedIn'));
  
          // this.customerService.adminLoggedIn = true
          // this.router.navigateByUrl('/modifycustomer');
          if (!localStorage.getItem('adminLoggedIn')) {
            localStorage.setItem('adminLoggedIn', '1');
          }
          console.log(localStorage.getItem('adminLoggedIn'));
          this.customerService.adminLoggedIn = true;
          this.router.navigateByUrl('/modifycustomer');
        }
        else {
          localStorage.setItem('id', x.customerId);
          // Check if the item is not already set to prevent refreshing
          if (!localStorage.getItem('customerLoggedIn')) {
            localStorage.setItem('customerLoggedIn', '1');
          }
          this.customerService.customerLoggedIn = true;
          const id = x.customerId;
          this.router.navigateByUrl(`/customerHome/${id}`);
        }
      } catch (error) {
        throw error
      }
    }, err => {
      console.log(err.error.errorMessgae);
  
    })
  }

  
  submitForm() {
    if (this.loginForm.valid) {
      console.log(this.loginForm.value);
    
    } else {
      this.markFormFieldsAsTouched(this.loginForm);
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

