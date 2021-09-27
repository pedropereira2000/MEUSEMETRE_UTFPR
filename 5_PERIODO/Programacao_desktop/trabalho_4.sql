--
-- PostgreSQL database dump
--

-- Dumped from database version 12.6
-- Dumped by pg_dump version 12.6

-- Started on 2021-05-25 21:30:25

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
-- TOC entry 1 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 2849 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 24696)
-- Name: clientes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clientes (
    id integer NOT NULL,
    cpf text NOT NULL,
    nome text NOT NULL,
    endereco text NOT NULL,
    telefone text NOT NULL
);


ALTER TABLE public.clientes OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 24702)
-- Name: clientes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.clientes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clientes_id_seq OWNER TO postgres;

--
-- TOC entry 2850 (class 0 OID 0)
-- Dependencies: 204
-- Name: clientes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.clientes_id_seq OWNED BY public.clientes.id;


--
-- TOC entry 205 (class 1259 OID 24704)
-- Name: filmes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.filmes (
    id integer NOT NULL,
    titulo text NOT NULL,
    genero text NOT NULL,
    duracao integer NOT NULL,
    classificacao text NOT NULL,
    studio text NOT NULL
);


ALTER TABLE public.filmes OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 24710)
-- Name: filmes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.filmes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.filmes_id_seq OWNER TO postgres;

--
-- TOC entry 2851 (class 0 OID 0)
-- Dependencies: 206
-- Name: filmes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.filmes_id_seq OWNED BY public.filmes.id;


--
-- TOC entry 207 (class 1259 OID 24712)
-- Name: locacoes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.locacoes (
    id integer NOT NULL,
    funcionario text NOT NULL,
    titulo_filme text NOT NULL,
    genero_filme text NOT NULL,
    studio_filme text NOT NULL,
    cpf_cliente text NOT NULL,
    data text NOT NULL,
    periodo_alugado integer NOT NULL,
    valor real NOT NULL,
    devolvido boolean NOT NULL
);


ALTER TABLE public.locacoes OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 24718)
-- Name: locacoes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.locacoes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.locacoes_id_seq OWNER TO postgres;

--
-- TOC entry 2852 (class 0 OID 0)
-- Dependencies: 208
-- Name: locacoes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.locacoes_id_seq OWNED BY public.locacoes.id;


--
-- TOC entry 2703 (class 2604 OID 24730)
-- Name: clientes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientes ALTER COLUMN id SET DEFAULT nextval('public.clientes_id_seq'::regclass);


--
-- TOC entry 2704 (class 2604 OID 24731)
-- Name: filmes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.filmes ALTER COLUMN id SET DEFAULT nextval('public.filmes_id_seq'::regclass);


--
-- TOC entry 2705 (class 2604 OID 24732)
-- Name: locacoes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.locacoes ALTER COLUMN id SET DEFAULT nextval('public.locacoes_id_seq'::regclass);


--
-- TOC entry 2838 (class 0 OID 24696)
-- Dependencies: 203
-- Data for Name: clientes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clientes (id, cpf, nome, endereco, telefone) FROM stdin;
17	478.856.778-42	Pedro Pereira	Rua 2	14-99697-7494
18	111.111.111-11	Jorge	Rua 3	14-46546-5465
19	555.555.555-55	Mario	Rua 1	14-65656-6565
20	889.879.876-55	Carla	Rua 2	64-64564-6545
21	321.321.323-45	Jonas	Rua 7	46-54564-6545
22	654.564.654-65	Pedro 1	fsafasdg	41-64517-4164
23	985.195.612-57	Pedro 2	fsjdgdfahsadg	64-16546-1257
24	375.165.618-76	Pedro 3	fsafgysg	54-16451-2654
25	486.787.213-12	Jorge Pedro Pereira	fsafhasg	41-76847-1241
1	686.868.968-96	zxbz	vzvf	67-86876-8766
2	564.456.498-49	fsafasdfasdfsdg	dsgdsfgdsfg	98-79879-8184
\.


--
-- TOC entry 2840 (class 0 OID 24704)
-- Dependencies: 205
-- Data for Name: filmes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.filmes (id, titulo, genero, duracao, classificacao, studio) FROM stdin;
2	Captão América	Ação	220	12	Disney
3	A casa monstro	Terror	150	12	Disney
1	Seu jorge	Musica	120	14	Brasil Cifras
6	Teste	Humor	100	16	Não sei
7	Captão Caverna	Terror	150	12	Não sei
4	afdsfsd	sdafasdfasd	1354125	L	fafsfsdfsdf
5	teste	Musica	150	L	Brasil Cifras
8	RacionaisMC	Musica	132	L	Brasil Cifras
9	RanatoRusso	Musica	150	L	Brasil Cifras
\.


--
-- TOC entry 2842 (class 0 OID 24712)
-- Dependencies: 207
-- Data for Name: locacoes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.locacoes (id, funcionario, titulo_filme, genero_filme, studio_filme, cpf_cliente, data, periodo_alugado, valor, devolvido) FROM stdin;
6	Pedro	A casa monstro	Terror	Disney	111.111.111-11	09/05/2021	1	40	t
7	Carla	Captão América	Ação	Disney	111.111.111-11	09/05/2021	2	10	f
\.


--
-- TOC entry 2853 (class 0 OID 0)
-- Dependencies: 204
-- Name: clientes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.clientes_id_seq', 2, true);


--
-- TOC entry 2854 (class 0 OID 0)
-- Dependencies: 206
-- Name: filmes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.filmes_id_seq', 9, true);


--
-- TOC entry 2855 (class 0 OID 0)
-- Dependencies: 208
-- Name: locacoes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.locacoes_id_seq', 1, false);


--
-- TOC entry 2707 (class 2606 OID 24724)
-- Name: clientes clientes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientes
    ADD CONSTRAINT clientes_pkey PRIMARY KEY (id);


--
-- TOC entry 2709 (class 2606 OID 24726)
-- Name: filmes filmes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.filmes
    ADD CONSTRAINT filmes_pkey PRIMARY KEY (id);


--
-- TOC entry 2711 (class 2606 OID 24728)
-- Name: locacoes locacoes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.locacoes
    ADD CONSTRAINT locacoes_pkey PRIMARY KEY (id);


-- Completed on 2021-05-25 21:30:25

--
-- PostgreSQL database dump complete
--

