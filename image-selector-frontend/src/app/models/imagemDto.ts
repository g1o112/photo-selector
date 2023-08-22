export class ImagemDto {

  private imagemUrl: String;

  private tagsToRemove: String[];

  private tagsToAdd: String[];

  constructor(imagemUrl: String, tagsToRemove: String[], tagsToAdd: String[]) {
    this.imagemUrl = imagemUrl;
    this.tagsToRemove = tagsToRemove;
    this.tagsToAdd = tagsToAdd;
  }

  public getImagemUrl(): String {
    return this.imagemUrl;
  }

  public getTagsToRemove(): String[] {
    return this.tagsToRemove;
  }

  public getTagsToAdd(): String[] {
    return this.tagsToAdd;
  }

  public setImagemId(imagemUrl: string) {
    this.imagemUrl = imagemUrl;
  }

  public setTagsToRemove(tagsToRemove: String[]) {
    this.tagsToRemove = tagsToRemove;
  }

  public setTagsToAdd(tagsToAdd: String[]) {
    this.tagsToAdd = tagsToAdd;
  }
}
