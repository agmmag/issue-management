import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {catchError} from "rxjs/operators";
import {error} from "util";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  get (path: string, params: HttpParams = new HttpParams()): Observable<any>{
    return this.get(path, {params}).pipe(catchError(this.formatError));
  }
  post (path: string, params: HttpParams = new HttpParams()): Observable<any>{
    return this.post(path, {params}).pipe(catchError(this.formatError));
  }
  put (path: string, params: HttpParams = new HttpParams()): Observable<any>{
    return this.put(path, {params}).pipe(catchError(this.formatError));
  }
  delete (path: string, params: HttpParams = new HttpParams()): Observable<any>{
    return this.delete(path, {params}).pipe(catchError(this.formatError));
  }
  delete2 (path: string, params: HttpParams = new HttpParams()): Observable<any>{
    return this.delete(path, {params}).pipe(catchError(this.formatError));
  }
  delete3 (path: string, params: HttpParams = new HttpParams()): Observable<any>{
    return this.delete(path, {params}).pipe(catchError(this.formatError));
  }

  private formatError(error: any){
    return Observable.of(error.error);
  }
}
