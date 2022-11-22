
CREATE TABLE `deportes`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(50) NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE `usuarios`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(50) NOT NULL,
    `email` VARCHAR(200) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `id_rol` INT NOT NULL,
    `saldo` DECIMAL(8, 2) NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE `equipos`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(200) NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE `eventos`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `id_deporte` INT NOT NULL,
    `fecha_inicio` TIMESTAMP NOT NULL,
    `fecha_fin` TIMESTAMP NOT NULL,
    `nombre` VARCHAR(100) NOT NULL,
    `id_equipo_ganador` INT,
    `resultado` VARCHAR(200),
    PRIMARY KEY(`id`)
);

CREATE TABLE `participantes`(
    `id_equipo` INT NOT NULL,
    `id_evento` INT NOT NULL,
    PRIMARY KEY(`id_equipo`, `id_evento`)
);

CREATE TABLE `apuestas`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `id_evento` INT NOT NULL,
    `id_usuario` INT NOT NULL,
    `prediccion` VARCHAR(200) NOT NULL,
    `cuota` DECIMAL(8, 2) NOT NULL,
    `cantidad` DECIMAL(8, 2) NOT NULL,
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
    `apuestas` ADD FOREIGN KEY(`id_evento`) REFERENCES `eventos`(`id`);
ALTER TABLE
    `participantes` ADD FOREIGN KEY(`id_equipo`) REFERENCES `equipos`(`id`);
ALTER TABLE
    `participantes` ADD FOREIGN KEY(`id_evento`) REFERENCES `eventos`(`id`);

