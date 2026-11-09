import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarEvenementsComponent } from './sidebar-evenements.component';

describe('SidebarEvenementsComponent', () => {
  let component: SidebarEvenementsComponent;
  let fixture: ComponentFixture<SidebarEvenementsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SidebarEvenementsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SidebarEvenementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
