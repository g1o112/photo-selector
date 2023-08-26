import { BackendService } from 'src/app/backend/backend.service';
import { Component, EventEmitter, Output, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PopupCadastroComponent } from '../popup-cadastro/popup-cadastro.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-colecao-pesquisa',
  templateUrl: './colecao-pesquisa.component.html',
  styleUrls: ['./colecao-pesquisa.component.css']
})
export class ColecaoPesquisaComponent implements OnInit{


  searchFormulario: FormGroup;
  textToSearch: String = '';
  isSearchBarShown = false;
  keywords !: String[];
  keywords2 !: String[];
  categoriaSelecionada: String = '';
  barra2: String[] = [];

  constructor(public dialog: MatDialog, private formBuilder: FormBuilder, private route: Router, private service: BackendService) {
    this.searchFormulario = this.formBuilder.group({
      fotoUrl: ['', [Validators.required]],
    });
  }
  ngOnInit(): void {
    this.service.getAllKeywords().subscribe(
      (data) => {
        this.keywords = data.slice(11,16);
        this.keywords2 = data.slice(0,4);
        console.log(this.keywords);
      },
      (error) =>{
        console.log(error);
      }
    )
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

  onClickCategoria(categoria: String) {

    this.barra2.push(categoria);
    this.categoriaSelecionada = categoria;
    this.route.navigateByUrl("/buscar/" + categoria);

  }

  removerCategoria(categoria: String) {
    const categoriaIndex = this.barra2.indexOf(categoria);
    if (categoriaIndex !== -1) {
      this.barra2.splice(categoriaIndex, 1);
    }
  }

}
