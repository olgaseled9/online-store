create table review
(
    id          bigint       not null primary key,
    review_body varchar(200) not null,
    created_by  date         not null,
    is_shown    boolean      not null,
    user_id     bigint       not null

);
alter sequence review_id_seq owned by review.id;
