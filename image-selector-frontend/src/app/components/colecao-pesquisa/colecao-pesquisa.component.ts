import { BackendService } from 'src/app/backend/backend.service';
import { Component, EventEmitter, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PopupCadastroComponent } from '../popup-cadastro/popup-cadastro.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-colecao-pesquisa',
  templateUrl: './colecao-pesquisa.component.html',
  styleUrls: ['./colecao-pesquisa.component.css']
})
export class ColecaoPesquisaComponent {


  searchFormulario: FormGroup;
  textToSearch: String = '';
  isSearchBarShown = false;

  constructor(public dialog: MatDialog, private formBuilder: FormBuilder, private route: Router, private service: BackendService) {
    this.searchFormulario = this.formBuilder.group({
      fotoUrl: ['', [Validators.required]],
    });
  }

  onInputSearch() {
    const searchText = (
      document.getElementsByName('search')[0] as HTMLInputElement
    ).value;
    if (searchText == '') return;
    this.route.navigateByUrl("/buscar/" + searchText);
    (document.getElementsByName('search')[0] as HTMLInputElement).value = "";
    (document.getElementsByName('search')[0] as HTMLInputElement).blur();
  }

  openCadastroPopup() {
    const dialogRef = this.dialog.open(PopupCadastroComponent);

    dialogRef.componentInstance.close.subscribe(() => {
      this.dialog.closeAll();
    });
  }

}
