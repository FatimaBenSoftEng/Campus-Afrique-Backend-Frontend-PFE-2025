import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListPendingPaymentsComponent } from './list-pending-payments.component';

describe('ListPendingPaymentsComponent', () => {
  let component: ListPendingPaymentsComponent;
  let fixture: ComponentFixture<ListPendingPaymentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListPendingPaymentsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListPendingPaymentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
