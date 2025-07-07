-- AUTORI
INSERT INTO author (id, name, surname, nationality, born_year, death_year, image_file_name, description) VALUES (1, 'George', 'Orwell', 'British', '1903-06-25', '1950-01-21', 'author1.jpg', 'George Orwell was a British writer and journalist, renowned for his dystopian novels such as "1984" and "Animal Farm". His works reflect themes of totalitarianism, surveillance, and social injustice, and have had a lasting impact on political thought and literature.');
INSERT INTO author (id, name, surname, nationality, born_year, death_year, image_file_name, description) VALUES (2, 'Harper', 'Lee', 'American', '1926-04-28', '2016-02-19', 'author2.jpg', 'Harper Lee was an American novelist best known for her only published novel during her lifetime, "To Kill a Mockingbird", a powerful critique of racial injustice and moral growth in the American South.');
INSERT INTO author (id, name, surname, nationality, born_year, death_year, image_file_name, description) VALUES (3, 'J.K.', 'Rowling', 'British', '1965-07-31', null, 'author3.jpg', 'J.K. Rowling is a British author who gained worldwide fame for creating the Harry Potter series. Her books, filled with magic, friendship, and resilience, have become a cornerstone of modern fantasy literature.');
INSERT INTO author (id, name, surname, nationality, born_year, death_year, image_file_name, description) VALUES (4, 'F. Scott', 'Fitzgerald', 'American', '1896-09-24', '1940-12-21', 'author4.jpg', 'F. Scott Fitzgerald was an American writer and a leading figure of the Jazz Age. He is best known for "The Great Gatsby", which explores themes of wealth, love, and the American Dream in 1920s America.');
INSERT INTO author (id, name, surname, nationality, born_year, death_year, image_file_name, description) VALUES (5, 'Jane', 'Austen', 'British', '1775-12-16', '1817-07-18', 'author5.jpg', 'Jane Austen was an English novelist who depicted the lives and loves of the British gentry with wit and realism. Her most famous works include "Pride and Prejudice", "Sense and Sensibility", and "Emma".');
INSERT INTO author (id, name, surname, nationality, born_year, death_year, image_file_name, description) VALUES (6, 'Mark', 'Twain', 'American', '1835-11-30', '1910-04-21', 'author6.jpg', 'Mark Twain, born Samuel Clemens, was a seminal figure in American literature. Known for "The Adventures of Tom Sawyer" and "Huckleberry Finn", his writing blends humor with sharp social commentary.');
INSERT INTO author (id, name, surname, nationality, born_year, death_year, image_file_name, description) VALUES (7, 'Ernest', 'Hemingway', 'American', '1899-07-21', '1961-07-02', 'author7.jpg', 'Ernest Hemingway was an American novelist and Nobel laureate, famous for his terse prose style and works such as "The Old Man and the Sea", "A Farewell to Arms", and "For Whom the Bell Tolls".');
INSERT INTO author (id, name, surname, nationality, born_year, death_year, image_file_name, description) VALUES (8, 'Leo', 'Tolstoy', 'Russian', '1828-09-09', '1910-11-20', 'author8.jpg', 'Leo Tolstoy was a Russian author of epic novels and profound philosophical essays. His masterpieces "War and Peace" and "Anna Karenina" explore themes of history, love, and moral struggle.');
INSERT INTO author (id, name, surname, nationality, born_year, death_year, image_file_name, description) VALUES (9, 'Charles', 'Dickens', 'British', '1812-02-07', '1870-06-09', 'author9.jpg', 'Charles Dickens was one of the most influential English novelists of the 19th century. He is known for "Oliver Twist", "David Copperfield", and "A Christmas Carol", often highlighting social injustice.');
INSERT INTO author (id, name, surname, nationality, born_year, death_year, image_file_name, description) VALUES (10, 'Gabriel', 'García Márquez', 'Colombian', '1927-03-06', '2014-04-17', 'author10.jpg', 'Gabriel García Márquez was a Colombian author and Nobel Prize winner who pioneered magical realism. His novel "One Hundred Years of Solitude" remains one of the most important literary works of the 20th century.');
INSERT INTO author (id, name, surname, nationality, born_year, death_year, image_file_name, description) VALUES (11, 'J.R.R.', 'Tolkien', 'British', '1892-01-03', '1973-09-02', 'author11.jpg', 'J.R.R. Tolkien was a philologist and fantasy author whose novels "The Hobbit" and "The Lord of the Rings" reshaped modern fantasy. He also created an extensive mythology and language system for Middle-earth.');
INSERT INTO author (id, name, surname, nationality, born_year, death_year, image_file_name, description) VALUES (12, 'Mary', 'Shelley', 'British', '1797-08-30', '1851-02-01', 'author12.jpg', 'Mary Shelley was a British novelist and the author of "Frankenstein", a foundational work of science fiction that explores themes of creation, humanity, and isolation.');
INSERT INTO author (id, name, surname, nationality, born_year, death_year, image_file_name, description) VALUES (13, 'Herman', 'Melville', 'American', '1819-08-01', '1891-09-28', 'author13.jpg', 'Herman Melville was an American author, best known for "Moby-Dick", a profound allegorical novel that explores obsession, nature, and the human condition.');
INSERT INTO author (id, name, surname, nationality, born_year, death_year, image_file_name, description) VALUES (14, 'Aldous', 'Huxley', 'British', '1894-07-26', '1963-11-22', 'author14.jpg', 'Aldous Huxley was an English writer and philosopher, known for his dystopian novel "Brave New World", which critiques consumerism and the loss of individuality in a technologically advanced society.');
INSERT INTO author (id, name, surname, nationality, born_year, death_year, image_file_name, description) VALUES (15, 'Victor', 'Hugo', 'French', '1802-02-26', '1885-05-22', 'author15.jpg', 'Victor Hugo was a French poet, novelist, and dramatist, best known for "Les Misérables" and "The Hunchback of Notre-Dame", works that explore themes of justice, love, and redemption.');
INSERT INTO author (id, name, surname, nationality, born_year, death_year, image_file_name, description) VALUES (16, 'Stuart', 'Russell', 'British', '1962-01-01', null, 'author16.jpg', 'Stuart Russell is a British computer scientist and co-author of the leading AI textbook. His work explores the implications of artificial intelligence on society, emphasizing ethics and control.');
INSERT INTO author (id, name, surname, nationality, born_year, death_year, image_file_name, description) VALUES (17, 'Peter', 'Norvig', 'American', '1956-01-01', null, 'author17.jpg', 'Peter Norvig is an American computer scientist, educator, and co-author of the widely used AI textbook. He has made significant contributions to artificial intelligence and software engineering.');

-- LIBRI
INSERT INTO book (id, title, description, year, cover_image_file_name) VALUES (1, '1984', 'In a dark future where every aspect of life is controlled by the Party and its omnipresent leader Big Brother, Winston Smith dares to question the truth. Living in a world where thoughts are monitored, history is rewritten, and individuality is crushed, Winston begins a dangerous quest for freedom, love, and rebellion — one that might cost him everything.', 1949, 'book1.jpg');
INSERT INTO book (id, title, description, year, cover_image_file_name) VALUES (2, 'To Kill a Mockingbird', 'Set in the racially segregated American South during the 1930s, this moving novel follows young Scout Finch as her father, a principled lawyer, defends a Black man wrongly accused of a grave crime. Through the eyes of a child, Harper Lee delivers a profound commentary on justice, compassion, and the human spirit in the face of prejudice.', 1960, 'book2.jpg');
INSERT INTO book (id, title, description, year, cover_image_file_name) VALUES (3, 'Harry Potter and the Philosophers Stone', 'When Harry Potter discovers he’s a wizard on his eleventh birthday, his life changes forever. Whisked away to the magical Hogwarts School, he finds friendship, wonder, and dark secrets hidden in its halls. As he uncovers the truth about his past, Harry must face a dangerous enemy whose power once left him orphaned — and who may not be gone for good.', 1997, 'book3.jpg');
INSERT INTO book (id, title, description, year, cover_image_file_name) VALUES (4, 'The Great Gatsby', 'Jay Gatsby is a mysterious millionaire known for his extravagant parties and tragic obsession with a woman from his past. Set against the glittering backdrop of 1920s New York, this novel explores the illusion of the American Dream and the disillusionment that often lies beneath wealth and glamour. Through the eyes of narrator Nick Carraway, Gatsby’s tragic story unfolds.', 1925, 'book4.jpg');
INSERT INTO book (id, title, description, year, cover_image_file_name) VALUES (5, 'Pride and Prejudice', 'Elizabeth Bennet is sharp, witty, and unafraid to speak her mind — especially when it comes to the proud and aloof Mr. Darcy. In this enduring classic of manners and romance, misunderstandings and social expectations clash as two strong-willed individuals learn to see beyond their prejudices, discovering love and respect in the most unlikely of places.', 1813, 'book5.jpg');
INSERT INTO book (id, title, description, year, cover_image_file_name) VALUES (6, 'Adventures of Huckleberry Finn', 'Tired of the constraints of civilization, young Huck Finn fakes his death and escapes down the Mississippi River. Joined by Jim, a runaway slave, Huck’s journey becomes one of adventure, danger, and self-discovery. Mark Twain’s masterpiece blends humor and grit to explore themes of freedom, morality, and friendship in pre-Civil War America.', 1884, 'book6.jpg');
INSERT INTO book (id, title, description, year, cover_image_file_name) VALUES (7, 'The Old Man and the Sea', 'Santiago is an old fisherman who hasn’t caught a fish in weeks. Determined to break his unlucky streak, he sets out into the Gulf Stream and hooks a massive marlin — beginning a battle of endurance, will, and respect between man and nature. Ernest Hemingway’s powerful tale is a poetic meditation on struggle, pride, and the meaning of success.', 1952, 'book7.jpg');
INSERT INTO book (id, title, description, year, cover_image_file_name) VALUES (8, 'War and Peace', 'Amidst the chaos of Napoleon’s invasion of Russia, the lives of aristocratic families intertwine in a sweeping saga of love, ambition, loss, and redemption. With unforgettable characters like Prince Andrei and Natasha, Tolstoy explores not only the tides of history but also the quiet, personal transformations that define what it means to be human.', 1869, 'book8.jpg');
INSERT INTO book (id, title, description, year, cover_image_file_name) VALUES (9, 'Great Expectations', 'Pip, a poor orphan, receives unexpected wealth from an anonymous benefactor and enters London high society. But success brings new challenges as he wrestles with identity, loyalty, and unfulfilled love. Charles Dickens crafts a timeless coming-of-age tale about the illusions of grandeur and the enduring strength of kindness and character.', 1861, 'book9.jpg');
INSERT INTO book (id, title, description, year, cover_image_file_name) VALUES (10, 'One Hundred Years of Solitude', 'The Buendía family’s history unfolds in the mythical town of Macondo, a place suspended between dream and reality. Across generations, magical events, political upheavals, and personal tragedies shape their fate. Gabriel García Márquez weaves a lush, poetic tapestry of life, death, love, and solitude in this cornerstone of magical realism.', 1967, 'book10.jpg');
INSERT INTO book (id, title, description, year, cover_image_file_name) VALUES (11, 'The Hobbit', 'Bilbo Baggins enjoys a quiet life in the Shire — until the wizard Gandalf recruits him for an epic quest to reclaim a lost dwarven kingdom from the dragon Smaug. Along the way, Bilbo encounters trolls, goblins, and a strange creature named Gollum. Tolkien’s beloved prelude to The Lord of the Rings is a tale of bravery, wit, and unexpected heroism.', 1937, 'book11.jpg');
INSERT INTO book (id, title, description, year, cover_image_file_name) VALUES (12, 'Frankenstein', 'Victor Frankenstein’s ambition to unlock the secrets of life results in the creation of a being he cannot control. As the creature seeks acceptance and revenge, a tragic chain of events unfolds. Mary Shelley’s haunting novel questions the boundaries of science and humanity, blending gothic horror with profound philosophical inquiry.', 1818, 'book12.jpg');
INSERT INTO book (id, title, description, year, cover_image_file_name) VALUES (13, 'Moby Dick', 'Captain Ahab is consumed by a single purpose: hunting the great white whale that maimed him. As his ship Pequod sails across the oceans, the crew faces existential dread and elemental fury. Melville’s epic is both a thrilling sea adventure and a deep exploration of obsession, revenge, and man’s place in the universe.', 1851, 'book13.jpg');
INSERT INTO book (id, title, description, year, cover_image_file_name) VALUES (14, 'Brave New World', 'In a future where happiness is engineered and individuality suppressed, Bernard Marx begins to question the values of the World State. When he introduces a “savage” outsider into society, the cracks in their utopia widen. Aldous Huxley’s prophetic vision remains a powerful warning against technological control and the loss of human dignity.', 1932, 'book14.jpg');
INSERT INTO book (id, title, description, year, cover_image_file_name) VALUES (15, 'Les Misérables', 'After years in prison for stealing a loaf of bread, Jean Valjean reinvents himself as a force for good — but is relentlessly pursued by the rigid inspector Javert. Victor Hugo’s sweeping novel weaves together the struggles of revolution, poverty, love, and redemption in a France on the edge of transformation.', 1862, 'book15.jpg');
INSERT INTO book (id, title, description, year, cover_image_file_name) VALUES (16, 'Why I Write', 'In this deeply personal collection of essays, George Orwell reflects on his journey as a writer, the political nature of literature, and the moral duties of the written word. Both philosophical and practical, it reveals the convictions and conflicts that shaped the voice behind some of the 20th century’s most enduring works.', 1946, 'book16.jpg');
INSERT INTO book (id, title, description, year, cover_image_file_name) VALUES (17, 'Artificial Intelligence: A Modern Approach', 'This comprehensive textbook offers a foundational and forward-looking perspective on artificial intelligence. Covering a wide array of topics—from basic search algorithms and knowledge representation to machine learning and robotics—it serves as the definitive guide for students, researchers, and practitioners seeking a deep understanding of the field.', 2010, 'book17.jpg');

-- RELAZIONI libro-autore
INSERT INTO book_authors (authors_id, books_id) VALUES (1, 1);
INSERT INTO book_authors (authors_id, books_id) VALUES (2, 2);
INSERT INTO book_authors (authors_id, books_id) VALUES (3, 3);
INSERT INTO book_authors (authors_id, books_id) VALUES (4, 4);
INSERT INTO book_authors (authors_id, books_id) VALUES (5, 5);
INSERT INTO book_authors (authors_id, books_id) VALUES (6, 6);
INSERT INTO book_authors (authors_id, books_id) VALUES (7, 7);
INSERT INTO book_authors (authors_id, books_id) VALUES (8, 8);
INSERT INTO book_authors (authors_id, books_id) VALUES (9, 9);
INSERT INTO book_authors (authors_id, books_id) VALUES (10, 10);
INSERT INTO book_authors (authors_id, books_id) VALUES (11, 11);
INSERT INTO book_authors (authors_id, books_id) VALUES (12, 12);
INSERT INTO book_authors (authors_id, books_id) VALUES (13, 13);
INSERT INTO book_authors (authors_id, books_id) VALUES (14, 14);
INSERT INTO book_authors (authors_id, books_id) VALUES (15, 15);
INSERT INTO book_authors (authors_id, books_id) VALUES (1, 16);
INSERT INTO book_authors (authors_id, books_id) VALUES (16, 17);
INSERT INTO book_authors (authors_id, books_id) VALUES (17, 17);


-- Immagini extra per il libro "1984" (id=1)
INSERT INTO book_image_file_names (book_id, image_file_names) VALUES (1, 'book1_extra1.jpg');
INSERT INTO book_image_file_names (book_id, image_file_names) VALUES (1, 'book1_extra2.jpg');

-- Immagini extra per "To Kill a Mockingbird" (id=2)
INSERT INTO book_image_file_names (book_id, image_file_names) VALUES (2, 'book2_extra1.jpg');

-- Immagini extra per "Harry Potter" (id=3)
INSERT INTO book_image_file_names (book_id, image_file_names) VALUES (3, 'book3_extra1.jpg');
INSERT INTO book_image_file_names (book_id, image_file_names) VALUES (3, 'book3_extra2.jpg');
INSERT INTO book_image_file_names (book_id, image_file_names) VALUES (3, 'book3_extra3.jpg');


-- UTENTE 
INSERT INTO users (id, name, surname, email, username) VALUES (1, 'Fla', 'Minia', 'mimi@gmail.com', 'Mimi');
INSERT INTO users (id, name, surname, email, username) VALUES (2, 'Jhon', 'Herrera', 'jhon.herrera@gmail.com', 'Jem');
INSERT INTO users (id, name, surname, email, username) VALUES (3, 'Luca', 'Busso', 'luca.bu@gmail.com', 'Kuca');
INSERT INTO users (id, name, surname, email, username) VALUES (4, 'Vale', 'Cini', 'vale.ci@gmail.com', 'Vale');
INSERT INTO users (id, name, surname, email, username) VALUES (5, 'Francesco', 'Totti', 'ErCap@gmail.com', 'Er Capitano');
INSERT INTO users (id, name, surname, email, username) VALUES (6, 'Daniele', 'De Rossi', 'Dani@gmail.com', 'DDR');

-- CREDENZIALI 
INSERT INTO credentials (id, user_id, password, role, username )VALUES (1, 1, '$2a$10$NyLD44CrvwpA/T.snwaoc.eyoMV5yhk0lYtCcEMmTgPOzvdvVXUQK', 'ADMIN', 'Mimi');
INSERT INTO credentials (id, user_id, password, role, username )VALUES (2, 2, '$2a$10$MhGj/IjAUWipHodqJA2V8O9WebwNMrth1vMyuht8g/K/IG2PsfRrG', 'DEFAULT', 'Jem');
INSERT INTO credentials (id, user_id, password, role, username )VALUES (3, 3, '$2a$10$MhGj/IjAUWipHodqJA2V8O9WebwNMrth1vMyuht8g/K/IG2PsfRrG', 'DEFAULT', 'Kuca');
INSERT INTO credentials (id, user_id, password, role, username )VALUES (4, 4, '$2a$10$MhGj/IjAUWipHodqJA2V8O9WebwNMrth1vMyuht8g/K/IG2PsfRrG', 'DEFAULT', 'Vale');
INSERT INTO credentials (id, user_id, password, role, username )VALUES (5, 5, '$2a$10$MhGj/IjAUWipHodqJA2V8O9WebwNMrth1vMyuht8g/K/IG2PsfRrG', 'DEFAULT', 'Er Capitano');
INSERT INTO credentials (id, user_id, password, role, username )VALUES (6, 6, '$2a$10$MhGj/IjAUWipHodqJA2V8O9WebwNMrth1vMyuht8g/K/IG2PsfRrG', 'DEFAULT', 'DDR');

-- REVIEWS 1–17 with dates
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (1, 'Dystopian masterwork', 4, '1984 is a thought‑provoking novel about society and freedom. An absolute must‑read.', 1, 2, '2024-05-10');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (2, 'Moving and powerful', 4, 'To Kill a Mockingbird touched my heart deeply. A story of justice and humanity.', 2, 3, '2024-06-12');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (3, 'A magical world', 4, 'Harry Potter is a wonderful journey into fantasy. Perfect for all ages.', 3, 4, '2025-01-15');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (4, 'Elegant prose', 4, 'The Great Gatsby is beautifully written, though it demands careful reading to fully appreciate.', 4, 5, '2024-07-08');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (5, 'Witty and romantic', 4, 'Pride and Prejudice is light yet smart. Elizabeth is an amazing character.', 5, 6, '2025-03-22');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (6, 'Freedom reflection', 4, 'Huck Finn tackles important themes with adventure and irony. A must‑read.', 6, 2, '2024-08-04');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (7, 'Intense and poetic', 4, 'A short but deep story. Hemingway is always so succinct.', 7, 3, '2025-04-16');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (8, 'Epic and profound', 4, 'War and Peace is a literary odyssey. It takes time, but it’s worth every page.', 8, 4, '2024-11-29');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (9, 'Coming‑of‑age and disenchantment', 4, 'Great Expectations tells the growth of a boy with great sensitivity.', 9, 5, '2024-10-17');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (10, 'Hypnotic and original', 4, 'A masterwork of magical realism. A unique experience.', 10, 6, '2025-06-01');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (11, 'Classic fantasy adventure', 4, 'The Hobbit is smooth and fun to read. Pure adventure!', 11, 2, '2024-09-23');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (12, 'Haunting and timely', 4, 'Frankenstein is a powerful novel that explores ethics and identity.', 12, 3, '2025-02-14');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (13, 'Deep and symbolic', 4, 'Moby Dick is dense and rich in meaning. Not easy, but fascinating.', 13, 4, '2024-12-05');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (14, 'Disturbing dystopia', 4, 'Brave New World anticipates many modern issues. Disturbing and thought‑provoking.', 14, 5, '2025-05-06');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (15, 'Extraordinary', 4, 'Les Misérables is a monumental work about pain and hope.', 15, 6, '2024-07-30');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (16, 'Intimate and reflective', 4, 'Why I Write offers an honest, personal insight into writing. Recommended.', 16, 2, '2024-06-05');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (17, 'Essential technical text', 4, 'Perfect for anyone wanting to understand artificial intelligence. Comprehensive and well explained.', 17, 3, '2025-01-25');

-- REVIEWS 
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (18, 'Eye-opening narrative', 4, '1984 paints a terrifying vision of a future with no freedom. Still incredibly relevant today.', 1, 4, '2025-01-10');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (19, 'Timeless lesson', 4, 'To Kill a Mockingbird teaches empathy and justice through the innocent eyes of a child.', 2, 5, '2024-11-05');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (20, 'Charming and magical', 4, 'Harry Potter is a magical ride filled with wonder, friendship, and suspense.', 3, 6, '2025-02-17');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (21, 'Dreams and illusions', 3, 'Gatsby’s story is a sad reminder of how the American Dream can become a nightmare.', 4, 2, '2024-09-21');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (22, 'Classic romance', 4, 'Jane Austen’s wit and insight into social dynamics make this a delightful read.', 5, 3, '2025-03-03');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (23, 'Adventurous and bold', 4, 'Huck Finn’s journey is both entertaining and a deep social commentary.', 6, 4, '2024-08-19');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (24, 'Simplicity and strength', 4, 'The Old Man and the Sea is short but powerful. It leaves a lasting impression.', 7, 5, '2025-05-12');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (25, 'Historical masterpiece', 4, 'War and Peace blends history with deeply human stories. A literary triumph.', 8, 6, '2025-04-01');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (26, 'Emotionally rich', 3, 'Great Expectations has unforgettable characters and moral depth.', 9, 2, '2024-12-16');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (27, 'Magical family saga', 4, 'One Hundred Years of Solitude is surreal, poetic, and deeply moving.', 10, 3, '2025-06-10');

INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (28, 'Too abstract for me', 2, '1984 had a strong message, but I found the style dry and emotionally distant.', 1, 5, '2024-10-12');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (29, 'Overrated', 3, 'To Kill a Mockingbird is important, but it didn’t engage me as much as I hoped.', 2, 6, '2025-03-18');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (30, 'Too childish', 2, 'I can see the appeal for younger readers, but Harry Potter wasn’t for me.', 3, 2, '2025-01-03');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (31, 'Didn’t connect', 3, 'The Great Gatsby was beautifully written but felt distant. I couldn’t connect with the characters.', 4, 3, '2024-09-10');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (32, 'Hard to get through', 2, 'Pride and Prejudice had clever moments but felt slow and outdated.', 5, 4, '2024-11-22');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (33, 'Boring parts', 3, 'Huckleberry Finn had good themes, but I found parts dragging and repetitive.', 6, 5, '2025-04-07');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (34, 'Not engaging', 2, 'The Old Man and the Sea felt repetitive. I struggled to stay interested.', 7, 6, '2024-12-11');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (35, 'Too long', 1, 'War and Peace is simply too long and dense. I gave up halfway.', 8, 2, '2025-02-26');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (36, 'Unrelatable', 3, 'Great Expectations was well written but felt outdated in its themes and characters.', 9, 3, '2024-07-14');
INSERT INTO review (id, title, vote, text, book_id, user_id, date) VALUES (37, 'Confusing at times', 2, 'One Hundred Years of Solitude had beautiful moments, but the story was hard to follow.', 10, 4, '2025-06-05');


SELECT setval('users_seq', (SELECT MAX(id) FROM users));
SELECT setval('credentials_seq', (SELECT MAX(id) FROM credentials));
SELECT setval('book_seq', (SELECT MAX(id) FROM book));
SELECT setval('author_seq', (SELECT MAX(id) FROM author));
SELECT setval('review_seq', (SELECT MAX(id) FROM review));