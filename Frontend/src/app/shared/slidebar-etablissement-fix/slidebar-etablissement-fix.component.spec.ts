import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SlidebarEtablissementFixComponent } from './slidebar-etablissement-fix.component';

describe('SlidebarEtablissementFixComponent', () => {
  let component: SlidebarEtablissementFixComponent;
  let fixture: ComponentFixture<SlidebarEtablissementFixComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SlidebarEtablissementFixComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SlidebarEtablissementFixComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
