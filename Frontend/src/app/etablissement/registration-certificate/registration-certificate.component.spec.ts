import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationCertificateComponent } from './registration-certificate.component';

describe('RegistrationCertificateComponent', () => {
  let component: RegistrationCertificateComponent;
  let fixture: ComponentFixture<RegistrationCertificateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistrationCertificateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistrationCertificateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
