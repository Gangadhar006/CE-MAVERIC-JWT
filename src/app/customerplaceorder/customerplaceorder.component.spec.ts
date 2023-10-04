import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerplaceorderComponent } from './customerplaceorder.component';

describe('CustomerplaceorderComponent', () => {
  let component: CustomerplaceorderComponent;
  let fixture: ComponentFixture<CustomerplaceorderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CustomerplaceorderComponent]
    });
    fixture = TestBed.createComponent(CustomerplaceorderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
