import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FooterEtablissementComponent } from './footer-etablissement.component';

describe('FooterEtablissementComponent', () => {
  let component: FooterEtablissementComponent;
  let fixture: ComponentFixture<FooterEtablissementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FooterEtablissementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FooterEtablissementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
