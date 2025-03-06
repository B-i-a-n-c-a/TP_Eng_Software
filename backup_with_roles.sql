--
-- PostgreSQL database cluster dump
--

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE application;
ALTER ROLE application WITH NOSUPERUSER INHERIT NOCREATEROLE NOCREATEDB LOGIN NOREPLICATION NOBYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:sSYyUWvMo2W5dQIlhkRB0g==$God/ARpxkgfcaexeHwcrHI8krW3r0S/F9rCt+KxjtpI=:wZnkGebf1Y88FBlZiPOhsRiLcarlaToKmKjkFkYPkVM=';
CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:ihEA9uIdlkRxNE3emUlgyQ==$Xc39riT1G0qFUVJQGFGXF9u9kY+3O1B0tdGcz0srhuI=:YKGQ33BO4vBKGYVfyDlRhWR0D71oqGrHlbvV0NpHdsU=';

--
-- User Configurations
--








--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

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

--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

\connect postgres

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
-- Name: funcionarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.funcionarios (
    nome character varying(50) NOT NULL,
    cpf character varying(14) NOT NULL,
    endereco text,
    data_nasc date,
    email character varying(50),
    crm_coren character varying(50) NOT NULL
);


ALTER TABLE public.funcionarios OWNER TO postgres;

--
-- Name: historico_vacinacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.historico_vacinacao (
    "id_aplicação" integer NOT NULL,
    cpf_aplicacao character varying(14),
    id_vacina_aplicacao integer,
    data_aplicacao date,
    nome_funcionario character varying(50)
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
-- Data for Name: funcionarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.funcionarios (nome, cpf, endereco, data_nasc, email, crm_coren) FROM stdin;
João Silveira	333.333.333-33	Rua Marco Aurélio, 53, Centro	2000-12-15	jsilveira@gmail.com	123456/MG
Letícia Ribeiro	444.444.444-44	Avenida Getúlio Vargas, 444, Centro	1995-09-07	ribeirole@outlook.com	111111/MG
Pâmela Almeida	555.555.555-55	Avenida Castelo Branco, 766, Centro	1999-01-08	pampam@hotmail.com	676788/MG
\.


--
-- Data for Name: historico_vacinacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.historico_vacinacao ("id_aplicação", cpf_aplicacao, id_vacina_aplicacao, data_aplicacao, nome_funcionario) FROM stdin;
8	111.111.111-11	5	2024-08-15	\N
9	111.111.111-11	1	2024-09-20	\N
10	111.111.111-11	8	2024-10-25	\N
11	111.111.111-11	12	2024-11-30	\N
12	111.111.111-11	3	2024-12-05	\N
13	111.111.111-11	10	2025-01-10	\N
14	111.111.111-11	7	2025-02-15	\N
15	111.111.111-11	14	2025-03-20	\N
16	111.111.111-11	2	2025-04-25	\N
17	111.111.111-11	9	2025-05-30	\N
18	222.222.222-22	15	2025-03-05	\N
19	222.222.222-22	8	2025-03-06	admin
20	222.222.222-22	14	2025-03-06	Andressa Peixoto
\.


--
-- Data for Name: lote; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.lote (id_lote, id_vacina, data_fabricacao, data_validade, quantidade) FROM stdin;
2023001	1	2023-01-15	2026-02-28	1000
2023002	2	2023-02-20	2027-03-31	1500
2023003	3	2023-03-25	2026-04-30	2000
2023004	4	2023-04-30	2028-05-31	1200
2023005	5	2023-05-05	2026-06-30	1800
2023006	6	2023-06-10	2027-07-31	2500
2023007	7	2023-07-15	2028-08-31	1300
2023009	9	2023-09-25	2027-10-31	2200
2023010	10	2023-10-30	2028-11-30	1600
2023011	11	2023-11-05	2026-12-31	2100
2023012	12	2023-12-10	2027-01-31	1700
2023013	13	2024-01-15	2028-02-28	2300
2024015	15	2024-03-25	2027-04-30	2599
2023008	8	2023-08-20	2026-09-30	1899
2024014	14	2024-02-20	2026-03-31	1399
\.


--
-- Data for Name: paciente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.paciente (nome, cpf, endereco, email, data_nasc, sexo) FROM stdin;
Joaquim da Costa Neto	111.111.111-11	Rua Exemplificação, 111, Apto 01, Bairro Exemplo	joaquim@exemplo.com	2011-11-11	masculino
Tâmara Thompson Souza	222.222.222-22	Rua das Acácias, 92, Bairro Jardim Florestal	tatasouza@gmail.com	1982-05-09	feminino
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
Cledislene	JjvVCx+QeTqhpzwgy142ehAoyEzldIp8g3RZGoMk6Hs=	ViCXRRpTrY6DfFW5d4RWzw==
João Silveira	LlkEbaSg7MwuSwQVGs4HLStLRJxtGdukHom28b2+llc=	X59Rm+se4xHTEn+P+10lXg==
Letícia Ribeiro	Yc7O/1KyErloNSi873U4pje/Zg/PGlOcpBfSLKYMcnE=	5aKnwJIjd1BSoqaGloTnIA==
Pâmela Almeida	4qxKYrTh+vO+6rWrg6HQbctbUDGQZWouGKePebfMX3k=	WYOUFaRjCHBzvcoQtTbNzQ==
\.


--
-- Data for Name: vacina_padronizada; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vacina_padronizada (id_vacina, nome_vacina, tipo_vacina, fabricante) FROM stdin;
1	Influenza	Inativada	Butantan
2	SCR	Atenuada	Fiocruz
3	Hepatite B	Recombinante	GSK
4	Febre Amarela	Atenuada	Bio-Manguinhos
5	COVID-19 (mRNA)	mRNA	Pfizer
6	Poliomielite	Inativada	Sanofi
7	HPV	Recombinante	Merck
8	Varicela	Atenuada	MSD
9	Meningite C	Conjugada	Novartis
10	Rotavírus	Atenuada	Rotarix
11	Pneumocócica	Conjugada	Prevnar
12	DTPa	Combinada	Adacel
13	Hepatite A	Inativada	Havrix
14	Raiva	Inativada	Verorab
15	Tétano	Toxoide	BioThrax
16	Experimental H7N9	Inativada	Butantan
\.


--
-- Name: historico_vacinacao_id_aplicação_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."historico_vacinacao_id_aplicação_seq"', 20, true);


--
-- Name: funcionarios funcionarios_cpf_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionarios
    ADD CONSTRAINT funcionarios_cpf_key UNIQUE (cpf);


--
-- Name: funcionarios funcionarios_crm_coren_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionarios
    ADD CONSTRAINT funcionarios_crm_coren_key UNIQUE (crm_coren);


--
-- Name: funcionarios funcionarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionarios
    ADD CONSTRAINT funcionarios_pkey PRIMARY KEY (cpf);


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
-- Name: funcionarios fk_users_funcionarios; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionarios
    ADD CONSTRAINT fk_users_funcionarios FOREIGN KEY (nome) REFERENCES public.users(username);


--
-- Name: historico_vacinacao historico_vacinacao_cpf_aplicacao_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historico_vacinacao
    ADD CONSTRAINT historico_vacinacao_cpf_aplicacao_fkey FOREIGN KEY (cpf_aplicacao) REFERENCES public.paciente(cpf);


--
-- Name: historico_vacinacao historico_vacinacao_id_vacina_aplicacao_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historico_vacinacao
    ADD CONSTRAINT historico_vacinacao_id_vacina_aplicacao_fkey FOREIGN KEY (id_vacina_aplicacao) REFERENCES public.vacina_padronizada(id_vacina);


--
-- Name: lote lote_id_vacina_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lote
    ADD CONSTRAINT lote_id_vacina_fkey FOREIGN KEY (id_vacina) REFERENCES public.vacina_padronizada(id_vacina);


--
-- Name: TABLE funcionarios; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT ON TABLE public.funcionarios TO application;


--
-- Name: TABLE historico_vacinacao; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT ON TABLE public.historico_vacinacao TO application;


--
-- Name: TABLE lote; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT ON TABLE public.lote TO application;


--
-- Name: TABLE paciente; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT ON TABLE public.paciente TO application;


--
-- Name: TABLE users; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT ON TABLE public.users TO application;


--
-- Name: TABLE vacina_padronizada; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT ON TABLE public.vacina_padronizada TO application;


--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database cluster dump complete
--

