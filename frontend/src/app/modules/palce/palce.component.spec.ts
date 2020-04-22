import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PalceComponent } from './palce.component';

describe('PalceComponent', () => {
  let component: PalceComponent;
  let fixture: ComponentFixture<PalceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PalceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PalceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
