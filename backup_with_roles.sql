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
SET xmloption = content;adm
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE postgres;
--
-- Name: postgres; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'pt-BR';


ALTER DATABASE postgres OWNER TO postgres;

\connect postgres

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
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


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

INSERT INTO public.funcionarios VALUES ('João Silveira', '333.333.333-33', 'Rua Marco Aurélio, 53, Centro', '2000-12-15', 'jsilveira@gmail.com', '123456/MG');
INSERT INTO public.funcionarios VALUES ('Letícia Ribeiro', '444.444.444-44', 'Avenida Getúlio Vargas, 444, Centro', '1995-09-07', 'ribeirole@outlook.com', '111111/MG');
INSERT INTO public.funcionarios VALUES ('Pâmela Almeida', '555.555.555-55', 'Avenida Castelo Branco, 766, Centro', '1999-01-08', 'pampam@hotmail.com', '676788/MG');
INSERT INTO public.funcionarios VALUES ('João Silva', '123.456.789-10', 'Rua Exemplificação, 123, Novo Exemplo', '2002-02-20', 'js_exemplo@exemplo.com', '654321/MG');
INSERT INTO public.funcionarios VALUES ('Andressa Peixoto', '123.456.710-10', 'Rua Exemplificação, 123, Novo Exemplo', '2002-02-20', 'apeixoto@exemplo.com', '654322/MG');
INSERT INTO public.funcionarios VALUES ('Bianca', '123.456.711-10', 'Rua Exemplificação, 123, Novo Exemplo', '2002-02-20', 'bianca@exemplo.com', '654323/MG');
INSERT INTO public.funcionarios VALUES ('Marcos', '123.456.712-10', 'Rua Exemplificação, 123, Novo Exemplo', '2002-02-20', 'marcos@exemplo.com', '654333/MG');
INSERT INTO public.funcionarios VALUES ('Rathurenny', '123.456.715-10', 'Rua Exemplificação, 123, Novo Exemplo', '2002-02-20', 'rathurenny@exemplo.com', '654444/MG');
INSERT INTO public.funcionarios VALUES ('Cledislene', '123.456.716-10', 'Rua Exemplificação, 123, Novo Exemplo', '2002-02-20', 'cleidinha@exemplo.com', '654555/MG');
INSERT INTO public.funcionarios VALUES ('Sandro Silvério', '122.333.444-45', 'Rua Campos Salles, 99, Alvorada', '1991-09-07', 'ssandro@outlook.com', '349867/SP');


--
-- Data for Name: historico_vacinacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.historico_vacinacao VALUES (19, '222.222.222-22', 8, '2025-03-06', 'admin');
INSERT INTO public.historico_vacinacao VALUES (20, '222.222.222-22', 14, '2025-03-06', 'Andressa Peixoto');
INSERT INTO public.historico_vacinacao VALUES (18, '222.222.222-22', 15, '2025-03-05', 'Letícia Ribeiro');
INSERT INTO public.historico_vacinacao VALUES (17, '111.111.111-11', 9, '2025-05-30', 'João Silveira');
INSERT INTO public.historico_vacinacao VALUES (16, '111.111.111-11', 2, '2025-04-25', 'Pâmela Almeida');
INSERT INTO public.historico_vacinacao VALUES (11, '111.111.111-11', 12, '2024-11-30', 'Marcos');
INSERT INTO public.historico_vacinacao VALUES (10, '111.111.111-11', 8, '2024-10-25', 'Bianca');
INSERT INTO public.historico_vacinacao VALUES (8, '111.111.111-11', 5, '2024-08-15', 'João Silva');
INSERT INTO public.historico_vacinacao VALUES (9, '111.111.111-11', 1, '2024-09-20', 'Andressa Peixoto');
INSERT INTO public.historico_vacinacao VALUES (15, '111.111.111-11', 14, '2025-03-20', 'Letícia Ribeiro');
INSERT INTO public.historico_vacinacao VALUES (14, '111.111.111-11', 7, '2025-02-15', 'João Silveira');
INSERT INTO public.historico_vacinacao VALUES (12, '111.111.111-11', 3, '2024-12-05', 'Rathurenny');
INSERT INTO public.historico_vacinacao VALUES (13, '111.111.111-11', 10, '2025-01-10', 'Cledislene');
INSERT INTO public.historico_vacinacao VALUES (21, '222.222.222-22', 17, '2023-01-10', 'Andressa Peixoto');
INSERT INTO public.historico_vacinacao VALUES (22, '222.222.222-22', 25, '2023-02-15', 'Letícia Ribeiro');
INSERT INTO public.historico_vacinacao VALUES (23, '222.222.222-22', 30, '2023-03-20', 'João Silveira');
INSERT INTO public.historico_vacinacao VALUES (24, '222.222.222-22', 19, '2023-04-25', 'Pâmela Almeida');
INSERT INTO public.historico_vacinacao VALUES (25, '222.222.222-22', 28, '2023-05-30', 'Marcos');
INSERT INTO public.historico_vacinacao VALUES (26, '222.222.222-22', 22, '2023-06-05', 'Bianca');
INSERT INTO public.historico_vacinacao VALUES (27, '222.222.222-22', 32, '2023-07-10', 'João Silva');
INSERT INTO public.historico_vacinacao VALUES (28, '222.222.222-22', 20, '2023-08-15', 'Andressa Peixoto');
INSERT INTO public.historico_vacinacao VALUES (29, '222.222.222-22', 27, '2023-09-20', 'Letícia Ribeiro');
INSERT INTO public.historico_vacinacao VALUES (30, '222.222.222-22', 23, '2023-10-25', 'João Silveira');
INSERT INTO public.historico_vacinacao VALUES (31, '222.222.222-22', 31, '2023-11-30', 'Rathurenny');
INSERT INTO public.historico_vacinacao VALUES (32, '222.222.222-22', 26, '2023-12-05', 'Cledislene');
INSERT INTO public.historico_vacinacao VALUES (33, '222.222.222-22', 18, '2024-01-10', 'Andressa Peixoto');
INSERT INTO public.historico_vacinacao VALUES (34, '222.222.222-22', 29, '2024-02-15', 'Letícia Ribeiro');
INSERT INTO public.historico_vacinacao VALUES (35, '222.222.222-22', 21, '2024-03-20', 'João Silveira');
INSERT INTO public.historico_vacinacao VALUES (36, '222.222.222-22', 24, '2024-04-25', 'Pâmela Almeida');


--
-- Data for Name: lote; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.lote VALUES (2023001, 1, '2023-01-15', '2026-02-28', 1000);
INSERT INTO public.lote VALUES (2023002, 2, '2023-02-20', '2027-03-31', 1500);
INSERT INTO public.lote VALUES (2023003, 3, '2023-03-25', '2026-04-30', 2000);
INSERT INTO public.lote VALUES (2023004, 4, '2023-04-30', '2028-05-31', 1200);
INSERT INTO public.lote VALUES (2023005, 5, '2023-05-05', '2026-06-30', 1800);
INSERT INTO public.lote VALUES (2023006, 6, '2023-06-10', '2027-07-31', 2500);
INSERT INTO public.lote VALUES (2023007, 7, '2023-07-15', '2028-08-31', 1300);
INSERT INTO public.lote VALUES (2023009, 9, '2023-09-25', '2027-10-31', 2200);
INSERT INTO public.lote VALUES (2023010, 10, '2023-10-30', '2028-11-30', 1600);
INSERT INTO public.lote VALUES (2023011, 11, '2023-11-05', '2026-12-31', 2100);
INSERT INTO public.lote VALUES (2023012, 12, '2023-12-10', '2027-01-31', 1700);
INSERT INTO public.lote VALUES (2023013, 13, '2024-01-15', '2028-02-28', 2300);
INSERT INTO public.lote VALUES (2024015, 15, '2024-03-25', '2027-04-30', 2599);
INSERT INTO public.lote VALUES (2023008, 8, '2023-08-20', '2026-09-30', 1899);
INSERT INTO public.lote VALUES (2024014, 14, '2024-02-20', '2026-03-31', 1399);
INSERT INTO public.lote VALUES (202517, 17, '2025-01-15', '2027-01-15', 1000);
INSERT INTO public.lote VALUES (202518, 18, '2025-02-20', '2027-02-20', 1500);
INSERT INTO public.lote VALUES (202519, 19, '2025-03-25', '2027-03-25', 1200);
INSERT INTO public.lote VALUES (202520, 20, '2025-04-30', '2027-04-30', 800);
INSERT INTO public.lote VALUES (202521, 21, '2025-05-05', '2027-05-05', 2000);
INSERT INTO public.lote VALUES (202522, 22, '2025-06-10', '2027-06-10', 900);
INSERT INTO public.lote VALUES (202523, 23, '2025-07-15', '2027-07-15', 1100);
INSERT INTO public.lote VALUES (202524, 24, '2025-08-20', '2027-08-20', 1300);
INSERT INTO public.lote VALUES (202525, 25, '2025-09-25', '2027-09-25', 1600);
INSERT INTO public.lote VALUES (202526, 26, '2025-10-30', '2027-10-30', 1400);
INSERT INTO public.lote VALUES (202527, 27, '2025-11-05', '2027-11-05', 1700);
INSERT INTO public.lote VALUES (202528, 28, '2025-12-10', '2027-12-10', 1800);
INSERT INTO public.lote VALUES (202529, 29, '2025-01-15', '2027-01-15', 1900);
INSERT INTO public.lote VALUES (202530, 30, '2025-02-20', '2027-02-20', 2100);
INSERT INTO public.lote VALUES (202531, 31, '2025-03-25', '2027-03-25', 2200);
INSERT INTO public.lote VALUES (202532, 32, '2025-04-30', '2027-04-30', 2300);


--
-- Data for Name: paciente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.paciente VALUES ('Joaquim da Costa Neto', '111.111.111-11', 'Rua Exemplificação, 111, Apto 01, Bairro Exemplo', 'joaquim@exemplo.com', '2011-11-11', 'masculino');
INSERT INTO public.paciente VALUES ('Tâmara Thompson Souza', '222.222.222-22', 'Rua das Acácias, 92, Bairro Jardim Florestal', 'tatasouza@gmail.com', '1982-05-09', 'feminino');


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users VALUES ('João Silva', 'dgsqf+RZ9rr7Rwr9d4btEZjYXaXyXYqSakzuZs6Jn6E=', 'axXPyS1nGGaT1GzeBel7fA==');
INSERT INTO public.users VALUES ('Andressa Peixoto', 'DNRIIxTaiVlk0Lc8T0S18tlNGsl7p5Jz2GNUXJD94gg=', 'eZ9f2YAqA69XWTOdp+sCbg==');
INSERT INTO public.users VALUES ('Bianca', 'gc+ZwIg3h4nVCxdXvl/769ZRjVX5Vi9tOsKGFVyeZuM=', 'tguYHAm0X3r+zDhx5tkJfA==');
INSERT INTO public.users VALUES ('Marcos', '2WU52GXTczWYQ1OLa4C7ztTIHj/LxdhBayuzWQn8nxg=', 'u4EvER4+awM6WJeTzSK82g==');
INSERT INTO public.users VALUES ('Rathurenny', 'A5Tye+XgdOcNPrWWZWdGIXVYVHAl0leSg2dMZfSSOLs=', 'u0pSAtESh+Hv74LwlyNhWQ==');
INSERT INTO public.users VALUES ('Cledislene', 'JjvVCx+QeTqhpzwgy142ehAoyEzldIp8g3RZGoMk6Hs=', 'ViCXRRpTrY6DfFW5d4RWzw==');
INSERT INTO public.users VALUES ('João Silveira', 'LlkEbaSg7MwuSwQVGs4HLStLRJxtGdukHom28b2+llc=', 'X59Rm+se4xHTEn+P+10lXg==');
INSERT INTO public.users VALUES ('Letícia Ribeiro', 'Yc7O/1KyErloNSi873U4pje/Zg/PGlOcpBfSLKYMcnE=', '5aKnwJIjd1BSoqaGloTnIA==');
INSERT INTO public.users VALUES ('Pâmela Almeida', '4qxKYrTh+vO+6rWrg6HQbctbUDGQZWouGKePebfMX3k=', 'WYOUFaRjCHBzvcoQtTbNzQ==');
INSERT INTO public.users VALUES ('Sandro Silvério', 'Ta6TH+WfKDniWkw4FjmBi2ni9x70ZQj9fupdSvhsxbM=', '8afmTTrM4AXjjtCldDQvbA==');


--
-- Data for Name: vacina_padronizada; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.vacina_padronizada VALUES (1, 'Influenza', 'Inativada', 'Butantan');
INSERT INTO public.vacina_padronizada VALUES (2, 'SCR', 'Atenuada', 'Fiocruz');
INSERT INTO public.vacina_padronizada VALUES (3, 'Hepatite B', 'Recombinante', 'GSK');
INSERT INTO public.vacina_padronizada VALUES (4, 'Febre Amarela', 'Atenuada', 'Bio-Manguinhos');
INSERT INTO public.vacina_padronizada VALUES (5, 'COVID-19 (mRNA)', 'mRNA', 'Pfizer');
INSERT INTO public.vacina_padronizada VALUES (6, 'Poliomielite', 'Inativada', 'Sanofi');
INSERT INTO public.vacina_padronizada VALUES (7, 'HPV', 'Recombinante', 'Merck');
INSERT INTO public.vacina_padronizada VALUES (8, 'Varicela', 'Atenuada', 'MSD');
INSERT INTO public.vacina_padronizada VALUES (9, 'Meningite C', 'Conjugada', 'Novartis');
INSERT INTO public.vacina_padronizada VALUES (10, 'Rotavírus', 'Atenuada', 'Rotarix');
INSERT INTO public.vacina_padronizada VALUES (11, 'Pneumocócica', 'Conjugada', 'Prevnar');
INSERT INTO public.vacina_padronizada VALUES (12, 'DTPa', 'Combinada', 'Adacel');
INSERT INTO public.vacina_padronizada VALUES (13, 'Hepatite A', 'Inativada', 'Havrix');
INSERT INTO public.vacina_padronizada VALUES (14, 'Raiva', 'Inativada', 'Verorab');
INSERT INTO public.vacina_padronizada VALUES (15, 'Tétano', 'Toxoide', 'BioThrax');
INSERT INTO public.vacina_padronizada VALUES (16, 'Experimental H7N9', 'Inativada', 'Butantan');
INSERT INTO public.vacina_padronizada VALUES (17, 'Sarampo', 'Atenuada', 'Butantan');
INSERT INTO public.vacina_padronizada VALUES (18, 'Caxumba', 'Atenuada', 'Fiocruz');
INSERT INTO public.vacina_padronizada VALUES (19, 'Rubéola', 'Atenuada', 'GSK');
INSERT INTO public.vacina_padronizada VALUES (20, 'Meningite B', 'Recombinante', 'Bio-Manguinhos');
INSERT INTO public.vacina_padronizada VALUES (21, 'Haemophilus influenzae tipo b (Hib)', 'Conjugada', 'Pfizer');
INSERT INTO public.vacina_padronizada VALUES (22, 'Coqueluche', 'Combinada', 'Sanofi');
INSERT INTO public.vacina_padronizada VALUES (23, 'Difteria', 'Combinada', 'Merck');
INSERT INTO public.vacina_padronizada VALUES (24, 'Tuberculose (BCG)', 'Atenuada', 'MSD');
INSERT INTO public.vacina_padronizada VALUES (25, 'Varicela Zoster', 'Atenuada', 'Novartis');
INSERT INTO public.vacina_padronizada VALUES (26, 'Dengue', 'Atenuada', 'Rotarix');
INSERT INTO public.vacina_padronizada VALUES (27, 'Zika', 'Inativada', 'Prevnar');
INSERT INTO public.vacina_padronizada VALUES (28, 'Chikungunya', 'Inativada', 'Adacel');
INSERT INTO public.vacina_padronizada VALUES (29, 'Febre Tifoide', 'Inativada', 'Havrix');
INSERT INTO public.vacina_padronizada VALUES (30, 'Cólera', 'Inativada', 'Verorab');
INSERT INTO public.vacina_padronizada VALUES (31, 'Leishmaniose', 'Inativada', 'BioThrax');
INSERT INTO public.vacina_padronizada VALUES (32, 'Peste', 'Inativada', 'Butantan');


--
-- Name: historico_vacinacao_id_aplicação_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."historico_vacinacao_id_aplicação_seq"', 36, true);


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
GRANT SELECT ON TABLE public.funcionarios TO defaultexception;


--
-- Name: TABLE historico_vacinacao; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT ON TABLE public.historico_vacinacao TO application;
GRANT SELECT ON TABLE public.historico_vacinacao TO defaultexception;


--
-- Name: TABLE lote; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT ON TABLE public.lote TO application;
GRANT SELECT ON TABLE public.lote TO defaultexception;


--
-- Name: TABLE paciente; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT ON TABLE public.paciente TO application;
GRANT SELECT ON TABLE public.paciente TO defaultexception;


--
-- Name: TABLE users; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT ON TABLE public.users TO application;
GRANT SELECT ON TABLE public.users TO defaultexception;


--
-- Name: TABLE vacina_padronizada; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT ON TABLE public.vacina_padronizada TO application;
GRANT SELECT ON TABLE public.vacina_padronizada TO defaultexception;


--
-- PostgreSQL database dump complete
--

