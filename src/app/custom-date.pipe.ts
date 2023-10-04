import { DatePipe } from '@angular/common';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'customDate'
})
export class CustomDatePipe implements PipeTransform {

  transform(value: string): string {
    const datePipe = new DatePipe('en-US');
    const formattedDate = datePipe.transform(value, 'dd/MM/yyyy HH:mm:ss', 'Asia/Kolkata');
    return formattedDate || value;
  }
  
  }


