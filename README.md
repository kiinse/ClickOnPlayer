# ClickOnPlayer
Плагин, позволяющий показать информацию при нажатии на игрока

## Команды:

- /click reload

select Countries.name as Country, COUNT(*) as Amount from Countries inner join Cities on Cities.country_id = Countries.id inner join Companies on Companies.city_id = Cities.id where  Companies.labors >+ 1000 group by Countries.name;

## Права:

- ClickOnPlayer.reload - Возможность перезагрузки плагина
- ClickOnPlayer.see - Разрешает просматривать ники пользователей
- ClickOnPlayer.show - Разрешает показывать ник другим пользователям при нажатии
- ClickOnPlayer.admin.see - Права для администраторов, благодаря которым можно увидеть ники даже у тех, у кого нет прав ClickOnPlayer.show

