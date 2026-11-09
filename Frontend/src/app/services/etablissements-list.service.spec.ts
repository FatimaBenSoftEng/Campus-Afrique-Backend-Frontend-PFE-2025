import { TestBed } from '@angular/core/testing';

import { EtablissementsListService } from './etablissements-list.service';

describe('EtablissementsListService', () => {
  let service: EtablissementsListService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EtablissementsListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
