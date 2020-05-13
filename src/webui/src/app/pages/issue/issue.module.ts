import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {IssueComponent} from './issue.component';
import {IssueRoutingModule} from "./issue.routing.module";
import {IssueService} from "../../services/shared/issue.service";
import {SharedModule} from "../../shared/shared.module";
import {NgxDatatableModule} from "@swimlane/ngx-datatable";

@NgModule({
  imports: [
    CommonModule,
    IssueRoutingModule,
    SharedModule,
    NgxDatatableModule
  ],
  providers: [IssueService],
  declarations: [IssueComponent]
})
export class IssueModule {
}
