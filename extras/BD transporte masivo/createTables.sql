-- Created by Vertabelo (http://vertabelo.com)
-- Modificado manualmente

-- tables
-- Table: administrador
CREATE TABLE administrador (
    administrador_empleado_id varchar  NOT NULL,
    CONSTRAINT administrador_pk PRIMARY KEY (administrador_empleado_id)
);

CREATE TABLE dir_operativo (
    dir_operativo_empleado_id varchar  NOT NULL,
    CONSTRAINT dir_operativo_pk PRIMARY KEY (dir_operativo_empleado_id)
);

-- Table: auxiliar_serv_cliente
CREATE TABLE aux_serv_cliente (
    aux_serv_cliente_empleado_id varchar  NOT NULL,
    aux_serv_cliente_estacion int  NOT NULL,
    CONSTRAINT auxiliar_cliente_pk PRIMARY KEY (aux_serv_cliente_empleado_id)
);

-- Table: tipo_bus
CREATE TABLE tipo_bus (
    tpbus_id int  NOT NULL,
    tpbus_nombre varchar(50)  NOT NULL,
    CONSTRAINT tpbus_pk PRIMARY KEY (tpbus_id)
);

-- Table: bus
CREATE TABLE bus (
    bus_placa varchar(7) NOT NULL,
    bus_tipo int NOT NULL,
    bus_ruta varchar(5) NOT NULL,
    CONSTRAINT bus_pk PRIMARY KEY (bus_placa)
);

-- Table: conductor
CREATE TABLE conductor (
    conductor_empleado_id varchar NOT NULL,
    conductor_bus varchar(7) NOT NULL,
	CONSTRAINT conductor_pk PRIMARY KEY (conductor_empleado_id)
);

-- Table: gerente
CREATE TABLE gerente (
    gerente_empleado_id varchar NOT NULL,
	CONSTRAINT gerente_pk PRIMARY KEY (gerente_empleado_id)
);

-- Table: aborda
CREATE TABLE aborda (
    aborda_tarjeta int  NOT NULL,
    aborda_ruta varchar  NOT NULL,
    aborda_fecha_hora timestamp  NOT NULL,
    CONSTRAINT aborda_pk PRIMARY KEY (aborda_tarjeta,aborda_fecha_hora)
);

-- Table: empleado
CREATE TABLE empleado (
    empleado_id varchar(10) NOT NULL,
    empleado_nombre varchar(50) NOT NULL,
    empleado_telefono varchar(20) NOT NULL,
	empleado_sueldo varchar(20) NOT NULL,
    empleado_rol int NOT NULL,
    CONSTRAINT empleado_pk PRIMARY KEY (empleado_id)
);

-- Table: estacion
CREATE TABLE estacion (
    estacion_id serial NOT NULL,
    estacion_nombre varchar(50) NOT NULL,
    estacion_ubicacion varchar(50) NOT NULL,
	estacion_administrador varchar NOT NULL,
    CONSTRAINT estacion_pk PRIMARY KEY (estacion_id)
);

-- Table: pqrs
CREATE TABLE pqrs (
    pqrs_ticket serial  NOT NULL,
	pqrs_motivo int NOT NULL,
    pqrs_descrip varchar(500)  NOT NULL,
    pqrs_fecha_inicio date  NOT NULL,
    pqrs_estado varchar(15)  NOT NULL,
    pqrs_usuario varchar(10)  NOT NULL,
    pqrs_estacion int NOT NULL,
    pqrs_resuelta_por varchar(10),
    pqrs_fecha_resuelta date,
    pqrs_respuesta varchar(500),
    CONSTRAINT pqrs_pk PRIMARY KEY (pqrs_ticket)
);

-- Table: tipo_motivo
CREATE TABLE tipo_motivo (
    tpmotivo_id serial  NOT NULL,
    tpmotivo_nombre varchar(50)  NOT NULL,
    CONSTRAINT tpmotivo_pk PRIMARY KEY (tpmotivo_id)
);



--Table: pqrs_medida
CREATE TABLE pqrs_medida (
	pqrs_medida_id serial NOT NULL,
	pqrs_medida_ticket int NOT NULL,
	pqrs_medida_descrip varchar(500) NOT NULL,
	pqrs_medida_estado varchar(7) NOT NULL,
	CONSTRAINT pqrs_medida_pk PRIMARY KEY (pqrs_medida_id, pqrs_medida_ticket)
);

-- Table: recarga
CREATE TABLE recarga (
    recarga_tarjeta int  NOT NULL,
    recarga_fecha_hora timestamp  NOT NULL,
    recarga_estacion int  NOT NULL,
    recarga_valor varchar(10)  NOT NULL,
    CONSTRAINT recarga_pk PRIMARY KEY (recarga_tarjeta,recarga_fecha_hora)
);

-- Table: ruta
CREATE TABLE ruta (
    ruta_id varchar(5) NOT NULL,
    ruta_descrip varchar(150)  NOT NULL,
    CONSTRAINT ruta_pk PRIMARY KEY (ruta_id)
);

-- Table: tarjeta
CREATE TABLE tarjeta (
    tarjeta_pin serial  NOT NULL,
    tarjeta_saldo varchar(10)  NOT NULL,
    tarjeta_estado boolean  NOT NULL,
    tarjeta_usuario varchar(10)  NOT NULL,
    CONSTRAINT tarjeta_pk PRIMARY KEY (tarjeta_pin)
);

-- Table: transita
CREATE TABLE transita (
    transita_ruta varchar(5) NOT NULL,
    transita_recorrido varchar  NOT NULL,
    CONSTRAINT transita_pk PRIMARY KEY (transita_ruta,transita_recorrido)
);

-- Table: turno
CREATE TABLE turno (
    turno_id serial  NOT NULL,
    turno_dia_inicio varchar(10)  NOT NULL,
    turno_dia_fin varchar(10),
    turno_hora_inicio varchar(10)  NOT NULL,
    turno_hora_fin varchar(10)  NOT NULL,
    CONSTRAINT turno_pk PRIMARY KEY (turno_id)
);

-- Table: turno_asignado
CREATE TABLE turno_asignado (
    tasignado_turno int  NOT NULL,
    tasignado_conductor varchar  NOT NULL,
    CONSTRAINT turno_asignado_pk PRIMARY KEY (tasignado_turno,tasignado_conductor)
);

-- Table: usuario
CREATE TABLE usuario (
    usuario_id varchar(10)  NOT NULL,
    usuario_nombre varchar(50) NOT NULL,
    usuario_telefono varchar(20) NOT NULL,
    CONSTRAINT usuario_pk PRIMARY KEY (usuario_id)
);

-- foreign keys
-- Reference: administrador_empleado (table: administrador)
ALTER TABLE administrador ADD CONSTRAINT administrador_empleado
    FOREIGN KEY (administrador_empleado_id)
    REFERENCES empleado (empleado_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: aux_serv_cliente_estacion (table: auxiliar_cliente)
ALTER TABLE aux_serv_cliente ADD CONSTRAINT aux_serv_cliente_estacion
    FOREIGN KEY (aux_serv_cliente_estacion)
    REFERENCES estacion (estacion_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;


-- Reference: aux_serv_cliente_empleado_id (table: auxiliar_cliente)
ALTER TABLE aux_serv_cliente ADD CONSTRAINT aux_serv_cliente_empleado_id
    FOREIGN KEY (aux_serv_cliente_empleado_id)
    REFERENCES empleado (empleado_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: bus_ruta (table: bus)
ALTER TABLE bus ADD CONSTRAINT bus_ruta
    FOREIGN KEY (bus_ruta)
    REFERENCES ruta (ruta_id) 
    ON UPDATE  CASCADE 
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: estacion_administrador (table: estacion)
ALTER TABLE estacion ADD CONSTRAINT estacion_administrador
    FOREIGN KEY (estacion_administrador)
    REFERENCES administrador (administrador_empleado_id) 
    ON UPDATE  CASCADE 
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;



-- Reference: bus_tipo (table: bus)
ALTER TABLE bus ADD CONSTRAINT bus_tipo
    FOREIGN KEY (bus_tipo)
    REFERENCES tipo_bus (tpbus_id) 
    ON UPDATE  CASCADE 
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: pqrs_motivo (table: tipo_motivo)
ALTER TABLE pqrs ADD CONSTRAINT pqrs_motivo
    FOREIGN KEY (pqrs_motivo)
    REFERENCES tipo_motivo (tpmotivo_id) 
    ON UPDATE  CASCADE 
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: conductor_bus (table: conductor)
ALTER TABLE conductor ADD CONSTRAINT conductor_bus
    FOREIGN KEY (conductor_bus)
    REFERENCES bus (bus_placa)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: conductor_empleado (table: conductor)
ALTER TABLE conductor ADD CONSTRAINT conductor_empleado
    FOREIGN KEY (conductor_empleado_id)
    REFERENCES empleado (empleado_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: gerente_empleado (table: gerente)
ALTER TABLE gerente ADD CONSTRAINT gerente_empleado
    FOREIGN KEY (gerente_empleado_id)
    REFERENCES empleado (empleado_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: aborda_ruta (table: aborda)
ALTER TABLE aborda ADD CONSTRAINT aborda_ruta
    FOREIGN KEY (aborda_ruta)
    REFERENCES ruta (ruta_id) 
    ON UPDATE  CASCADE 
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: aborda_tarjeta (table: aborda)
ALTER TABLE aborda ADD CONSTRAINT aborda_tarjeta
    FOREIGN KEY (aborda_tarjeta)
    REFERENCES tarjeta (tarjeta_pin) 
    ON UPDATE  CASCADE 
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: dir_operativo_empleado (table: dir_operativo)
ALTER TABLE dir_operativo ADD CONSTRAINT dir_operativo_empleado_id
    FOREIGN KEY (dir_operativo_empleado_id)
    REFERENCES empleado (empleado_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: pqrs_administrador (table: pqrs)
ALTER TABLE pqrs ADD CONSTRAINT pqrs_administrador
    FOREIGN KEY (pqrs_resuelta_por)
    REFERENCES administrador (administrador_empleado_id) 
    ON UPDATE  CASCADE 
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: pqrs_estacion (table: pqrs)
ALTER TABLE pqrs ADD CONSTRAINT pqrs_estacion
    FOREIGN KEY (pqrs_estacion)
    REFERENCES estacion (estacion_id) 
    ON UPDATE  CASCADE 
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: pqrs_usuario (table: pqrs)
ALTER TABLE pqrs ADD CONSTRAINT pqrs_usuario
    FOREIGN KEY (pqrs_usuario)
    REFERENCES usuario (usuario_id) 
    ON UPDATE  CASCADE 
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: recarga_estacion (table: recarga)
ALTER TABLE recarga ADD CONSTRAINT recarga_estacion
    FOREIGN KEY (recarga_estacion)
    REFERENCES estacion (estacion_id) 
    ON UPDATE  CASCADE 
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: recarga_tarjeta (table: recarga)
ALTER TABLE recarga ADD CONSTRAINT recarga_tarjeta
    FOREIGN KEY (recarga_tarjeta)
    REFERENCES tarjeta (tarjeta_pin) 
    ON UPDATE  CASCADE 
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: tarjeta_usuario (table: tarjeta)
ALTER TABLE tarjeta ADD CONSTRAINT tarjeta_usuario
    FOREIGN KEY (tarjeta_usuario)
    REFERENCES usuario (usuario_id) 
    ON UPDATE  CASCADE 
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: transita_ruta (table: transita)
ALTER TABLE transita ADD CONSTRAINT transita_ruta
    FOREIGN KEY (transita_ruta)
    REFERENCES ruta (ruta_id) 
    ON UPDATE  CASCADE 
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: turno_asignado_conductor (table: turno_asignado)
ALTER TABLE turno_asignado ADD CONSTRAINT turno_asignado_conductor
    FOREIGN KEY (tasignado_conductor)
    REFERENCES conductor (conductor_empleado_id) 
    ON UPDATE  CASCADE 
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: turno_asignado_turno (table: turno_asignado)
ALTER TABLE turno_asignado ADD CONSTRAINT turno_asignado_turno
    FOREIGN KEY (tasignado_turno)
    REFERENCES turno (turno_id) 
    ON UPDATE  CASCADE 
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: pqrs_medida_ticket (table: pqrs_medida)
ALTER TABLE pqrs_medida ADD CONSTRAINT pqrs_medida_ticket
    FOREIGN KEY (pqrs_medida_ticket)
    REFERENCES pqrs (pqrs_ticket) 
    ON UPDATE  CASCADE 
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.