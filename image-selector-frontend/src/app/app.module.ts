import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { NgxMasonryModule } from 'ngx-masonry';
import { MainToolbarComponent } from './components/main-toolbar/main-toolbar.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { NoopAnimationsModule, BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { GaleriaComponent } from './components/galeria/galeria.component';
import { OverlayModule } from '@angular/cdk/overlay';
import { PopupCadastroComponent } from './components/popup-cadastro/popup-cadastro.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatStepperModule } from '@angular/material/stepper';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { ImagemExpandidaComponent } from './components/imagem-expandida/imagem-expandida.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { SnackbarFormularioComponent } from './components/snackbar-formulario/snackbar-formulario.component';
import { AdvancedSearchComponent } from './components/advanced-search/advanced-search.component';
import { ExemploComponent } from './components/exemplo/exemplo.component';
import { ColecaoPesquisaComponent } from './components/colecao-pesquisa/colecao-pesquisa.component';
import { HomeComponentComponent } from './components/home-component/home-component.component';



@NgModule({
  declarations: [
    AppComponent,
    MainToolbarComponent,
    GaleriaComponent,
    PopupCadastroComponent,
    ImagemExpandidaComponent,
    PageNotFoundComponent,
    SnackbarFormularioComponent,
    AdvancedSearchComponent,
    ExemploComponent,
    ColecaoPesquisaComponent,
    HomeComponentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgxMasonryModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    NoopAnimationsModule,
    OverlayModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatStepperModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSnackBarModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
