package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int count = 0;
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                count++;
            }
        }
        if (count > 0) {
            SchoolBook[] schoolBooksTemp = new SchoolBook[count];
            for (int i = 0, j = 0; i < schoolBooks.length; i++) {
                if (schoolBooks[i].getName().equals(name)) {
                    schoolBooksTemp[j] = schoolBooks[i];
                    j++;
                }
            }
            return schoolBooksTemp;
        } else {
            return new SchoolBook[0];
        }
    }

    @Override
    public boolean removeByName(String name) {
        if (findByName(name).length > 0) {
            SchoolBook[] schoolBooksTemp = new SchoolBook[schoolBooks.length - findByName(name).length];
            for (int i = 0, j = 0; i < schoolBooks.length; i++) {
                if (schoolBooks[i].getName().equals(name)) {
                    schoolBooksTemp[j] = schoolBooks[i];
                    j++;
                }
            }
            schoolBooks = schoolBooksTemp;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
