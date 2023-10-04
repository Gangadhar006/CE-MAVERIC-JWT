import { Component } from '@angular/core';
//import userData from './users.json';
import { MatDialog} from "@angular/material/dialog";
import { CreatecustomerComponent } from '../createcustomer/createcustomer.component';
import { Customers } from '../models/customer.model';
import { CustomerService } from '../customer.service';
import { EditcustomerComponent } from '../editcustomer/editcustomer.component';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Overlay } from '@angular/cdk/overlay';

@Component({
  selector: 'app-adminmodifycustomer',
  templateUrl: './adminmodifycustomer.component.html',
  styleUrls: ['./adminmodifycustomer.component.css']
})
export class AdminmodifycustomerComponent {
  hide : boolean = true;
  users:Customers[] = [];
  pageSize: number = 5; // Number of items per page
 
  // Pagination
  p: number = 1; // Current page, default to 1

  get pagedUsers(): any[] {
    const startIndex = (this.p - 1) * this.pageSize;
    return this.users.slice(startIndex, startIndex + this.pageSize);
  }
  

  get pages(): number[] {
    const pageCount = Math.ceil(this.users.length / this.pageSize);
    return Array.from({ length: pageCount }, (_, index) => index + 1);
  }

  changePage(pageNum: number): void {
    if (pageNum >= 1 && pageNum <= this.pages.length) {
      this.p = pageNum;
    }
  }
  constructor(private dialog:MatDialog,private customerService: CustomerService,private router:Router,private route:ActivatedRoute,private overlay:Overlay){}
  
  ngOnInit(): void {
    this.getAllCustomerss();
   
   }

   
  onCreate(){
    this.dialog.open(CreatecustomerComponent, {
      
      maxWidth:'750px', // Set desired width
      height:'max-content',
    });
  }
  

  getAllCustomerss() {
    this.customerService.getAllCustomers().subscribe(customers => {
     this.users = customers;
     });
   }
 
  // onDelete(id:string){te
  //   this.customerService.deleteCustomer().subscribe(()=> console.log('Employee deleted'));
      
  // }

  // onDelete(id:number){
  //     this.customerService.deleteCustomer(id).subscribe(data =>{
  //       this.getAllCustomerss();
  //       this.successAlert()
  //       }) 
  // }
  onDelete(id: number) {
    // Show SweetAlert2 confirmation dialog
    Swal.fire({
      title: 'Are you sure?',
      text: 'You will not be able to recover this customer!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, cancel',
    }).then((result) => {
      if (result.isConfirmed) {
        // User confirmed deletion, call your delete method here
        this.customerService.deleteCustomer(id).subscribe(() => {
          this.getAllCustomerss();
        });
  
        // Show a success message
        Swal.fire('Deleted!', 'The customer has been deleted.', 'success');
      }
    });
  }
  

  onEdit(id:any){
    console.log(id);
    this.customerService.edit=id
    this.dialog.open(EditcustomerComponent ,{
      width:'750px', // Set desired width
      height:'max-content'
    });
    this.getAllCustomerss();
    this.router.navigateByUrl('/modifycustomer')
  }

  onAccountsClick(id:number){
    this.router.navigate([`accountshome`,id]);
  }
  


  successAlert() {
    this.customerService.openCustomAlert('Clicked!');
    
  }
}
