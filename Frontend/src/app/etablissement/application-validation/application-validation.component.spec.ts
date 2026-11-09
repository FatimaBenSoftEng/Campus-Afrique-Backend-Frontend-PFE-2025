import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplicationValidationComponent } from './application-validation.component';

describe('ApplicationValidationComponent', () => {
  let component: ApplicationValidationComponent;
  let fixture: ComponentFixture<ApplicationValidationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ApplicationValidationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApplicationValidationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
