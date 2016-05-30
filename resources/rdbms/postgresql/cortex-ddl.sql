\c cerebral_cortex

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.3
-- Dumped by pg_dump version 9.5.1

-- Started on 2016-05-30 16:02:25 CEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12018)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2312 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 2 (class 3079 OID 575802)
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- TOC entry 2313 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 172 (class 1259 OID 575813)
-- Name: active_admin_comments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE active_admin_comments (
    id integer NOT NULL,
    namespace character varying,
    body text,
    resource_id character varying NOT NULL,
    resource_type character varying NOT NULL,
    author_id integer,
    author_type character varying,
    created_at timestamp without time zone,
    updated_at timestamp without time zone
);


ALTER TABLE active_admin_comments OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 575819)
-- Name: active_admin_comments_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE active_admin_comments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE active_admin_comments_id_seq OWNER TO postgres;

--
-- TOC entry 2314 (class 0 OID 0)
-- Dependencies: 173
-- Name: active_admin_comments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE active_admin_comments_id_seq OWNED BY active_admin_comments.id;


--
-- TOC entry 174 (class 1259 OID 575821)
-- Name: admin_users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE admin_users (
    id integer NOT NULL,
    email character varying DEFAULT ''::character varying NOT NULL,
    encrypted_password character varying DEFAULT ''::character varying NOT NULL,
    reset_password_token character varying,
    reset_password_sent_at timestamp without time zone,
    remember_created_at timestamp without time zone,
    sign_in_count integer DEFAULT 0 NOT NULL,
    current_sign_in_at timestamp without time zone,
    last_sign_in_at timestamp without time zone,
    current_sign_in_ip inet,
    last_sign_in_ip inet,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE admin_users OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 575830)
-- Name: admin_users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE admin_users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE admin_users_id_seq OWNER TO postgres;

--
-- TOC entry 2315 (class 0 OID 0)
-- Dependencies: 175
-- Name: admin_users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE admin_users_id_seq OWNED BY admin_users.id;


--
-- TOC entry 176 (class 1259 OID 575832)
-- Name: datapoints_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE datapoints_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE datapoints_id_seq OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 575834)
-- Name: datasources_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE datasources_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE datasources_id_seq OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 575836)
-- Name: datastreams; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE datastreams (
    id integer NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    datasource_id integer,
    participant_id uuid
);


ALTER TABLE datastreams OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 575839)
-- Name: datastreams_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE datastreams_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE datastreams_id_seq OWNER TO postgres;

--
-- TOC entry 2316 (class 0 OID 0)
-- Dependencies: 179
-- Name: datastreams_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE datastreams_id_seq OWNED BY datastreams.id;


--
-- TOC entry 180 (class 1259 OID 575841)
-- Name: m_cerebrum_applications_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE m_cerebrum_applications_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE m_cerebrum_applications_id_seq OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 575843)
-- Name: m_cerebrum_platform_apps_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE m_cerebrum_platform_apps_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE m_cerebrum_platform_apps_id_seq OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 575845)
-- Name: m_cerebrum_platforms_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE m_cerebrum_platforms_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE m_cerebrum_platforms_id_seq OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 575847)
-- Name: oauth_access_token; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE oauth_access_token (
    token_id character varying(256),
    token bytea,
    authentication_id character varying(256),
    user_name character varying(256),
    client_id character varying(256),
    authentication bytea,
    refresh_token character varying(256)
);


ALTER TABLE oauth_access_token OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 575853)
-- Name: oauth_client_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE oauth_client_details (
    client_id character varying(256) NOT NULL,
    resource_ids character varying(256),
    client_secret character varying(256),
    scope character varying(256),
    authorized_grant_types character varying(256),
    web_server_redirect_uri character varying(256),
    authorities character varying(256),
    access_token_validity integer,
    refresh_token_validity integer,
    additional_information character varying(4096),
    autoapprove character varying(256)
);


ALTER TABLE oauth_client_details OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 575859)
-- Name: oauth_refresh_token; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE oauth_refresh_token (
    token_id character varying(256),
    token bytea,
    authentication bytea
);


ALTER TABLE oauth_refresh_token OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 575865)
-- Name: participant_studies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE participant_studies (
    id integer NOT NULL,
    participant_id uuid,
    study_id integer,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE participant_studies OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 575868)
-- Name: participant_studies_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE participant_studies_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE participant_studies_id_seq OWNER TO postgres;

--
-- TOC entry 2317 (class 0 OID 0)
-- Dependencies: 187
-- Name: participant_studies_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE participant_studies_id_seq OWNED BY participant_studies.id;


--
-- TOC entry 188 (class 1259 OID 575870)
-- Name: participants; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE participants (
    id uuid DEFAULT uuid_generate_v4() NOT NULL,
    identifier character varying,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE participants OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 575877)
-- Name: schema_migrations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE schema_migrations (
    version character varying NOT NULL
);


ALTER TABLE schema_migrations OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 575883)
-- Name: studies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE studies (
    id integer NOT NULL,
    identifier character varying,
    name character varying,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE studies OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 575889)
-- Name: studies_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE studies_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE studies_id_seq OWNER TO postgres;

--
-- TOC entry 2318 (class 0 OID 0)
-- Dependencies: 191
-- Name: studies_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE studies_id_seq OWNED BY studies.id;


--
-- TOC entry 2162 (class 2604 OID 575891)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY active_admin_comments ALTER COLUMN id SET DEFAULT nextval('active_admin_comments_id_seq'::regclass);


--
-- TOC entry 2166 (class 2604 OID 575892)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY admin_users ALTER COLUMN id SET DEFAULT nextval('admin_users_id_seq'::regclass);


--
-- TOC entry 2167 (class 2604 OID 575893)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY datastreams ALTER COLUMN id SET DEFAULT nextval('datastreams_id_seq'::regclass);


--
-- TOC entry 2168 (class 2604 OID 575894)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY participant_studies ALTER COLUMN id SET DEFAULT nextval('participant_studies_id_seq'::regclass);


--
-- TOC entry 2170 (class 2604 OID 575895)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY studies ALTER COLUMN id SET DEFAULT nextval('studies_id_seq'::regclass);


--
-- TOC entry 2172 (class 2606 OID 575898)
-- Name: active_admin_comments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY active_admin_comments
    ADD CONSTRAINT active_admin_comments_pkey PRIMARY KEY (id);


--
-- TOC entry 2177 (class 2606 OID 575900)
-- Name: admin_users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY admin_users
    ADD CONSTRAINT admin_users_pkey PRIMARY KEY (id);


--
-- TOC entry 2181 (class 2606 OID 575902)
-- Name: datastreams_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY datastreams
    ADD CONSTRAINT datastreams_pkey PRIMARY KEY (id);


--
-- TOC entry 2185 (class 2606 OID 575904)
-- Name: oauth_client_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY oauth_client_details
    ADD CONSTRAINT oauth_client_details_pkey PRIMARY KEY (client_id);


--
-- TOC entry 2189 (class 2606 OID 575906)
-- Name: participant_studies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY participant_studies
    ADD CONSTRAINT participant_studies_pkey PRIMARY KEY (id);


--
-- TOC entry 2191 (class 2606 OID 575908)
-- Name: participants_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY participants
    ADD CONSTRAINT participants_pkey PRIMARY KEY (id);


--
-- TOC entry 2194 (class 2606 OID 575910)
-- Name: studies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY studies
    ADD CONSTRAINT studies_pkey PRIMARY KEY (id);


--
-- TOC entry 2173 (class 1259 OID 575911)
-- Name: index_active_admin_comments_on_author_type_and_author_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX index_active_admin_comments_on_author_type_and_author_id ON active_admin_comments USING btree (author_type, author_id);


--
-- TOC entry 2174 (class 1259 OID 575912)
-- Name: index_active_admin_comments_on_namespace; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX index_active_admin_comments_on_namespace ON active_admin_comments USING btree (namespace);


--
-- TOC entry 2175 (class 1259 OID 575913)
-- Name: index_active_admin_comments_on_resource_type_and_resource_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX index_active_admin_comments_on_resource_type_and_resource_id ON active_admin_comments USING btree (resource_type, resource_id);


--
-- TOC entry 2178 (class 1259 OID 575914)
-- Name: index_admin_users_on_email; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX index_admin_users_on_email ON admin_users USING btree (email);


--
-- TOC entry 2179 (class 1259 OID 575915)
-- Name: index_admin_users_on_reset_password_token; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX index_admin_users_on_reset_password_token ON admin_users USING btree (reset_password_token);


--
-- TOC entry 2182 (class 1259 OID 575916)
-- Name: index_datastreams_on_datasource_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX index_datastreams_on_datasource_id ON datastreams USING btree (datasource_id);


--
-- TOC entry 2183 (class 1259 OID 575917)
-- Name: index_datastreams_on_participant_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX index_datastreams_on_participant_id ON datastreams USING btree (participant_id);


--
-- TOC entry 2186 (class 1259 OID 575918)
-- Name: index_participant_studies_on_participant_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX index_participant_studies_on_participant_id ON participant_studies USING btree (participant_id);


--
-- TOC entry 2187 (class 1259 OID 575919)
-- Name: index_participant_studies_on_study_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX index_participant_studies_on_study_id ON participant_studies USING btree (study_id);


--
-- TOC entry 2192 (class 1259 OID 575920)
-- Name: unique_schema_migrations; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX unique_schema_migrations ON schema_migrations USING btree (version);


--
-- TOC entry 2195 (class 2606 OID 575921)
-- Name: fk_rails_2c9d94efa2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY datastreams
    ADD CONSTRAINT fk_rails_2c9d94efa2 FOREIGN KEY (participant_id) REFERENCES participants(id);


--
-- TOC entry 2196 (class 2606 OID 575926)
-- Name: fk_rails_a427dc1be7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY participant_studies
    ADD CONSTRAINT fk_rails_a427dc1be7 FOREIGN KEY (participant_id) REFERENCES participants(id);


--
-- TOC entry 2197 (class 2606 OID 575931)
-- Name: fk_rails_acbc1a23cd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY participant_studies
    ADD CONSTRAINT fk_rails_acbc1a23cd FOREIGN KEY (study_id) REFERENCES studies(id);


--
-- TOC entry 2311 (class 0 OID 0)
-- Dependencies: 8
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-05-30 16:02:25 CEST

--
-- PostgreSQL database dump complete
--


