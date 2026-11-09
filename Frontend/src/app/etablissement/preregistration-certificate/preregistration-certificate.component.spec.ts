import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreregistrationCertificateComponent } from './preregistration-certificate.component';

describe('PreregistrationCertificateComponent', () => {
  let component: PreregistrationCertificateComponent;
  let fixture: ComponentFixture<PreregistrationCertificateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PreregistrationCertificateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PreregistrationCertificateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
