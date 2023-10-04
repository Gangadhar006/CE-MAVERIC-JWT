import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerPlaceOrderComponent } from './customer-place-order.component';

describe('CustomerPlaceOrderComponent', () => {
  let component: CustomerPlaceOrderComponent;
  let fixture: ComponentFixture<CustomerPlaceOrderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CustomerPlaceOrderComponent]
    });
    fixture = TestBed.createComponent(CustomerPlaceOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
