import { TestBed } from '@angular/core/testing';

import { FirstAidService } from './first-aid.service';

describe('FirstAidService', () => {
  let service: FirstAidService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FirstAidService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
