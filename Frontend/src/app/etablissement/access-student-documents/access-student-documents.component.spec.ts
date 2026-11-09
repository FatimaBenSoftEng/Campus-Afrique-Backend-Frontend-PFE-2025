import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccessStudentDocumentsComponent } from './access-student-documents.component';

describe('AccessStudentDocumentsComponent', () => {
  let component: AccessStudentDocumentsComponent;
  let fixture: ComponentFixture<AccessStudentDocumentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AccessStudentDocumentsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccessStudentDocumentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
