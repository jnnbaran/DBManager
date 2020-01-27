create database knowledgebase;
use knowledgebase;

# 1
create table UsersRole
(
    RoleId int(4)       not null
        primary key,
    Role   varchar(100) null
);
INSERT INTO Knowledgebase.UsersRole (RoleId, Role) VALUES (1, 'Admin');
INSERT INTO Knowledgebase.UsersRole (RoleId, Role) VALUES (2, 'Editor');
INSERT INTO Knowledgebase.UsersRole (RoleId, Role) VALUES (3, 'User');

# 2
create table Users
(
    UserId   int(4) auto_increment
        primary key,
    UserName varchar(100) null,
    RoleId   int          not null,
    Password varchar(20)  null,
    constraint Users_ibfk_1
        foreign key (RoleId) references UsersRole (RoleId)
);
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (1, 'Alicja', 3, '123');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (2, 'Bionizy13', 3, '321');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (3, 'Celina', 3, '159');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (4, 'Daniel', 1, '951');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (5, 'Eleonora', 3, '789');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (6, 'Ferdynand', 3, '987');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (7, 'Graszka', 3, '357');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (9, 'Ivona', 1, '456');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (10, 'Jemdrula', 3, '654');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (11, 'Karyna', 3, '147');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (12, 'Lucjan', 3, '741');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (13, 'Margarette', 3, '852');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (911, 'ZenonXenon', 3, '258');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (1111, 'BaranekShaun', 2, '271');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (1313, 'Otton', 2, '369');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (1488, 'ModelSolowa', 2, '314');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (2137, 'Ryuusuke', 2, '963');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (10009, 'Alicjanana', 3, '123');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (10020, 'woww', 2, 'eewwooo');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (10022, 'raz', 3, 'dwa');
INSERT INTO Knowledgebase.Users (UserId, UserName, RoleId, Password) VALUES (10023, 'ktos', 3, 'mkYOn12$');

# 3
create table Category
(
    CategoryId int         not null
        primary key,
    Category   varchar(25) null
);
INSERT INTO Knowledgebase.Category (CategoryId, Category) VALUES (1, 'Food');
INSERT INTO Knowledgebase.Category (CategoryId, Category) VALUES (2, 'Music');
INSERT INTO Knowledgebase.Category (CategoryId, Category) VALUES (3, 'Programming');
INSERT INTO Knowledgebase.Category (CategoryId, Category) VALUES (4, 'Marketing');
INSERT INTO Knowledgebase.Category (CategoryId, Category) VALUES (5, 'History');

# 4
create table Questions
(
    QuestionId int(4) auto_increment
        primary key,
    Date       date          null,
    UserId     int           not null,
    Title      varchar(200)  null,
    Question   varchar(1000) null,
    CategoryId int           not null,
    Accepted   tinyint(1)    not null
);
INSERT INTO Knowledgebase.Questions (QuestionId, Date, UserId, Title, Question, CategoryId, Accepted) VALUES (1, '2019-12-15', 1, 'Unable to ssh into remote Linux by ngrok', 'The remote Linux computer is in an internal network and has no public IP address. So I installed ngrok.

ngrok tcp 22
ngrok by @inconshreveable (Ctrl+C to quit) Tunnel Status online
Version 2.0.19/2.0.17
Web Interface http://127.0.0.1:4040
Forwarding tcp://0.tcp.ngrok.io:36428 -> localhost:22
Connections ttl opn rt1 rt5 p50 p90
0 0 0.00 0.00 0.00 0.00

I checked that sshd is running.

At the local PC, I tried

ssh myuser@ngrok.com -p36428
which gave rise to

ssh: connect to host ngrok.com port 36428: Connection refused', 3, 1);
INSERT INTO Knowledgebase.Questions (QuestionId, Date, UserId, Title, Question, CategoryId, Accepted) VALUES (2, '2019-12-15', 1, 'Fargate for analytics service?
', 'I am looking at hosting Matomo analytics on Fargate + RDS. I have never used Fargate before. This would be for very low-traffic websites (0 to 10 clicks per day). I estimate the smallest MySQL RDS will be about $10/month, and if I went with EC2 over Fargate, that would be another $10/month. I am planning to deploy the Matomo Docker image, so Fargate also looks easier to configure in that regard.



Is Fargate the right choice? Will it bootup fast enough? Could I end up spending more than EC2?', 3, 1);
INSERT INTO Knowledgebase.Questions (QuestionId, Date, UserId, Title, Question, CategoryId, Accepted) VALUES (3, '2019-12-15', 1, 'Can someone tell me what propType does?
', 'https://github.com/mui-org/material-ui/blob/master/packages/material-ui/src/AppBar/AppBar.js

It seems that it''s used to tell the developers what you can pass as props, but wouldn''t it be better to just put comments in the code since it''s going to affect performances in a negative way? Can someone tell me what are the pros and cons of doing this?', 3, 0);
INSERT INTO Knowledgebase.Questions (QuestionId, Date, UserId, Title, Question, CategoryId, Accepted) VALUES (4, '2019-12-10', 2, 'What is the difference between Domino''s Pizza and Pizza Hut?', 'I’m coming into this thing completely neutral—the Switzerland of entirely manufactured pizza-chain pissing contests. The only thing I care about when ordering cheap delivery pizza is whose coupons were most recently in my mailbox. There is nothing to cloud my judgment.

', 1, 1);
INSERT INTO Knowledgebase.Questions (QuestionId, Date, UserId, Title, Question, CategoryId, Accepted) VALUES (5, '2019-12-10', 2, 'Ideal Customer', ' Who is our ideal customer??', 4, 0);
INSERT INTO Knowledgebase.Questions (QuestionId, Date, UserId, Title, Question, CategoryId, Accepted) VALUES (6, '2019-12-01', 4, 'Effectiveness of content marketing', 'How do we measure the effectiveness of content marketing?', 4, 1);
INSERT INTO Knowledgebase.Questions (QuestionId, Date, UserId, Title, Question, CategoryId, Accepted) VALUES (7, '2019-12-15', 2, 'gdf', 'fdhfdh', 2, 1);
INSERT INTO Knowledgebase.Questions (QuestionId, Date, UserId, Title, Question, CategoryId, Accepted) VALUES (8, '2019-12-16', 2, 'fdh', 'dsghfh', 2, 1);
INSERT INTO Knowledgebase.Questions (QuestionId, Date, UserId, Title, Question, CategoryId, Accepted) VALUES (9, '2019-12-16', 2, 'fdhf', 'fdshjdfj', 2, 0);
INSERT INTO Knowledgebase.Questions (QuestionId, Date, UserId, Title, Question, CategoryId, Accepted) VALUES (11, '2019-12-16', 2, 'nanana', 'lalalaa', 2, 0);
INSERT INTO Knowledgebase.Questions (QuestionId, Date, UserId, Title, Question, CategoryId, Accepted) VALUES (12, '2019-12-16', 2, 'kjhgfd', 'nknknk', 2, 1);
INSERT INTO Knowledgebase.Questions (QuestionId, Date, UserId, Title, Question, CategoryId, Accepted) VALUES (13, '2019-12-16', 2, 'dfgh', 'nanan', 2, 0);

#5
create table Answers
(
    AnswerId   int auto_increment
        primary key,
    Date       date         null,
    Answer     varchar(500) null,
    QuestionID int          null,
    UserId     int          not null,
    constraint Answers_ibfk_1
        foreign key (UserId) references Users (UserId),
    constraint Answers_ibfk_2
        foreign key (QuestionID) references Questions (QuestionId)
);
INSERT INTO Knowledgebase.Answers (AnswerId, Date, Answer, QuestionID, UserId) VALUES (1, '2020-01-05', 'You are connecting to the wrong destination address. The command should be
ssh myuser@0.tcp.ngrok.io -p36428
Notice the different hostname (ie 0.tcp.ngrok.io instead of ngrok.com).
And generally you would want to put the user@hostname after all the options (eg -p36428), even though it doesn''t generally cause any issues.', 1, 3);
INSERT INTO Knowledgebase.Answers (AnswerId, Date, Answer, QuestionID, UserId) VALUES (2, '2020-01-05', 'LALAL LALALL IT IS WHAT IT IS', 1, 4);
INSERT INTO Knowledgebase.Answers (AnswerId, Date, Answer, QuestionID, UserId) VALUES (3, '2020-01-17', 'Fargate is the most expensive of the container options you have on AWS. Also its not like on Google - the container will stay up 100% of the time.

If I understand the Matomo service correctly then AWS Lightsail would be a much better option for you', 2, 1);
INSERT INTO Knowledgebase.Answers (AnswerId, Date, Answer, QuestionID, UserId) VALUES (4, '2020-01-20', 'PropTypes are a primitive way of adding types to the props of your components. The real benefit is at dev time and can be removed by Babel.', 2, 1);
INSERT INTO Knowledgebase.Answers (AnswerId, Date, Answer, QuestionID, UserId) VALUES (5, '2020-01-20', 'Since JavaScript is a loose type language, prototype help catch errors while in development. Once you assign a type to an element you will get an error if you accidentally change it in your code.', 2, 1);
INSERT INTO Knowledgebase.Answers (AnswerId, Date, Answer, QuestionID, UserId) VALUES (6, '2020-01-20', 'mam', 3, 1);
INSERT INTO Knowledgebase.Answers (AnswerId, Date, Answer, QuestionID, UserId) VALUES (7, '2020-01-20', 'mam', 3, 1);
INSERT INTO Knowledgebase.Answers (AnswerId, Date, Answer, QuestionID, UserId) VALUES (8, '2020-01-20', 'sh', 3, 1);
INSERT INTO Knowledgebase.Answers (AnswerId, Date, Answer, QuestionID, UserId) VALUES (9, '2020-01-20', 'nn', 1, 1);
INSERT INTO Knowledgebase.Answers (AnswerId, Date, Answer, QuestionID, UserId) VALUES (11, '2020-01-20', 'oo', 3, 1);
INSERT INTO Knowledgebase.Answers (AnswerId, Date, Answer, QuestionID, UserId) VALUES (12, '2020-01-27', 'fd', 1, 1);
INSERT INTO Knowledgebase.Answers (AnswerId, Date, Answer, QuestionID, UserId) VALUES (13, '2020-01-27', 'fd', 1, 1);
INSERT INTO Knowledgebase.Answers (AnswerId, Date, Answer, QuestionID, UserId) VALUES (14, '2020-01-27', 'dg', 1, 1);

#6
create table Comments
(
    CommentId int auto_increment
        primary key,
    UserID    int          null,
    AnswerID  int          null,
    Date      date         null,
    Comment   varchar(150) null,
    constraint Comments_ibfk_1
        foreign key (UserID) references Users (UserId),
    constraint Comments_ibfk_2
        foreign key (AnswerID) references Answers (AnswerId)
);
INSERT INTO Knowledgebase.Comments (CommentId, UserID, AnswerID, Date, Comment) VALUES (1, 1, 1, '2020-01-20', 'why');
INSERT INTO Knowledgebase.Comments (CommentId, UserID, AnswerID, Date, Comment) VALUES (2, 2, 1, '2020-01-20', 'oh no');
INSERT INTO Knowledgebase.Comments (CommentId, UserID, AnswerID, Date, Comment) VALUES (3, 3, 2, '2020-01-20', 'rly');
INSERT INTO Knowledgebase.Comments (CommentId, UserID, AnswerID, Date, Comment) VALUES (6, 1, 9, '2020-01-20', 'aa');

#7
create table Rating
(
    RatingId int(4) auto_increment
        primary key,
    UserId   int       not null,
    AnswerId int       not null,
    Rating   binary(1) not null comment '{0,1}',
    constraint Rating_ibfk_1
        foreign key (UserId) references Users (UserId),
    constraint Rating_ibfk_2
        foreign key (AnswerId) references Answers (AnswerId)
);

INSERT INTO Knowledgebase.Rating (RatingId, UserId, AnswerId, Rating) VALUES (1, 2, 1, 0x31);
INSERT INTO Knowledgebase.Rating (RatingId, UserId, AnswerId, Rating) VALUES (2, 5, 1, 0x31);
INSERT INTO Knowledgebase.Rating (RatingId, UserId, AnswerId, Rating) VALUES (3, 6, 1, 0x30);
INSERT INTO Knowledgebase.Rating (RatingId, UserId, AnswerId, Rating) VALUES (4, 1, 1, 0x31);
INSERT INTO Knowledgebase.Rating (RatingId, UserId, AnswerId, Rating) VALUES (5, 1, 4, 0x31);
INSERT INTO Knowledgebase.Rating (RatingId, UserId, AnswerId, Rating) VALUES (6, 1, 2, 0x31);
INSERT INTO Knowledgebase.Rating (RatingId, UserId, AnswerId, Rating) VALUES (7, 1, 5, 0x31);
INSERT INTO Knowledgebase.Rating (RatingId, UserId, AnswerId, Rating) VALUES (8, 1, 6, 0x31);
INSERT INTO Knowledgebase.Rating (RatingId, UserId, AnswerId, Rating) VALUES (9, 1, 7, 0x31);
INSERT INTO Knowledgebase.Rating (RatingId, UserId, AnswerId, Rating) VALUES (10, 1, 8, 0x31);
INSERT INTO Knowledgebase.Rating (RatingId, UserId, AnswerId, Rating) VALUES (11, 4, 5, 0x31);
INSERT INTO Knowledgebase.Rating (RatingId, UserId, AnswerId, Rating) VALUES (12, 1, 9, 0x30);
INSERT INTO Knowledgebase.Rating (RatingId, UserId, AnswerId, Rating) VALUES (13, 12, 1, 0x31);
INSERT INTO Knowledgebase.Rating (RatingId, UserId, AnswerId, Rating) VALUES (14, 12, 2, 0x31);
INSERT INTO Knowledgebase.Rating (RatingId, UserId, AnswerId, Rating) VALUES (15, 12, 9, 0x31);