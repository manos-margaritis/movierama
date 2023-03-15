import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {first} from 'rxjs/operators';

import {AccountService, AlertService} from '@app/_services';

import {User} from '../_models';

@Component({templateUrl: 'add-edit.component.html'})
export class AddEditComponent implements OnInit {
  form!: FormGroup;
  id?: string;
  title!: string;
  loading = false;
  submitting = false;
  submitted = false;
  user?: User | null;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private accountService: AccountService,
    private alertService: AlertService,
  ) {
    this.accountService.user.subscribe(x => this.user = x);
  }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];

    // form with validation rules
    this.form = this.formBuilder.group({
      title: ['', Validators.required],
      description: ['', Validators.required]
    });

    this.title = 'Add Movie';
  }

  get f() {
    return this.form.controls;
  }

  onSubmit() {
    this.submitted = true;

    // reset alerts on submit
    this.alertService.clear();

    // stop here if form is invalid
    if (this.form.invalid) {
      return;
    }

    this.submitting = true;
    if (this.user && this.user.id) {
      this.accountService.createMovie(this.form.value, this.user, this.user.id)
        .pipe(first())
        .subscribe({
          next: () => {
            this.alertService.success('Movie created', {keepAfterRouteChange: true});
            this.router.navigateByUrl('/movies');
          },
          error: error => {
            this.alertService.error(error);
            this.submitting = false;
          }
        })
    }
  }
}
