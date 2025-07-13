import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Mpin } from './mpin';

describe('Mpin', () => {
  let component: Mpin;
  let fixture: ComponentFixture<Mpin>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Mpin]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Mpin);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
