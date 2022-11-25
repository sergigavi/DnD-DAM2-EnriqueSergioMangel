import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LPloginComponent } from './lplogin.component';

describe('LPloginComponent', () => {
  let component: LPloginComponent;
  let fixture: ComponentFixture<LPloginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LPloginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LPloginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
