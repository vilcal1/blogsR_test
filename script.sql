BEGIN;
create DATABASE worksoft
DROP TABLE IF EXISTS "WorkSoft"."BLOGS";

CREATE TABLE IF NOT EXISTS "WorkSoft"."BLOGS"
(
    "ID" numeric(8,0) NOT NULL,
    "TITLE" character varying(50) NOT NULL,
    "DESCRIPTION" character varying(4000) NOT NULL,
    CONSTRAINT "BLOGS_PK" PRIMARY KEY ("ID")
)

DROP TABLE IF EXISTS "WorkSoft"."BLOG_READERS";

CREATE TABLE IF NOT EXISTS "WorkSoft"."BLOG_READERS"
(
    "R_ID" numeric(8,0) NOT NULL,
    "B_ID" numeric(8,0) NOT NULL,
    CONSTRAINT "TABLE_3_BLOGS_FK" FOREIGN KEY ("B_ID")
        REFERENCES "WorkSoft"."BLOGS" ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "TABLE_3_READERS_FK" FOREIGN KEY ("R_ID")
        REFERENCES "WorkSoft"."READERS" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;
)

DROP TABLE IF EXISTS "WorkSoft"."READERS";

CREATE TABLE IF NOT EXISTS "WorkSoft"."READERS"
(
    "Id" numeric(8,0) NOT NULL,
    name character varying(8) NOT NULL,
    CONSTRAINT "READERS_PK" PRIMARY KEY ("Id")
)

ALTER TABLE IF EXISTS "WorkSoft"."BLOG_READERS"
    ADD CONSTRAINT "TABLE_3_BLOGS_FK" FOREIGN KEY ("B_ID")
    REFERENCES "WorkSoft"."BLOGS" ("ID") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS "WorkSoft"."BLOG_READERS"
    ADD CONSTRAINT "TABLE_3_READERS_FK" FOREIGN KEY ("R_ID")
    REFERENCES "WorkSoft"."READERS" ("Id") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

END;

*******************seguridad************
-- DROP TABLE IF EXISTS worksoft."user";

CREATE TABLE IF NOT EXISTS worksoft."user"
(
    id numericn umeric(8,0) NOT NULL,
    username character varying(50) COLLATE pg_catalog."default",
    password character varying(10) COLLATE pg_catalog."default",
    CONSTRAINT user_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS worksoft."user"
    OWNER to postgres;