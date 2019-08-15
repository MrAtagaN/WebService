import { Component, OnInit } from '@angular/core';
import {PageContextService} from "../../services/page-context.service";
import {HttpClient} from "@angular/common/http";
import {tap} from "rxjs/operators";

interface Person {
  name:String,
  age:Number
}
@Component({
  selector: 'app-page2',
  templateUrl: './page2.component.html',
  styleUrls: ['./page2.component.css']
})
export class Page2Component implements OnInit {

  private httpClient :HttpClient;
  private persons : Person[];

  constructor( context : PageContextService, httpClient :HttpClient) {
    context.pageName = "page2";
    this.httpClient = httpClient;
  }

  ngOnInit() {
    this.httpClient.get<Person[]>("http://localhost/getUser").pipe(tap( persons => this.persons = persons)).subscribe();

  }

}
