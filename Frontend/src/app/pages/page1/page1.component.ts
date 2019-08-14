import { Component, OnInit } from '@angular/core';
import {PageContextService} from "../../services/page-context.service";

@Component({
  selector: 'app-page1',
  templateUrl: './page1.component.html',
  styleUrls: ['./page1.component.css']
})
export class Page1Component implements OnInit {

  constructor( context : PageContextService) {
    context.pageName = "page1";
  }

  ngOnInit() {
  }

}
