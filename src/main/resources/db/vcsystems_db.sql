DROP DATABASE vcsystems_db;

CREATE DATABASE IF NOT EXISTS vcsystems_db;
USE vcsystems_db;
CREATE TABLE gerente (
                         id_gerente BIGINT AUTO_INCREMENT PRIMARY KEY,
                         id_usuario BIGINT NOT NULL,
                         area VARCHAR(255),
                         telefono VARCHAR(50),
                         creado_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                         actualizado_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
                         FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);
CREATE TABLE tecnico (
                         id_tecnico BIGINT AUTO_INCREMENT PRIMARY KEY,
                         id_usuario BIGINT NOT NULL,
                         especialidad VARCHAR(255),
                         telefono VARCHAR(50),
                         creado_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                         actualizado_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
                         FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);
CREATE TABLE usuario (
                         id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
                         rol ENUM('GERENTE', 'CLIENTE', 'TECNICO', 'ADMIN') NOT NULL,
                         nombre VARCHAR(255) NOT NULL,
                         correo VARCHAR(255) NOT NULL UNIQUE,
                         contrasena VARCHAR(255) NOT NULL,
                         creado_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                         actualizado_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);


CREATE TABLE cliente (
                         id_cliente BIGINT AUTO_INCREMENT PRIMARY KEY,
                         id_usuario BIGINT NOT NULL,
                         nombre_empresa VARCHAR(255) NOT NULL,
                         direccion_empresa VARCHAR(255),
                         creado_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                         actualizado_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
                         FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);


CREATE TABLE proveedor (
                           id_proveedor BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(255) NOT NULL,
                           telefono VARCHAR(255),
                           contacto VARCHAR(255),
                           direccion VARCHAR(255),
                           creado_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                           actualizado_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);


CREATE TABLE diccionario_fallas (
                                    id_falla BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    codigo_falla VARCHAR(255) NOT NULL UNIQUE,
                                    descripcion VARCHAR(255),
                                    creado_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                    actualizado_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);


CREATE TABLE incidencia (
                            id_incidencia BIGINT AUTO_INCREMENT PRIMARY KEY,
                            id_cliente BIGINT NOT NULL,
                            id_tecnico BIGINT,
                            id_falla BIGINT,
                            descripcion VARCHAR(255) NOT NULL,
                            estado ENUM('PENDIENTE', 'ASIGNADA', 'EN_PROCESO', 'RESUELTA', 'CERRADA') NOT NULL,
                            prioridad ENUM('BAJA', 'MEDIA', 'ALTA') DEFAULT 'MEDIA',
                            fecha_creacion DATETIME(6),
                            fecha_asignacion DATETIME(6),
                            fecha_resolucion DATETIME(6),
                            creado_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                            actualizado_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
                            FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
                            FOREIGN KEY (id_tecnico) REFERENCES usuario(id_usuario),
                            FOREIGN KEY (id_falla) REFERENCES diccionario_fallas(id_falla)
);


CREATE TABLE solicitud_repuesto (
                                    id_solicitud BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    id_incidencia BIGINT NOT NULL,
                                    id_proveedor BIGINT,
                                    id_tecnico BIGINT NOT NULL,
                                    justificacion VARCHAR(255) NOT NULL,
                                    estado ENUM('PENDIENTE', 'ENVIADO', 'RECIBIDO') NOT NULL DEFAULT 'PENDIENTE',
                                    creado_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                    actualizado_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
                                    FOREIGN KEY (id_incidencia) REFERENCES incidencia(id_incidencia),
                                    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor),
                                    FOREIGN KEY (id_tecnico) REFERENCES usuario(id_usuario)
);

-- proveedores de prueba
INSERT INTO proveedor (nombre, telefono, contacto, direccion, creado_at, actualizado_at) VALUES
                                                                                             ('Repuestos SAC', '+51987654321', 'contacto@repuestos.com', 'Av. Industrial 456, Lima', NOW(6), NOW(6)),
                                                                                             ('Soluciones Industriales', '+51912345678', 'ventas@soluciones.com', 'Jr. Comercio 789, Lima', NOW(6), NOW(6));

-- diccionario de fallas
INSERT INTO diccionario_fallas (codigo_falla, descripcion, creado_at, actualizado_at) VALUES
                                                                                          ('F001', 'Fallo en el compresor', NOW(6), NOW(6)),
                                                                                          ('F002', 'Fuga de refrigerante', NOW(6), NOW(6)),
                                                                                          ('F003', 'Problema eléctrico', NOW(6), NOW(6)),
                                                                                          ('F004', 'Sobrecalentamiento', NOW(6), NOW(6)),
                                                                                          ('F005', 'Ruido anormal', NOW(6), NOW(6));

-- pruebitas
SHOW TABLES;
SELECT 'USUARIOS:' as tabla;
SELECT id_usuario, rol, nombre, correo FROM usuario;
SELECT 'CLIENTES:' as tabla;
SELECT * FROM cliente;
SELECT 'INCIDENCIAS:' as tabla;
SELECT id_incidencia, descripcion, estado, prioridad FROM incidencia;

SELECT correo, contrasena FROM usuario;
SELECT * FROM cliente;
