CREATE TABLE "user" (
                        id bigserial not null
                            constraint user_pkey
                                primary key,
                        email varchar(255),
                        external_id uuid,
                        name varchar(255),
                        password varchar(255),
                        username varchar(255)
);

CREATE TABLE "movie" (
                         id bigserial not null
                             constraint movie_pkey
                                 primary key,
                         description varchar(255),
                         external_id uuid,
                         publication_date timestamp,
                         title varchar(255),
                         user_id bigint
                             constraint user_fkey
                                 references "user"
);

CREATE TABLE "movie_likes" (
                               movie_id bigint not null
                                   constraint movie_lfkey
                                       references movie,
                               user_id  bigint not null
                                   constraint user_lfkey
                                       references "user",
                               constraint movie_likes_pkey
                                   primary key (movie_id, user_id)
);

CREATE TABLE "movie_hates" (
                               movie_id bigint not null
                                   constraint movie_hfkey
                                       references movie,
                               user_id  bigint not null
                                   constraint user_hfkey
                                       references "user",
                               constraint movie_hates_pkey
                                   primary key (movie_id, user_id)
);