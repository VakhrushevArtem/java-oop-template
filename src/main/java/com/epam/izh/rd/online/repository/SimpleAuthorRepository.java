package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository{
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            authors = Arrays.copyOf(authors, authors.length + 1);
            authors[authors.length - 1] = author;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        Author authorTemp = null;
        for (Author author : authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                authorTemp = author;
            }
        }
        return authorTemp;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            Author[] authorsTemp = new Author[authors.length - 1];
            for (int i = 0, j = 0; i < authors.length; i++) {
                if (!authors[i].equals(author)) {
                    authorsTemp[j] = authors[i];
                    j++;
                }
            }
            authors = authorsTemp;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int count() {
        return authors.length;
    }
}
