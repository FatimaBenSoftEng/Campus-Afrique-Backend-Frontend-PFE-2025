import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiveFormationComponent } from './active-formation.component';

describe('ActiveFormationComponent', () => {
  let component: ActiveFormationComponent;
  let fixture: ComponentFixture<ActiveFormationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActiveFormationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActiveFormationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
