import { Component, OnInit } from '@angular/core';
import {PageContextService} from "../../services/page-context.service";

@Component({
  selector: 'app-page2',
  templateUrl: './page2.component.html',
  styleUrls: ['./page2.component.css']
})
export class Page2Component implements OnInit {

  constructor( context : PageContextService) {
    context.pageName = "page2";
  }

  ngOnInit() {
  }

}
