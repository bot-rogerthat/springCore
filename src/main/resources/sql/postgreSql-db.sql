-- Sequence: public."Auditorium_id_seq"

-- DROP SEQUENCE public."Auditorium_id_seq";

CREATE SEQUENCE public."Auditorium_id_seq"
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 7
CACHE 1;
ALTER TABLE public."Auditorium_id_seq"
OWNER TO postgres;

-- Sequence: public."Event_id_seq"

-- DROP SEQUENCE public."Event_id_seq";

CREATE SEQUENCE public."Event_id_seq"
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 4
CACHE 1;
ALTER TABLE public."Event_id_seq"
OWNER TO postgres;

-- Sequence: public."Ticket_id_seq"

-- DROP SEQUENCE public."Ticket_id_seq";

CREATE SEQUENCE public."Ticket_id_seq"
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 8
CACHE 1;
ALTER TABLE public."Ticket_id_seq"
OWNER TO postgres;

-- Sequence: public."User_id_seq"

-- DROP SEQUENCE public."User_id_seq";

CREATE SEQUENCE public."User_id_seq"
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 3
CACHE 1;
ALTER TABLE public."User_id_seq"
OWNER TO postgres;

-- Sequence: public."VipSeats_id_seq"

-- DROP SEQUENCE public."VipSeats_id_seq";

CREATE SEQUENCE public."VipSeats_id_seq"
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 20
CACHE 1;
ALTER TABLE public."VipSeats_id_seq"
OWNER TO postgres;

-- Sequence: public.event_stat_id_seq

-- DROP SEQUENCE public.event_stat_id_seq;

CREATE SEQUENCE public.event_stat_id_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE public.event_stat_id_seq
OWNER TO postgres;

-- Sequence: discount_stat_id_seq

-- DROP SEQUENCE discount_stat_id_seq;

CREATE SEQUENCE discount_stat_id_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE discount_stat_id_seq
OWNER TO postgres;

-- Sequence: public.employee_id_seq

-- DROP SEQUENCE public.employee_id_seq;

CREATE SEQUENCE public.employee_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
ALTER TABLE public.employee_id_seq
  OWNER TO postgres;

-- Sequence: public.job_id_seq

-- DROP SEQUENCE public.job_id_seq;

CREATE SEQUENCE public.job_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 3
  CACHE 1;
ALTER TABLE public.job_id_seq
  OWNER TO postgres;


-- Table: public.user_

-- DROP TABLE public.user_;

CREATE TABLE public.user_
(
  id           INTEGER NOT NULL DEFAULT nextval('"User_id_seq"' :: REGCLASS),
  name         CHARACTER VARYING(255),
  email        CHARACTER VARYING(255),
  day_of_birth DATE,
  CONSTRAINT pk_user_id PRIMARY KEY (id)
)
WITH (
OIDS =FALSE
);
ALTER TABLE public.user_
OWNER TO postgres;

-- Table: public.auditorium

-- DROP TABLE public.auditorium;

CREATE TABLE public.auditorium
(
  id              INTEGER NOT NULL DEFAULT nextval('"Auditorium_id_seq"' :: REGCLASS),
  name            CHARACTER VARYING(255),
  number_of_seats BIGINT,
  CONSTRAINT pk_auditorium_id PRIMARY KEY (id)
)
WITH (
OIDS =FALSE
);
ALTER TABLE public.auditorium
OWNER TO postgres;

-- Table: public.vip_seat

-- DROP TABLE public.vip_seat;

CREATE TABLE public.vip_seat
(
  id            INTEGER NOT NULL DEFAULT nextval('"VipSeats_id_seq"' :: REGCLASS),
  auditorium_id BIGINT,
  seat_number   BIGINT,
  CONSTRAINT pk_vip_seats_id PRIMARY KEY (id),
  CONSTRAINT fk_auditorium_id FOREIGN KEY (auditorium_id)
  REFERENCES public.auditorium (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
OIDS =FALSE
);
ALTER TABLE public.vip_seat
OWNER TO postgres;

-- Table: public.event

-- DROP TABLE public.event;

CREATE TABLE public.event
(
  id            INTEGER NOT NULL DEFAULT nextval('"Event_id_seq"' :: REGCLASS),
  name          CHARACTER VARYING(255),
  date          TIMESTAMP WITHOUT TIME ZONE,
  "time"        TIME WITHOUT TIME ZONE,
  price         BIGINT,
  rating        CHARACTER VARYING(255),
  auditorium_id BIGINT,
  CONSTRAINT pk_event_id PRIMARY KEY (id)
)
WITH (
OIDS =FALSE
);
ALTER TABLE public.event
OWNER TO postgres;

-- Table: public.ticket

-- DROP TABLE public.ticket;

CREATE TABLE public.ticket
(
  id        INTEGER NOT NULL DEFAULT nextval('"Ticket_id_seq"' :: REGCLASS),
  seat      CHARACTER VARYING(255),
  is_booked BOOLEAN,
  event_id  BIGINT,
  user_id   BIGINT,
  CONSTRAINT pk_ticket_id PRIMARY KEY (id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id)
  REFERENCES public.user_ (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
OIDS =FALSE
);
ALTER TABLE public.ticket
OWNER TO postgres;

-- Table: public.event_stat

-- DROP TABLE public.event_stat;

CREATE TABLE public.event_stat
(
  id                     INTEGER NOT NULL DEFAULT nextval('event_stat_id_seq' :: REGCLASS),
  event_id               BIGINT,
  count_by_event_name    BIGINT,
  count_by_event_price   BIGINT,
  count_by_ticket_booked BIGINT,
  CONSTRAINT pk_event_stat_id PRIMARY KEY (id),
  CONSTRAINT fk_event_id FOREIGN KEY (event_id)
  REFERENCES public.event (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
OIDS =FALSE
);
ALTER TABLE public.event_stat
OWNER TO postgres;

-- Table: discount_stat

-- DROP TABLE discount_stat;

CREATE TABLE discount_stat
(
  id            SERIAL NOT NULL,
  user_id       BIGINT,
  discount_name CHARACTER VARYING(255),
  count         BIGINT,
  CONSTRAINT pk_discount_stat_id PRIMARY KEY (id)
)
WITH (
OIDS =FALSE
);
ALTER TABLE discount_stat
OWNER TO postgres;

-- Table: public.employee

-- DROP TABLE public.employee;

CREATE TABLE public.employee
(
  id integer NOT NULL DEFAULT nextval('employee_id_seq'::regclass),
  name character varying(255),
  CONSTRAINT pk_employee_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.employee
  OWNER TO postgres;

-- Table: public.job

-- DROP TABLE public.job;

CREATE TABLE public.job
(
  id integer NOT NULL DEFAULT nextval('job_id_seq'::regclass),
  description character varying(255),
  employee_id bigint,
  date timestamp without time zone,
  CONSTRAINT pk_job_id PRIMARY KEY (id),
  CONSTRAINT fk_employee_id FOREIGN KEY (employee_id)
      REFERENCES public.employee (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.job
  OWNER TO postgres;
