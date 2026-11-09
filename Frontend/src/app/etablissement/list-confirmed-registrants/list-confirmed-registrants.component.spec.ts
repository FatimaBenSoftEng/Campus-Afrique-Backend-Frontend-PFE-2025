import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListConfirmedRegistrantsComponent } from './list-confirmed-registrants.component';

describe('ListConfirmedRegistrantsComponent', () => {
  let component: ListConfirmedRegistrantsComponent;
  let fixture: ComponentFixture<ListConfirmedRegistrantsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListConfirmedRegistrantsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListConfirmedRegistrantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
