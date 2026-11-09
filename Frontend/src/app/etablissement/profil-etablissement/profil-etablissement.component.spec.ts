import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilEtablissementComponent } from './profil-etablissement.component';

describe('ProfilEtablissementComponent', () => {
  let component: ProfilEtablissementComponent;
  let fixture: ComponentFixture<ProfilEtablissementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfilEtablissementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfilEtablissementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
