import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormulaireVisaComponent } from './formulaire-visa.component';

describe('FormulaireVisaComponent', () => {
  let component: FormulaireVisaComponent;
  let fixture: ComponentFixture<FormulaireVisaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormulaireVisaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormulaireVisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
