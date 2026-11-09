import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormulairePreinscriptionComponent } from './formulaire-preinscription.component';

describe('FormulairePreinscriptionComponent', () => {
  let component: FormulairePreinscriptionComponent;
  let fixture: ComponentFixture<FormulairePreinscriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormulairePreinscriptionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormulairePreinscriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
