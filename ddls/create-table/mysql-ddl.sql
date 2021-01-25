CREATE TABLE ENCRYPTED_TRANSACTION (ENCODED_PAYLOAD BLOB NOT NULL, HASH VARBINARY(100) NOT NULL, TIMESTAMP BIGINT, PRIMARY KEY (HASH));
CREATE TABLE ENCRYPTED_RAW_TRANSACTION (ENCRYPTED_KEY BLOB NOT NULL, ENCRYPTED_PAYLOAD BLOB NOT NULL, NONCE BLOB NOT NULL, SENDER BLOB NOT NULL, TIMESTAMP BIGINT, HASH VARBINARY(100) NOT NULL, PRIMARY KEY (HASH));
CREATE TABLE PRIVACY_GROUP(ID BLOB NOT NULL, LOOKUP_ID BLOB NOT NULL, DATA BLOB NOT NULL, TIMESTAMP BIGINT, PRIMARY KEY (ID));
