import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizationInterviewsComponent } from './organization-interviews.component';

describe('OrganizationInterviewsComponent', () => {
  let component: OrganizationInterviewsComponent;
  let fixture: ComponentFixture<OrganizationInterviewsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrganizationInterviewsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrganizationInterviewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
