export class Page {

  constructor() {
    this.number = 0;
    this.size = 10;
  }

  //The number of elements in the page
  size: number = 0;
  //The total number of elements
  totalElements: number = 0;
  //The total number of pages
  totalPages: number = 0;
  //The current page number
  number: number = 0;
}
