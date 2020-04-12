import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllCinemasComponent } from './all-cinemas.component';

describe('AllCinemasComponent', () => {
  let component: AllCinemasComponent;
  let fixture: ComponentFixture<AllCinemasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllCinemasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllCinemasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
