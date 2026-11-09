import { TestBed } from '@angular/core/testing';

import { FormulaireSharedService } from './formulaire-shared.service';

describe('FormulaireSharedService', () => {
  let service: FormulaireSharedService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FormulaireSharedService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
