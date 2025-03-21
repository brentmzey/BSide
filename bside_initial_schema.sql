-- Enable the uuid-ossp extension to generate UUIDs
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- User Table
CREATE TABLE t_user (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    profile_photo_id UUID,
    CONSTRAINT fk_profile_photo FOREIGN KEY(profile_photo_id) REFERENCES t_profile_photo(id)
);

-- Profile Photo Table
CREATE TABLE t_profile_photo (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL,
    photo_url TEXT NULL,
    photo_image BYTEA NULL, -- Byte array of the image when dumped to HEX
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES t_user(id)
);

-- Voice Recording Table
CREATE TABLE t_voice_recording (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    url TEXT NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES t_user(id)
);

-- Proust Questionnaire Table
CREATE TABLE t_proust_questionnaire (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    questionnaire_identifier TEXT NOT NULL,
    questionnaire_name TEXT NOT NULL
);

-- Questions Table
CREATE TABLE t_question (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    questionnaire_id UUID NOT NULL,
    question TEXT NOT NULL,
    CONSTRAINT fk_questionnaire FOREIGN KEY(questionnaire_id) REFERENCES t_proust_questionnaire(id)
);

-- Proust Response Table
CREATE TABLE t_proust_response (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    questionnaire_id UUID NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_questionnaire FOREIGN KEY(questionnaire_id) REFERENCES t_proust_questionnaire(id),
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES t_user(id)
);

-- Responses Table
CREATE TABLE t_proust_response_text (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    response TEXT NOT NULL
);

-- Join Table for Proust Responses and Responses
CREATE TABLE t_proust_response_text_xref (
    proust_response_id UUID NOT NULL,
    response_id UUID NOT NULL,
    PRIMARY KEY (proust_response_id, response_id),
    CONSTRAINT fk_proust_response FOREIGN KEY(proust_response_id) REFERENCES t_proust_response_text(id),
    CONSTRAINT fk_response FOREIGN KEY(response_id) REFERENCES t_proust_response(id)
);

-- Date Detail Table
CREATE TABLE t_date_detail (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL,
    date BIGINT NOT NULL, -- Unix timestamp in milliseconds
    location TEXT NOT NULL,
    details TEXT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES t_user(id)
);

-- Reflection Table
CREATE TABLE t_reflection (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL,
    date_detail_id UUID NOT NULL,
    reflection TEXT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES t_user(id),
    CONSTRAINT fk_date_detail FOREIGN KEY(date_detail_id) REFERENCES t_date_detail(id)
);