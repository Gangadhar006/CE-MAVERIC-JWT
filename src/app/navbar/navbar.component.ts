import { Component } from '@angular/core';
import { CustomerService } from '../customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(public _customerService:CustomerService,private router:Router){}

  custId: number = parseInt(localStorage.getItem('id') ?? '0', 10);

  goBack(): void {
    this.router.navigate(['/modifycustomer']); // Use the Router service to navigate back to the previous screen
  }

  logout(): void {
    // Call the logout method from your AuthService
    this._customerService.logout();
  }
}
