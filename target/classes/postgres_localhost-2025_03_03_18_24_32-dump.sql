--
-- PostgreSQL database dump
--

-- Dumped from database version 17.3
-- Dumped by pg_dump version 17.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: historico_vacinacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.historico_vacinacao (
    "id_aplicação" integer NOT NULL,
    cpf_aplicacao character varying(14),
    id_lote_aplicacao integer,
    data_aplicacao date,
    local_aplicacao character varying(255)
);


ALTER TABLE public.historico_vacinacao OWNER TO postgres;

--
-- Name: historico_vacinacao_id_aplicação_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."historico_vacinacao_id_aplicação_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."historico_vacinacao_id_aplicação_seq" OWNER TO postgres;

--
-- Name: historico_vacinacao_id_aplicação_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."historico_vacinacao_id_aplicação_seq" OWNED BY public.historico_vacinacao."id_aplicação";


--
-- Name: lote; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lote (
    id_lote integer NOT NULL,
    id_vacina integer,
    data_fabricacao date,
    data_validade date,
    quantidade integer
);


ALTER TABLE public.lote OWNER TO postgres;

--
-- Name: paciente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.paciente (
    nome character varying(255),
    cpf character varying(14) NOT NULL,
    endereco text,
    email character varying(50),
    data_nasc date,
    sexo character varying(10)
);


ALTER TABLE public.paciente OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    username character varying(255) NOT NULL,
    password character varying(255),
    salt character varying(50)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: vacina_padronizada; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vacina_padronizada (
    id_vacina integer NOT NULL,
    nome_vacina character varying(255) NOT NULL,
    tipo_vacina character varying(50),
    fabricante character varying(255)
);


ALTER TABLE public.vacina_padronizada OWNER TO postgres;

--
-- Name: historico_vacinacao id_aplicação; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historico_vacinacao ALTER COLUMN "id_aplicação" SET DEFAULT nextval('public."historico_vacinacao_id_aplicação_seq"'::regclass);


--
-- Data for Name: historico_vacinacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.historico_vacinacao ("id_aplicação", cpf_aplicacao, id_lote_aplicacao, data_aplicacao, local_aplicacao) FROM stdin;
\.


--
-- Data for Name: lote; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.lote (id_lote, id_vacina, data_fabricacao, data_validade, quantidade) FROM stdin;
90996	90996	2024-10-17	2025-10-17	50
1234	1234	2024-06-07	2025-06-07	123
\.


--
-- Data for Name: paciente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.paciente (nome, cpf, endereco, email, data_nasc, sexo) FROM stdin;
Joaquim da Costa Neto	111.111.111-11	Rua Exemplificação, 111, Apto 01, Bairro Exemplo	joaquim@exemplo.com	2011-11-11	masculino
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (username, password, salt) FROM stdin;
João Silva	dgsqf+RZ9rr7Rwr9d4btEZjYXaXyXYqSakzuZs6Jn6E=	axXPyS1nGGaT1GzeBel7fA==
Andressa Peixoto	DNRIIxTaiVlk0Lc8T0S18tlNGsl7p5Jz2GNUXJD94gg=	eZ9f2YAqA69XWTOdp+sCbg==
Bianca	gc+ZwIg3h4nVCxdXvl/769ZRjVX5Vi9tOsKGFVyeZuM=	tguYHAm0X3r+zDhx5tkJfA==
Marcos	2WU52GXTczWYQ1OLa4C7ztTIHj/LxdhBayuzWQn8nxg=	u4EvER4+awM6WJeTzSK82g==
Rathurenny	A5Tye+XgdOcNPrWWZWdGIXVYVHAl0leSg2dMZfSSOLs=	u0pSAtESh+Hv74LwlyNhWQ==
\.


--
-- Data for Name: vacina_padronizada; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vacina_padronizada (id_vacina, nome_vacina, tipo_vacina, fabricante) FROM stdin;
1234	Exemplo_contra_doença	RDNA	Exemplo_fabricante
1235	Exemplo_contra_outra_doença	Convencional	Exemplo_fabricante_2
90996	Anti-tifóide	Convencional	Puffizer
\.


--
-- Name: historico_vacinacao_id_aplicação_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."historico_vacinacao_id_aplicação_seq"', 1, false);


--
-- Name: historico_vacinacao historico_vacinacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historico_vacinacao
    ADD CONSTRAINT historico_vacinacao_pkey PRIMARY KEY ("id_aplicação");


--
-- Name: lote lote_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lote
    ADD CONSTRAINT lote_pkey PRIMARY KEY (id_lote);


--
-- Name: paciente paciente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT paciente_pkey PRIMARY KEY (cpf);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (username);


--
-- Name: vacina_padronizada vacina_padronizada_nome_vacina_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vacina_padronizada
    ADD CONSTRAINT vacina_padronizada_nome_vacina_key UNIQUE (nome_vacina);


--
-- Name: vacina_padronizada vacina_padronizada_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vacina_padronizada
    ADD CONSTRAINT vacina_padronizada_pkey PRIMARY KEY (id_vacina);


--
-- Name: historico_vacinacao historico_vacinacao_cpf_aplicacao_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historico_vacinacao
    ADD CONSTRAINT historico_vacinacao_cpf_aplicacao_fkey FOREIGN KEY (cpf_aplicacao) REFERENCES public.paciente(cpf);


--
-- Name: historico_vacinacao historico_vacinacao_id_lote_aplicacao_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historico_vacinacao
    ADD CONSTRAINT historico_vacinacao_id_lote_aplicacao_fkey FOREIGN KEY (id_lote_aplicacao) REFERENCES public.lote(id_lote);


--
-- Name: lote lote_id_vacina_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lote
    ADD CONSTRAINT lote_id_vacina_fkey FOREIGN KEY (id_vacina) REFERENCES public.vacina_padronizada(id_vacina);


--
-- PostgreSQL database dump complete
--

