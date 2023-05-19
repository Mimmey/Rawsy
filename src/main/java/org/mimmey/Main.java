package org.mimmey;

//TODO: images, zips, audios
//TODO: авторасчет длительности трека
//TODO: авторасчет рейтинга
//TODO: indices db
//TODO: зацикленность — добавить "все"
//TODO: есть ли вокал — добавить "все"
//TODO: проверить, мапятся ли автоматически в треке списки жанров, настроений и комментов
//TODO: sync
//TODO: выход во время удаления
//TODO: required в описании dto

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}