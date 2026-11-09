import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComplementDemandeComponent } from './complement-demande.component';

describe('ComplementDemandeComponent', () => {
  let component: ComplementDemandeComponent;
  let fixture: ComponentFixture<ComplementDemandeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ComplementDemandeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ComplementDemandeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
