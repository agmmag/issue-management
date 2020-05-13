import {Injectable} from "@angular/core";
import {ApiService} from "../api.service";
import {Observable} from 'rxjs';
import {map} from "rxjs/internal/operators";

@Injectable({
  providedIn: 'root'
})
export class IssueService {

  private ISSUE_PATH = "/issue";

  constructor(private apiService: ApiService) {
  }

  getAll(page): Observable<any> {
    return this.apiService.get(this.ISSUE_PATH + '/pagination', page).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          console.log(res);
          return {};
        }
      }
    ));
  }

  getByID(id): Observable<any> {
    return this.apiService.get(this.ISSUE_PATH, id).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          console.log(res);
          return {};
        }
      }
    ));
  }

  createIssue(issue): Observable<any> {
    return this.apiService.post(this.ISSUE_PATH, issue).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          console.log(res);
          return {};
        }
      }
    ));
  }

  delete(id): Observable<any> {
    return this.apiService.delete(this.ISSUE_PATH, id).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          console.log(res);
          return {};
        }
      }
    ));
  }
}
