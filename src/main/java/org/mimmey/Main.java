package org.mimmey;

//TODO: security
//TODO: images, zips, audios
//TODO: методы получения всех типов, жанров и тд  DONE
//TODO: дописать swagger для responsebody везде   DONE
//TODO: оплата корзины
//TODO: авторасчет длительности трека
//TODO: авторасчет рейтинга
//TODO: mappers                                   DONE
//TODO: PagingAndSortingRepos                     DONE
//TODO: исправить циклы зависимостей              DONE
//TODO: объединять репорты                        DONE
//TODO: indices db
//TODO: getCommentsAuthoredBy()                   DEPRECATED
//TODO: getReportsAuthoredBy()                    DEPRECATED
//TODO: changecost убрать                         DONE
//TODO: add remove links в контроллерах убрать    DONE
//TODO: required params                           DONE
//TODO: зацикленность — добавить "все"
//TODO: есть ли вокал — добавить "все"
//TODO: проверить, мапятся ли автоматически в треке списки жанров, настроений и комментов
//TODO: page and pageSize to ints                 DONE
//TODO: sync
//TODO: выход во время удаления
//TODO: во время апдейта пользователя обновлять данные во всех сессиях
//TODO: проверка принадлежности трека юзеру
//TODO: required в описании dto

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}