import { HttpResponseBase, HttpStatusCode } from '@angular/common/http';
import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatStepper } from '@angular/material/stepper';
import { BackendService } from 'src/app/backend/backend.service';
import { SnackbarFormularioComponent } from '../snackbar-formulario/snackbar-formulario.component';

@Component({
  selector: 'app-popup-cadastro',
  templateUrl: './popup-cadastro.component.html',
  styleUrls: ['./popup-cadastro.component.css'],
})
export class PopupCadastroComponent {
  @ViewChild(MatStepper) stepper!: MatStepper;
  imageFormulario: FormGroup;

  @Output() close = new EventEmitter<void>();

  constructor(
    private formBuilder: FormBuilder,
    private service: BackendService,
    private _snackBar: MatSnackBar,
  ) {
    this.imageFormulario = this.formBuilder.group({
      fotoUrl: ['', [Validators.required]],
    });
  }

  urlImage: string = '';

  isUrlValid!: boolean;

  keywordsPadrao!: String[];
  newKeywords: String[] = [];
  keywordIsLoading: boolean = true;
  newKeyword: string = '';
  keywordsFromMetadataToRemove: String[] = [];

  fecharDialog() {
    this.close.emit();
  }

  isValidURL(url: string): boolean {
    try {
      new URL(url);
      if (this.checkURL(url)) {
        return true;
      }
      return false;
    } catch (error) {
      return false;
    }
  }

  get fotoUrl() {
    return this.imageFormulario.get('fotoUrl');
  }

  onSubmit() {
    if (this.keywordsPadrao.length == 0) {
      this.mostrarErro();
      return;
    }

    this.service.addImagemComCategorias(
      this.urlImage,
      this.newKeywords,
      this.keywordsFromMetadataToRemove).subscribe(
        (response: boolean) => {
          this.exibirSnackbar(response);
        },
        (error) => {
          this.exibirSnackbar(false);
        }
      )

    this.fecharDialog();
  }

  exibirSnackbar(sucesso: boolean) {
    if(sucesso) {
      this._snackBar.open('Foto adicionada com sucesso!', 'Fechar');
    } else {
      this._snackBar.open('Erro ao adicionar foto.', 'Fechar')
    }
  }

  toSecondStep(newUrl: string) {
    this.urlImage = newUrl;
    if (this.urlImage.trim() == '' || !this.isValidURL(this.urlImage)) {
      this.isUrlValid = false;
      return;
    }
    this.isUrlValid = true;
    this.stepper.next();
    this.getKeywords(this.urlImage);
  }

  checkURL(url: string) {
    return url.match(/\.(jpeg|jpg|gif|png)$/) != null;
  }

  getKeywords(url: string) {
    this.service.getKeywordsFromUrl(url).subscribe(
      (result) => {
        this.keywordsPadrao = result;
          this.keywordIsLoading = false;
      },
      (error) => {
        return error;
      }
    );
  }

  onInputTag() {
    const categoria = (
      document.getElementsByName('newTag')[0] as HTMLInputElement
    ).value;
    if (categoria.trim() == '') return;
    this.addNewTag(categoria);
    this.newKeyword = '';
  }

  checagemPadrao: String[] = [];
  checagemNovas: String[] = [];
  private addNewTag(tag: string) {
    this.keywordsPadrao.forEach((keyword: String) => {
      this.checagemPadrao.push(keyword.toLowerCase());
    });
    this.newKeywords.forEach((keyword: String) => {
      this.checagemNovas.push(keyword.toLowerCase());
    });

    if (this.checagemPadrao.includes(tag.toLowerCase()) || this.checagemNovas.includes(tag.toLowerCase())) {
      return;
    }

    this.keywordsPadrao.push(tag);
    this.newKeywords.push(tag);
  }

  onRemoveTag(newTag: String) {
    this.keywordsPadrao = this.keywordsPadrao.filter((tag) => tag != newTag);
    if (this.newKeywords.includes(newTag)) {
      this.newKeywords = this.newKeywords.filter((tag) => tag != newTag);
      return;
    }
    this.keywordsFromMetadataToRemove.push(newTag);
  }

  showError: boolean = false;

  mostrarErro() {
    if (this.showError) {
      return;
    }
    this.showError = true;
    setTimeout(() => {
      this.showError = false;
    }, 3000);
  }
}
