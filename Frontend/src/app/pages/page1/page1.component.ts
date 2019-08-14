import { Component, OnInit } from '@angular/core';
import {PageContextService} from "../../services/page-context.service";
import {HttpClient} from "@angular/common/http";
import {tap} from "rxjs/operators";

interface Employee {
  id?: String,
  employee_name?: String,
  employee_salary?: String,
  employee_age?: String,
  profile_image?: String
}

@Component({
  selector: 'app-page1',
  templateUrl: './page1.component.html',
  styleUrls: ['./page1.component.css']
})
export class Page1Component implements OnInit {

  private employees : Employee[];
  private httpClient :HttpClient;
  private testObj : String[]= new Array<String>("sad","aa");

  constructor( context :PageContextService, httpClient :HttpClient) {
    context.pageName = "page1";
    this.httpClient = httpClient;
  }

  ngOnInit() {
    this.httpClient.get<Employee[]>("http://dummy.restapiexample.com/api/v1/employees").pipe(tap( employee => this.employees = employee)).subscribe();

  }

}
