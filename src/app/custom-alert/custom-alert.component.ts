import { Component, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-custom-alert',
  templateUrl: './custom-alert.component.html',
  styleUrls: ['./custom-alert.component.css']
})
export class CustomAlertComponent implements OnInit {
 // constructor(@Inject(MAT_DIALOG_DATA) public data: { message: string }) {}
 ngOnInit(): void {
  // Set a timeout of 3 seconds (3000 milliseconds) to close the alert
  setTimeout(() => {
    this.closeAlert();
  }, 3000);
}
closeAlert(){

}
}
