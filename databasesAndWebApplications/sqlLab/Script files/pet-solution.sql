
use petDatabase;

drop view if exists MyView;
Create VIEW MyView as select count(*) as 'deaths' from pet where death is not null; 
select * from MyView;


select * from pet where (birth between '1993-01-01' and '1998-12-31') and sex='f';

select pet.name, pet.birth, pet.species from pet order by pet.name DESC;

select sex as 'Gender', count(*) as 'Number' from pet group by sex;

