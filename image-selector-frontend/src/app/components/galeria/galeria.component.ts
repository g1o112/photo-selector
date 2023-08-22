import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BackendService } from 'src/app/backend/backend.service';
import { Imagem } from 'src/app/models/imagem';

@Component({
  selector: 'app-galeria',
  templateUrl: './galeria.component.html',
  styleUrls: ['./galeria.component.css']
})
export class GaleriaComponent implements OnInit {


  tag!: String | null;
  @Input() images!: Imagem[];
  termoDeBusca!: String;

  constructor(
    private service: BackendService,
    private route: ActivatedRoute
  ) {}

  async ngOnInit(): Promise<void> {
    this.route.paramMap.subscribe(async params => {
      this.tag = params.get('tag')
      if (this.tag == null) {
        this.images = await this.getAllImages();
        console.log(this.images)
      } else {
        this.images = await this.getImagesByTag(this.tag);
      }
    });
  }

  async getAllImages(): Promise<Imagem[]> {
    return new Promise<Imagem[]>((resolve, reject) => {
      this.service.getAllImages().subscribe(
        (data: Imagem[]) => {
          resolve(data);
        },
        (error) => {
          reject(error);
        }
      )
    })
  }

  async getImagesByTag(tag: String): Promise<Imagem[]> {
    return new Promise<Imagem[]>((resolve, reject) => {
      this.service.getImagesByTag(tag).subscribe(
        async (data: Imagem[]) => {
          if (data.length == 0) {
            data = await this.getAllImages();
            resolve(data);
          } else {
            resolve(data);
          }
        },
        (error) => {
          reject(error);
        }
      )
    })
  }
}
