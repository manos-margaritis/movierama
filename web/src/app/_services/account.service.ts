import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {map} from 'rxjs/operators';

import {environment} from '@environments/environment';
import {User} from '@app/_models';
import {Movie} from '@app/_models/movie';
import {Vote} from '@app/_models/vote';

@Injectable({providedIn: 'root'})
export class AccountService {
  private userSubject: BehaviorSubject<User | null>;
  public user: Observable<User | null>;

  constructor(
    private router: Router,
    private http: HttpClient
  ) {
    this.userSubject = new BehaviorSubject(JSON.parse(localStorage.getItem('user')!));
    this.user = this.userSubject.asObservable();
  }

  public get userValue() {
    return this.userSubject.value;
  }

  login(username: string, password: string) {
    console.log(`${environment.apiUrl}`);
    let body = new URLSearchParams();
    body.set("user", username);
    body.set("password", password);
    let headers = new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded'});
    let options = {headers: headers};
    return this.http.post<User>(`${environment.apiUrl}/users/login`, body, options)
      .pipe(map(user => {
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        console.log(JSON.stringify(user));
        localStorage.setItem('user', JSON.stringify(user));
        this.userSubject.next(user);
        return user;
      }));
  }

  logout() {
    // remove user from local storage and set current user to null
    localStorage.removeItem('user');
    this.userSubject.next(null);
    this.router.navigate(['/movies']);
  }

  register(user: User) {
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    let options = {headers: headers};
    return this.http.post(`${environment.apiUrl}/users/register`, user, options);
  }

  createMovie(movie: Movie, user: User, userId: string) {
    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    let data = {userId: userId};

    movie.publisher = new User();
    movie.publisher.username = user.username;
    movie.publisher.id = user.id;

    let options = {params: new HttpParams({fromObject: data}), headers: headers};

    return this.http.post(`${environment.apiUrl}/movies`, movie, options);
  }

  getAllMovies(): Observable<any[]> {
    return this.http.get<Movie[]>(`${environment.apiUrl}/movies`);
  }

  getAllMoviesByUser(id?: string) {
    const userId: string = id !== undefined ? id : '';

    let data = {userId: userId};
    return this.http.get<Movie[]>(`${environment.apiUrl}/movies`, {params: data});
  }

  likeHate(userId: string, movieId: string, like: boolean) {
    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    let data = {userId: userId};

    let options = {params: new HttpParams({fromObject: data}), headers: headers};

    let vote = new Vote();
    vote.like = like;
    vote.movie = movieId;

    return this.http.put(`${environment.apiUrl}/votes`, vote, options);
  }

  removeLikeHate(userId: string, movieId: string) {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    let data = {userId: userId, movie: movieId};

    let options = {params: new HttpParams({fromObject: data}), headers: headers};

    return this.http.delete(`${environment.apiUrl}/votes`, options);
  }

  getAllLikedMoviesByUser(id?: string) {
    const userId: string = id !== undefined ? id : '';

    let data = {userId: userId};
    return this.http.get<Movie[]>(`${environment.apiUrl}/movies/likes`, {params: data});
  }

  getAllHatedMoviesByUser(id?: string) {
    const userId: string = id !== undefined ? id : '';

    let data = {userId: userId};
    return this.http.get<Movie[]>(`${environment.apiUrl}/movies/hates`, {params: data});
  }

  update(id: string, params: any) {
    return this.http.put(`${environment.apiUrl}/users/${id}`, params)
      .pipe(map(x => {
        // update stored user if the logged in user updated their own record
        if (id == this.userValue?.id) {
          // update local storage
          const user = {...this.userValue, ...params};
          localStorage.setItem('user', JSON.stringify(user));

          // publish updated user to subscribers
          this.userSubject.next(user);
        }
        return x;
      }));
  }
}
