import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormulaireTransportComponent } from './formulaire-transport.component';

describe('FormulaireTransportComponent', () => {
  let component: FormulaireTransportComponent;
  let fixture: ComponentFixture<FormulaireTransportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormulaireTransportComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormulaireTransportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
