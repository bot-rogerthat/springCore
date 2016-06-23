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


-- Table: public.user_

-- DROP TABLE public.user_;

CREATE TABLE public.user_
(
  id integer NOT NULL DEFAULT nextval('"User_id_seq"'::regclass),
  name character varying(255),
  email character varying(255),
  day_of_birth date,
  CONSTRAINT pk_user_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.user_
  OWNER TO postgres;

-- Table: public.auditorium

-- DROP TABLE public.auditorium;

CREATE TABLE public.auditorium
(
  id integer NOT NULL DEFAULT nextval('"Auditorium_id_seq"'::regclass),
  name character varying(255),
  number_of_seats bigint,
  CONSTRAINT pk_auditorium_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.auditorium
  OWNER TO postgres;

-- Table: public.vip_seat

-- DROP TABLE public.vip_seat;

CREATE TABLE public.vip_seat
(
  id integer NOT NULL DEFAULT nextval('"VipSeats_id_seq"'::regclass),
  auditorium_id bigint,
  seat_number bigint,
  CONSTRAINT pk_vip_seats_id PRIMARY KEY (id),
  CONSTRAINT fk_auditorium_id FOREIGN KEY (auditorium_id)
      REFERENCES public.auditorium (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.vip_seat
  OWNER TO postgres;
  

-- Table: public.event

-- DROP TABLE public.event;

CREATE TABLE public.event
(
  id integer NOT NULL DEFAULT nextval('"Event_id_seq"'::regclass),
  name character varying(255),
  date timestamp without time zone,
  "time" time without time zone,
  price bigint,
  rating character varying(255),
  auditorium_id bigint,
  CONSTRAINT pk_event_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.event
  OWNER TO postgres;

-- Table: public.ticket

-- DROP TABLE public.ticket;

CREATE TABLE public.ticket
(
  id integer NOT NULL DEFAULT nextval('"Ticket_id_seq"'::regclass),
  seat character varying(255),
  is_booked boolean,
  event_id bigint,
  user_id bigint,
  CONSTRAINT pk_ticket_id PRIMARY KEY (id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id)
      REFERENCES public.user_ (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.ticket
  OWNER TO postgres;

-- Table: public.event_stat

-- DROP TABLE public.event_stat;

CREATE TABLE public.event_stat
(
  id integer NOT NULL DEFAULT nextval('event_stat_id_seq'::regclass),
  event_id bigint,
  count_by_event_name bigint,
  count_by_event_price bigint,
  count_by_ticket_booked bigint,
  CONSTRAINT pk_event_stat_id PRIMARY KEY (id),
  CONSTRAINT fk_event_id FOREIGN KEY (event_id)
      REFERENCES public.event (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.event_stat
  OWNER TO postgres;