import {Injectable} from "@angular/core";
import {ApiService} from "../api.service";
import {Observable} from 'rxjs';
import {map} from "rxjs/internal/operators";

@Injectable({
  providedIn: 'root'
})
export class IssueHistoryService {

  private ISSUE_HISTORY_PATH = "/issue/history";

  constructor(private apiService: ApiService) {
  }

  getAll(page): Observable<any> {
    return this.apiService.get(this.ISSUE_HISTORY_PATH + '/pagination', page).pipe(map(
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
    return this.apiService.get(this.ISSUE_HISTORY_PATH, id).pipe(map(
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

  createIssueHistory(issueHistory): Observable<any> {
    return this.apiService.post(this.ISSUE_HISTORY_PATH, issueHistory).pipe(map(
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
    return this.apiService.delete(this.ISSUE_HISTORY_PATH + '/' + id).pipe(map(
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
