import {Component, OnInit} from '@angular/core';
import {first} from 'rxjs/operators';
import {Router, ActivatedRoute} from '@angular/router';

import {AccountService, AlertService} from '@app/_services';

import {User, Movie} from '../_models';
import {Observable} from 'rxjs';
import {NgxSpinnerService} from "ngx-spinner";


@Component({templateUrl: 'list.component.html', styleUrls: ['list.component.css']})
export class ListComponent implements OnInit {
  id?: string;
  movies?: any[];
  likedMovies: any[] = [];
  hatedMovies: any[] = [];
  filterBy?: string;
  user?: User | null;

  loading = false;

  constructor(private route: ActivatedRoute,
              private router: Router, private accountService: AccountService, private spinner: NgxSpinnerService, private alertService: AlertService) {
    this.accountService.user.subscribe(x => this.user = x);
  }

  ngOnInit() {
    let filter = this.route.snapshot.queryParamMap.get('filterBy');

    if (filter) {
      this.filterBy = filter;
    }

    this.init();
  }

  public init() {
    this.spinner.show();
    this.id = this.route.snapshot.params['id'];

    if (this.id) {
      if (this.id === 'likes') {
        this.accountService.getAllMoviesByUser()
          .pipe(first())
          .subscribe(movies => this.movies = movies.sort((m1, m2) => {
            let likes1 = m1.likes ? m1.likes : '';
            let likes2 = m2.likes ? m2.likes : '';
            return (likes1 < likes2) ? 1 : -1
          }));
      } else if (this.id === 'hates') {
        this.accountService.getAllMoviesByUser()
          .pipe(first())
          .subscribe(movies => this.movies = movies.sort((m1, m2) => {
            let hates1 = m1.hates ? m1.hates : '';
            let hates2 = m2.hates ? m2.hates : '';
            return (hates1 < hates2) ? 1 : -1
          }));
      } else if (this.id === 'dates') {
        this.accountService.getAllMoviesByUser()
          .pipe(first())
          .subscribe(movies => this.movies = movies.sort((m1, m2) => {
            let publicationDate1 = m1.publicationDate ? m1.publicationDate : '';
            let publicationDate2 = m2.publicationDate ? m2.publicationDate : '';
            return new Date(publicationDate2).getTime() - new Date(publicationDate1).getTime();
          }));
      } else {
        this.accountService.getAllMoviesByUser(this.id)
          .pipe(first())
          .subscribe(movies => this.movies = movies);
      }
    } else {
      this.accountService.getAllMovies()
        .pipe(first())
        .subscribe(movies => this.movies = movies);
    }
    this.populateLikesAndHates();
    this.spinner.hide();
  }

  public populateLikesAndHates() {
    if (this.user) {
      this.accountService.getAllLikedMoviesByUser(this.user.id)
        .pipe(first())
        .subscribe(likedMovies => {
          this.likedMovies = likedMovies
        });
      this.accountService.getAllHatedMoviesByUser(this.user.id)
        .pipe(first())
        .subscribe(hatedMovies => {
          this.hatedMovies = hatedMovies
        });
    }
  }

  public like(movie: Movie) {
    this.spinner.show();
    if (this.user && this.user.id && movie.id) {

      this.accountService.likeHate(this.user.id, movie.id, true)
        .pipe(first())
        .subscribe({
          next: () => {
            this.alertService.success('Liked successful', {keepAfterRouteChange: true});
            this.ngOnInit();
          }, error: error => {
            console.log(error);
            // this.alertService.error('You cannot like a movie that you published');
            this.loading = false;
            this.ngOnInit();
          }
        });

    }
    this.spinner.hide();
  }

  public hate(movie: Movie) {
    if (this.user && this.user.id && movie.id) {

      this.accountService.likeHate(this.user.id, movie.id, false)
        .pipe(first())
        .subscribe({
          next: () => {
            this.alertService.success('Hated successful', {keepAfterRouteChange: true});
            this.ngOnInit();
          }, error: error => {
            console.log(error);
            // this.alertService.error('You cannot hate a movie that you published');
            this.loading = false;
            this.ngOnInit();
          }
        });
    }
  }

  public sort(filter: string) {
    console.log(filter);
    this.router.navigate(['/movies/' + filter], {queryParamsHandling: 'merge'});
    this.ngOnInit();
  }

  public showLikeMessage(movie: Movie): boolean {
    let result = false;
    if (this.user && this.user.id) {
      const userId = this.user.id;
      result = this.likedMovies.filter(lmovie => lmovie.id === movie.id).length === 1;
    }
    return result;
  }

  public showHateMessage(movie: Movie): boolean {
    let result = false;
    if (this.user && this.user.id) {
      const userId = this.user.id;
      result = this.hatedMovies.filter(hmovie => hmovie.id === movie.id).length === 1;
    }
    return result;
  }

  public removeLikeHate(movie: Movie) {
    if (this.user && this.user.id && movie.id) {
      this.accountService.removeLikeHate(this.user.id, movie.id).subscribe(res => {
        console.log('Logging response from hate: ', res);
        this.init();
      })
      console.log('ngOnInit called');
      setTimeout(() => {
        this.ngOnInit();
      }, 5000);
    }
  }

  public transformDate(publicationDate: string): string {
    if (!publicationDate) {
      return 'a long time ago';
    }
    let time = (Date.now() - new Date(publicationDate).getTime()) / 1000;
    if (time < 10) {
      return 'just now';
    } else if (time < 60) {
      return 'a moment ago';
    }
    const divider = [60, 60, 24, 30, 12];
    const string = [' second', ' minute', ' hour', ' day', ' month', ' year'];
    let i;
    for (i = 0; Math.floor(time / divider[i]) > 0; i++) {
      time /= divider[i];
    }
    const plural = Math.floor(time) > 1 ? 's' : '';
    return Math.floor(time) + string[i] + plural + ' ago';
  }
}
