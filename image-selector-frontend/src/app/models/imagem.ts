import { DomSanitizer, SafeUrl } from "@angular/platform-browser";

export class Imagem {

  id: number;

  url: string;

  allKeywords: string[];

  secureUrl: SafeUrl;

  constructor(id: number, url: string, allKeywords: string[], private sanitizer: DomSanitizer) {
    this.id = id;
    this.url = url;
    this.allKeywords = allKeywords;
    this.secureUrl = this.getSafeUrl(url);
  }

  getSafeUrl(url: string) {
    return this.sanitizer.bypassSecurityTrustUrl(url);
  }


}
