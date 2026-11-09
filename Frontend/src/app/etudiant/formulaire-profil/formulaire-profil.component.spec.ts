import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormulaireProfilComponent } from './formulaire-profil.component';

describe('FormulaireProfilComponent', () => {
  let component: FormulaireProfilComponent;
  let fixture: ComponentFixture<FormulaireProfilComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormulaireProfilComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormulaireProfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
