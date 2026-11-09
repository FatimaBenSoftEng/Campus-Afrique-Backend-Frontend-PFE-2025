import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarEtablissementComponent } from './navbar-etablissement.component';

describe('NavbarEtablissementComponent', () => {
  let component: NavbarEtablissementComponent;
  let fixture: ComponentFixture<NavbarEtablissementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NavbarEtablissementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NavbarEtablissementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
