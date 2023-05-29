create table users
(
    id         bigint auto_increment
        primary key,
    admin      bit          not null,
    created_at datetime(6)  null,
    email      varchar(50)  null,
    first_name varchar(20)  null,
    password   varchar(120) null,
    updated_at datetime(6)  null,
    constraint UK6dotkott2kjsp8vw4d0m25fb7
        unique (email)
);

create table subjects
(
    id          bigint auto_increment
        primary key,
    description varchar(500) null,
    title       varchar(255) null,
    constraint UK5wgtfx456x82wwx7yk8182dwu
        unique (title)
);

create table subscriptions
(
    id         bigint auto_increment,
    subject_id bigint not null,
    user_id    bigint not null,
    primary key (id, user_id, subject_id),
    constraint FK8ejb8e2fndtnpalu38hbn7cvv
        foreign key (subject_id) references subjects (id),
    constraint FKhro52ohfqfbay9774bev0qinr
        foreign key (user_id) references users (id)
);

create table posts
(
    id          bigint auto_increment
        primary key,
    created_at  datetime(6)   null,
    description varchar(5000) null,
    title       varchar(200)  null,
    updated_at  datetime(6)   null,
    author_id   bigint        null,
    subject_id  bigint        null,
    constraint FK6xvn0811tkyo3nfjk2xvqx6ns
        foreign key (author_id) references users (id),
    constraint FKk31c8ca9t9q3hp8w73ymubhap
        foreign key (subject_id) references subjects (id)
);

create table messages
(
    id         bigint auto_increment
        primary key,
    created_at datetime(6)  not null,
    message    varchar(500) null,
    author_id  bigint       null,
    post_id    bigint       null,
    constraint FK86vxjyfsjfddqi277p88mjt3f
        foreign key (post_id) references posts (id),
    constraint FKowtlim26svclkatusptbgi7u1
        foreign key (author_id) references users (id)
);

