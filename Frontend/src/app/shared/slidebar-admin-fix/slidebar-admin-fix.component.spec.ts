import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SlidebarAdminFixComponent } from './slidebar-admin-fix.component';

describe('SlidebarAdminFixComponent', () => {
  let component: SlidebarAdminFixComponent;
  let fixture: ComponentFixture<SlidebarAdminFixComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SlidebarAdminFixComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SlidebarAdminFixComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
