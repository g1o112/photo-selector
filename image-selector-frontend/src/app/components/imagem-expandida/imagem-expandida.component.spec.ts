import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImagemExpandidaComponent } from './imagem-expandida.component';

describe('ImagemExpandidaComponent', () => {
  let component: ImagemExpandidaComponent;
  let fixture: ComponentFixture<ImagemExpandidaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ImagemExpandidaComponent]
    });
    fixture = TestBed.createComponent(ImagemExpandidaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
