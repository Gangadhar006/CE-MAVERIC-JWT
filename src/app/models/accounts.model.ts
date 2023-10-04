export interface Accounts {
  "accountNumber": string;
  "amount": number,
  "accountType": string,
  "active": string,
  "currency": string
}

export class Account {
  "accountNumber": string;
  "amount": number;
  "accountType": string;
  "active": string;
  "currency": string;
}

export class Transaction {
  srcAccount: string;
  destAccount: string;
  amount: number;
}


export interface Transactions {
  srcAccount: string;
  destAccount: string;
  amount: number;
  currencyPair: string;
  rate: number;
  time: string | null;
  totalValue: string;
}
