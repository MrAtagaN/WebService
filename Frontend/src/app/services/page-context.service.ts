import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PageContextService {

  pageName : string = "test" ;

  constructor() { }
}
