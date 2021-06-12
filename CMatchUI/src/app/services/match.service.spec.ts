import { TestBed } from '@angular/core/testing';

import { MatchService } from './match.service';

describe('MatchService', () => {
  let service: MatchService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MatchService);
  });
});
