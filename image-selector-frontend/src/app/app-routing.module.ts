import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GaleriaComponent } from './components/galeria/galeria.component';
import { ImagemExpandidaComponent } from './components/imagem-expandida/imagem-expandida.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { AdvancedSearchComponent } from './components/advanced-search/advanced-search.component';
import { ColecaoPesquisaComponent } from './components/colecao-pesquisa/colecao-pesquisa.component';
import { HomeComponentComponent } from './components/home-component/home-component.component';



const routes: Routes = [
  { path: 'home', component: HomeComponentComponent},
  { path: '', component: HomeComponentComponent },
  { path: 'galeria', component: GaleriaComponent },
  { path: 'buscar/:tag', component: HomeComponentComponent },
  { path: 'busca-avancada', component: AdvancedSearchComponent },
  { path: 'image/:id', component: ImagemExpandidaComponent },
  { path: '**', redirectTo: 'pagina-nao-encontrada' },
  { path: 'pagina-nao-encontrada', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
