import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DogService {

  private baseUrl = 'http://localhost:8181/springboot-crud-rest/api/v1/dogs'; // TODO conferir

  constructor(private http: HttpClient) { }

  getDog(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createDog(dog: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, dog);
  }

  updateDog(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteDog(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getDogsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
