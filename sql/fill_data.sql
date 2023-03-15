INSERT INTO public.user(external_id, name, password, username) VALUES ('17478eac-dbb0-4498-8036-f87e0ceb0cae', 'manos', '$2a$10$1U9wpf8D5JrSkjie/CXrWeDppcXnR1YXQym3cFUqQRVBUXHkE87cy', 'manos');
INSERT INTO public.user(external_id, name, password, username) VALUES ('5d2ed7f6-c307-11ed-afa1-0242ac120002', 'vassiliki', '$2a$10$1U9wpf8D5JrSkjie/CXrWeDppcXnR1YXQym3cFUqQRVBUXHkE87cy', 'vassiliki');
INSERT INTO public.user(external_id, name, password, username) VALUES ('5d2ede04-c307-11ed-afa1-0242ac120002', 'nikos', '$2a$10$1U9wpf8D5JrSkjie/CXrWeDppcXnR1YXQym3cFUqQRVBUXHkE87cy', 'nikos');
INSERT INTO public.user(external_id, name, password, username) VALUES ('5d2edf44-c307-11ed-afa1-0242ac120002', 'michalis', '$2a$10$1U9wpf8D5JrSkjie/CXrWeDppcXnR1YXQym3cFUqQRVBUXHkE87cy', 'michalis');
INSERT INTO public.user(external_id, name, password, username) VALUES ('5d2ee052-c307-11ed-afa1-0242ac120002', 'stavroula', '$2a$10$1U9wpf8D5JrSkjie/CXrWeDppcXnR1YXQym3cFUqQRVBUXHkE87cy', 'stavroula');

INSERT INTO public.movie(description, external_id, publication_date, title, user_id) VALUES ('A middle-aged Chinese immigrant is swept up into an insane adventure in which she alone can save existence by exploring other universes and connecting with the lives she could have led.', '5d2ee156-c307-11ed-afa1-0242ac120002', '2023-03-12 15:48:13.078853', 'Everything Everywhere All at Once', '1');
INSERT INTO public.movie(description, external_id, publication_date, title, user_id) VALUES ('The Turtle brothers as they work to earn the love of New York City while facing down an army of mutants.', '0de85cf2-c308-11ed-afa1-0242ac120002', '2023-02-12 15:48:13.078853', 'Teenage Mutant Ninja Turtles: Mutant Mayhem', '2');
INSERT INTO public.movie(description, external_id, publication_date, title, user_id) VALUES ('Scott Lang and Hope Van Dyne, along with Hank Pym and Janet Van Dyne, explore the Quantum Realm, where they interact with strange creatures and embark on an adventure that goes beyond the limits of what they thought was possible.', '0de85fb8-c308-11ed-afa1-0242ac120002', '2023-02-15 15:48:13.078853', 'Ant-Man and the Wasp: Quantumania', '3');
INSERT INTO public.movie(description, external_id, publication_date, title, user_id) VALUES ('Jake Sully lives with his newfound family formed on the extrasolar moon Pandora. Once a familiar threat returns to finish what was previously started, Jake must work with Neytiri and the army of the Na''vi race to protect their home.', '0de864cc-c308-11ed-afa1-0242ac120002', '2023-01-22 15:48:13.078853', 'Avatar: The Way of Water', '3');
INSERT INTO public.movie(description, external_id, publication_date, title, user_id) VALUES ('The story of The Super Mario Bros. on their journey through the Mushroom Kingdom', '0de866ac-c308-11ed-afa1-0242ac120002', '2023-02-10 15:48:13.078853', 'The Super Mario Bros. Movie', '4');
INSERT INTO public.movie(description, external_id, publication_date, title, user_id) VALUES ('A robotics engineer at a toy company builds a life-like doll that begins to take on a life of its own.', '0de8683c-c308-11ed-afa1-0242ac120002', '2023-02-10 11:48:13.078853', 'M3GAN', '1');
INSERT INTO public.movie(description, external_id, publication_date, title, user_id) VALUES ('The people of Wakanda fight to protect their home from intervening world powers as they mourn the death of King T''Challa.', '0de8695e-c308-11ed-afa1-0242ac120002', current_timestamp, 'Black Panther: Wakanda Forever', '5');

INSERT INTO public.movie_likes(movie_id, user_id) VALUES (1, 2);
INSERT INTO public.movie_likes(movie_id, user_id) VALUES (1, 3);
INSERT INTO public.movie_likes(movie_id, user_id) VALUES (1, 4);
INSERT INTO public.movie_likes(movie_id, user_id) VALUES (3, 5);

INSERT INTO public.movie_hates(movie_id, user_id) VALUES (5, 1);
INSERT INTO public.movie_hates(movie_id, user_id) VALUES (5, 2);
INSERT INTO public.movie_hates(movie_id, user_id) VALUES (5, 3);
INSERT INTO public.movie_hates(movie_id, user_id) VALUES (5, 5);
INSERT INTO public.movie_hates(movie_id, user_id) VALUES (2, 4);