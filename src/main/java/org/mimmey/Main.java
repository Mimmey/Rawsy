package org.mimmey;

//TODO: images, zips
//TODO: авторасчет длительности трека
//TODO: авторасчет рейтинга
//TODO: проверить, мапятся ли автоматически в треке списки жанров, настроений и комментов
//TODO: sync
//TODO: выход во время удаления
//TODO: мапперы

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}