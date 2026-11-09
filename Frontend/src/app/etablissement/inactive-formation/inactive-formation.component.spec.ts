import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InactiveFormationComponent } from './inactive-formation.component';

describe('InactiveFormationComponent', () => {
  let component: InactiveFormationComponent;
  let fixture: ComponentFixture<InactiveFormationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InactiveFormationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InactiveFormationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
