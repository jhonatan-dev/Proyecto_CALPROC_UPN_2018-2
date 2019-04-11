#Crear una base de datos con este nombre, indicado en application.properties
#Los datos de cada tabla están en import.sql
CREATE DATABASE bdprestamos;
USE bdprestamos;

CREATE TABLE cliente (
  id bigint(20) NOT NULL,
  gastos_fijos_mensuales double NOT NULL,
  ingresos_netos_mensuales double NOT NULL,
  tipo_cliente varchar(255) NOT NULL,
  persona_id bigint(20) NOT NULL
);

CREATE TABLE cuota (
  id bigint(20) NOT NULL,
  amortizacion double NOT NULL,
  cuota_fija double NOT NULL,
  fecha_vencimiento date NOT NULL,
  interes_mensual double NOT NULL,
  numero_cuota int(11) NOT NULL,
  pip double NOT NULL,
  saldo double NOT NULL,
  seguro_del_bien double NOT NULL,
  seguro_desgravamen_mensual double NOT NULL,
  pago_id bigint(20) NULL,
  prestamo_id bigint(20) NOT NULL
);

CREATE TABLE pago (
  id bigint(20) NOT NULL,
  codigo varchar(255) NOT NULL,
  fecha_pago date NOT NULL,
  importe double NOT NULL,
  monto_cuota double NOT NULL,
  monto_mora double NOT NULL,
  usuario_id bigint(20) NOT NULL
);

CREATE TABLE persona (
  id bigint(20) NOT NULL,
  apellidos varchar(255)  NOT NULL,
  dni varchar(8)  NOT NULL,
  fecha_nacimiento date NOT NULL,
  nombres varchar(255)  NOT NULL,
  usuario_id bigint(20) NULL
);

CREATE TABLE prestamo (
  id bigint(20) NOT NULL,
  codigo varchar(19) NOT NULL,
  costo_envio_a_casa double NOT NULL,
  cuota_inicial int(11) NOT NULL,
  dia_de_pago int(11) NOT NULL,
  fecha_desembolso date NOT NULL,
  importe_asegurado double NULL,
  interes_moratorio double NOT NULL,
  monto_a_financiar double NOT NULL,
  numero_cuotas int(11) NOT NULL,
  tasa_seguro_del_bien double NULL,
  tdes double NOT NULL,
  tea double NOT NULL,
  tipo_cuota varchar(255) NOT NULL,
  tipo_de_seguro_desgravamen varchar(255) NULL,
  tipo_del_bien varchar(255) NULL,
  tipo_prestamo varchar(255) NOT NULL,
  tipo_seguro_del_bien varchar(255) NULL,
  cliente_id bigint(20) NOT NULL,
  usuario_id bigint(20) NOT NULL
);

CREATE TABLE rol (
  id bigint(20) NOT NULL,
  nombre varchar(255)  NOT NULL,
  usuario_id bigint(20) NOT NULL
);

CREATE TABLE usuario (
  id bigint(20) NOT NULL,
  clave varchar(60)  NOT NULL,
  habilitado bit(1) NOT NULL,
  nombre_usuario varchar(30) NOT NULL
);

ALTER TABLE cliente
  ADD PRIMARY KEY (id),
  ADD KEY FK_PERSONA_ID (persona_id);

ALTER TABLE cuota
  ADD PRIMARY KEY (id),
  ADD KEY FK_C_PAGO_ID (pago_id),
  ADD KEY FK_C_PRESTAMO_ID (prestamo_id);

ALTER TABLE pago
  ADD PRIMARY KEY (id),
  ADD KEY FK_PG_USUARIO_ID (usuario_id);

ALTER TABLE persona
  ADD PRIMARY KEY (id),
  ADD UNIQUE KEY UK_P_DNI (dni),
  ADD KEY FK_USUARIO_ID (usuario_id);

ALTER TABLE prestamo
  ADD PRIMARY KEY (id),
  ADD KEY FK_P_CLIENTE_ID (cliente_id),
  ADD KEY FK_P_USUARIO_ID (usuario_id);

ALTER TABLE rol
  ADD PRIMARY KEY (id),
  ADD KEY FK_R_USUARIO_ID (usuario_id);

ALTER TABLE usuario
  ADD PRIMARY KEY (id),
  ADD UNIQUE KEY UK_U_NOMBREUSUARIO (nombre_usuario);

ALTER TABLE cliente
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE cuota
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE pago
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE persona
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE prestamo
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE rol
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE usuario
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE cliente
  ADD CONSTRAINT FK_PERSONA_ID FOREIGN KEY (persona_id) REFERENCES persona (id);

ALTER TABLE cuota
  ADD CONSTRAINT FK_C_PAGO_ID FOREIGN KEY (pago_id) REFERENCES pago (id),
  ADD CONSTRAINT FK_C_PRESTAMO_ID FOREIGN KEY (prestamo_id) REFERENCES prestamo (id);

ALTER TABLE pago
  ADD CONSTRAINT FK_PG_USUARIO_ID FOREIGN KEY (usuario_id) REFERENCES usuario (id);

ALTER TABLE persona
  ADD CONSTRAINT FK_USUARIO_ID FOREIGN KEY (usuario_id) REFERENCES usuario (id);

ALTER TABLE prestamo
  ADD CONSTRAINT FK_P_CLIENTE_ID FOREIGN KEY (cliente_id) REFERENCES cliente (id),
  ADD CONSTRAINT FK_P_USUARIO_ID FOREIGN KEY (usuario_id) REFERENCES usuario (id);

ALTER TABLE rol
  ADD CONSTRAINT FK_R_USUARIO_ID FOREIGN KEY (usuario_id) REFERENCES usuario (id);