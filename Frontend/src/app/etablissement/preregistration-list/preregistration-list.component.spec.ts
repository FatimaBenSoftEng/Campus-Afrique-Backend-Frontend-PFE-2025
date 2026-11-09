import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreregistrationListComponent } from './preregistration-list.component';

describe('PreregistrationListComponent', () => {
  let component: PreregistrationListComponent;
  let fixture: ComponentFixture<PreregistrationListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PreregistrationListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PreregistrationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
