\c cerebral_cortex

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12361)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2265 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 2 (class 3079 OID 16408)
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- TOC entry 2266 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 182 (class 1259 OID 16419)
-- Name: active_admin_comments; Type: TABLE; Schema: public; Owner: cerebralcortex
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


ALTER TABLE active_admin_comments OWNER TO cerebralcortex;

--
-- TOC entry 183 (class 1259 OID 16425)
-- Name: active_admin_comments_id_seq; Type: SEQUENCE; Schema: public; Owner: cerebralcortex
--

CREATE SEQUENCE active_admin_comments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE active_admin_comments_id_seq OWNER TO cerebralcortex;

--
-- TOC entry 2267 (class 0 OID 0)
-- Dependencies: 183
-- Name: active_admin_comments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cerebralcortex
--

ALTER SEQUENCE active_admin_comments_id_seq OWNED BY active_admin_comments.id;


--
-- TOC entry 184 (class 1259 OID 16427)
-- Name: admin_users; Type: TABLE; Schema: public; Owner: cerebralcortex
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


ALTER TABLE admin_users OWNER TO cerebralcortex;

--
-- TOC entry 185 (class 1259 OID 16436)
-- Name: admin_users_id_seq; Type: SEQUENCE; Schema: public; Owner: cerebralcortex
--

CREATE SEQUENCE admin_users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE admin_users_id_seq OWNER TO cerebralcortex;

--
-- TOC entry 2268 (class 0 OID 0)
-- Dependencies: 185
-- Name: admin_users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cerebralcortex
--

ALTER SEQUENCE admin_users_id_seq OWNED BY admin_users.id;


--
-- TOC entry 186 (class 1259 OID 16438)
-- Name: datapoints; Type: TABLE; Schema: public; Owner: cerebralcortex
--

CREATE TABLE datapoints (
    id integer NOT NULL,
    "timestamp" timestamp without time zone,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    datastream_id integer,
    sample jsonb,
    "offset" integer
);


ALTER TABLE datapoints OWNER TO cerebralcortex;

--
-- TOC entry 187 (class 1259 OID 16444)
-- Name: datapoints_id_seq; Type: SEQUENCE; Schema: public; Owner: cerebralcortex
--

CREATE SEQUENCE datapoints_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE datapoints_id_seq OWNER TO cerebralcortex;

--
-- TOC entry 2269 (class 0 OID 0)
-- Dependencies: 187
-- Name: datapoints_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cerebralcortex
--

ALTER SEQUENCE datapoints_id_seq OWNED BY datapoints.id;


--
-- TOC entry 188 (class 1259 OID 16446)
-- Name: datasources; Type: TABLE; Schema: public; Owner: cerebralcortex
--

CREATE TABLE datasources (
    id integer NOT NULL,
    identifier character varying,
    datasourcetype character varying,
    datadescriptor jsonb,
    metadata jsonb,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    m_cerebrum_application_id integer,
    m_cerebrum_platform_id integer,
    m_cerebrum_platform_app_id integer
);


ALTER TABLE datasources OWNER TO cerebralcortex;

--
-- TOC entry 189 (class 1259 OID 16452)
-- Name: datasources_id_seq; Type: SEQUENCE; Schema: public; Owner: cerebralcortex
--

CREATE SEQUENCE datasources_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE datasources_id_seq OWNER TO cerebralcortex;

--
-- TOC entry 2270 (class 0 OID 0)
-- Dependencies: 189
-- Name: datasources_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cerebralcortex
--

ALTER SEQUENCE datasources_id_seq OWNED BY datasources.id;


--
-- TOC entry 190 (class 1259 OID 16454)
-- Name: datastreams; Type: TABLE; Schema: public; Owner: cerebralcortex
--

CREATE TABLE datastreams (
    id integer NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    datasource_id integer,
    participant_id uuid
);


ALTER TABLE datastreams OWNER TO cerebralcortex;

--
-- TOC entry 191 (class 1259 OID 16457)
-- Name: datastreams_id_seq; Type: SEQUENCE; Schema: public; Owner: cerebralcortex
--

CREATE SEQUENCE datastreams_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE datastreams_id_seq OWNER TO cerebralcortex;

--
-- TOC entry 2271 (class 0 OID 0)
-- Dependencies: 191
-- Name: datastreams_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cerebralcortex
--

ALTER SEQUENCE datastreams_id_seq OWNED BY datastreams.id;


--
-- TOC entry 192 (class 1259 OID 16459)
-- Name: m_cerebrum_applications; Type: TABLE; Schema: public; Owner: cerebralcortex
--

CREATE TABLE m_cerebrum_applications (
    id integer NOT NULL,
    applicationtype character varying,
    identifier character varying,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    metadata jsonb
);


ALTER TABLE m_cerebrum_applications OWNER TO cerebralcortex;

--
-- TOC entry 193 (class 1259 OID 16465)
-- Name: m_cerebrum_applications_id_seq; Type: SEQUENCE; Schema: public; Owner: cerebralcortex
--

CREATE SEQUENCE m_cerebrum_applications_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE m_cerebrum_applications_id_seq OWNER TO cerebralcortex;

--
-- TOC entry 2272 (class 0 OID 0)
-- Dependencies: 193
-- Name: m_cerebrum_applications_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cerebralcortex
--

ALTER SEQUENCE m_cerebrum_applications_id_seq OWNED BY m_cerebrum_applications.id;


--
-- TOC entry 194 (class 1259 OID 16467)
-- Name: m_cerebrum_platform_apps; Type: TABLE; Schema: public; Owner: cerebralcortex
--

CREATE TABLE m_cerebrum_platform_apps (
    id integer NOT NULL,
    platformapptype character varying,
    identifier character varying,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    metadata jsonb
);


ALTER TABLE m_cerebrum_platform_apps OWNER TO cerebralcortex;

--
-- TOC entry 195 (class 1259 OID 16473)
-- Name: m_cerebrum_platform_apps_id_seq; Type: SEQUENCE; Schema: public; Owner: cerebralcortex
--

CREATE SEQUENCE m_cerebrum_platform_apps_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE m_cerebrum_platform_apps_id_seq OWNER TO cerebralcortex;

--
-- TOC entry 2273 (class 0 OID 0)
-- Dependencies: 195
-- Name: m_cerebrum_platform_apps_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cerebralcortex
--

ALTER SEQUENCE m_cerebrum_platform_apps_id_seq OWNED BY m_cerebrum_platform_apps.id;


--
-- TOC entry 196 (class 1259 OID 16475)
-- Name: m_cerebrum_platforms; Type: TABLE; Schema: public; Owner: cerebralcortex
--

CREATE TABLE m_cerebrum_platforms (
    id integer NOT NULL,
    platformtype character varying,
    identifier character varying,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    metadata jsonb
);


ALTER TABLE m_cerebrum_platforms OWNER TO cerebralcortex;

--
-- TOC entry 197 (class 1259 OID 16481)
-- Name: m_cerebrum_platforms_id_seq; Type: SEQUENCE; Schema: public; Owner: cerebralcortex
--

CREATE SEQUENCE m_cerebrum_platforms_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE m_cerebrum_platforms_id_seq OWNER TO cerebralcortex;

--
-- TOC entry 2274 (class 0 OID 0)
-- Dependencies: 197
-- Name: m_cerebrum_platforms_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cerebralcortex
--

ALTER SEQUENCE m_cerebrum_platforms_id_seq OWNED BY m_cerebrum_platforms.id;


--
-- TOC entry 198 (class 1259 OID 16483)
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
-- TOC entry 199 (class 1259 OID 16489)
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
-- TOC entry 200 (class 1259 OID 16495)
-- Name: oauth_refresh_token; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE oauth_refresh_token (
    token_id character varying(256),
    token bytea,
    authentication bytea
);


ALTER TABLE oauth_refresh_token OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16501)
-- Name: participant_studies; Type: TABLE; Schema: public; Owner: cerebralcortex
--

CREATE TABLE participant_studies (
    id integer NOT NULL,
    participant_id uuid,
    study_id integer,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE participant_studies OWNER TO cerebralcortex;

--
-- TOC entry 202 (class 1259 OID 16504)
-- Name: participant_studies_id_seq; Type: SEQUENCE; Schema: public; Owner: cerebralcortex
--

CREATE SEQUENCE participant_studies_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE participant_studies_id_seq OWNER TO cerebralcortex;

--
-- TOC entry 2275 (class 0 OID 0)
-- Dependencies: 202
-- Name: participant_studies_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cerebralcortex
--

ALTER SEQUENCE participant_studies_id_seq OWNED BY participant_studies.id;


--
-- TOC entry 203 (class 1259 OID 16506)
-- Name: participants; Type: TABLE; Schema: public; Owner: cerebralcortex
--

CREATE TABLE participants (
    id uuid DEFAULT uuid_generate_v4() NOT NULL,
    identifier character varying,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE participants OWNER TO cerebralcortex;

--
-- TOC entry 204 (class 1259 OID 16513)
-- Name: schema_migrations; Type: TABLE; Schema: public; Owner: cerebralcortex
--

CREATE TABLE schema_migrations (
    version character varying NOT NULL
);


ALTER TABLE schema_migrations OWNER TO cerebralcortex;

--
-- TOC entry 205 (class 1259 OID 16519)
-- Name: studies; Type: TABLE; Schema: public; Owner: cerebralcortex
--

CREATE TABLE studies (
    id integer NOT NULL,
    identifier character varying,
    name character varying,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE studies OWNER TO cerebralcortex;

--
-- TOC entry 206 (class 1259 OID 16525)
-- Name: studies_id_seq; Type: SEQUENCE; Schema: public; Owner: cerebralcortex
--

CREATE SEQUENCE studies_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE studies_id_seq OWNER TO cerebralcortex;

--
-- TOC entry 2276 (class 0 OID 0)
-- Dependencies: 206
-- Name: studies_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cerebralcortex
--

ALTER SEQUENCE studies_id_seq OWNED BY studies.id;


--
-- TOC entry 2083 (class 2604 OID 16527)
-- Name: id; Type: DEFAULT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY active_admin_comments ALTER COLUMN id SET DEFAULT nextval('active_admin_comments_id_seq'::regclass);


--
-- TOC entry 2087 (class 2604 OID 16528)
-- Name: id; Type: DEFAULT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY admin_users ALTER COLUMN id SET DEFAULT nextval('admin_users_id_seq'::regclass);


--
-- TOC entry 2088 (class 2604 OID 16529)
-- Name: id; Type: DEFAULT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY datapoints ALTER COLUMN id SET DEFAULT nextval('datapoints_id_seq'::regclass);


--
-- TOC entry 2089 (class 2604 OID 16530)
-- Name: id; Type: DEFAULT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY datasources ALTER COLUMN id SET DEFAULT nextval('datasources_id_seq'::regclass);


--
-- TOC entry 2090 (class 2604 OID 16531)
-- Name: id; Type: DEFAULT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY datastreams ALTER COLUMN id SET DEFAULT nextval('datastreams_id_seq'::regclass);


--
-- TOC entry 2091 (class 2604 OID 16532)
-- Name: id; Type: DEFAULT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY m_cerebrum_applications ALTER COLUMN id SET DEFAULT nextval('m_cerebrum_applications_id_seq'::regclass);


--
-- TOC entry 2092 (class 2604 OID 16533)
-- Name: id; Type: DEFAULT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY m_cerebrum_platform_apps ALTER COLUMN id SET DEFAULT nextval('m_cerebrum_platform_apps_id_seq'::regclass);


--
-- TOC entry 2093 (class 2604 OID 16534)
-- Name: id; Type: DEFAULT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY m_cerebrum_platforms ALTER COLUMN id SET DEFAULT nextval('m_cerebrum_platforms_id_seq'::regclass);


--
-- TOC entry 2094 (class 2604 OID 16535)
-- Name: id; Type: DEFAULT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY participant_studies ALTER COLUMN id SET DEFAULT nextval('participant_studies_id_seq'::regclass);


--
-- TOC entry 2096 (class 2604 OID 16536)
-- Name: id; Type: DEFAULT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY studies ALTER COLUMN id SET DEFAULT nextval('studies_id_seq'::regclass);


--
-- TOC entry 2098 (class 2606 OID 16539)
-- Name: active_admin_comments_pkey; Type: CONSTRAINT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY active_admin_comments
    ADD CONSTRAINT active_admin_comments_pkey PRIMARY KEY (id);


--
-- TOC entry 2103 (class 2606 OID 16541)
-- Name: admin_users_pkey; Type: CONSTRAINT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY admin_users
    ADD CONSTRAINT admin_users_pkey PRIMARY KEY (id);


--
-- TOC entry 2107 (class 2606 OID 16543)
-- Name: datapoints_pkey; Type: CONSTRAINT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY datapoints
    ADD CONSTRAINT datapoints_pkey PRIMARY KEY (id);


--
-- TOC entry 2110 (class 2606 OID 16545)
-- Name: datasources_pkey; Type: CONSTRAINT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY datasources
    ADD CONSTRAINT datasources_pkey PRIMARY KEY (id);


--
-- TOC entry 2116 (class 2606 OID 16547)
-- Name: datastreams_pkey; Type: CONSTRAINT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY datastreams
    ADD CONSTRAINT datastreams_pkey PRIMARY KEY (id);


--
-- TOC entry 2120 (class 2606 OID 16549)
-- Name: m_cerebrum_applications_pkey; Type: CONSTRAINT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY m_cerebrum_applications
    ADD CONSTRAINT m_cerebrum_applications_pkey PRIMARY KEY (id);


--
-- TOC entry 2122 (class 2606 OID 16551)
-- Name: m_cerebrum_platform_apps_pkey; Type: CONSTRAINT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY m_cerebrum_platform_apps
    ADD CONSTRAINT m_cerebrum_platform_apps_pkey PRIMARY KEY (id);


--
-- TOC entry 2124 (class 2606 OID 16553)
-- Name: m_cerebrum_platforms_pkey; Type: CONSTRAINT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY m_cerebrum_platforms
    ADD CONSTRAINT m_cerebrum_platforms_pkey PRIMARY KEY (id);


--
-- TOC entry 2126 (class 2606 OID 16555)
-- Name: oauth_client_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY oauth_client_details
    ADD CONSTRAINT oauth_client_details_pkey PRIMARY KEY (client_id);


--
-- TOC entry 2130 (class 2606 OID 16557)
-- Name: participant_studies_pkey; Type: CONSTRAINT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY participant_studies
    ADD CONSTRAINT participant_studies_pkey PRIMARY KEY (id);


--
-- TOC entry 2132 (class 2606 OID 16559)
-- Name: participants_pkey; Type: CONSTRAINT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY participants
    ADD CONSTRAINT participants_pkey PRIMARY KEY (id);


--
-- TOC entry 2135 (class 2606 OID 16561)
-- Name: studies_pkey; Type: CONSTRAINT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY studies
    ADD CONSTRAINT studies_pkey PRIMARY KEY (id);


--
-- TOC entry 2111 (class 1259 OID 16620)
-- Name: datasourcetype; Type: INDEX; Schema: public; Owner: cerebralcortex
--

CREATE INDEX datasourcetype ON datasources USING btree (datasourcetype);


--
-- TOC entry 2099 (class 1259 OID 16562)
-- Name: index_active_admin_comments_on_author_type_and_author_id; Type: INDEX; Schema: public; Owner: cerebralcortex
--

CREATE INDEX index_active_admin_comments_on_author_type_and_author_id ON active_admin_comments USING btree (author_type, author_id);


--
-- TOC entry 2100 (class 1259 OID 16563)
-- Name: index_active_admin_comments_on_namespace; Type: INDEX; Schema: public; Owner: cerebralcortex
--

CREATE INDEX index_active_admin_comments_on_namespace ON active_admin_comments USING btree (namespace);


--
-- TOC entry 2101 (class 1259 OID 16564)
-- Name: index_active_admin_comments_on_resource_type_and_resource_id; Type: INDEX; Schema: public; Owner: cerebralcortex
--

CREATE INDEX index_active_admin_comments_on_resource_type_and_resource_id ON active_admin_comments USING btree (resource_type, resource_id);


--
-- TOC entry 2104 (class 1259 OID 16565)
-- Name: index_admin_users_on_email; Type: INDEX; Schema: public; Owner: cerebralcortex
--

CREATE UNIQUE INDEX index_admin_users_on_email ON admin_users USING btree (email);


--
-- TOC entry 2105 (class 1259 OID 16566)
-- Name: index_admin_users_on_reset_password_token; Type: INDEX; Schema: public; Owner: cerebralcortex
--

CREATE UNIQUE INDEX index_admin_users_on_reset_password_token ON admin_users USING btree (reset_password_token);


--
-- TOC entry 2108 (class 1259 OID 16567)
-- Name: index_datapoints_on_datastream_id; Type: INDEX; Schema: public; Owner: cerebralcortex
--

CREATE INDEX index_datapoints_on_datastream_id ON datapoints USING btree (datastream_id);


--
-- TOC entry 2112 (class 1259 OID 16568)
-- Name: index_datasources_on_m_cerebrum_application_id; Type: INDEX; Schema: public; Owner: cerebralcortex
--

CREATE INDEX index_datasources_on_m_cerebrum_application_id ON datasources USING btree (m_cerebrum_application_id);


--
-- TOC entry 2113 (class 1259 OID 16569)
-- Name: index_datasources_on_m_cerebrum_platform_app_id; Type: INDEX; Schema: public; Owner: cerebralcortex
--

CREATE INDEX index_datasources_on_m_cerebrum_platform_app_id ON datasources USING btree (m_cerebrum_platform_app_id);


--
-- TOC entry 2114 (class 1259 OID 16570)
-- Name: index_datasources_on_m_cerebrum_platform_id; Type: INDEX; Schema: public; Owner: cerebralcortex
--

CREATE INDEX index_datasources_on_m_cerebrum_platform_id ON datasources USING btree (m_cerebrum_platform_id);


--
-- TOC entry 2117 (class 1259 OID 16571)
-- Name: index_datastreams_on_datasource_id; Type: INDEX; Schema: public; Owner: cerebralcortex
--

CREATE INDEX index_datastreams_on_datasource_id ON datastreams USING btree (datasource_id);


--
-- TOC entry 2118 (class 1259 OID 16572)
-- Name: index_datastreams_on_participant_id; Type: INDEX; Schema: public; Owner: cerebralcortex
--

CREATE INDEX index_datastreams_on_participant_id ON datastreams USING btree (participant_id);


--
-- TOC entry 2127 (class 1259 OID 16573)
-- Name: index_participant_studies_on_participant_id; Type: INDEX; Schema: public; Owner: cerebralcortex
--

CREATE INDEX index_participant_studies_on_participant_id ON participant_studies USING btree (participant_id);


--
-- TOC entry 2128 (class 1259 OID 16574)
-- Name: index_participant_studies_on_study_id; Type: INDEX; Schema: public; Owner: cerebralcortex
--

CREATE INDEX index_participant_studies_on_study_id ON participant_studies USING btree (study_id);


--
-- TOC entry 2133 (class 1259 OID 16575)
-- Name: unique_schema_migrations; Type: INDEX; Schema: public; Owner: cerebralcortex
--

CREATE UNIQUE INDEX unique_schema_migrations ON schema_migrations USING btree (version);


--
-- TOC entry 2137 (class 2606 OID 16576)
-- Name: fk_rails_03f1d65a1a; Type: FK CONSTRAINT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY datasources
    ADD CONSTRAINT fk_rails_03f1d65a1a FOREIGN KEY (m_cerebrum_application_id) REFERENCES m_cerebrum_applications(id);


--
-- TOC entry 2140 (class 2606 OID 16581)
-- Name: fk_rails_2c9d94efa2; Type: FK CONSTRAINT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY datastreams
    ADD CONSTRAINT fk_rails_2c9d94efa2 FOREIGN KEY (participant_id) REFERENCES participants(id);


--
-- TOC entry 2138 (class 2606 OID 16586)
-- Name: fk_rails_6cd0b6fef5; Type: FK CONSTRAINT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY datasources
    ADD CONSTRAINT fk_rails_6cd0b6fef5 FOREIGN KEY (m_cerebrum_platform_id) REFERENCES m_cerebrum_platforms(id);


--
-- TOC entry 2141 (class 2606 OID 16591)
-- Name: fk_rails_7fdf2c6e3e; Type: FK CONSTRAINT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY datastreams
    ADD CONSTRAINT fk_rails_7fdf2c6e3e FOREIGN KEY (datasource_id) REFERENCES datasources(id);


--
-- TOC entry 2142 (class 2606 OID 16596)
-- Name: fk_rails_a427dc1be7; Type: FK CONSTRAINT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY participant_studies
    ADD CONSTRAINT fk_rails_a427dc1be7 FOREIGN KEY (participant_id) REFERENCES participants(id);


--
-- TOC entry 2143 (class 2606 OID 16601)
-- Name: fk_rails_acbc1a23cd; Type: FK CONSTRAINT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY participant_studies
    ADD CONSTRAINT fk_rails_acbc1a23cd FOREIGN KEY (study_id) REFERENCES studies(id);


--
-- TOC entry 2139 (class 2606 OID 16606)
-- Name: fk_rails_bcd516299c; Type: FK CONSTRAINT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY datasources
    ADD CONSTRAINT fk_rails_bcd516299c FOREIGN KEY (m_cerebrum_platform_app_id) REFERENCES m_cerebrum_platform_apps(id);


--
-- TOC entry 2136 (class 2606 OID 16611)
-- Name: fk_rails_fc298b36ca; Type: FK CONSTRAINT; Schema: public; Owner: cerebralcortex
--

ALTER TABLE ONLY datapoints
    ADD CONSTRAINT fk_rails_fc298b36ca FOREIGN KEY (datastream_id) REFERENCES datastreams(id);


--
-- TOC entry 2264 (class 0 OID 0)
-- Dependencies: 8
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;



