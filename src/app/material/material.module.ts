import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import * as Material from "@angular/material";
import {MatDialogModule} from "@angular/material/dialog";



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatDialogModule
  ],
  exports : [
    MatDialogModule
  ]
})
export class MaterialModule { }
