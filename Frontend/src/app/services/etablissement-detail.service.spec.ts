import { TestBed } from '@angular/core/testing';

import { EtablissementDetailService } from './etablissement-detail.service';

describe('EtablissementDetailService', () => {
  let service: EtablissementDetailService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EtablissementDetailService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
