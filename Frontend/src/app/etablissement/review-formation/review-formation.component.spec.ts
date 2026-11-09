import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewFormationComponent } from './review-formation.component';

describe('ReviewFormationComponent', () => {
  let component: ReviewFormationComponent;
  let fixture: ComponentFixture<ReviewFormationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReviewFormationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReviewFormationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
