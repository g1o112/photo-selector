import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ColecaoPesquisaComponent } from './colecao-pesquisa.component';

describe('ColecaoPesquisaComponent', () => {
  let component: ColecaoPesquisaComponent;
  let fixture: ComponentFixture<ColecaoPesquisaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ColecaoPesquisaComponent]
    });
    fixture = TestBed.createComponent(ColecaoPesquisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
