import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthHub } from './auth-hub';

describe('AuthHub', () => {
  let component: AuthHub;
  let fixture: ComponentFixture<AuthHub>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuthHub]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuthHub);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
