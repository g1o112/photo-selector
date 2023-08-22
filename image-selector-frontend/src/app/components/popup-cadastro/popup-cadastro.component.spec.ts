import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupCadastroComponent } from './popup-cadastro.component';

describe('PopupCadastroComponent', () => {
  let component: PopupCadastroComponent;
  let fixture: ComponentFixture<PopupCadastroComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PopupCadastroComponent]
    });
    fixture = TestBed.createComponent(PopupCadastroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
