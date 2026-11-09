import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormulaireBourseComponent } from './formulaire-bourse.component';

describe('FormulaireBourseComponent', () => {
  let component: FormulaireBourseComponent;
  let fixture: ComponentFixture<FormulaireBourseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormulaireBourseComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormulaireBourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
