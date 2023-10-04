  export interface Customers{
    "id":number,
    "firstName": string,
    "lastName": string,
    "dob":Date,
    "age": number,
    "email": string,
    "gender": string,
    "phone": string;
    "password":string;
  }

  
export class Customer {
    "id":number;
    "firstName": string;
    "lastName": string;
    "dob": Date;
    "age": number;
    "email": string;
    "gender": string;
    "phone": string;
    "password":string;
  }
  
  export class Login{
    "email":string;
    "password":string;
  }