import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProjectComponent} from './project.component';
import {ProjectRoutingModule} from "./project.routing.module";
import {ProjectService} from "../../services/shared/project.service";
import {NgxDatatableModule} from "@swimlane/ngx-datatable";

@NgModule({
  imports: [
    CommonModule,
    NgxDatatableModule,
    ProjectRoutingModule
  ],
  providers: [ProjectService],
  declarations: [ProjectComponent]
})
export class ProjectModule {
}
