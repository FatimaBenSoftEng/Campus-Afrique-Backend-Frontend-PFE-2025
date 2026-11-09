import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormulaireHebergementComponent } from './formulaire-hebergement.component';

describe('FormulaireHebergementComponent', () => {
  let component: FormulaireHebergementComponent;
  let fixture: ComponentFixture<FormulaireHebergementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormulaireHebergementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormulaireHebergementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
