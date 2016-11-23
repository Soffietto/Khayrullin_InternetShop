CREATE TABLE products
(
    id INTEGER PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    cost INTEGER
);
CREATE TABLE users
(
    id INTEGER DEFAULT nextval('users_id_seq'::regclass) NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) PRIMARY KEY NOT NULL,
    password VARCHAR(50) NOT NULL,
    phonenumber VARCHAR(50) NOT NULL,
    addres VARCHAR(50)
);
CREATE UNIQUE INDEX users_id_uindex ON users (id);
CREATE UNIQUE INDEX users_email_uindex ON users (email);
CREATE UNIQUE INDEX "users_phoneNumber_uindex" ON users (phonenumber);


INSERT INTO public.products (id, name, description, cost) VALUES (20, 'Jackal', 'The object of the game is to load more gold onto your ship than any other player.', 22);
INSERT INTO public.products (id, name, description, cost) VALUES (21, 'Twister', 'A person is eliminated when they fall or when their elbow or knee touches the mat.', 34);
INSERT INTO public.products (id, name, description, cost) VALUES (22, 'Jenga', 'During the game, players take turns removing one block at a time from a tower constructed of 54 blocks.', 11);
INSERT INTO public.products (id, name, description, cost) VALUES (24, 'UNO', 'The aim of the game is to be the first player to score 500 points. This is achieved (usually over several rounds of play) by a player discarding all of their cards and earning points corresponding to the value of the remaining cards still held by the other players.', 10);

INSERT INTO public.users (id, name, email, password, phonenumber, addres) VALUES (5, 'Ilyasov Damir', 'damir@gmail.com', 'damir3', '1111', 'asfg');
INSERT INTO public.users (id, name, email, password, phonenumber, addres) VALUES (0, 'Azat Khayrullin', 'admin', 'admin', '007', 'Hell!');
INSERT INTO public.users (id, name, email, password, phonenumber, addres) VALUES (6, 'Lukyanov Stepan', 'stepan@gmail.com', 'stepan3', '12315', 'sadsaf');
INSERT INTO public.users (id, name, email, password, phonenumber, addres) VALUES (7, 'Mirhusainov Ruslan', 'mirhusainow@gmail.com', '123123', '+7(900)043-28-96', 'Kazan, Bolotnikova street, 33 - 21');
