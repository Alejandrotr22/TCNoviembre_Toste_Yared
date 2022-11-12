CREATE TABLE `deportes`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(50) NOT NULL
);
ALTER TABLE
    `deportes` ADD PRIMARY KEY `deportes_id_primary`(`id`);
CREATE TABLE `usuarios`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(50) NOT NULL,
    `email` VARCHAR(200) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `id_rol` INT NOT NULL,
    `saldo` DECIMAL(8, 2) NOT NULL
);
ALTER TABLE
    `usuarios` ADD PRIMARY KEY `usuarios_id_primary`(`id`);
CREATE TABLE `equipos`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(200) NOT NULL
);
ALTER TABLE
    `equipos` ADD PRIMARY KEY `equipos_id_primary`(`id`);
CREATE TABLE `tipos_apuesta`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `descripcion` VARCHAR(200) NOT NULL
);
ALTER TABLE
    `tipos_apuesta` ADD PRIMARY KEY `tipos_apuesta_id_primary`(`id`);
CREATE TABLE `eventos`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `id_deporte` INT NOT NULL,
    `fecha_inicio` TIMESTAMP NOT NULL,
    `fecha_fin` TIMESTAMP NOT NULL,
    `nombre` VARCHAR(100) NOT NULL
);
ALTER TABLE
    `eventos` ADD PRIMARY KEY `eventos_id_primary`(`id`);
CREATE TABLE `participantes`(
    `id_equipo` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `id_evento` INT NOT NULL
);
ALTER TABLE
    `participantes` ADD PRIMARY KEY `participantes_id_equipo_primary`(`id_equipo`);
ALTER TABLE
    `participantes` ADD PRIMARY KEY `participantes_id_evento_primary`(`id_evento`);
CREATE TABLE `apuestas`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `id_evento` INT NOT NULL,
    `id_usuario` INT NOT NULL,
    `id_tipo` INT NOT NULL,
    `resultado` VARCHAR(50) NOT NULL,
    `cuota` DECIMAL(8, 2) NOT NULL,
    `cantidad` DECIMAL(8, 2) NOT NULL,
    `estado` VARCHAR(50) NOT NULL
);
ALTER TABLE
    `apuestas` ADD PRIMARY KEY `apuestas_id_primary`(`id`);
CREATE TABLE `historial_eventos`(
    `id_evento` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `id_equipo_ganador` INT NOT NULL
);
ALTER TABLE
    `historial_eventos` ADD PRIMARY KEY `historial_eventos_id_evento_primary`(`id_evento`);
ALTER TABLE
    `historial_eventos` ADD PRIMARY KEY `historial_eventos_id_equipo_ganador_primary`(`id_equipo_ganador`);
CREATE TABLE `roles`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(25) NOT NULL
);
ALTER TABLE
    `roles` ADD PRIMARY KEY `roles_id_primary`(`id`);
ALTER TABLE
    `eventos` ADD CONSTRAINT `eventos_id_deporte_foreign` FOREIGN KEY(`id_deporte`) REFERENCES `deportes`(`id`);
ALTER TABLE
    `apuestas` ADD CONSTRAINT `apuestas_id_usuario_foreign` FOREIGN KEY(`id_usuario`) REFERENCES `usuarios`(`id`);
ALTER TABLE
    `usuarios` ADD CONSTRAINT `usuarios_id_rol_foreign` FOREIGN KEY(`id_rol`) REFERENCES `roles`(`id`);
ALTER TABLE
    `apuestas` ADD CONSTRAINT `apuestas_id_tipo_foreign` FOREIGN KEY(`id_tipo`) REFERENCES `tipos_apuesta`(`id`);
ALTER TABLE
    `apuestas` ADD CONSTRAINT `apuestas_id_evento_foreign` FOREIGN KEY(`id_evento`) REFERENCES `eventos`(`id`);