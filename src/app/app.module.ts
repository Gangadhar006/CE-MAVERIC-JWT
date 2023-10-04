import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { FormsModule ,ReactiveFormsModule} from '@angular/forms';
import { HomeComponent } from './home/home.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { AboutComponent } from './about/about.component';
import { CustomerplaceorderComponent } from './customerplaceorder/customerplaceorder.component';
import { AdminhomeComponent } from './adminhome/adminhome.component';
import { AdminmodifycustomerComponent } from './adminmodifycustomer/adminmodifycustomer.component';
import { AccountshomeComponent } from './accountshome/accountshome.component';
import { AccountcreateComponent } from './accountcreate/accountcreate.component';
import { MatDialogModule } from '@angular/material/dialog';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { CreatecustomerComponent } from './createcustomer/createcustomer.component';
import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './navbar/navbar.component';
import { NgConfirmModule } from 'ng-confirm-box';
import { EditcustomerComponent } from './editcustomer/editcustomer.component';
import { AccounteditComponent } from './accountedit/accountedit.component';
import { NgxUiLoaderHttpModule, NgxUiLoaderModule } from 'ngx-ui-loader';

import { CreateCustomerComponent } from './create-customer/create-customer.component';
import { CustomerBlockComponent } from './customer-block/customer-block.component';
import { CustomerHomeComponent } from './customer-home/customer-home.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { CustomerPlaceOrderComponent } from './customer-place-order/customer-place-order.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import { CustomAlertComponent } from './custom-alert/custom-alert.component';
import { CustomDatePipe } from './custom-date.pipe';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    HomeComponent,
    AboutComponent,
    CustomerplaceorderComponent,
    AdminhomeComponent,
    AdminmodifycustomerComponent,
    AccountshomeComponent,
    AccountcreateComponent,
    CreatecustomerComponent,
    NavbarComponent,
    EditcustomerComponent,
    AccounteditComponent,
    CreateCustomerComponent,
    CustomerBlockComponent,
    CustomerHomeComponent,
    CustomerPlaceOrderComponent,
    AdminHomeComponent,
    CustomAlertComponent,
    CustomDatePipe
    
  ],
  exports:[
    BrowserAnimationsModule
  ],
  
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgConfirmModule,
    NgxUiLoaderModule,
    BrowserAnimationsModule,
    MatSnackBarModule,
    NgxUiLoaderHttpModule.forRoot({
      showForeground:true,
    })
    
  ],
  
  providers: [],
  bootstrap: [AppComponent]
  
  
})
export class AppModule { }
