import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {IssueService} from "../../../services/shared/issue.service";
import {UserService} from "../../../services/shared/user.service";
import {ProjectService} from "../../../services/shared/project.service";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-issue-detail',
  templateUrl: './issue-detail.component.html',
  styleUrls: ['./issue-detail.component.scss']
})
export class IssueDetailComponent implements OnInit {

  //Route parameter options
  id: number;
  private sub: any;
  issueDetailForm: FormGroup;

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
              private issueService: IssueService,
              private formBuilder: FormBuilder) {

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
      this.issueDetailForm = this.createIssueDetailFormGroup(response);
      this.dataTableRows = response['issueHistories'];
    })
  }

  createIssueDetailFormGroup(response) {
    return this.formBuilder.group({
      id: response['id'],
      description: response['description'],
      details: response['details'],
      date: response['date'],
      issueStatus: response['issueStatus'],
      assignee_id: response['assignee']['id'],
      project_id: response['project']['id'],
      project_manager: response['project']['manager']['nameSurname']
    });
  }

  saveIssue() {
    this.issueService.updateIssue(this.issueDetailForm.value).subscribe(response => {
      console.log(response)
      this.loadIssueDetails()
    })
  }

  fromJsonDate(jDate): string {
    const bDate: Date = new Date(jDate);
    return bDate.toISOString().substring(0, 10);
  }
}
