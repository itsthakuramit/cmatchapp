import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OldMatchesComponent } from './old-matches.component';

describe('OldMatchesComponent', () => {
  let component: OldMatchesComponent;
  let fixture: ComponentFixture<OldMatchesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OldMatchesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OldMatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
