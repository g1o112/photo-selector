import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SnackbarFormularioComponent } from './snackbar-formulario.component';

describe('SnackbarFormularioComponent', () => {
  let component: SnackbarFormularioComponent;
  let fixture: ComponentFixture<SnackbarFormularioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SnackbarFormularioComponent]
    });
    fixture = TestBed.createComponent(SnackbarFormularioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
