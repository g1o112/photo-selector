import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExemploComponent } from './exemplo.component';

describe('ExemploComponent', () => {
  let component: ExemploComponent;
  let fixture: ComponentFixture<ExemploComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ExemploComponent]
    });
    fixture = TestBed.createComponent(ExemploComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
