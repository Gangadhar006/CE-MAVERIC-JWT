import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminmodifycustomerComponent } from './adminmodifycustomer.component';

describe('AdminmodifycustomerComponent', () => {
  let component: AdminmodifycustomerComponent;
  let fixture: ComponentFixture<AdminmodifycustomerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminmodifycustomerComponent]
    });
    fixture = TestBed.createComponent(AdminmodifycustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
