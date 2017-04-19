--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: dataadmin; Type: DATABASE; Schema: -; Owner: forestnewark
--

CREATE DATABASE dataadmin WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE dataadmin OWNER TO forestnewark;

\connect dataadmin

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: actionitems; Type: TABLE; Schema: public; Owner: forestnewark
--

CREATE TABLE actionitems (
    actionitemid integer NOT NULL,
    item text,
    status character varying(25),
    addedby character varying(20)
);


ALTER TABLE actionitems OWNER TO forestnewark;

--
-- Name: actionitems_actionitemid_seq; Type: SEQUENCE; Schema: public; Owner: forestnewark
--

CREATE SEQUENCE actionitems_actionitemid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE actionitems_actionitemid_seq OWNER TO forestnewark;

--
-- Name: actionitems_actionitemid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: forestnewark
--

ALTER SEQUENCE actionitems_actionitemid_seq OWNED BY actionitems.actionitemid;


--
-- Name: person; Type: TABLE; Schema: public; Owner: forestnewark
--

CREATE TABLE person (
    personid integer NOT NULL,
    firstname character varying(20),
    lastname character varying(20),
    rank character varying(20),
    permission character varying(20),
    email character varying(25),
    username character varying(20),
    password character varying(20)
);


ALTER TABLE person OWNER TO forestnewark;

--
-- Name: person_personid_seq; Type: SEQUENCE; Schema: public; Owner: forestnewark
--

CREATE SEQUENCE person_personid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE person_personid_seq OWNER TO forestnewark;

--
-- Name: person_personid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: forestnewark
--

ALTER SEQUENCE person_personid_seq OWNED BY person.personid;


--
-- Name: actionitems actionitemid; Type: DEFAULT; Schema: public; Owner: forestnewark
--

ALTER TABLE ONLY actionitems ALTER COLUMN actionitemid SET DEFAULT nextval('actionitems_actionitemid_seq'::regclass);


--
-- Name: person personid; Type: DEFAULT; Schema: public; Owner: forestnewark
--

ALTER TABLE ONLY person ALTER COLUMN personid SET DEFAULT nextval('person_personid_seq'::regclass);


--
-- Data for Name: actionitems; Type: TABLE DATA; Schema: public; Owner: forestnewark
--

COPY actionitems (actionitemid, item, status, addedby) FROM stdin;
1	Create Database Manager	In Progress	Forest Newark
\.


--
-- Name: actionitems_actionitemid_seq; Type: SEQUENCE SET; Schema: public; Owner: forestnewark
--

SELECT pg_catalog.setval('actionitems_actionitemid_seq', 1, true);


--
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: forestnewark
--

COPY person (personid, firstname, lastname, rank, permission, email, username, password) FROM stdin;
1	Forest	Newark	SGT	admin	forest.newark@gmail.com	forest-newark	\N
\.


--
-- Name: person_personid_seq; Type: SEQUENCE SET; Schema: public; Owner: forestnewark
--

SELECT pg_catalog.setval('person_personid_seq', 1, true);


--
-- Name: actionitems actionitems_pkey; Type: CONSTRAINT; Schema: public; Owner: forestnewark
--

ALTER TABLE ONLY actionitems
    ADD CONSTRAINT actionitems_pkey PRIMARY KEY (actionitemid);


--
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: forestnewark
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (personid);


--
-- Name: actionitems_actionitemid_uindex; Type: INDEX; Schema: public; Owner: forestnewark
--

CREATE UNIQUE INDEX actionitems_actionitemid_uindex ON actionitems USING btree (actionitemid);


--
-- Name: person_personid_uindex; Type: INDEX; Schema: public; Owner: forestnewark
--

CREATE UNIQUE INDEX person_personid_uindex ON person USING btree (personid);


--
-- PostgreSQL database dump complete
--

