INSERT INTO candidates(id, first_name, middle_name, last_name, notice_period, salary_expectation, tags, type,
                       years_of_experience, country_id, main_technology_id)

VALUES (1, 'Denis', 'Kalenga', 'Watshipamba', 1, 2000, 'dev, java', 'DEVELOPER', 10, 50, 1),
       (2, 'Clark', 'Wa Mutela', 'Elie', 1, 3000, 'dev, java', 'ARCHITECT', 3, 50, 1),
       (3, 'James', 'Lungenyi', 'FLer', 1, 1000, 'dev, java', 'ARCHITECT', 10, 50, 1),
       (4, 'Carol', 'Kulumba', 'Greg', 1, 1000, 'dev, java', 'ARCHITECT', 10, 50, 1),
       (5, 'Lydie', 'Kavria', 'Kety', 3, 4000, 'dev, java', 'DEVELOPER', 10, 50, 1),
       (6, 'Linda', 'Kahambu', 'Lili', 3, 6000, 'dev, java', 'DEVELOPER', 4, 50, 1),
       (7, 'Christelle', 'Mbakanyaki', 'Lili', 3, 6000, 'dev, java', 'DEVELOPER', 4, 50, 1),
       (8, 'Jordan', 'Katembo', 'Maajabu', 3, 8000, 'dev, java', 'DEVELOPER', 4, 50, 1),
       (9, 'Destin', 'Karen', 'Blair', 15, 8000, 'dev, java', 'DEVELOPER', 4, 50, 1),
       (10, 'Gloire', 'Mulla', 'Glodi', 2, 2000, 'dev, java', 'DEVELOPER', 4, 49, 1);

INSERT INTO candidate_language (candidate_id, languages)
VALUES (1, 'FRENCH'),
       (1, 'ENGLISH'),
       (1, 'SPANISH'),
       (2, 'FRENCH');

INSERT INTO candidate_technology(candidate_id, technology_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1);