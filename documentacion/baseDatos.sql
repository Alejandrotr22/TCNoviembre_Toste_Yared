<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> feature_CreacionProyectoJPAEntities_6
CREATE TABLE `deportes`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(50) NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE `usuarios`(
<<<<<<< HEAD
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
=======

CREATE TABLE `deportes`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(50) NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE `usuarios`(
    `id` INT NOT NULL AUTO_INCREMENT,
>>>>>>> feature_CasosDeUso_3
=======
    `id` INT NOT NULL AUTO_INCREMENT,
>>>>>>> feature_CreacionProyectoJPAEntities_6
    `nombre` VARCHAR(50) NOT NULL,
    `email` VARCHAR(200) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `id_rol` INT NOT NULL,
<<<<<<< HEAD
<<<<<<< HEAD
    `saldo` DECIMAL(8, 2) NOT NULL
=======
    `saldo` DECIMAL(8, 2) NOT NULL,
    PRIMARY KEY(`id`)
>>>>>>> feature_CreacionProyectoJPAEntities_6
);

CREATE TABLE `equipos`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(200) NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE `tipos_apuesta`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `descripcion` VARCHAR(200) NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE `eventos`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `id_deporte` INT NOT NULL,
    `fecha_inicio` TIMESTAMP NOT NULL,
    `fecha_fin` TIMESTAMP NOT NULL,
    `nombre` VARCHAR(100) NOT NULL,
    `id_equipo_ganador` INT,
    PRIMARY KEY(`id`)
);

CREATE TABLE `participantes`(
    `id_equipo` INT NOT NULL,
    `id_evento` INT NOT NULL,
    PRIMARY KEY(`id_equipo`, `id_evento`)
);

CREATE TABLE `apuestas`(
<<<<<<< HEAD
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
=======
    `saldo` DECIMAL(8, 2) NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE `equipos`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(200) NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE `tipos_apuesta`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `descripcion` VARCHAR(200) NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE `eventos`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `id_deporte` INT NOT NULL,
    `fecha_inicio` TIMESTAMP NOT NULL,
    `fecha_fin` TIMESTAMP NOT NULL,
    `nombre` VARCHAR(100) NOT NULL,
    `id_equipo_ganador` INT,
    PRIMARY KEY(`id`)
);

CREATE TABLE `participantes`(
    `id_equipo` INT NOT NULL,
    `id_evento` INT NOT NULL,
    PRIMARY KEY(`id_equipo`, `id_evento`)
);

CREATE TABLE `apuestas`(
    `id` INT NOT NULL AUTO_INCREMENT,
>>>>>>> feature_CasosDeUso_3
=======
    `id` INT NOT NULL AUTO_INCREMENT,
>>>>>>> feature_CreacionProyectoJPAEntities_6
    `id_evento` INT NOT NULL,
    `id_usuario` INT NOT NULL,
    `id_tipo` INT NOT NULL,
    `resultado` VARCHAR(50) NOT NULL,
    `cuota` DECIMAL(8, 2) NOT NULL,
    `cantidad` DECIMAL(8, 2) NOT NULL,
<<<<<<< HEAD
<<<<<<< HEAD
    `estado` VARCHAR(50) NOT NULL
=======
    `estado` VARCHAR(50) NOT NULL,
    PRIMARY KEY(`id`)
>>>>>>> feature_CreacionProyectoJPAEntities_6
);

CREATE TABLE `roles`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(25) NOT NULL,
    PRIMARY KEY(`id`)
);

ALTER TABLE
    `eventos` ADD FOREIGN KEY(`id_deporte`) REFERENCES `deportes`(`id`);
ALTER TABLE
    `eventos` ADD FOREIGN KEY(`id_equipo_ganador`) REFERENCES `equipos`(`id`);
ALTER TABLE
    `apuestas` ADD FOREIGN KEY(`id_usuario`) REFERENCES `usuarios`(`id`);
ALTER TABLE
    `usuarios` ADD FOREIGN KEY(`id_rol`) REFERENCES `roles`(`id`);
ALTER TABLE
    `apuestas` ADD FOREIGN KEY(`id_tipo`) REFERENCES `tipos_apuesta`(`id`);
ALTER TABLE
    `apuestas` ADD FOREIGN KEY(`id_evento`) REFERENCES `eventos`(`id`);
ALTER TABLE
    `participantes` ADD FOREIGN KEY(`id_equipo`) REFERENCES `equipos`(`id`);
ALTER TABLE
<<<<<<< HEAD
    `apuestas` ADD CONSTRAINT `apuestas_id_evento_foreign` FOREIGN KEY(`id_evento`) REFERENCES `eventos`(`id`);
=======
    `estado` VARCHAR(50) NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE `roles`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(25) NOT NULL,
    PRIMARY KEY(`id`)
);

ALTER TABLE
    `eventos` ADD FOREIGN KEY(`id_deporte`) REFERENCES `deportes`(`id`);
ALTER TABLE
    `eventos` ADD FOREIGN KEY(`id_equipo_ganador`) REFERENCES `equipos`(`id`);
ALTER TABLE
    `apuestas` ADD FOREIGN KEY(`id_usuario`) REFERENCES `usuarios`(`id`);
ALTER TABLE
    `usuarios` ADD FOREIGN KEY(`id_rol`) REFERENCES `roles`(`id`);
ALTER TABLE
    `apuestas` ADD FOREIGN KEY(`id_tipo`) REFERENCES `tipos_apuesta`(`id`);
ALTER TABLE
    `apuestas` ADD FOREIGN KEY(`id_evento`) REFERENCES `eventos`(`id`);
ALTER TABLE
    `participantes` ADD FOREIGN KEY(`id_equipo`) REFERENCES `equipos`(`id`);
ALTER TABLE
    `participantes` ADD FOREIGN KEY(`id_evento`) REFERENCES `eventos`(`id`);

>>>>>>> feature_CasosDeUso_3
=======
    `participantes` ADD FOREIGN KEY(`id_evento`) REFERENCES `eventos`(`id`);

>>>>>>> feature_CreacionProyectoJPAEntities_6
