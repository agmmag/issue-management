import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {IssueService} from "../../../services/shared/issue.service";
import {UserService} from "../../../services/shared/user.service";
import {ProjectService} from "../../../services/shared/project.service";

@Component({
  selector: 'app-issue-detail',
  templateUrl: './issue-detail.component.html',
  styleUrls: ['./issue-detail.component.scss']
})
export class IssueDetailComponent implements OnInit {

  //Route parameter options
  id: number;
  private sub: any;

  issueDetail = {};

  //History table options
  dataTableRows = [];
  columns = [];

  //Dropdown values
  projectOptions = [];
  assigneeOptions = [];
  issueStatusOptions = [];

  constructor(private route: ActivatedRoute,
              private userService: UserService,
              private projectService: ProjectService,
              private issueService: IssueService) {

  }

  ngOnInit() {

    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.loadIssueDetails();

    });

    this.columns = [
      {prop: 'id', name: 'No'},
      {prop: 'description', name: 'Description'},
      {prop: 'date', name: 'Issue Date'},
      {prop: 'issueStatus', name: 'Issue Status'},
      {prop: 'assignee.nameSurname', name: 'Assignee'},
      {prop: 'issue.project.projectName', name: 'Project Name'}
    ];

    this.loadProjects();
    this.loadAssignees();
    this.loadIssueStatuses();
  }

  private loadAssignees() {
    this.userService.getAll().subscribe(response => {
      this.assigneeOptions = response;
    })
  }

  private loadIssueStatuses() {
    this.issueService.getAllIssueStatuses().subscribe(response => {
      this.issueStatusOptions = response;
    })
  }

  private loadProjects() {
    this.projectService.getAll().subscribe(response => {
      this.projectOptions = response;
    })
  }

  private loadIssueDetails() {
    this.issueService.getByIdWithDetails(this.id).subscribe(response => {
      this.issueDetail = response;
      this.dataTableRows = response['issueHistories'];
    })
  }
}
