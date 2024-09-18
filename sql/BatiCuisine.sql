--
-- PostgreSQL database dump
--

-- Dumped from database version 16.4
-- Dumped by pg_dump version 16.4

-- Started on 2024-09-18 23:12:20

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 845 (class 1247 OID 24583)
-- Name: etatprojet; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.etatprojet AS ENUM (
    'ENCOURS',
    'TERMINE',
    'SUSPENDU',
    'ANNULE'
);


ALTER TYPE public.etatprojet OWNER TO postgres;

--
-- TOC entry 848 (class 1247 OID 24592)
-- Name: typecomposant; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.typecomposant AS ENUM (
    'MATERIEL',
    'MAINDOEUVRE'
);


ALTER TYPE public.typecomposant OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 24597)
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clients (
    id uuid NOT NULL,
    nom character varying(255) NOT NULL,
    adresse character varying(255),
    telephone character varying(20),
    estprofessionnel boolean
);


ALTER TABLE public.clients OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24614)
-- Name: composants; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.composants (
    id uuid NOT NULL,
    nom character varying(255) NOT NULL,
    tauxtva double precision,
    typecomposant public.typecomposant,
    projetid uuid
);


ALTER TABLE public.composants OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 24630)
-- Name: devis; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.devis (
    id uuid NOT NULL,
    montantestime numeric(10,2),
    dateemission date,
    datevalidite date,
    accepte boolean,
    projetid uuid
);


ALTER TABLE public.devis OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 24627)
-- Name: maindoeuvres; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.maindoeuvres (
    tauxhoraire numeric(10,2),
    heurestravail double precision,
    productiviteouvrier double precision
)
INHERITS (public.composants);


ALTER TABLE public.maindoeuvres OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 24624)
-- Name: materiaux; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.materiaux (
    coutunitaire numeric(15,2),
    quantite double precision,
    couttransport numeric(10,2),
    coefficientqualite double precision
)
INHERITS (public.composants);


ALTER TABLE public.materiaux OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 24604)
-- Name: projets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.projets (
    id uuid NOT NULL,
    nom character varying(255) NOT NULL,
    surface double precision,
    couttotal numeric(15,2),
    etatprojet public.etatprojet,
    clientid uuid
);


ALTER TABLE public.projets OWNER TO postgres;

--
-- TOC entry 4867 (class 0 OID 24597)
-- Dependencies: 215
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clients (id, nom, adresse, telephone, estprofessionnel) FROM stdin;
\.


--
-- TOC entry 4869 (class 0 OID 24614)
-- Dependencies: 217
-- Data for Name: composants; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.composants (id, nom, tauxtva, typecomposant, projetid) FROM stdin;
\.


--
-- TOC entry 4872 (class 0 OID 24630)
-- Dependencies: 220
-- Data for Name: devis; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.devis (id, montantestime, dateemission, datevalidite, accepte, projetid) FROM stdin;
\.


--
-- TOC entry 4871 (class 0 OID 24627)
-- Dependencies: 219
-- Data for Name: maindoeuvres; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.maindoeuvres (id, nom, tauxtva, typecomposant, projetid, tauxhoraire, heurestravail, productiviteouvrier) FROM stdin;
\.


--
-- TOC entry 4870 (class 0 OID 24624)
-- Dependencies: 218
-- Data for Name: materiaux; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.materiaux (id, nom, tauxtva, typecomposant, projetid, coutunitaire, quantite, couttransport, coefficientqualite) FROM stdin;
\.


--
-- TOC entry 4868 (class 0 OID 24604)
-- Dependencies: 216
-- Data for Name: projets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.projets (id, nom, surface, couttotal, etatprojet, clientid) FROM stdin;
\.


--
-- TOC entry 4714 (class 2606 OID 24603)
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- TOC entry 4718 (class 2606 OID 24618)
-- Name: composants composants_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.composants
    ADD CONSTRAINT composants_pkey PRIMARY KEY (id);


--
-- TOC entry 4720 (class 2606 OID 24634)
-- Name: devis devis_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.devis
    ADD CONSTRAINT devis_pkey PRIMARY KEY (id);


--
-- TOC entry 4716 (class 2606 OID 24608)
-- Name: projets projets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projets
    ADD CONSTRAINT projets_pkey PRIMARY KEY (id);


--
-- TOC entry 4722 (class 2606 OID 24619)
-- Name: composants composants_projetid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.composants
    ADD CONSTRAINT composants_projetid_fkey FOREIGN KEY (projetid) REFERENCES public.projets(id);


--
-- TOC entry 4723 (class 2606 OID 24635)
-- Name: devis devis_projetid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.devis
    ADD CONSTRAINT devis_projetid_fkey FOREIGN KEY (projetid) REFERENCES public.projets(id);


--
-- TOC entry 4721 (class 2606 OID 24609)
-- Name: projets projets_clientid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projets
    ADD CONSTRAINT projets_clientid_fkey FOREIGN KEY (clientid) REFERENCES public.clients(id);


-- Completed on 2024-09-18 23:12:21

--
-- PostgreSQL database dump complete
--

