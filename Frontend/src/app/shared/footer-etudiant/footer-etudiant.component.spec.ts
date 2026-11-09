import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FooterEtudiantComponent } from './footer-etudiant.component';

describe('FooterEtudiantComponent', () => {
  let component: FooterEtudiantComponent;
  let fixture: ComponentFixture<FooterEtudiantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FooterEtudiantComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FooterEtudiantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
