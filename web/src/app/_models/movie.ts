import { User } from "./user";

export class Movie {
    id?: string;
    title?: string;
    description?: string;
    publisher?: User;
    publicationDate?: Date;
    likes?: number;
    hates?: number;
}