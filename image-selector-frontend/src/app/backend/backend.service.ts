import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Imagem } from '../models/imagem';
import { ImagemDto } from '../models/imagemDto';

@Injectable({
  providedIn: 'root'
})
export class BackendService{

  private urlAPI = 'http://localhost:8080/api'

  constructor(private http: HttpClient) {}

  public getAllImages(): Observable<Imagem[]> {
    return this.http.get<Imagem[]>(`${this.urlAPI}/images/all-images`);
  }

  public getImageById(id: number) {
    return this.http.get<Imagem>(`${this.urlAPI}/images/imagem/${id}`);
  }

  public getKeywordsFromUrl(url: string) {
    return this.http.get<String[]>(`${this.urlAPI}/images/get-keywords?url=${url}`)
  }

  public addImagemComCategorias(url: String, newKeywords: String[], trKeywords: String[]): Observable<boolean> {
    return this.http.post<boolean>(`${this.urlAPI}/images/imagem-dto`, new ImagemDto(url, trKeywords, newKeywords));
  }

  public getImagesByTag(tag: String) {
    return this.http.get<Imagem[]>(`${this.urlAPI}/keywords/exibir/${tag}`);
  }

  public startDataBase() {
    return this.http.get(`${this.urlAPI}/images/start-bd`).subscribe(
      (data) => {
        console.log(data)
      },
      (error) => {
        console.log(error);
      }
    );
  }



}
